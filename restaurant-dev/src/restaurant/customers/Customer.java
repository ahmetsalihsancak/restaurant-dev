package restaurant.customers;

import java.util.ArrayList;
import java.util.List;

import restaurant.menu.MenuItem;

public class Customer {

	private int no;
	private String name;
	private List<MenuItem> itemList;
	private float totalPrice;
	
	public Customer(int no, String name) {
		this.no = no;
		this.name = name;
		createCustomer();
	}
	
	private void createCustomer() {
		itemList = new ArrayList<MenuItem>();
		totalPrice = 0;
	}
	
	public void addItem(MenuItem item) {
		itemList.add(item);
		totalPrice = totalPrice + (item.getPrice() * item.getCount());
	}
	
	public float getTotalPrice() {
		return totalPrice;
	}
	
	public List<MenuItem> getItemList(){
		return itemList;
	}
}
