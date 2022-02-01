package restaurant.files.classes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerFileReader {

	private File file;
	private List<String> fileLineList = new ArrayList();
	private List<String[]> fileLineListArray = new ArrayList();
	
	
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
