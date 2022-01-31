package restaurant.files.classes;

import java.io.File;
import java.util.Scanner;

public class CustomerFileReader {

	File file;
	
	public void readFileScannerLine(File file) {
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				String scanned = scanner.nextLine();
				switch (scanned) {
				case "#":
					break;
				default:
					System.out.println(scanner.next());
					break;
				}
			}
			scanner.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
