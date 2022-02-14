package restaurant.customers;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import restaurant.files.classes.CustomerFile;
import restaurant.main.MainWindow;
import restaurant.menu.MenuItem;
import restaurant.files.PaymentData.paymentType_e;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;
import javax.swing.Icon;

public class CustomerPanel extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableModelPayment;
	private JTable table;
	private JTextField textFieldCount;
	private static CustomerFile customerFile;
	private static JLabel lbl1;
	private String imageFileName;
	private JTextField textFieldCount_payment;
	private JTable table_payment;
	private JLabel lblPrice_payment;
	private JTable table_paymentType;
	private JLabel lblPrice_paymentMoney;
	
	/**
	 * Create the frame.
	 */
	public CustomerPanel(Customer customer, List<MenuItem> menuList) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 570, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		try {
			imageFileName = MainWindow.getImageFileName();
			setIconImage(ImageIO.read(new File(imageFileName)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
		
		setTitle(customer.getName());
		customer.updateLabel(customer.getName());
		customerFile = MainWindow.getCustomerFile();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 534, 324);
		contentPane.add(tabbedPane);
		
		/* TAB 1: CUSTOMER INFO */
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("MASA B\u0130LG\u0130S\u0130", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 244, 184);
		panel.add(scrollPane);

		
		String[] header = {"\u00DCr\u00FCn", "Fiyat", "Adet"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(header);
		tableModelPayment = new DefaultTableModel();
		tableModelPayment.setColumnIdentifiers(header);
		
		fillTableModel(tableModel, customer);
		table = new JTable();
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		
		lbl1 = new JLabel(customer.getName());
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbl1.setBounds(10, 11, 384, 40);
		panel.add(lbl1);
		
		JLabel lblPrice = new JLabel("Toplam \u00DCcret: ");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrice.setBounds(10, 257, 244, 26);
		panel.add(lblPrice);
		
		updatePrice(lblPrice, customer.getTotalPrice());
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(270, 96, 145, 22);
		panel.add(comboBox);
		fillMenuList(comboBox, menuList);
		
		JButton btnAdd = new JButton("Ekle");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					addButtonAction(comboBox, customer, lblPrice, lblPrice_payment, menuList);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,e2,"Hata",0);
				}
			}
		});
		btnAdd.setBounds(270, 62, 66, 23);
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("Sil");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					removeButtonAction(comboBox, customer, lblPrice, lblPrice_payment);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,e2,"Hata",0);
				}
			}
		});
		btnRemove.setBounds(349, 62, 66, 23);
		panel.add(btnRemove);
		
		textFieldCount = new JTextField();
		textFieldCount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		textFieldCount.setText("1");
		textFieldCount.setBounds(425, 96, 99, 22);
		panel.add(textFieldCount);
		textFieldCount.setColumns(10);
		
		JButton btnRemoveAll = new JButton("S\u0131f\u0131rla");
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButtonAction(customer, lblPrice, lblPrice);
			}
		});
		btnRemoveAll.setBounds(425, 261, 99, 23);
		panel.add(btnRemoveAll);
		
		JButton btnChangeCustomerName = new JButton("\u0130sim De\u011Fi\u015Ftir");
		btnChangeCustomerName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EditCustomerPanelFrame edit = new EditCustomerPanelFrame(customer);
					edit.setVisible(true);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,e2,"Hata",0);
				}
			}
		});
		btnChangeCustomerName.setBounds(404, 11, 120, 40);
		panel.add(btnChangeCustomerName);
		
		JButton btnIncrease = new JButton("+");
		btnIncrease.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIncrease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int count = Integer.parseInt(textFieldCount.getText());
					count++;
					textFieldCount.setText(String.valueOf(count));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Geçerli olmayan bir ürün siliniyor.","Hata",0);
				}
			}
		});
		btnIncrease.setBounds(425, 62, 45, 23);
		panel.add(btnIncrease);
		
		JButton btnDecrease = new JButton("-");
		btnDecrease.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnDecrease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int count = Integer.parseInt(textFieldCount.getText());
					if(count > 1) count--;
					textFieldCount.setText(String.valueOf(count));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,e2,"Hata",0);
				}
			}
		});
		btnDecrease.setBounds(479, 62, 45, 23);
		panel.add(btnDecrease);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(270, 129, 145, 154);
		panel.add(panel_1);
		panel_1.add(new JLabel(MainWindow.getImageIcon()));
		
		JComboBox<paymentType_e> comboBoxPaymentType = new JComboBox();
		comboBoxPaymentType.setModel(new DefaultComboBoxModel(paymentType_e.values()));
		comboBoxPaymentType.setBounds(425, 129, 99, 22);
		panel.add(comboBoxPaymentType);
		
		/* TAB 2 : PAYMENT */
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\u00D6DEME B\u0130LG\u0130S\u0130", null, panel_2, null);
		panel_2.setLayout(null);
				
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 62, 244, 184);
		panel_2.add(scrollPane_2);
				
		table_payment = new JTable();
		table_payment.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table_payment.setEnabled(false);
		table_payment.setModel(tableModel);
		scrollPane_2.setViewportView(table_payment);
		
		JLabel lbl1_payment = new JLabel(customer.getName());
		lbl1_payment.setBounds(10, 11, 244, 40);
		lbl1_payment.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_2.add(lbl1_payment);
		
		lblPrice_payment = new JLabel("Toplam \u00DCcret: 0.0 TL");
		lblPrice_payment.setBounds(10, 257, 244, 26);
		lblPrice_payment.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_payment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_2.add(lblPrice_payment);
		
		updatePrice(lblPrice_payment, customer.getTotalPrice());
				
		JComboBox<String> comboBox_item_payment = new JComboBox<String>();
		comboBox_item_payment.setBounds(264, 45, 145, 22);
		panel_2.add(comboBox_item_payment);
		fillMenuList(comboBox_item_payment, menuList);
				
		JButton btnAdd_payment = new JButton("Ekle");
		btnAdd_payment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addButtonAction_Payment(comboBox_item_payment, customer, lblPrice_paymentMoney, textFieldCount);
			}
		});
		btnAdd_payment.setBounds(264, 11, 66, 23);
		panel_2.add(btnAdd_payment);
				
		JButton btnRemove_payment = new JButton("Sil");
		btnRemove_payment.setBounds(343, 11, 66, 23);
		panel_2.add(btnRemove_payment);
				
		textFieldCount_payment = new JTextField();
		textFieldCount_payment.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		textFieldCount_payment.setBounds(419, 45, 100, 22);
		textFieldCount_payment.setText("1");
		textFieldCount_payment.setColumns(10);
		panel_2.add(textFieldCount_payment);
			
		JButton btnIncrease_payment = new JButton("+");
		btnIncrease_payment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int count = Integer.parseInt(textFieldCount_payment.getText());
					count++;
					textFieldCount_payment.setText(String.valueOf(count));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Geçerli olmayan bir ürün siliniyor.","Hata",0);
				}
			}
		});
		btnIncrease_payment.setBounds(419, 11, 45, 23);
		btnIncrease_payment.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(btnIncrease_payment);
				
		JButton btnDecrease_payment = new JButton("-");
		btnDecrease_payment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int count = Integer.parseInt(textFieldCount_payment.getText());
					if(count > 1) count--;
					textFieldCount_payment.setText(String.valueOf(count));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,e2,"Hata",0);
				}
			}
		});
		btnDecrease_payment.setBounds(474, 11, 45, 23);
		btnDecrease_payment.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_2.add(btnDecrease_payment);
		
		JComboBox<paymentType_e> comboBoxPaymentType_payment = new JComboBox<paymentType_e>();
		comboBoxPaymentType_payment.setModel(new DefaultComboBoxModel(paymentType_e.values()));
		comboBoxPaymentType_payment.setBounds(264, 78, 145, 22);
		panel_2.add(comboBoxPaymentType_payment);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(264, 111, 255, 135);
		panel_2.add(scrollPane_1);
		
		table_paymentType = new JTable();
		table_paymentType.setEnabled(false);
		scrollPane_1.setViewportView(table_paymentType);
		table_paymentType.setModel(tableModelPayment);
		
		JButton btnGetPayment = new JButton("\u00D6deme Al");
		btnGetPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(MainWindow.getMoneyFile().file.getAbsolutePath());
				customer.getPayment().setPaymentType((paymentType_e) comboBoxPaymentType_payment.getSelectedItem());
				MainWindow.getMoneyFile().writeToMoneyFile(customer.getPayment());
			}
		});
		btnGetPayment.setBounds(419, 78, 99, 23);
		panel_2.add(btnGetPayment);
		
		lblPrice_paymentMoney = new JLabel("\u00D6denecek \u00DCcret: 0.0 TL");
		lblPrice_paymentMoney.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice_paymentMoney.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrice_paymentMoney.setBounds(264, 257, 255, 26);
		panel_2.add(lblPrice_paymentMoney);
		
	}
	
	private void fillTableModel(DefaultTableModel tableModel, Customer customer) {
		for (int i = tableModel.getRowCount()-1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (int i = 0; i < customer.getItemList().size(); i++) {
			String name = customer.getItemList().get(i).getName();
			float price = customer.getItemList().get(i).getPrice();
			int count = customer.getItemList().get(i).getCount();
			if(count > 0)tableModel.addRow(new Object[]{name, price, count});
		}
	}
	
	private void fillTableModelPayment(DefaultTableModel tableModel, Customer customer) {
		for (int i = tableModel.getRowCount()-1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (int i = 0; i < customer.getPayment().getItemList().size(); i++) {
			String name = customer.getPayment().getItemList().get(i).getName();
			float price = customer.getPayment().getItemList().get(i).getPrice();
			int count = customer.getPayment().getItemList().get(i).getCount();
			//System.out.println(name + "  " + price + "  " + count);
			if(count > 0)tableModel.addRow(new Object[]{name, price, count});
		}
	}
	
	private void fillMenuList(JComboBox<String> comboBox, List<MenuItem> menuList) {
		for (MenuItem menuItem : menuList) {
			comboBox.addItem(menuItem.getName());
		}
	}
	
	private void updatePrice(JLabel lblPrice, float price) {
		lblPrice.setText("Toplam \u00DCcret: " + price +" TL");
	}
	
	private void updatePaymentPrice(JLabel lblPrice, float price) {
		lblPrice.setText("\u00D6denecek \u00DCcret: " + price +" TL");
	}
	
	private void resetButtonAction(Customer customer, JLabel lblPrice, JLabel lblPrice_payment) {
		try {
			customer.updateName("Masa " + customer.getNo());
			customer.updateLabel(customer.getName());
			customer.getItemList().clear();
			setTitle(customer.getName());
			lbl1.setText(customer.getName());
			fillTableModel(tableModel, customer);
			updatePrice(lblPrice, customer.getTotalPrice());
			updatePrice(lblPrice_payment, customer.getTotalPrice());
			customer.getPayment().getItemList().clear();
			setCustomerText(customer);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null,e2,"Hata",0);
		}
	}
	
	private void addButtonAction(JComboBox<String> comboBox, Customer customer, JLabel lblPrice, JLabel lblPrice_payment, List<MenuItem> menuList) {
		boolean itemFounded = false;
		String itemName = comboBox.getSelectedItem().toString();
		for (MenuItem menuItem : menuList) {
			if (menuItem.getName().equalsIgnoreCase(itemName)) {
				for (MenuItem customerItem : customer.getItemList()) {
					if (customerItem.getName().equalsIgnoreCase(itemName)) {
						itemFounded = true;
						int oldCount = customerItem.getCount();
						customerItem.setCount(oldCount + Integer.parseInt(textFieldCount.getText()));
						break;
					} else {
						itemFounded = false;
					}
				}
				if (!itemFounded) {
					MenuItem newItem = new MenuItem(itemName,menuItem.getPrice());
					newItem.setCount(Integer.parseInt(textFieldCount.getText()));
					customer.addItem(newItem);
				}
				fillTableModel(tableModel, customer);
				updatePrice(lblPrice, customer.getTotalPrice());
				updatePrice(lblPrice_payment, customer.getTotalPrice());
			}
		}
		setCustomerText(customer);
	}
	
	private void addButtonAction_Payment(JComboBox<String> comboBox, Customer customer, JLabel lblPrice, JTextField textFieldCount) {
		boolean itemFounded = false;
		String itemName = comboBox.getSelectedItem().toString();
		for (MenuItem customerItem : customer.getItemList()) {
			if (customerItem.getName().equalsIgnoreCase(itemName)) {
				for (MenuItem paymentItem : customer.getPayment().getItemList()) {
					if (paymentItem.getName().equalsIgnoreCase(itemName)) {
						itemFounded = true;
						int oldCount = paymentItem.getCount();
						paymentItem.setCount(oldCount + Integer.parseInt(textFieldCount.getText()));
						break;
					}
					else {
						itemFounded = false;
					}
				}
				if (!itemFounded) {
					MenuItem newItem = new MenuItem(itemName,customerItem.getPrice());
					newItem.setCount(Integer.parseInt(textFieldCount.getText()));
					customer.getPayment().getItemList().add(newItem);
				}
				fillTableModelPayment(tableModelPayment, customer);
				updatePaymentPrice(lblPrice, customer.getPayment().getTotalPayment());
			}
		}
	}
	
	private void removeButtonAction(JComboBox<String> comboBox, Customer customer, JLabel lblPrice, JLabel lblPrice_payment) {
		String itemName = comboBox.getSelectedItem().toString();
		boolean itemFounded = false;
		for (MenuItem customerItem : customer.getItemList()) {
			if (customerItem.getName().equalsIgnoreCase(itemName)) {
				int oldCount = customerItem.getCount();
				if (oldCount == 0) {
					JOptionPane.showMessageDialog(null,"Geçerli olmayan bir ürün siliniyor.","Hata",0);
					itemFounded = true;
					break;
				}
				int newCount = oldCount - Integer.parseInt(textFieldCount.getText());
				if (newCount >= 0) {
					customerItem.setCount(oldCount - Integer.parseInt(textFieldCount.getText()));
					itemFounded = true;
				} else {
					if(customerItem.getCount() != 0)JOptionPane.showMessageDialog(null,"Ürün sayýsý 0'dan küçük olamaz.","Hata",0);
				}
				break;
			}
		}
		if (!itemFounded) {
			JOptionPane.showMessageDialog(null,"Geçerli olmayan bir ürün siliniyor.","Hata",0);
		}
		fillTableModel(tableModel, customer);
		updatePrice(lblPrice, customer.getTotalPrice());
		updatePrice(lblPrice_payment, customer.getTotalPrice());
		setCustomerText(customer);
	}
	
	public static void setCustomerText(Customer customer) {
		String first = customer.getNo() + "," + customer.getName();
		String items = "";
		for (int i = 0; i < customer.getItemList().size(); i++) {
			String itemName = customer.getItemList().get(i).getName();
			int itemCount = customer.getItemList().get(i).getCount();
			items = items + "," +itemName + "," + itemCount;
		}
		String text = first + items;
		customerFile.writeCustomerToCustomerFile(text, customer.getNo());
	}
	
	public static void setLabel(String text) {
		lbl1.setText(text);
	}
}
