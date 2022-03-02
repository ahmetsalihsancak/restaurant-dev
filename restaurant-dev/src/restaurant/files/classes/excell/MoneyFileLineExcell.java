package restaurant.files.classes.excell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import restaurant.customers.Customer;
import restaurant.files.PaymentData;
import restaurant.files.PaymentData.paymentType_e;
import restaurant.main.MainWindow;
import restaurant.menu.MenuItem;

public class MoneyFileLineExcell {

	public static File file;
	public static File monthlyFile;
	private static List<String> fileLineList = new ArrayList<String>();
	private static List<String[]> fileLineListArray = new ArrayList<String[]>();
	private static String filePath;
	
	public MoneyFileLineExcell(String fileName) {
		createFile(fileName);
	}
	
	public enum months {
		OCAK,
		SUBAT,
		MART,
		NISAN,
		MAYIS,
		HAZIRAN,
		TEMMUZ,
		AGUSTOS,
		EYLUL,
		EKIM,
		KASIM,
		ARALIK
	}

	private void createFile(String name) {
		try {
			File moneyMKDIR = new File("money");
			if (!moneyMKDIR.exists()) moneyMKDIR.mkdir();
			filePath = "money/" + name;
			file = new File(filePath);
			Calendar c = MainWindow.getCalendar();
			System.out.println(c.getTime());
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			System.out.println(year + "  " + month + "  " + day);
			if (!file.exists()) {
				System.out.println(year + "  " + month + "  " + day+ "  aaaaaaaaaaaaaaaaa");
				file.createNewFile();
				FileWriter writer = new FileWriter(filePath);
				String text = day + "\t" + month + "\t" + year;
				for (MenuItem menuItem : MainWindow.getMenuExcell().getMenuList()) {
					text = text + "\t" + menuItem.getName() + "\t" + "0";
				}
				text = text + "\tNakit\t0\tKart\t0\tToplam\t0";
				writer.write(text + "\n");
				writer.close();
				readFileScannerLine(file);
				System.out.println("dosya oluþtu	" + file.getName());
			} else {
				readFileScannerLine(file);
				if (Integer.parseInt(fileLineListArray.get(fileLineListArray.size()-1)[0]) != day) {
					file.delete();
					file.createNewFile();
					String text = day + "\t" + month + "\t" + year;
					System.out.println(text+ "  bbbbbbbbbbbbbbb");
					for (MenuItem menuItem : MainWindow.getMenuExcell().getMenuList()) {
						text = text + "\t" + menuItem.getName() + "\t" + "0";
					}
					text = text + "\tNakit\t0\tKart\t0\tToplam\t0";
					String[] s;
					Scanner scanner = new Scanner(text);
					while(scanner.hasNextLine()) {
						String scanned = scanner.nextLine();
						fileLineList.add(scanned);
						s = scanned.split("\t");
						fileLineListArray.add(s);
					}
					scanner.close();
					FileWriter writer = new FileWriter(filePath);
					for (String string : fileLineList) {
						writer.write(string + "\n");
					}
					writer.close();
					System.out.println("dosya var	" + file.getName());
					readFileScannerLine(file);
				}
				System.out.println(file.getAbsolutePath());	
				}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
			
		}
	}
	
	public void writeToMoneyFile(PaymentData paymentData) {
		try {
			List<MenuItem> addedItemsList = paymentData.getItemList();
			paymentType_e pType = paymentData.getPaymentType();
			Calendar c = MainWindow.getCalendar();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int day = c.get(Calendar.DATE);
			
			fileLineList.clear();
			fileLineListArray.clear();
			
			String[] s;
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String scanned = scanner.nextLine();
				System.out.println("b: " + scanned);
				s = scanned.split("\t");
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
					switch (pType) {
					case NAKIT:
						s[s.length-5] = String.valueOf(Float.parseFloat(s[s.length-5]) + paymentData.getTotalPayment());
						break;
					case KART:
						s[s.length-3] = String.valueOf(Float.parseFloat(s[s.length-3]) + paymentData.getTotalPayment());
						break;
					}
					s[s.length-1] = String.valueOf(Float.parseFloat(s[s.length-1]) + paymentData.getTotalPayment());

					scanned = "";
					for (int i = 0; i < s.length; i++) {
						scanned = scanned + s[i] + "\t";
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

	public void updateFile(String text) {
		try {
			fileLineList.remove(fileLineList.size()-1);
			fileLineListArray.remove(fileLineListArray.size()-1);
			fileLineList.add(text);
			String[] s = text.split("\t");
			fileLineListArray.add(s);
			file.delete();
			file.createNewFile();
			FileWriter writer = new FileWriter(filePath);
			for (String string : fileLineList) {
				writer.write(string + "\n");
			}
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public File getFile() {
		return file;
	}
	
	public List<String> getFileScannerLineList(File file) {
		List<String> fileLineList1 = new ArrayList<String>();
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String scanned = scanner.nextLine();
				fileLineList1.add(scanned);
			}
			scanner.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
		return fileLineList1;
	}
	
	public List<String[]> getFileScannerLineListSplitted(File file) {
		List<String[]> fileLineList1 = new ArrayList<String[]>();
		try {
			Scanner scanner = new Scanner(file);
			String[] s;
			while(scanner.hasNextLine()) {
				String scanned = scanner.nextLine();
				s = scanned.split("\t");
				fileLineList1.add(s);
			}
			scanner.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
		return fileLineList1;
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
				s = scanned.split("\t");
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
