package restaurant.menu;

public class MenuItem {

	private String name;
	private float price;
	private int count;
	private float discount;
	
	public MenuItem(String name, float price) {
		this.name = name;
		this.price = price;
		count = 0;
		discount = 0;
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
}
