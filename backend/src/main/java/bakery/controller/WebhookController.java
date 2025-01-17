package bakery.controller;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;

import bakery.model.PaymentMethod;
import bakery.model.TblOrder;
import bakery.model.TblPayment;
import bakery.repository.OrderRepository;
import bakery.repository.OrderStatusRepository;
import bakery.repository.PaymentRepository;



import com.stripe.net.ApiResource;

@RestController
public class WebhookController {
	@Value("${stripe.wh_key}")
    private String webhookKey;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @PostMapping("webhook")
    public ResponseEntity<String> handleWebhookEvent(@RequestBody String payload,
                                                     @RequestHeader("Stripe-Signature") String sigHeader) {
        // Verify the Stripe signature
        try {
            Event event = Webhook.constructEvent(payload, sigHeader, webhookKey);
            
            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = null;
            if (dataObjectDeserializer.getObject().isPresent()) {
                stripeObject = dataObjectDeserializer.getObject().get();
            } else {
                // Deserialization failed, probably due to an API version mismatch.
                // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
                // instructions on how to handle this case, or return an error here.
            }
            // Process the event based on its type
            switch (event.getType()) {
                case "payment_intent.succeeded":
                	PaymentIntent paymentIntent = (PaymentIntent)stripeObject;

                    // Retrieve the metadata associated with the PaymentIntent
                    String orderId = paymentIntent.getMetadata().get("orderId");
                    TblOrder order = orderRepository.getReferenceById(Long.parseLong(orderId));
                    TblPayment payment = new TblPayment();
                    payment.setPaymentDate(Date.from(Instant.now()));
                    payment.setPaymentMethod(PaymentMethod.CARD);
                    payment.setAmount(order.getTotal());
                    payment.setOrder(order);
                    paymentRepository.save(payment);

                    order.setStatus(orderStatusRepository.getReferenceById(4L));
                    orderRepository.save(order);
                    break;
                case "payment_intent.failed":
                    // Handle failed payment intent event
                    break;
                // Handle other event types as needed
            }
            return ResponseEntity.ok().build();
        } catch (SignatureVerificationException e) {
            // Invalid signature, ignore the event or handle the error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
