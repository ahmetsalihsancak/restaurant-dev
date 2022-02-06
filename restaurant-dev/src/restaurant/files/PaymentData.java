package restaurant.files;

import java.util.ArrayList;
import java.util.List;

import restaurant.menu.MenuItem;

public class PaymentData {

	List<MenuItem> itemList;
	
	public PaymentData() {
		itemList = new ArrayList<MenuItem>();
	}
	
	public void addItem(MenuItem item) {
		itemList.add(item);
	}
	
	public List<MenuItem> getItemList(){
		return itemList;
	}
	
}
