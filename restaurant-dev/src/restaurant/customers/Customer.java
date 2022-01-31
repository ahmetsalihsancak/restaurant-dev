package restaurant.customers;

import java.util.ArrayList;
import java.util.List;

import restaurant.menu.MenuItem;

public class Customer {

	private int no;
	private String name;
	private List<MenuItem> itemList;
	private float totalPrice;
	
	public Customer(int no) {
		this.no = no;
		this.name = "Masa: " + no;
		createCustomer();
	}
	
	public Customer(int no, String name) {
		this.no = no;
		this.name = name;
		createCustomer();
	}
	
	private void createCustomer() {
		itemList = new ArrayList<MenuItem>();
		totalPrice = 0;
	}
}
