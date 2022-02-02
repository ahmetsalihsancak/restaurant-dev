package restaurant.customers;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditCustomerPanelFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public EditCustomerPanelFrame(Customer customer) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle(customer.getName() + " Ýsim Düzenlemesi");
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 89);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 394, 35);
		panel.add(textField);
		textField.setColumns(10);
		textField.setText(customer.getName());
		
		JButton btnNewButton = new JButton("Onayla");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer.updateName(textField.getText());
				CustomerPanel.setCustomerText(customer);
				setTitle(customer.getName());
			}
		});
		btnNewButton.setBounds(163, 57, 89, 23);
		panel.add(btnNewButton);
	}
}
