package restaurant.customers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class CustomerPanel extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public CustomerPanel(Customer customer) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 544, 389);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 179, 262, 199);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		String[] header = {"\u00DCr\u00FCn", "Fiyat", "Adet"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(header);
		fillTableModel(tableModel, customer);
		table.setModel(tableModel);
	}
	
	private void fillTableModel(DefaultTableModel tableModel, Customer customer) {
		for (int i = 0; i < customer.getItemList().size(); i++) {
			String name = customer.getItemList().get(i).getName();
			float price = customer.getItemList().get(i).getPrice();
			int count = customer.getItemList().get(i).getCount();
			tableModel.addRow(new Object[]{name, price, count});
		}
	}
}
