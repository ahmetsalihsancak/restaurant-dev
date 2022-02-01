package restaurant.main;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import restaurant.files.classes.CustomerFileReader;
import restaurant.files.classes.RestaurantFile;
import restaurant.menu.Menu;
import restaurant.menu.MenuItem;
import restaurant.customers.Customer;
import restaurant.customers.CustomerPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private RestaurantFile file;
	private Menu menu;
	
	private List<Customer> customerList;
	
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
		customerList = new ArrayList();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 625);
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
		
		JButton btnCustomer1 = new JButton("1");
		btnCustomer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer1.setBounds(458, 98, 89, 23);
		frame.getContentPane().add(btnCustomer1);
		
		JButton btnCustomer2 = new JButton("2");
		btnCustomer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer2.setBounds(458, 132, 89, 23);
		frame.getContentPane().add(btnCustomer2);
		
		JButton btnCustomer3 = new JButton("3");
		btnCustomer3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer3.setBounds(458, 166, 89, 23);
		frame.getContentPane().add(btnCustomer3);
		
		JButton btnCustomer4 = new JButton("4");
		btnCustomer4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer4.setBounds(458, 200, 89, 23);
		frame.getContentPane().add(btnCustomer4);
		
		JButton btnCustomer5 = new JButton("5");
		btnCustomer5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer5.setBounds(458, 234, 89, 23);
		frame.getContentPane().add(btnCustomer5);
		
		JButton btnCustomer6 = new JButton("6");
		btnCustomer6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer6.setBounds(458, 268, 89, 23);
		frame.getContentPane().add(btnCustomer6);
		
		JButton btnCustomer7 = new JButton("7");
		btnCustomer7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer7.setBounds(458, 302, 89, 23);
		frame.getContentPane().add(btnCustomer7);
		
		JButton btnCustomer8 = new JButton("8");
		btnCustomer8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer8.setBounds(458, 336, 89, 23);
		frame.getContentPane().add(btnCustomer8);
		
		JButton btnCustomer9 = new JButton("9");
		btnCustomer9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer9.setBounds(458, 370, 89, 23);
		frame.getContentPane().add(btnCustomer9);
		
		JButton btnCustomer10 = new JButton("10");
		btnCustomer10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer10.setBounds(458, 404, 89, 23);
		frame.getContentPane().add(btnCustomer10);
		
		JButton btnCustomer11 = new JButton("11");
		btnCustomer11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer11.setBounds(458, 438, 89, 23);
		frame.getContentPane().add(btnCustomer11);
		
		JButton btnCustomer12 = new JButton("12");
		btnCustomer12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer12.setBounds(458, 472, 89, 23);
		frame.getContentPane().add(btnCustomer12);
		
		File file = new File("customer.txt");
		CustomerFileReader c = new CustomerFileReader();
		c.readFileScannerLine(file);
		fillCustomerList(customerList, c.getFileLinesSplitted());
	}
	
	private void fillTableModel(DefaultTableModel tableModel) {
		for (int i = 0; i < menu.getMenuList().size(); i++) {
			String name = menu.getMenuList().get(i).getName();
			float price = menu.getMenuList().get(i).getPrice();
			tableModel.addRow(new Object[]{name,price});
		}
	}
	
	private void fillCustomerList(List<Customer> customerList, List<String[]> fileLineListArray) {
		Customer c;
		for (String[] strings : fileLineListArray) {
			int no = Integer.parseInt(strings[0]);
			String name = strings[1];
			c = new Customer(no, name);
			for (int i = 2; i < strings.length; i=i+2) {
				String itemName = strings[i];
				for (MenuItem item : menu.getMenuList()) {
					if(item.getName().equalsIgnoreCase(itemName)) {
						MenuItem cItem = item;
						cItem.setCount(Integer.parseInt(strings[i+1]));
						c.addItem(cItem);
						break;
					}
				}
			}
			customerList.add(c);
		}
	}
	
	private void customerButtonAction(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		int customerNo = Integer.parseInt(btn.getText());
		CustomerPanel cPanel = new CustomerPanel(customerList.get(customerNo-1));
		cPanel.setVisible(true);
	}
}
