package restaurant.menu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Menu {
	
	private List<MenuItem> icecekList;
	
	public Menu(File menuFile) {
		icecekList = new ArrayList<>();
		fillMenu(menuFile);
	}
	
	public List<MenuItem> getMenuList() {
		return icecekList;
	}
	
	private void fillMenu(File file) {
		try {
			int counter = 0;
			String name = null;
			float price = 0;
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String scannerN = scanner.next();
				if (counter == 0) {
					name = scannerN;
					counter++;
				} else {
					price = Float.parseFloat(scannerN);
					icecekList.add(new MenuItem(name, price));
					counter = 0;
				}
			}
			scanner.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
	}
}
