package restaurant.files;

import java.util.ArrayList;
import java.util.List;

import restaurant.menu.MenuItem;

public class PaymentData {

	private List<MenuItem> itemList;
	private float totalPayment;
	
	public PaymentData() {
		itemList = new ArrayList<MenuItem>();
		totalPayment = 0;
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
	
}
