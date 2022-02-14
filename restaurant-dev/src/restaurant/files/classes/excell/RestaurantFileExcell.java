package restaurant.files.classes.excell;

import java.io.File;

public class RestaurantFileExcell {

	private File file;
	
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
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public File getFile() {
		return file;
	}
}
