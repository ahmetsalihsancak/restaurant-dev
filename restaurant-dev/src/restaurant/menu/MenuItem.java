package restaurant.menu;

public class MenuItem {

	private String name;
	private float price;
	private int count;
	private float discount;
	private paymentType_e paymentType;
	
	public enum paymentType_e {
		NAKIT,
		KK
	}
	
	public MenuItem(String name, float price) {
		this.name = name;
		this.price = price;
		count = 0;
		discount = 0;
		paymentType = paymentType_e.NAKIT;
	}
	
	public void setPaymentType(paymentType_e type) {
		paymentType = type;
	}

	public String getName() {
		return name;
	}
	
	public float getPrice() {
		return price;
	}

	public void setCount(int i) {
		this.count = i;
	}
	
	public int getCount() {
		return count;
	}
	
	public paymentType_e getPaymentType() {
		return paymentType;
	}
}
