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

import restaurant.files.classes.CustomerFile;
import restaurant.files.classes.RestaurantFile;
import restaurant.menu.Menu;
import restaurant.menu.MenuItem;
import restaurant.customers.Customer;
import restaurant.customers.CustomerPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainWindow {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private RestaurantFile menuFile;
	private static CustomerFile customerFile;
	private Menu menu;
	
	private List<Customer> customerList;
	private List<JLabel> labelList;
	
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
		menuFile = new RestaurantFile("menu.txt");
		customerFile = new CustomerFile("customer.txt");
		menu = new Menu(menuFile.getFile());
		customerList = new ArrayList<Customer>();
		labelList = new ArrayList<>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("RETTO");
		frame.setBounds(100, 100, 630, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 200, 438, 393);
		frame.getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 416, 369);
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
		btnCustomer1.setBounds(475, 196, 60, 23);
		frame.getContentPane().add(btnCustomer1);
		
		JButton btnCustomer2 = new JButton("2");
		btnCustomer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer2.setBounds(475, 230, 60, 23);
		frame.getContentPane().add(btnCustomer2);
		
		JButton btnCustomer3 = new JButton("3");
		btnCustomer3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer3.setBounds(475, 264, 60, 23);
		frame.getContentPane().add(btnCustomer3);
		
		JButton btnCustomer4 = new JButton("4");
		btnCustomer4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer4.setBounds(475, 298, 60, 23);
		frame.getContentPane().add(btnCustomer4);
		
		JButton btnCustomer5 = new JButton("5");
		btnCustomer5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer5.setBounds(475, 332, 60, 23);
		frame.getContentPane().add(btnCustomer5);
		
		JButton btnCustomer6 = new JButton("6");
		btnCustomer6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer6.setBounds(475, 366, 60, 23);
		frame.getContentPane().add(btnCustomer6);
		
		JButton btnCustomer7 = new JButton("7");
		btnCustomer7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer7.setBounds(475, 400, 60, 23);
		frame.getContentPane().add(btnCustomer7);
		
		JButton btnCustomer8 = new JButton("8");
		btnCustomer8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer8.setBounds(475, 434, 60, 23);
		frame.getContentPane().add(btnCustomer8);
		
		JButton btnCustomer9 = new JButton("9");
		btnCustomer9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer9.setBounds(475, 468, 60, 23);
		frame.getContentPane().add(btnCustomer9);
		
		JButton btnCustomer10 = new JButton("10");
		btnCustomer10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer10.setBounds(475, 502, 60, 23);
		frame.getContentPane().add(btnCustomer10);
		
		JButton btnCustomer11 = new JButton("11");
		btnCustomer11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer11.setBounds(475, 536, 60, 23);
		frame.getContentPane().add(btnCustomer11);
		
		JButton btnCustomer12 = new JButton("12");
		btnCustomer12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButtonAction(e);
			}
		});
		btnCustomer12.setBounds(475, 570, 60, 23);
		frame.getContentPane().add(btnCustomer12);
		
		JLabel lbl1 = new JLabel("New label");
		lbl1.setBounds(545, 200, 75, 14);
		frame.getContentPane().add(lbl1);
		labelList.add(lbl1);
		
		JLabel lbl2 = new JLabel("New label");
		lbl2.setBounds(545, 234, 75, 14);
		frame.getContentPane().add(lbl2);
		labelList.add(lbl2);
		
		JLabel lbl3 = new JLabel("New label");
		lbl3.setBounds(545, 268, 75, 14);
		frame.getContentPane().add(lbl3);
		labelList.add(lbl3);
		
		JLabel lbl4 = new JLabel("New label");
		lbl4.setBounds(545, 302, 75, 14);
		frame.getContentPane().add(lbl4);
		labelList.add(lbl4);
		
		JLabel lbl5 = new JLabel("New label");
		lbl5.setBounds(545, 336, 75, 14);
		frame.getContentPane().add(lbl5);
		labelList.add(lbl5);
		
		JLabel lbl6 = new JLabel("New label");
		lbl6.setBounds(545, 370, 75, 14);
		frame.getContentPane().add(lbl6);
		labelList.add(lbl6);
		
		JLabel lbl7 = new JLabel("New label");
		lbl7.setBounds(545, 404, 75, 14);
		frame.getContentPane().add(lbl7);
		labelList.add(lbl7);
		
		JLabel lbl8 = new JLabel("New label");
		lbl8.setBounds(545, 438, 75, 14);
		frame.getContentPane().add(lbl8);
		labelList.add(lbl8);
		
		JLabel lbl9 = new JLabel("New label");
		lbl9.setBounds(545, 472, 75, 14);
		frame.getContentPane().add(lbl9);
		labelList.add(lbl9);
		
		JLabel lbl10 = new JLabel("New label");
		lbl10.setBounds(545, 506, 75, 14);
		frame.getContentPane().add(lbl10);
		labelList.add(lbl10);
		
		JLabel lbl11 = new JLabel("New label");
		lbl11.setBounds(545, 540, 75, 14);
		frame.getContentPane().add(lbl11);
		labelList.add(lbl11);
		
		JLabel lbl12 = new JLabel("New label");
		lbl12.setBounds(545, 574, 75, 14);
		frame.getContentPane().add(lbl12);
		labelList.add(lbl12);
		
		JLabel lblMenu = new JLabel("MENU");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMenu.setBounds(20, 166, 438, 23);
		frame.getContentPane().add(lblMenu);
		
		File file = new File("customer.txt");
		customerFile.readFileScannerLine(file);
		fillCustomerList(customerList, customerFile.getFileLinesSplitted());
	}
	
	public static CustomerFile getCustomerFile() {
		return customerFile;
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
			c.addLabel(labelList.get(no-1));
			for (int i = 2; i < strings.length; i=i+2) {
				String itemName = strings[i];
				for (MenuItem item : menu.getMenuList()) {
					if(item.getName().equalsIgnoreCase(itemName)) {
						MenuItem cItem = new MenuItem(itemName, item.getPrice());
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
		CustomerPanel cPanel = new CustomerPanel(customerList.get(customerNo-1), menu.getMenuList());
		cPanel.setVisible(true);
	}
}
