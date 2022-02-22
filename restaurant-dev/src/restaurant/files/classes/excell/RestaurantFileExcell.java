package restaurant.files.classes.excell;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class RestaurantFileExcell {

	private File file;
	private static List<String> fileLineList = new ArrayList<String>();
	private static List<String[]> fileLineListArray = new ArrayList<String[]>();
	
	public RestaurantFileExcell(String fileName) {
		createFile(fileName);
	}
	
	private void createFile(String name) {
		try {
			file = new File(name);
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("dosya oluþtu	" + file.getName());
			} else {
				System.out.println("dosya var	" + file.getName());
			}
			System.out.println(file.getAbsolutePath());
			readFileScannerLine(file);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void addNewItem(String text) {
		String s[];
		s = text.split("\t");
		fileLineList.add(text);
		fileLineListArray.add(s);
		updateFile();
	}
	
	private void updateFile() {
		try {
			file.delete();
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			for (String w : fileLineList) {
				writer.write(w + "\n");
			}
			writer.close();
			readFileScannerLine(file);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void updateFile_Price() {
		try {
			file.delete();
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			for (String[] ws : fileLineListArray) {
				writer.write(ws[0] + "\t" + ws[1] + "\n");
			}
			writer.close();
			readFileScannerLine(file);
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
	
	public List<String> getFileLines(){
		return fileLineList;
	}
	
	public List<String[]> getFileLinesSplitted(){
		return fileLineListArray;
	}
}
