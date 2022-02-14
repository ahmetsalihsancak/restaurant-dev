package restaurant.files;

import java.util.ArrayList;
import java.util.List;

import restaurant.menu.MenuItem;

public class MonthlyPaymentData {
	
	private List<MenuItem> itemList;
	private float cashTotal;
	private float debitTotal;
	private float total;
	
	public MonthlyPaymentData() {
		itemList = new ArrayList<MenuItem>();
		cashTotal = 0;
		debitTotal = 0;
		total = 0;
	}
	
	public void addItem(MenuItem item) {
		itemList.add(item);
	}
	
	public List<MenuItem> getItemList(){
		return itemList;
	}
	
	public void setCashTotal(float m) {
		cashTotal = cashTotal + m;
	}
	
	public void setDebitTotal(float m) {
		debitTotal = debitTotal + m;
	}
	
	public void setTotal(float m) {
		total = total + m;
	}
	
	public float getTotalPayment() {
		total = 0;
		for (MenuItem menuItem : itemList) {
			total = total + (menuItem.getPrice() * menuItem.getCount());
		}
		return total;
	}
}
