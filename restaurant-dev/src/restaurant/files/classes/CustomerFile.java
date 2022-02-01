package restaurant.files.classes;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerFile {

	private File file;
	private List<String> fileLineList = new ArrayList();
	private List<String[]> fileLineListArray = new ArrayList();
	
	public CustomerFile(String fileName) {
		createFile(fileName);
	}
	
	private void createFile(String name) {

		file = new File(name);
		try {
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
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public File getFile() {
		return file;
	}
	
	public void readFileScannerLine(File file) {
		try {
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
			// TODO: handle exception
		}
	}
	
	public List<String> getFileLines(){
		return fileLineList;
	}
	
	public List<String[]> getFileLinesSplitted(){
		return fileLineListArray;
	}
	
}
