package bakery.request;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import bakery.model.TblOrderItem;
import bakery.model.TblOrderStatus;
import bakery.model.TblUser;

public class OrderCreateRequest {

	private Date orderDate = Date.from(Instant.now());
	private TblOrderStatus status;
	private BigDecimal total;
	private List<TblOrderItem> orderItems;
	private TblUser user;
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public TblOrderStatus getStatus() {
		return status;
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
	public List<TblOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TblOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TblUser getUser() {
		return user;
	}
	public void setUser(TblUser user) {
		this.user = user;
	}
	
	
}