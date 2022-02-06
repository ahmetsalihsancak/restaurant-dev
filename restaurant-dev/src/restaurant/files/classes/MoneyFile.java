package restaurant.files.classes;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class MoneyFile {
	
	private File file;
	private List<String> fileLineList = new ArrayList<String>();
	private List<String[]> fileLineListArray = new ArrayList<String[]>();
	
	public MoneyFile(String fileName) {
		createFile(fileName);
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
