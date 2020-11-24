import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Error extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	JLabel lbl;
	/**
	 * Create the frame.
	 */
	public Error(){
		setIconImage(Main.E);
		setTitle("FB Marching Band Attendence: Error");
		setBounds(100, 100,  700, 400);
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
		
		JLabel lblError = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblError.setBounds(21, 10, 622, 47);
		panel.add(lblError);
		
		
		lbl = new JLabel("The passwords did not match!");
		lbl.setForeground(Color.BLACK);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lbl.setBounds(21, 122, 622, 51);
		panel.add(lbl);
	}
	public Error(boolean exit){
		if (exit)
		{
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		setBounds(100, 100,  700, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblError = new JLabel("ERROR");
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblError.setBounds(21, 10, 622, 47);
		panel.add(lblError);
		
		
		lbl = new JLabel("The passwords did not match!");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lbl.setBounds(21, 122, 622, 51);
		panel.add(lbl);
	}

}