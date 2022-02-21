package restaurant.money;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import restaurant.files.classes.MoneyFile;
import restaurant.files.classes.MoneyFileLine;
import restaurant.files.classes.excell.MoneyFileLineExcell;
import restaurant.main.MainWindow;
import restaurant.menu.Menu;
import restaurant.menu.MenuExcell;
import restaurant.menu.MenuItem;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MoneyFrame extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableModel2;
	private JTable table;
	private JScrollPane scrollPane;
	private MenuExcell menuExcell;
	private MoneyFileLineExcell moneyFileExcell;
	private File[] listOfMoneyFile;
	private JTable table_1;
	
	/**
	 * Create the frame.
	 */
	public MoneyFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1152, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 1116, 408);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		moneyFileExcell = MainWindow.getMoneyFileExcell();
		menuExcell = MainWindow.getMenuExcell();
		listOfMoneyFile = MainWindow.getListOfMoneyFiles();
		
		String[] header = new String[menuExcell.getMenuList().size()*2 + 9];
		/*header[0] = "Gun";
		header[1] = "Ay";
		header[2] = "Yil";
		header[menuExcell.getMenuList().size()*2 + 3] = "Nakit";
		header[menuExcell.getMenuList().size()*2 + 5] = "Kart";
		header[menuExcell.getMenuList().size()*2 + 7] = "Toplam";
		for (int i = 0; i < menuExcell.getMenuList().size(); i++) {
			header[i*2+3] = menuExcell.getMenuList().get(i).getName();
		}*/
		
		tableModel = new DefaultTableModel();
		tableModel2 = new DefaultTableModel();
		
		tableModel.setColumnIdentifiers(header);
		table.setModel(tableModel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 11, 128, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("G\u00F6ster");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = (String) comboBox.getSelectedItem();
				//fillMoneyTable(fileName);
				fill(fileName);
				calculateMonth(fileName);
			}
		});
		btnNewButton.setBounds(148, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		fillCombobox(comboBox);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 44, 1116, 48);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
	}
	
	private void fillCombobox(JComboBox comboBox) {
		for (File file : listOfMoneyFile) {
			String fileName = file.getName();
			comboBox.addItem(fileName);
		}
	}
	
	private void fillMoneyTable(String fileName) {
		for (int i = tableModel.getRowCount()-1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		File file = new File("money/"+fileName);
		List<String[]> fileLineSp = moneyFileExcell.getFileScannerLineListSplitted(file);
		System.out.println(fileLineSp.size());
		for (String[] a : fileLineSp) {
			tableModel.addRow(a);
		}
	}
	
	private void calculateMonth(String fileName) {
		String[] s = new String[menuExcell.getMenuList().size() + 4];
		String[] ss = fileName.split("-");
		s[0] = ss[0];
		for (int i = 1; i < s.length; i++) {
			int a = 0;
			for (int j = 0; j < tableModel.getRowCount(); j++) {
				String o = (String) tableModel.getValueAt(j, i);
				if (o != null) {
					a = a + Integer.parseInt((String) o);
				}
				System.out.println(a);
			}
			s[i] = String.valueOf(a);
		}
		tableModel2.addRow(s);
	}
	
	private void fill(String fileName) {
		for (int i = tableModel.getRowCount()-1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		if (tableModel2.getRowCount() > 0) tableModel2.removeRow(0);
		File file = new File("money/"+fileName);
		String[] header = new String[menuExcell.getMenuList().size() + 4];
		header[0] = "Gun";
		header[menuExcell.getMenuList().size() + 1] = "Nakit";
		header[menuExcell.getMenuList().size() + 2] = "Kart";
		header[menuExcell.getMenuList().size() + 3] = "Toplam";
		for (int i = 0; i < menuExcell.getMenuList().size(); i++) {
			header[i+1] = menuExcell.getMenuList().get(i).getName();
		}
		tableModel.setColumnIdentifiers(header);
		table.setModel(tableModel);
		header[0] = "Ay";
		tableModel2.setColumnIdentifiers(header);
		table_1.setModel(tableModel2);
		
		List<String[]> fileLineSp = moneyFileExcell.getFileScannerLineListSplitted(file);
		for (int i = 0; i < moneyFileExcell.getFileScannerLineList(file).size(); i++) {
			String[] s = new String[menuExcell.getMenuList().size() + 4];
			for (int ii = 0; ii < s.length; ii++) {
				s[i] = "0";
			}
			s[0] = fileLineSp.get(i)[0] + "-" + fileLineSp.get(i)[1] + "-" + fileLineSp.get(i)[2];
			for (int j = 3; j < fileLineSp.get(i).length; j = j + 2) {
				for (int k = 1; k < header.length; k++) {
					if (header[k].equalsIgnoreCase(fileLineSp.get(i)[j])) {
						s[k] = fileLineSp.get(i)[j+1];
						break;
					}
				}
			}
			tableModel.addRow(s);
		}
	}
}
