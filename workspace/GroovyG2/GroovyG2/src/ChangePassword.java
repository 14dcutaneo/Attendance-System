import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	 JPasswordField passwordField_2;
	 JPasswordField passwordField_1;
	 JPasswordField passwordField_3;
	 JTextField textField;
	 JTextField textField_1;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ChangePassword() {
		setIconImage(Main.A);
		setTitle("FB Marching Band Attendence");
		setBounds(100, 0, 700, 900);
		
		int posX = Main.x/2 - (700/2);
		int posY = Main.y/2 - (900/2);
		
		setBounds(posX,posY,700,900);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblChangePassword = new JLabel("Set Password");
		lblChangePassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangePassword.setBackground(new Color(240, 240, 240));
		lblChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblChangePassword.setBounds(21, 10, 622, 51);
		lblChangePassword.setForeground(Color.BLACK);
		panel.add(lblChangePassword);
		
		JLabel lblCurrentPassword = new JLabel("Current Password");
		lblCurrentPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentPassword.setForeground(Color.BLACK);
		lblCurrentPassword.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblCurrentPassword.setBounds(203, 130, 258, 39);
		panel.add(lblCurrentPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblNewPassword.setForeground(Color.BLACK);
		lblNewPassword.setBounds(21, 271, 258, 39);
		panel.add(lblNewPassword);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBackground(SystemColor.textHighlight);
		btnChangePassword.setForeground(Color.BLACK);
		btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnChangePassword.setBounds(142, 739, 387, 59);
		panel.add(btnChangePassword);
		
		ChangePassword cp = this;
		
		btnChangePassword.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (textField.isEnabled() && Main.secure.log(passwordField_3.getText()) && !passwordField_2.getText().equals("") && !textField.getText().equals("") && !textField_1.getText().equals(""))
				{
					if (passwordField_2.getText().equals(passwordField_1.getText()))
					{// Ik
						Main.secure.gPass = Main.pu.encrypt(passwordField_2.getText());
						Main.secure.secQU = textField.getText();
						Main.secure.secAN = PowerUsers.encrypt(textField_1.getText());
						cp.dispose();
						try {
							Main.secure.save();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							Error e1 = new Error();
							e1.lbl.setText("An internal error occured. Try again");
							e1.setVisible(true);
							//PrintServ.exc.add(e);
							new Errors.AMDNCError(
									Errors.EHAND.Type.THROWN, //TYPE
											"ChangePassword.java "//CLASS
												+ "ChangePassword() "//METHOD
												+ "btnChangePassword.addActionListener"//<<EXTRA>>
											, "Unable to save Main.secure" //DESCRIPTION
												);
						}
					}
					else
					{
						Error r = new Error();
						r.lbl.setText("Passwords don't match");
						r.setVisible(true);
					}
				}
				else
				{
					if (!textField.isEnabled() && passwordField_2.getText().equals(passwordField_1.getText()) && !passwordField_2.getText().equals(""))
					{
						if (PowerUsers.encrypt(textField_1.getText()).equals(Main.secure.secAN))
						{
							Main.secure.gPass = PowerUsers.encrypt(passwordField_2.getText());
							try {
								Main.secure.save();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								//PrintServ.exc.add(e);
								new Errors.AMDNCError(
										Errors.EHAND.Type.THROWN, //TYPE
												"ChangePassword.java "//CLASS
													+ "ChangePassword() "//METHOD
													+ "btnChangePassword.addActionListener"//<<EXTRA>>
												, "Unable to save Main.secure ", //DESCRIPTION,
												"SECOND CATCH"
													);
							}
							cp.dispose();
						}
						else
						{
							Error r = new Error();
							r.lbl.setText("Wrong security answer");
							r.setEnabled(true);
						}
					}
					else
					{
						if (!passwordField_2.getText().equals(passwordField_1.getText()))
						{
							Error r = new Error();
							r.lbl.setText("Passwords don't match");
							r.setEnabled(true);
						}
						else if (!PowerUsers.encrypt(textField_1.getText()).equals(Main.secure.secAN))
						{
							
						}
						else
						{
							Error r = new Error();
							r.lbl.setText("Something wrong with reponses");
							r.setEnabled(true);
						}
					}
				}
			}
		});
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblConfirmPassword.setForeground(Color.BLACK);
		lblConfirmPassword.setBounds(385, 271, 258, 39);
		panel.add(lblConfirmPassword);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("Tahoma", Font.PLAIN, 42));
		passwordField_2.setForeground(Color.BLACK);
		passwordField_2.setBounds(385, 331, 258, 32);
		panel.add(passwordField_2);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setForeground(Color.BLACK);
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 42));
		passwordField_1.setBounds(21, 331, 258, 32);
		panel.add(passwordField_1);
		
		passwordField_3 = new JPasswordField();
		passwordField_3.setForeground(Color.BLACK);
		passwordField_3.setFont(new Font("Tahoma", Font.PLAIN, 42));
		passwordField_3.setBounds(203, 181, 258, 32);
		panel.add(passwordField_3);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 42));
		textField.setColumns(10);
		textField.setBounds(21, 466, 622, 61);
		textField.setForeground(Color.BLACK);
		panel.add(textField);
		
		JLabel lblEnterASecurity = new JLabel("Enter a Security Question:");
		lblEnterASecurity.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterASecurity.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblEnterASecurity.setForeground(Color.BLACK);
		lblEnterASecurity.setBounds(21, 407, 622, 51);
		panel.add(lblEnterASecurity);
		
		JLabel lblAnswerTheQuestion = new JLabel("Answer the Question:");
		lblAnswerTheQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnswerTheQuestion.setForeground(Color.BLACK);
		lblAnswerTheQuestion.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblAnswerTheQuestion.setBounds(21, 577, 622, 39);
		panel.add(lblAnswerTheQuestion);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 42));
		textField_1.setColumns(10);
		textField_1.setBounds(21, 627, 622, 61);
		textField_1.setForeground(Color.BLACK);
		panel.add(textField_1);
	}
}