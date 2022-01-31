package restaurant.files.classes;

import java.io.File;

public class RestaurantFile {

	private File file;
	
	public RestaurantFile(String fileName) {
		createFile(fileName);
	}
	
	private void createFile(String name) {

		file = new File(name);
		try {
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
