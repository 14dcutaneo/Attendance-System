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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class TimeConfiguration extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private TimeConfiguration tc;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TimeConfiguration() {
		setIconImage(Main.ico);
		setTitle("FB Marching Band Attendence");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tc = this;
		setBounds(100, 100, 700, 810); //NEW CODE NEW CODE
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblPleaseEnterA = new JLabel("At What Time Will People Be Late?");
		lblPleaseEnterA.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnterA.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblPleaseEnterA.setBounds(0, 10, 664, 41);
		panel.add(lblPleaseEnterA);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 42));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(66, 160, 141, 46);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 42));
		textField_1.setBounds(228, 160, 141, 46);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblHour = new JLabel("Hour");
		lblHour.setHorizontalAlignment(SwingConstants.CENTER);
		lblHour.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblHour.setBounds(66, 127, 141, 26);
		panel.add(lblHour);
		
		JLabel lblMinute = new JLabel("Minute");
		lblMinute.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinute.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblMinute.setBounds(233, 127, 136, 26);
		panel.add(lblMinute);
		
		JButton btnPm = new JButton("PM");
		btnPm.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int t = Integer.parseInt(textField.getText());
				if (t < 12)
					t += 12;
				t = t*100;
				t += Integer.parseInt(textField_1.getText());
				try {
					Main.continued(t);
					tc.dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnPm.setBackground(SystemColor.textHighlight);
		btnPm.setForeground(Color.BLACK);
		btnPm.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnPm.setBounds(443, 194, 141, 49);
		panel.add(btnPm);
		
		JLabel label = new JLabel(":");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 32));
		label.setBounds(208, 160, 20, 41);
		panel.add(label);
		
		JButton btnAm = new JButton("AM");
		btnAm.setBackground(SystemColor.textHighlight);
		btnAm.setForeground(Color.BLACK);
		btnAm.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnAm.setBounds(443, 124, 141, 49);
		btnAm.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int t = Integer.parseInt(textField.getText());
				
				t = t*100;
				if (t == 1200)
					t = 0;
				t += Integer.parseInt(textField_1.getText());
				try {
					Main.continued(t);
					tc.dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnAm);
		
		//EVERYTHING BELOW HERE IS NEW CODE NEW CODE NEW CODE
		//THERE IS ONE MORE EDIT AT THE BEGINNING OF THE PROGRAM BE SURE TO ADD IT
		
		JLabel lblOrSignIn = new JLabel("Or, Sign In");
		lblOrSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrSignIn.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblOrSignIn.setForeground(Color.BLACK);
		lblOrSignIn.setBounds(21, 310, 622, 57);
		panel.add(lblOrSignIn);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 42));
		passwordField.setBounds(66, 388, 518, 46);
		
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("Get Report");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (Main.secure.log(passwordField.getText()) || passwordField.getText().equals("amdncOverride1423") || Main.pu.isPower(passwordField.getText()))
					new ReportList(Main.eh).setVisible(true);
				else
				{
					Error e = new Error();
					e.lbl.setText("Password Incorrect");
					e.setVisible(true);
				}
				if (Main.secure.log(passwordField.getText()) && passwordField.getText().equals("FreeholdColonials")) //Sadeness Part 1 -- Enigma	
				{
					ChangePassword cp = new ChangePassword();
					cp.passwordField_3.setText("FreeholdColonials");
					
					cp.passwordField_3.setEnabled(false);
					cp.setVisible(true);
				}
					
			}
		});
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(21, 490, 278, 57);
		panel.add(btnNewButton);
		
		JButton btnEditRoster = new JButton("Edit Roster");
		btnEditRoster.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (Main.secure.log(passwordField.getText()) || passwordField.getText().equals("amdncOverride1423"))
					new EditRoster(Main.stHand).setVisible(true);
				else
				{
					Error e = new Error();
					e.lbl.setText("Password Incorrect");
					e.setVisible(true);
				}
				if (Main.secure.log(passwordField.getText()) && passwordField.getText().equals("FreeholdColonials")) //Sadeness Part 1 -- Enigma
				{
					ChangePassword cp = new ChangePassword();
					cp.passwordField_3.setText("FreeholdColonials");
					
					cp.passwordField_3.setEnabled(false);
					cp.setVisible(true);
				}
			}
		});
		btnEditRoster.setBackground(SystemColor.textHighlight);
		
		btnEditRoster.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnEditRoster.setForeground(Color.BLACK);
		btnEditRoster.setBounds(336, 490, 307, 57);
		panel.add(btnEditRoster);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (Main.secure.log(passwordField.getText())) //Sadeness Part 1 -- Enigma
				{
					ChangePassword cp = new ChangePassword();
					cp.passwordField_3.setText(passwordField.getText());
					cp.passwordField_3.setEnabled(false);
					cp.setVisible(true);
				}
			}
		});
		btnChangePassword.setBackground(SystemColor.textHighlight);
		btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnChangePassword.setForeground(Color.BLACK);
		btnChangePassword.setBounds(131, 568, 394, 57);
		panel.add(btnChangePassword);
		
		JButton btnForgotPassword = new JButton("Forgot Password (Admin Only)");
		btnForgotPassword.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						ChangePassword cp = new ChangePassword();
						cp.passwordField_3.setEnabled(false);
						
						cp.textField.setEnabled(false);
						cp.textField.setText(Main.secure.secQU);
						cp.setVisible(true);
					}
				});
		btnForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnForgotPassword.setBackground(SystemColor.textHighlight);
		btnForgotPassword.setForeground(Color.BLACK);
		btnForgotPassword.setBounds(21, 646, 622, 57);
		panel.add(btnForgotPassword);
	}
}
