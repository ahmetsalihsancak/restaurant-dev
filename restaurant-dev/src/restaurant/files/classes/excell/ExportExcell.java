package restaurant.files.classes.excell;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ExportExcell {

	private DefaultTableModel tableModel;
	
	public ExportExcell(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}
	
	public void export(String fileName) {
		try {
			FileWriter fileWriter = new FileWriter(new File(fileName));
			
			fileWriter.write("tab1" + "\t");
			fileWriter.write("tab2" + "\t");
			fileWriter.write("tab3" + "\t");
			fileWriter.write("\n");
			

			fileWriter.write("tab1A" + "\t");
			fileWriter.write("tab2A" + "\t");
			fileWriter.write("tab3A" + "\t");
			fileWriter.write("\n");
			

			fileWriter.write("tab1B" + "\t");
			fileWriter.write("tab2B" + "\t");
			fileWriter.write("tab3B" + "\t");
			fileWriter.write("\n");
			
			fileWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void read() {
		try {
			String[] s;
			Scanner scanner = new Scanner(new File("ahmet.xls"));
			while(scanner.hasNextLine()) {
				String scanned = scanner.nextLine();
				System.out.println(scanned);
				s = scanned.split("\t");
				for (String string : s) {
					System.out.println(string);
				}
			}
			scanner.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
	}
	
}
