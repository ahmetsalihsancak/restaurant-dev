package restaurant.customers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import restaurant.main.MainWindow;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class EditCustomerPanelFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String imageFileName;
	private JButton btnNewButton_1;

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
		setTitle(customer.getName() + " Ýsim Düzenlemesi");
		setResizable(false);
		
		try {
			imageFileName = MainWindow.getImageFileName();
			setIconImage(ImageIO.read(new File(imageFileName)));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,e,"Hata",0);
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 89);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 295, 35);
		panel.add(textField);
		textField.setColumns(10);
		textField.setText(customer.getName());
		
		JButton btnNewButton = new JButton("Onayla");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					customer.updateName(textField.getText());
					CustomerPanel.setCustomerText(customer);
					CustomerPanel.setLabel(customer.getName());
					customer.updateLabel(customer.getName());
					setTitle(customer.getName());
					CustomerPanel.setLabel2(customer.getName());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,e2,"Hata",0);
				}
			}
		});
		btnNewButton.setBounds(315, 11, 89, 35);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Masa \u0130smini S\u0131f\u0131rla");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					customer.updateName("Masa " + customer.getNo());
					CustomerPanel.setCustomerText(customer);
					CustomerPanel.setLabel(customer.getName());
					CustomerPanel.setLabel2(customer.getName());
					customer.updateLabel(customer.getName());
					setTitle(customer.getName());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,e2,"Hata",0);
				}
			}
		});
		btnNewButton_1.setBounds(125, 55, 142, 23);
		panel.add(btnNewButton_1);
	}
}
