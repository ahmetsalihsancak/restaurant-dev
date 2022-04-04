package restaurant.customers;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import restaurant.files.classes.excell.CustomerFileExcell;
import restaurant.main.MainWindow;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class CustomerSettingsPanel extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private CustomerFileExcell customerFile;
	private JComboBox comboBox;
	private JTextField textField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnChangeName;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	
	/**
	 * Create the frame.
	 */
	public CustomerSettingsPanel() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setTitle("Masa Ayarlarý");
		
		try {
			setIconImage(ImageIO.read(new File(MainWindow.getImageFileName())));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
		
		customerFile = MainWindow.getCustomerFileExcell();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 225, 239);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		String[] header = {"Masa Numarasý","Masa Adý"};
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(header);
		table.setModel(tableModel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(245, 30, 44, 22);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(299, 31, 125, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewLabel = new JLabel("No");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(243, 12, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Varsay\u0131lan Masa Ad\u0131");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(299, 12, 125, 14);
		contentPane.add(lblNewLabel_1);
		
		btnChangeName = new JButton("De\u011Fi\u015Ftir");
		btnChangeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText() != "" || textField.getText() != null) changeNameButtonAction();
				else JOptionPane.showMessageDialog(null,"Masa adý boþ býrakýlamaz.","Hata",0);
			}
		});
		btnChangeName.setBounds(245, 63, 179, 23);
		contentPane.add(btnChangeName);
		
		lblNewLabel_2 = new JLabel("\u00D6NEML\u0130 NOT");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(245, 97, 179, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("<html>Varsay\u0131lan masa ad\u0131 de\u011Fi\u015Ftirildikten sonra, "
				+ "masa ad\u0131n\u0131n ana ekranda ve masa panelinde de g\u00FCncellenmesi i\u00E7in "
				+ "program\u0131n yeniden ba\u015Flat\u0131lmas\u0131 gerekmektedir.</html>");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(245, 122, 179, 128);
		contentPane.add(lblNewLabel_3);
		fillTable();
		fillComboBox();
	}
	
	private void changeNameButtonAction() {
		int no = Integer.parseInt(comboBox.getSelectedItem().toString()); 
		for (int i = 0; i < customerFile.getFileLinesSplitted().size(); i++) {
			if (i+1 == no) customerFile.getFileLinesSplitted().set(i, new String[]{
					customerFile.getFileLinesSplitted().get(i)[0],
					textField.getText()
			});
		}
		customerFile.updateCustomerName();
		fillTable();
	}
	
	private void fillComboBox() {
		comboBox.removeAll();
		for (String[] c : customerFile.getFileLinesSplitted()) {
			comboBox.addItem(c[0]);
		}
	}
	
	private void fillTable() {
		for (int i = tableModel.getRowCount()-1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
		for (String[] c : customerFile.getFileLinesSplitted()) {
			tableModel.addRow(c);
		}
	}

}
