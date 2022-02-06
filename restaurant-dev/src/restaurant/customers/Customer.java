package restaurant.customers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import restaurant.files.PaymentData;
import restaurant.menu.MenuItem;

public class Customer {

	private int no;
	private String name;
	private List<MenuItem> itemList;
	private JLabel label;
	private float totalPrice;
	private PaymentData payment;
	
	public Customer(int no, String name) {
		this.no = no;
		this.name = name;
		createCustomer();
	}
	
	private void createCustomer() {
		itemList = new ArrayList<MenuItem>();
		totalPrice = 0;
		label = null;
		payment = new PaymentData();
	}
	
	private void updatePrice() {
		totalPrice = 0;
		for (MenuItem item : itemList) {
			totalPrice = totalPrice + (item.getPrice() * item.getCount());
		}
	}
	
	public void updateName(String name) {
		this.name = name;
	}
	
	public void addItem(MenuItem item) {
		itemList.add(item);
	}
	
	public void addLabel(JLabel label) {
		this.label = label;
		this.label.setText(name);
	}
	
	public void updateLabel(String text) {
		label.setText(text);
	}
	
	public PaymentData getPayment() {
		return payment;
	}
	
	public String getName() {
		return name;
	}
	
	public float getTotalPrice() {
		updatePrice();
		return totalPrice;
	}
	
	public List<MenuItem> getItemList(){
		return itemList;
	}
	
	public int getNo() {
		return no;
	}
}
