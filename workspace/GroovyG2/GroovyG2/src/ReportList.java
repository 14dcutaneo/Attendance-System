import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;
import java.awt.Button;

public class ReportList extends JFrame {

	private JPanel contentPane;
	List list;
	ReportList Parent = this;
	EventHandler rh;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public ReportList(EventHandler eh) {
		rh = eh;
		setIconImage(Main.A);
		setTitle("FB Marching Band Attendence: Reports");
		setBounds(100, 100, 920, 700);
		
		int posX = Main.x/2 - (920/2);
		int posY = Main.y/2 - (700/2);
		
		setBounds(posX,posY,920,700);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblListOfAvailable = new JLabel("List of Available Reports");
		lblListOfAvailable.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblListOfAvailable.setBounds(243, 21, 457, 51);
		lblListOfAvailable.setForeground(Color.BLACK);
		panel.add(lblListOfAvailable);
		
		list = new List();
		list.setFont(new Font("Tahoma", Font.BOLD, 30));
		list.setForeground(Color.BLACK);
		list.setBounds(10, 78, 864, 468);
		list.select(0);
		
		for (int i = 0; i < eh.events.size(); i++)
		{
			try{
			list.add("Report from: " + 
		eh.
		events.
		get(i).
		getFriendlyDate());
			}
			catch(Exception c){
				new Errors.AMDNCError(
						Errors.EHAND.Type.WARNING, //TYPE
								"ReportList.java "//CLASS
									+ "ReportList(EventHandler eh) "//METHOD
									+ ""//<<EXTRA>>
								, "Unable to load index: " + i //DESCRIPTION
									);
			}
		}
		
		panel.add(list);
		
		Button button = new Button("View Report");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				new GetReport(eh.events.get(list.getSelectedIndex()),0).setVisible(true);
			}
		});
		button.setBackground(SystemColor.textHighlight);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Tahoma", Font.PLAIN, 42));
		button.setBounds(604, 552, 270, 56);
		panel.add(button);
		
		Button button_1 = new Button("Delete Report");
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 42));
		button_1.setBackground(SystemColor.textHighlight);
		button_1.setBounds(326, 552, 272, 56);
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Event e = eh.events.get(list.getSelectedIndex());
				String toDel = e.saveAs;
				DelCon dc = new DelCon(toDel,Parent);
				dc.setVisible(true);
				dc.txtpnAreYouSure.setText("Are you sure you would like to delete the report from " + e.getFriendlyDate() + " ?");
			}
				
		});
	
	}
	
	public void confirmDelete(String toDel)
	{
		Boolean b = new File(System.getenv("APPDATA") + "/amdnc/saves/EVENT." + toDel + ".amdnc").delete();
		
		if (!b)
		{
			Error ep = new Error();
			ep.setVisible(true);
			ep.lbl.setText("Error in file deletion");
		}
		else
		{
			rh.update();
			new ReportList(rh).setVisible(true);
			Parent.dispose();
		}
	
	}
}