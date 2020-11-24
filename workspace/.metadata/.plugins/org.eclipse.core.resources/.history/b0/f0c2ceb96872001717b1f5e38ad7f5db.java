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

import java.awt.Font;
import java.awt.List;
import java.awt.Button;

public class EditRoster extends JFrame {

	private JPanel contentPane;
	Student[] s;
	EditRoster er;
	/**
	 * Launch the application.

	/**
	 * Create the frame.
	 */
	public EditRoster(StudentHandler stHand) {
		setIconImage(Main.ico);
		setTitle("FB Marching Band Attendence");
		er = this;
		setBounds(100, 100, 920, 700);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblListOfStudents = new JLabel("List of Students");
		lblListOfStudents.setForeground(Color.BLACK);
		lblListOfStudents.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblListOfStudents.setBounds(290, 21, 293, 51);
		panel.add(lblListOfStudents);
		
		List list = new List();
		
		s = stHand.getAllStudents();
		
		for (Student str: s)
		{
			list.add(str.lFirst + " " + str.lLast);
		}
		
		list.setFont(new Font("Tahoma", Font.BOLD, 30));
		list.setBounds(10, 79, 864, 468);
		panel.add(list);
		
		Button button = new Button("Add Student");
		button.setForeground(Color.BLACK);
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				AddStudent as = new AddStudent(er);
				as.setVisible(true);
			}
		});
		
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Tahoma", Font.PLAIN, 42));
		button.setBackground(SystemColor.textHighlight);
		button.setBounds(626, 553, 248, 56);
		panel.add(button);
		
		Button button_1 = new Button("Remove Student");
		button_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				stHand.delStudent(stHand.getStudent(list.getSelectedItem()).assignmentID);
				
				new EditRoster(stHand).setVisible(true);
				new RestartToSaveChanges().setVisible(true);
				try {
					stHand.Save();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				er.dispose();
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 42));
		button_1.setBackground(SystemColor.textHighlight);
		button_1.setBounds(273, 553, 336, 56);
		panel.add(button_1);
		
		Button button_2 = new Button("Power Users");
		button_2.setForeground(Color.BLACK);
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 42));
		button_2.setBackground(SystemColor.textHighlight);
		button_2.setBounds(10, 553, 248, 56);
		button_2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new puGUI().setVisible(true);
			}
		});
		panel.add(button_2);
	}

}

