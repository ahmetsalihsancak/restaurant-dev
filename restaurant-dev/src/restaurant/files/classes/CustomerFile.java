package restaurant.files.classes;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CustomerFile {

	private File file;
	private List<String> fileLineList = new ArrayList<String>();
	private List<String[]> fileLineListArray = new ArrayList<String[]>();
	
	public CustomerFile(String fileName) {
		createFile(fileName);
	}
	
	private void createFile(String name) {
		try {
			file = new File(name);
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("dosya oluþtu	" + file.getName());
				FileWriter writer = new FileWriter(name);
				writer.write("1,Masa 1\n"
						+ "2,Masa 2\n"
						+ "3,Masa 3\n"
						+ "4,Masa 4\n"
						+ "5,Masa 5\n"
						+ "6,Masa 6\n"
						+ "7,Masa 7\n"
						+ "8,Masa 8\n"
						+ "9,Masa 9\n"
						+ "10,Masa 10\n"
						+ "11,Masa 11\n"
						+ "12,Masa 12");
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
	
	public void writeCustomerToCustomerFile(String text, int no) {
		try {
			fileLineList.clear();
			fileLineListArray.clear();
			String[] s;
			int counter = 0;
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				counter++;
				if (counter == no) {
					String scanned = scanner.nextLine();
					scanned = text;
					fileLineList.add(scanned);
					s = scanned.split(",");
					fileLineListArray.add(s);
				} else {
					String scanned = scanner.nextLine();
					fileLineList.add(scanned);
					s = scanned.split(",");
					fileLineListArray.add(s);
				}
			}
			scanner.close();
			file.delete();
			file.createNewFile();
			FileWriter writer = new FileWriter(file.getName());
			for (String string : fileLineList) {
				writer.write(string + "\n");
			}
			writer.close();
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
