package bakery.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import bakery.model.ShippingMethod;
import bakery.model.TblOrderArrivalDetails;
import bakery.model.TblOrderStatus;
import bakery.model.TblUser;

public class OrderResponse {

	private Long orderId;
	private Date orderDate;
	private TblOrderStatus status;
	private BigDecimal total;
	private ShippingMethod shippingMethod;
	private List<OrderItemResponse> orderItemsItems;
	private TblOrderArrivalDetails arrivalDetails;
	private TblUser user;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public TblOrderStatus getStatus() {
		return status;
	}
	
	
	public ShippingMethod getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(ShippingMethod shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	public void setStatus(TblOrderStatus status) {
		this.status = status;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public List<OrderItemResponse> getOrderItemsItems() {
		return orderItemsItems;
	}
	public void setOrderItemsItems(List<OrderItemResponse> orderItemsItems) {
		this.orderItemsItems = orderItemsItems;
	}
	public TblUser getUser() {
		return user;
	}
	public void setUser(TblUser user) {
		this.user = user;
	}
	public TblOrderArrivalDetails getArrivalDetails() {
		return arrivalDetails;
	}
	public void setArrivalDetails(TblOrderArrivalDetails arrivalDetails) {
		this.arrivalDetails = arrivalDetails;
	}
	
	
}
