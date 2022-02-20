package restaurant.money;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
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
	private JTable table;
	private JScrollPane scrollPane;
	private MenuExcell menuExcell;
	private MoneyFileLineExcell moneyFileExcell;
	private File[] listOfMoneyFile;
	
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
		scrollPane.setBounds(10, 44, 1116, 472);
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
		tableModel.setColumnIdentifiers(header);
		
		table.setModel(tableModel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 11, 128, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("G\u00F6ster");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = (String) comboBox.getSelectedItem();
				fillMoneyTable(fileName);
			}
		});
		btnNewButton.setBounds(148, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		fillCombobox(comboBox);
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
}
