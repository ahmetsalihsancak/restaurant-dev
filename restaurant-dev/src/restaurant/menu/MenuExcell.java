package restaurant.menu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MenuExcell {
	
	private List<MenuItem> icecekList;
	
	public MenuExcell(File menuFile) {
		icecekList = new ArrayList<>();
		fillMenu(menuFile);
	}
	
	public List<MenuItem> getMenuList() {
		return icecekList;
	}
	
	public void fillMenu(File file) {
		try {
			icecekList.clear();
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
	
	public void fillTableModel(DefaultTableModel tableModel) {
		try {
			for (int i = tableModel.getRowCount()-1; i >= 0; i--) {
				tableModel.removeRow(i);
			}
			for (int i = 0; i < this.getMenuList().size(); i++) {
				String name = this.getMenuList().get(i).getName();
				float price = this.getMenuList().get(i).getPrice();
				tableModel.addRow(new Object[]{name,price});
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
	}
}
