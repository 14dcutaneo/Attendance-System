import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Desktop;  //New Import
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL; //New Import

import javax.swing.JTextPane;
import javax.swing.JButton;

public class Update extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Update(String r) {
		setIconImage(Main.E);
		setTitle("FB Marching Band Attendence: Update available");
		setBounds(100, 100, 700, 450);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		int posX = Main.x/2 - (700/2);
		int posY = Main.y/2 - (450/2);
		setBounds(posX,posY,700,450);
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblListOfAvailable = new JLabel("Update Available!");
		lblListOfAvailable.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblListOfAvailable.setBounds(170, 5, 324, 51);
		lblListOfAvailable.setForeground(Color.RED);
		panel.add(lblListOfAvailable);
		
		JTextPane txtpnAnUpdateFor = new JTextPane();
		txtpnAnUpdateFor.setEditable(false);
		txtpnAnUpdateFor.setFont(new Font("Tahoma", Font.PLAIN, 32));
		txtpnAnUpdateFor.setForeground(Color.BLACK);
		txtpnAnUpdateFor.setBackground(Color.ORANGE);
		txtpnAnUpdateFor.setText("An update for this program is now available for download. Click on \"Update Now\" to downlad the latest version. Be sure to replace this program with newly downloaded one.");
		panel.add(txtpnAnUpdateFor);
		txtpnAnUpdateFor.setBounds(21, 77, 622, 201);
		JButton btnUpdateNow = new JButton("Update Now");
		btnUpdateNow.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnUpdateNow.setBackground(SystemColor.textHighlight);
		btnUpdateNow.setForeground(Color.BLACK);
		btnUpdateNow.setBounds(158, 297, 324, 51);
		btnUpdateNow.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
				    //Desktop.getDesktop().browse(new URL(r).toURI());
				    //Desktop.getDesktop().open(new File(Main.dir+"update.jar"));
				    //System.exit(ABORT);
					dispose();
				} catch (Exception e) {
					new Errors.AMDNCError(
							Errors.EHAND.Type.FATAL, //TYPE
									"Update.java "//CLASS
										+ "Update(String r) "//METHOD
										+ "btnUpdateNow"//<<EXTRA>>
									, "Unable to open update.jar" //DESCRIPTION
										);
				}
			}
		});	
		panel.add(btnUpdateNow);
		
	}


}
