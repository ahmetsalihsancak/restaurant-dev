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
import restaurant.menu.MenuItem.paymentType_e;
import javax.swing.DefaultComboBoxModel;

public class CustomerPanel extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField textFieldCount;
	private static CustomerFile customerFile;
	private static JLabel lbl1;
	private String imageFileName;
	
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
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 534, 324);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 244, 221);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		String[] header = {"\u00DCr\u00FCn", "Fiyat", "Adet"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(header);
		fillTableModel(tableModel, customer);
		table.setModel(tableModel);
		
		lbl1 = new JLabel(customer.getName());
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbl1.setBounds(10, 11, 384, 40);
		panel.add(lbl1);
		
		JLabel lblPrice = new JLabel("Toplam \u00DCcret: ");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrice.setBounds(10, 287, 244, 26);
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
					addButtonAction(comboBox, customer, lblPrice, menuList);
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
					removeButtonAction(comboBox, customer, lblPrice);
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
				try {
					customer.updateName("Masa " + customer.getNo());
					customer.updateLabel(customer.getName());
					customer.getItemList().clear();
					setTitle(customer.getName());
					lbl1.setText(customer.getName());
					fillTableModel(tableModel, customer);
					updatePrice(lblPrice, customer.getTotalPrice());
					setCustomerText(customer);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,e2,"Hata",0);
				}
			}
		});
		btnRemoveAll.setBounds(425, 291, 99, 23);
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

		JComboBox<String> comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(paymentType_e.values()));
		comboBox_1.setBounds(425, 129, 99, 22);
		panel.add(comboBox_1);
		
		JButton btnPayment = new JButton("\u00D6deme Al");
		btnPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentButtonAction(comboBox, comboBox_1, customer, lblPrice, menuList);
			}
		});
		btnPayment.setBounds(425, 260, 99, 23);
		panel.add(btnPayment);
		
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
	
	private void fillMenuList(JComboBox<String> comboBox, List<MenuItem> menuList) {
		for (MenuItem menuItem : menuList) {
			comboBox.addItem(menuItem.getName());
		}
	}
	
	private void updatePrice(JLabel lblPrice, float price) {
		lblPrice.setText("Toplam \u00DCcret: " + price +" TL");
	}
	
	private void paymentButtonAction(JComboBox<String> comboBox, JComboBox<paymentType_e> comboBox2, Customer customer, JLabel lblPrice, List<MenuItem> menuList) {
		boolean itemFounded = false;
		String itemName = comboBox.getSelectedItem().toString();
		for (MenuItem menuItem : menuList) {
			if (menuItem.getName().equalsIgnoreCase(itemName)) {
				for (MenuItem customerItem : customer.getItemList()) {
					if (customerItem.getName().equalsIgnoreCase(itemName)) {
						itemFounded = true;
						int oldCount = customerItem.getCount();
						int itemCount = Integer.parseInt(textFieldCount.getText());
						int newCount = oldCount - itemCount;
						removeButtonAction(comboBox, customer, lblPrice);
						if(oldCount > 0 && newCount > 0) {
							if(itemCount > 0) {
								boolean pItemFounded = false;
								for (MenuItem paymentItem : customer.getPayment().getItemList()) {
									if (customerItem.getName().equalsIgnoreCase(paymentItem.getName())) {
										
									} else {
										MenuItem newPaymentItem = customerItem;
										newPaymentItem.setCount(itemCount);
										newPaymentItem.setPaymentType((paymentType_e)comboBox2.getSelectedItem());
									}
								}
							}
							JOptionPane.showMessageDialog(null,textFieldCount.getText() + " adet " + itemName + " ürününün ödemesi baþarýlý.","Bilgi",1);
						}
						break;
					} else {
						itemFounded = false;
					}
				}
				if (!itemFounded) {
					JOptionPane.showMessageDialog(null,"Geçerli olmayan bir ürünün ödemesi alýnýyor.","Hata",0);
				}
			}
		}
	}
	
	private void addButtonAction(JComboBox<String> comboBox, Customer customer, JLabel lblPrice, List<MenuItem> menuList) {
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
			}
		}
		setCustomerText(customer);
	}
	
	private void removeButtonAction(JComboBox<String> comboBox, Customer customer, JLabel lblPrice) {
		String itemName = comboBox.getSelectedItem().toString();
		boolean showed = false;
		for (MenuItem customerItem : customer.getItemList()) {
			if (customerItem.getName().equalsIgnoreCase(itemName)) {
				int oldCount = customerItem.getCount();
				int newCount = oldCount - Integer.parseInt(textFieldCount.getText());
				if (newCount >= 0) {
					customerItem.setCount(oldCount - Integer.parseInt(textFieldCount.getText()));
				} else {
					if(customerItem.getCount() != 0)JOptionPane.showMessageDialog(null,"Ürün sayýsý 0'dan küçük olamaz.","Hata",0);
					else if(!showed) {
						JOptionPane.showMessageDialog(null,"Geçerli olmayan bir ürün siliniyor.","Hata",0);
						showed = true;
					}
				}
				break;
			}
			else if(!showed) {
				JOptionPane.showMessageDialog(null,"Geçerli olmayan bir ürün siliniyor.","Hata",0);
				showed = true;
			}
		}
		fillTableModel(tableModel, customer);
		updatePrice(lblPrice, customer.getTotalPrice());
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
