package restaurant.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
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

import restaurant.files.classes.excell.MoneyFileLineExcell;
import restaurant.files.classes.excell.RestaurantFileExcell;
import restaurant.main.MainWindow;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;

public class MenuSettingsPanel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private MenuExcell menuExcell;
	private JTextField textField;
	private JTextField textField_1;
	private RestaurantFileExcell menuFileExcell;
	private JTextField textField_2;

	/**
	 * Create the frame.
	 */
	public MenuSettingsPanel() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Menü Düzenleme");
		
		try {
			setIconImage(ImageIO.read(new File(MainWindow.getImageFileName())));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 250, 257);
		contentPane.add(scrollPane);
		
		menuExcell = MainWindow.getMenuExcell();
		menuFileExcell = MainWindow.getMenuFileExcell();
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		String[] header = {"\u00DCr\u00FCn", "Fiyat"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(header);
		table.setModel(tableModel);
		
		textField = new JTextField();
		textField.setBounds(270, 40, 108, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		textField_1.setBounds(388, 40, 46, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u00DCr\u00FCn Ad\u0131");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(270, 15, 108, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fiyat");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(388, 15, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(270, 105, 108, 22);
		contentPane.add(comboBox);
		
		fillMenuList(comboBox, menuExcell.getMenuList());

		JButton btnAddItem = new JButton("Ekle");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addButtonAction(comboBox, menuExcell.getMenuList());
			}
		});
		btnAddItem.setBounds(270, 71, 164, 23);
		contentPane.add(btnAddItem);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		textField_2.setBounds(388, 106, 46, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnUpdatePrice = new JButton("Fiyat\u0131 G\u00FCncelle");
		btnUpdatePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePriceButtonAction(comboBox, menuExcell.getMenuList());
			}
		});
		btnUpdatePrice.setBounds(270, 138, 164, 23);
		contentPane.add(btnUpdatePrice);
		
		JLabel lblNewLabel_2 = new JLabel("\u00D6NEML\u0130 NOT");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(270, 172, 164, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("<html><p>Yeni fiyatlar\u0131n masalarda da g\u00FCncellenmesi i\u00E7in masalar\u0131n s\u0131f\u0131rlanmas\u0131 gerekmektedir.</p></html>");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(270, 197, 164, 70);
		contentPane.add(lblNewLabel_3);
		menuExcell.fillTableModel(tableModel);
	}
	
	private void addButtonAction(JComboBox<String> comboBox, List<MenuItem> menuList) {
		String text = textField.getText() + "\t" + textField_1.getText();
		menuFileExcell.addNewItem(text);
		menuExcell.fillMenu(menuFileExcell.getFile());
		menuExcell.fillTableModel(tableModel);
		menuExcell.fillTableModel(MainWindow.getMenuTableModel());
		updateMoneyFile();
		fillMenuList(comboBox, menuList);
	}
	
	private void updateMoneyFile() {
		try {
			MoneyFileLineExcell moneyFile = MainWindow.getMoneyFileExcell();
			String[] day = moneyFile.getFileLinesSplitted().get(moneyFile.getFileLinesSplitted().size() - 1);
			String[] s = new String[day.length + 2];
			for (int i = 0; i < day.length-6; i++) {
				s[i] = day[i];
			}
			s[day.length-6] = textField.getText();
			s[day.length-5] = "0";
			for (int i = day.length-6; i < day.length; i++) {
				s[i+2] = day[i];
			}
			String text = "";
			for (int i = 0; i < s.length; i++) {
				text = text + s[i];
				if (i != s.length-1) text = text + "\t";
			}
			moneyFile.updateFile(text);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
	}
	
	private void updatePriceButtonAction(JComboBox comboBox, List<MenuItem> menuList) {
		for (int i = 0; i < menuFileExcell.getFileLinesSplitted().size(); i++) {
			if (menuFileExcell.getFileLinesSplitted().get(i)[0].equalsIgnoreCase(comboBox.getSelectedItem().toString())) {
				String[] s = {menuFileExcell.getFileLinesSplitted().get(i)[0],textField_2.getText()};
				menuFileExcell.getFileLinesSplitted().set(i, s);
			}
		}
		menuFileExcell.updateFile_Price();
		menuExcell.fillMenu(menuFileExcell.getFile());
		menuExcell.fillTableModel(tableModel);
		menuExcell.fillTableModel(MainWindow.getMenuTableModel());
		fillMenuList(comboBox, menuList);
	}
	
	private void fillMenuList(JComboBox<String> comboBox, List<MenuItem> menuList) {
		comboBox.removeAll();
		for (MenuItem menuItem : menuList) {
			comboBox.addItem(menuItem.getName());
		}
	}
}
