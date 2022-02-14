package restaurant.files;

import java.util.ArrayList;
import java.util.List;

import restaurant.menu.MenuItem;

public class PaymentData {

	private List<MenuItem> itemList;
	private paymentType_e paymentType;
	private float totalPayment;

	public enum paymentType_e {
		NAKIT,
		KART
	}
	
	public PaymentData() {
		itemList = new ArrayList<MenuItem>();
		totalPayment = 0;
		paymentType = paymentType_e.NAKIT;
	}
	
	public void setPaymentType(paymentType_e pType) {
		paymentType = pType;
	}
	
	public void addItem(MenuItem item) {
		itemList.add(item);
	}
	
	public List<MenuItem> getItemList(){
		return itemList;
	}
	
	public float getTotalPayment() {
		totalPayment = 0;
		for (MenuItem menuItem : itemList) {
			totalPayment = totalPayment + (menuItem.getPrice() * menuItem.getCount());
		}
		return totalPayment;
	}
	
	public paymentType_e getPaymentType() {
		return paymentType;
	}
	
}
