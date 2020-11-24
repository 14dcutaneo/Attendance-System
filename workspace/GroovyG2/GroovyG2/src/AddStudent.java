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
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddStudent extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AddStudent(Student change, EditRoster starter)
	{
		setIconImage(Main.A);
		setTitle("EDIT STUDENT");
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
		
		JLabel lblNewLabel = new JLabel("Change a student name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(21, 10, 622, 42);
		lblNewLabel.setForeground(Color.BLACK);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 42));
		textField.setBounds(21, 143, 290, 52);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 42));
		textField_1.setColumns(10);
		textField_1.setBounds(353, 143, 290, 52);
		panel.add(textField_1);
		AddStudent as = this;
		JButton btnAddStudent = new JButton("Change Student");
		btnAddStudent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String name1 = textField.getText();
				String name2 = textField_1.getText();
				if ((name1+name2).contains(" "))
				{
					Error e = new Error();
					e.lbl.setText("Please do not include any spaces in names");
					e.setVisible(true);
					return;
				}
				StudentHandler stHand = Main.stHand;
				as.setVisible(false);
				try {
					change.lFirst = name1;
					change.lLast = name2;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//PrintServ.exc.add(e);
					new Errors.AMDNCError(Errors.EHAND.Type.THROWN, "AddStudent.java AddStudent(EditRoster starter) btnAddStudent.ActionListener", "Unable to save STHand");
				}
				//new EditRoster(stHand).setVisible(true);
				try {
					stHand.Save();
				} catch (IOException e) {
					PrintServ.exc.add(e);
				}
				starter.respring();
				new RestartToSaveChanges().setVisible(true);
				as.dispose();
			}
		}
	);
		btnAddStudent.setBackground(SystemColor.textHighlight);
		btnAddStudent.setForeground(Color.BLACK);
		btnAddStudent.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnAddStudent.setBounds(120, 222, 423, 49);
		panel.add(btnAddStudent);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblFirstName.setForeground(Color.BLACK);
		lblFirstName.setBounds(21, 96, 290, 42);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblLastName.setBounds(356, 96, 287, 42);
		lblLastName.setForeground(Color.BLACK);
		panel.add(lblLastName);
	}
	public AddStudent(EditRoster starter) {
		setIconImage(Main.A);
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
		
		JLabel lblNewLabel = new JLabel("Add A Student");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(21, 10, 622, 42);
		lblNewLabel.setForeground(Color.BLACK);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 42));
		textField.setBounds(21, 143, 290, 52);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 42));
		textField_1.setColumns(10);
		textField_1.setBounds(353, 143, 290, 52);
		panel.add(textField_1);
		AddStudent as = this;
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String name1 = textField.getText();
				String name2 = textField_1.getText();
				if ((name1+name2).contains(" "))
				{
					Error e = new Error();
					e.lbl.setText("Please do not include any spaces in names");
					e.setVisible(true);
					return;
				}
				StudentHandler stHand = Main.stHand;
				as.setVisible(false);
				try {
					stHand.addStudent(new Student(name1, name2, stHand.getFreeIDNumber()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//PrintServ.exc.add(e);
					new Errors.AMDNCError(Errors.EHAND.Type.THROWN, "AddStudent.java AddStudent(EditRoster starter) btnAddStudent.ActionListener", "Unable to save STHand");
				}
				//new EditRoster(stHand).setVisible(true);
				starter.respring();
				new RestartToSaveChanges().setVisible(true);
				as.dispose();
			}
		}
	);
		btnAddStudent.setBackground(SystemColor.textHighlight);
		btnAddStudent.setForeground(Color.BLACK);
		btnAddStudent.setFont(new Font("Tahoma", Font.PLAIN, 42));
		btnAddStudent.setBounds(120, 222, 423, 49);
		panel.add(btnAddStudent);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.CENTER);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblFirstName.setForeground(Color.BLACK);
		lblFirstName.setBounds(21, 96, 290, 42);
		panel.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblLastName.setBounds(356, 96, 287, 42);
		lblLastName.setForeground(Color.BLACK);
		panel.add(lblLastName);
	}

}
