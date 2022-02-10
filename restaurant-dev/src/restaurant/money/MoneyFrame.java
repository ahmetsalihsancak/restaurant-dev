package restaurant.money;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import restaurant.files.classes.MoneyFile;
import restaurant.main.MainWindow;
import restaurant.menu.Menu;
import restaurant.menu.MenuItem;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class MoneyFrame extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane;
	private Menu menu;
	private MoneyFile moneyFile;

	/**
	 * Create the frame.
	 */
	public MoneyFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 860, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 824, 505);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		moneyFile = MainWindow.getMoneyFile();
		menu = MainWindow.getMenu();
		
		String[] header = new String[menu.getMenuList().size()*2 + 6];
		header[0] = "Gun";
		header[1] = "Ay";
		header[2] = "Yil";
		header[menu.getMenuList().size()*2 + 3] = "Nakit";
		header[menu.getMenuList().size()*2 + 4] = "Kart";
		header[menu.getMenuList().size()*2 + 5] = "Toplam";
		for (int i = 0; i < menu.getMenuList().size(); i++) {
			header[i*2+3] = menu.getMenuList().get(i).getName();
		}
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(header);
		
		table.setModel(tableModel);
		fillMoneyTable();
	}
	
	private void fillMoneyTable() {
		for (int i = tableModel.getRowCount()-1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		System.out.println(moneyFile.getFileLinesSplitted().size());
		for (String[] a : moneyFile.getFileLinesSplitted()) {
			tableModel.addRow(a);
		}
	}
}
