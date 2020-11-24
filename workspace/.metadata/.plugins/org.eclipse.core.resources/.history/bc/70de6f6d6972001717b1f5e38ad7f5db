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
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;

public class AttendanceOptions extends JFrame {

	private JPanel contentPane;
	public int atten = -1;
	AttendanceOptions ao;
	/**
	 * Launch the application.
	 */

	

	/**
	 * Create the frame.
	 */
	public AttendanceOptions(Event e, long marker, GetReport gr) {
		setIconImage(Main.ico);
		setTitle("FB Marching Band Attendence");
		ao = this;
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblIsThisPerson = new JLabel("Is This Person");
		lblIsThisPerson.setForeground(Color.BLACK);
		lblIsThisPerson.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblIsThisPerson.setHorizontalAlignment(SwingConstants.CENTER);
		lblIsThisPerson.setBounds(0, 10, 639, 43);
		panel.add(lblIsThisPerson);
		
		JButton btnPresent = new JButton("Present");
		btnPresent.addActionListener(new ActionListener() 
		{
			// 0 = Absent unexcused
			// 1 = Absent excused
			// 2 = Present
			// 3 = Late
			public void actionPerformed(ActionEvent arg0) 
			{
				atten = 2;
				e.overrideStatus(marker, atten);
				gr.respring();
				try {
					e.save();
					new RestartToSaveChanges().setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
				}
				ao.dispose();
			}
		});
		
		btnPresent.setForeground(Color.BLACK);
		btnPresent.setBackground(SystemColor.textHighlight);
		btnPresent.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnPresent.setBounds(21, 97, 248, 56);
		panel.add(btnPresent);
		
		JButton btnLate = new JButton("Late");
		btnLate.setForeground(Color.BLACK);
		btnLate.setBackground(SystemColor.textHighlight);
		btnLate.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnLate.setBounds(21, 194, 248, 56);
		panel.add(btnLate);
		btnLate.addActionListener(new ActionListener() 
		{
			// 0 = Absent unexcused
			// 1 = Absent excused
			// 2 = Present
			// 3 = Late
			public void actionPerformed(ActionEvent arg0) 
			{
				atten = 3;
				e.overrideStatus(marker, atten);
				gr.respring();
				try {
					e.save();
					new RestartToSaveChanges().setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
				}
				ao.dispose();
			}
		});
		
		JButton btnAbsentexcused = new JButton("Absent (Excused)");
		btnAbsentexcused.addActionListener(new ActionListener() 
		{
			// 0 = Absent unexcused
			// 1 = Absent excused
			// 2 = Present
			// 3 = Late
			public void actionPerformed(ActionEvent arg0) 
			{
				atten = 1;
				e.overrideStatus(marker, atten);
				gr.respring();
				try {
					e.save();
					new RestartToSaveChanges().setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
				}
				ao.dispose();
			}
		});
		btnAbsentexcused.setForeground(Color.BLACK);
		btnAbsentexcused.setBackground(SystemColor.textHighlight);
		btnAbsentexcused.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnAbsentexcused.setBounds(315, 97, 324, 56);
		panel.add(btnAbsentexcused);
		
		JButton btnAbsentunexcused = new JButton("Absent (Unexcused)");
		btnAbsentunexcused.addActionListener(new ActionListener() 
		{
			// 0 = Absent unexcused
			// 1 = Absent excused
			// 2 = Present
			// 3 = Late
			public void actionPerformed(ActionEvent arg0) 
			{
				atten = 0;
				e.overrideStatus(marker, atten);
				try {
					e.save();
					new RestartToSaveChanges().setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
				}
				gr.respring();
				ao.dispose();
			}
		});
		
		btnAbsentunexcused.setForeground(Color.BLACK);
		btnAbsentunexcused.setBackground(SystemColor.textHighlight);
		btnAbsentunexcused.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnAbsentunexcused.setBounds(315, 194, 328, 56);
		panel.add(btnAbsentunexcused);
		
	}
}
