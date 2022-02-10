package restaurant.files.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import restaurant.main.MainWindow;
import restaurant.menu.Menu;
import restaurant.menu.MenuItem;

public class MoneyFile {
	
	public File file;
	private List<String> fileLineList = new ArrayList<String>();
	private List<String[]> fileLineListArray = new ArrayList<String[]>();
	private String filePath;
	
	public MoneyFile(String fileName) {
		createFile1(fileName);
	}
	
	private void createFile1(String name) {
		try {
			filePath = "money/" + name;
			file = new File(filePath);
			Calendar c = Calendar.getInstance();
			System.out.println(c.getTime());
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			System.out.println(year + "  " + month + "  " + day);
			if (!file.exists()) {
				file.createNewFile();
				FileWriter writer = new FileWriter(filePath);
				for (int i = 0; i < c.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
					String text = ((i+1) + "," + month + "," + year);
					for (MenuItem menuItem : MainWindow.getMenu().getMenuList()) {
						text = text + "," + menuItem.getName() + "," + "0";
					}
					writer.write(text + "\n");
				}
				writer.close();
				System.out.println("dosya oluþtu	" + file.getName());
			} else {
				System.out.println("dosya var	" + file.getName());
			}
			System.out.println(file.getAbsolutePath());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
	}
	
	public void writeToMoneyFile(List<MenuItem> addedItemsList) {
		try {
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			String text = day + "," + month + "," + year;
			
			fileLineList.clear();
			fileLineListArray.clear();
			
			String[] s;
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String scanned = scanner.nextLine();
				System.out.println("b: " + scanned);
				s = scanned.split(",");
				if (s[0].equalsIgnoreCase(String.valueOf(day))) {
					System.out.println("EQUAL");
					for (int i = 3; i < s.length; i = i + 2) {
						System.out.println("s name : " + s[i]);
						for (MenuItem addedItems : addedItemsList) {
							System.out.println("item name " + addedItems.getName());
							if (s[i].equalsIgnoreCase(addedItems.getName())) {
								System.out.println(addedItems.getName() + " sayý " + addedItems.getCount());
								int count = Integer.parseInt(s[i+1]) + addedItems.getCount();
								s[i+1] = String.valueOf(count);
								System.out.println(addedItems.getName() + " sayý " + addedItems.getCount() + " yeni " + count);
							}
						}
					}
					scanned = "";
					for (int i = 0; i < s.length; i++) {
						if (i == s.length-1) {
							scanned = scanned + s[i];
							break;
						}
						scanned = scanned + s[i] + ",";
					}
				}
				fileLineListArray.add(s);
				fileLineList.add(scanned);
			}
			scanner.close();

			FileWriter writer = new FileWriter(filePath);
			for (String string : fileLineList) {
				writer.write(string + "\n");
			}
			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createFile(String name) {
		try {
			String filePath = "money/" + name;
			file = new File(filePath);
			Calendar c = Calendar.getInstance();
			System.out.println(c.getTime());
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			System.out.println(year + "  " + month + "  " + c.get(Calendar.DATE));
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("dosya oluþtu	" + file.getName());
				FileWriter writer = new FileWriter(filePath);
				for (int i = 0; i < c.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
					writer.write((i+1) + "-" + month + "-" + year +",\n");
				}
				writer.close();
			} else {
				System.out.println("dosya var	" + file.getName());
			}
			System.out.println(file.getAbsolutePath());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
	}
	
	public File getFile() {
		return file;
	}

	public void readFileScannerLine(File file) {
		try {
			fileLineList.clear();
			fileLineListArray.clear();
			String[] s;
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String scanned = scanner.nextLine();
				fileLineList.add(scanned);
				s = scanned.split(",");
				fileLineListArray.add(s);
			}
			scanner.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
	}
	
	public List<String> getFileLines(){
		return fileLineList;
	}
	
	public List<String[]> getFileLinesSplitted(){
		return fileLineListArray;
	}
}
