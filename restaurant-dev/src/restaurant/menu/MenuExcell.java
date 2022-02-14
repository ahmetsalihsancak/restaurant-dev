package restaurant.menu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class MenuExcell {
	
	private List<MenuItem> icecekList;
	
	public MenuExcell(File menuFile) {
		icecekList = new ArrayList<>();
		fillMenu(menuFile);
	}
	
	public List<MenuItem> getMenuList() {
		return icecekList;
	}
	
	private void fillMenu(File file) {
		try {
			String name = null;
			float price = 0;
			Scanner scanner = new Scanner(file);
			String s[];
			while (scanner.hasNextLine()) {
				String scanned = scanner.nextLine();
				s = scanned.split("\t");
				name = s[0];
				price = Float.parseFloat(s[1]);
				icecekList.add(new MenuItem(name, price));
			}
			scanner.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
	}
}
