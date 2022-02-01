package restaurant.customers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import restaurant.menu.Menu;
import restaurant.menu.MenuItem;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerPanel extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField textFieldCount;

	/**
	 * Create the frame.
	 */
	public CustomerPanel(Customer customer, List<MenuItem> menuList) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle(customer.getName());
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 544, 389);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 262, 199);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(table);
		String[] header = {"\u00DCr\u00FCn", "Fiyat", "Adet"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(header);
		fillTableModel(tableModel, customer);
		table.setModel(tableModel);
		
		JLabel lbl1 = new JLabel(customer.getName());
		lbl1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lbl1.setBounds(10, 11, 262, 40);
		panel.add(lbl1);
		
		JLabel lblPrice = new JLabel("Toplam \u00DCcret: ");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPrice.setBounds(10, 352, 262, 26);
		panel.add(lblPrice);
		
		updatePrice(lblPrice, customer.getTotalPrice());
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(282, 150, 116, 22);
		panel.add(comboBox);
		fillMenuList(comboBox, menuList);
		
		JButton btnAdd = new JButton("Ekle");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addButtonAction(comboBox, customer, lblPrice, menuList);
			}
		});
		btnAdd.setBounds(282, 116, 89, 23);
		panel.add(btnAdd);
		
		JButton btnRemove = new JButton("Sil");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeButtonAction(comboBox, customer, lblPrice);
			}
		});
		btnRemove.setBounds(381, 116, 89, 23);
		panel.add(btnRemove);
		
		textFieldCount = new JTextField();
		textFieldCount.setText("1");
		textFieldCount.setBounds(408, 151, 86, 20);
		panel.add(textFieldCount);
		textFieldCount.setColumns(10);
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
	}
	
	private void removeButtonAction(JComboBox<String> comboBox, Customer customer, JLabel lblPrice) {
		String itemName = comboBox.getSelectedItem().toString();
		for (MenuItem customerItem : customer.getItemList()) {
			if (customerItem.getName().equalsIgnoreCase(itemName)) {
				int oldCount = customerItem.getCount();
				int newCount = oldCount - Integer.parseInt(textFieldCount.getText());
				if (newCount >= 0) {
					customerItem.setCount(oldCount - Integer.parseInt(textFieldCount.getText()));
				}else {
					JOptionPane.showMessageDialog(null,"Girilen deðer 0'dan küçük","Hata",0);
				}
				break;
			}
		}
		fillTableModel(tableModel, customer);
		updatePrice(lblPrice, customer.getTotalPrice());
	}
}
