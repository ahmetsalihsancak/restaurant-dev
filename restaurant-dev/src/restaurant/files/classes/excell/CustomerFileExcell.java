package restaurant.files.classes.excell;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CustomerFileExcell {
	
	private File file;
	private List<String> fileLineList = new ArrayList<String>();
	private List<String[]> fileLineListArray = new ArrayList<String[]>();
	
	public CustomerFileExcell(String fileName) {
		createFile(fileName);
	}
	
	private void createFile(String name) {
		try {
			file = new File(name);
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("dosya oluþtu	" + file.getName());
				FileWriter writer = new FileWriter(name);
				writer.write("1\tMasa 1\n"
						+ "2\tMasa 2\n"
						+ "3\tMasa 3\n"
						+ "4\tMasa 4\n"
						+ "5\tMasa 5\n"
						+ "6\tMasa 6\n"
						+ "7\tMasa 7\n"
						+ "8\tMasa 8\n"
						+ "9\tMasa 9\n"
						+ "10\tMasa 10\n"
						+ "11\tMasa 11\n"
						+ "12\tMasa 12");
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
				s = scanned.split("\t");
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
					s = scanned.split("\t");
					fileLineListArray.add(s);
				} else {
					String scanned = scanner.nextLine();
					fileLineList.add(scanned);
					s = scanned.split("\t");
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
