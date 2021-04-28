/* Olutoni Oluwole
 */

package shopifydatachallenge;

public class OrderRecord {
	private int orderID;

	private int shopID;
	
	private int userID;
	
	private int orderAmount;

	private int totalItems;
	
	private String PaymentMethod;
	
	/**
	 * The no parameter constructor does not set any values.
	 */
	public OrderRecord() {
		super();
	}
	
	/**
	 * Returns the OrderID field
	 * @return the OrderID field
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * Changes the orderID field with the value passed
	 * @param a reference to an int for the orderID field
	 */
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	/**
	 * Returns the ShopID field
	 * @return the ShopID field
	 */
	public int getShopID() {
		return shopID;
	}

	/**
	 * Changes the shopID field with the value passed
	 * @param a reference to an int for the shopID field
	 */
	public void setShopID(int shopID) {
		this.shopID = shopID;
	}

	/**
	 * Returns the userID field
	 * @return the userID field
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * Changes the userID field with the value passed
	 * @param a reference to an int for the userID field
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * Returns the orderAmount field
	 * @return the userID field
	 */
	public int getOrderAmount() {
		return orderAmount;
	}

	/**
	 * Changes the order amount field with the value passed
	 * @param a reference to an int for the order amount field
	 */
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * Returns the Total Items field
	 * @return the Total Items field
	 */
	public int getTotalItems() {
		return totalItems;
	}

	/**
	 * Changes the Total items field with the value passed
	 * @param a reference to an int for the total items field
	 */
	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}
	
	/**
	 * Returns the payment method field
	 * @return the payment method field
	 */
	public String getPaymentMethod() {
		return PaymentMethod;
	}

	/**
	 * Changes the payment method field with the value passed
	 * @param name a reference to a String for payment method field
	 */
	public void setPaymentMethod(String PaymentMethod) {
		this.PaymentMethod = PaymentMethod;
	}
	
	/**
	 * Provides a comma-separated-value String representation.
	 * @ return a comma-separated-value String representation.
	 */
	@Override
	public String toString() {
		String format = "%d,%d,%d,%d,%d,%s";
		return String.format(format,
				getOrderID(), getShopID(), getUserID(), getOrderAmount(),
				getTotalItems(), getPaymentMethod());
	}
}
