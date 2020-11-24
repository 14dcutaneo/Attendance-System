import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class RestartToSaveChanges extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public RestartToSaveChanges() {
		
		setIconImage(Main.E);
		setTitle("FB Marching Band Attendence");
		setBounds(100, 100, 700, 400);
		int posX = Main.x/2 - (700/2);
		int posY = Main.y/2 - (400/2);
		
		setBounds(posX,posY,700,400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNotice = new JLabel("NOTICE");
		lblNotice.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblNotice.setForeground(Color.RED);
		lblNotice.setBounds(21, 10, 622, 41);
		panel.add(lblNotice);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(SystemColor.textHighlight);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnOk.setForeground(Color.BLACK);
		btnOk.setBounds(265, 257, 141, 41);
		panel.add(btnOk);
		RestartToSaveChanges k = this;
		
		
		btnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
					k.dispose();
					Main.WAIVER = true;
			}
		});
		
		
		JLabel lblPleaseRestartThe = new JLabel("Please restart the program to save changes");
		lblPleaseRestartThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseRestartThe.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblPleaseRestartThe.setForeground(Color.BLACK);
		lblPleaseRestartThe.setBounds(21, 122, 622, 51);
		panel.add(lblPleaseRestartThe);
		
		
		if (Main.WAIVER)
		{
			k.dispose();
		}
	}

}