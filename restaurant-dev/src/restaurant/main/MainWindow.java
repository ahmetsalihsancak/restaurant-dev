package restaurant.main;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import restaurant.files.classes.CustomerFileReader;
import restaurant.files.classes.RestaurantFile;
import restaurant.menu.Menu;

public class MainWindow {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private RestaurantFile file;
	private Menu menu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		file = new RestaurantFile("menu.txt");
		menu = new Menu(file.getFile());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 98, 438, 434);
		frame.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 416, 410);
		panel.add(scrollPane_1);
		
		table = new JTable();
		String[] header = {"\u00DCr\u00FCn", "Fiyat"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(header);
		scrollPane_1.setViewportView(table);
		fillTableModel(tableModel);
		table.setModel(tableModel);
		File file = new File("customer.txt");
		CustomerFileReader c = new CustomerFileReader();
		c.readFileScannerLine(file);
	}
	
	private void fillTableModel(DefaultTableModel tableModel) {
		for (int i = 0; i < menu.getMenuList().size(); i++) {
			String name = menu.getMenuList().get(i).getName();
			float price = menu.getMenuList().get(i).getPrice();
			tableModel.addRow(new Object[]{name,price});
		}
	}
}
