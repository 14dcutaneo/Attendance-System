import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JOptionPane;
import java.awt.List;

public class GetReport extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	GetReport g;
	List list;
	Event e1;
	public GetReport(Event e) 
	{
		setIconImage(Main.ico);
		setTitle("FB Marching Band Attendence");
		e1 = e;
		g = this;
		this.setTitle(e.getFriendlyDate());
		setBounds(100, 100, 920, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblGetReport = new JLabel("Get Report");
		lblGetReport.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblGetReport.setBounds(350, 21, 220, 51);
		panel.add(lblGetReport);
		
		Button button = new Button("Save Report");
		button.setFont(new Font("Tahoma", Font.PLAIN, 42));
		button.setBackground(SystemColor.textHighlight);
		button.setForeground(Color.BLACK);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				File out = new File(e.saveAs + ".txt");
				try {
					out.createNewFile();
					BufferedWriter bw = new BufferedWriter(new FileWriter(out));
					bw.write("Report for: " + e.getFriendlyDate());
					bw.newLine();
					bw.flush();
					String[] r = e.beautify();
					for (String s: r)
					{
						if (!s.startsWith("Error: Student"))
						{
							bw.write(s);
							bw.newLine();
							bw.flush();
						}
					}
					bw.write("End of report of " + e.getFriendlyDate());
					bw.flush();
					bw.close();
					JOptionPane.showMessageDialog(null, "Report saved as: " + e.saveAs + ".txt");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error Occured, please contact Administrator! <Code: GetReport.java Button.actionPerformed BufferedWriter bw = new Buffered...>");
				}
				
			}
		});
		button.setBounds(626, 553, 248, 56);
		panel.add(button);
	
		list = new List();
		list.setFont(new Font("Times New Roman", Font.BOLD, 30));
		list.setBounds(10, 78, 864, 468);
		
		String[] s = e.beautify();
		
		for (int i = 0; i < s.length; i++)
		{
			list.add(s[i]);
		}
		panel.add(list);
		Button button_1 = new Button("Edit Person");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 42));
		button_1.setBackground(SystemColor.textHighlight);
		button_1.setBounds(379, 553, 230, 56);
		button_1.setForeground(Color.BLACK);
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent ae) 
			{
				// 0 = Absent unexcused
				// 1 = Absent excused
				// 2 = Present
				// 3 = Late
				System.out.println(list.getSelectedIndex());
				System.out.println(e.beautify()[list.getSelectedIndex()]);
				AttendanceOptions ao = new AttendanceOptions(e,e.getMarker(list.getSelectedIndex()).id, g);
				ao.setVisible(true);
				try {
					System.out.println("HERE");
					e.save();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error Occured, please contact Administrator! <Code: GetReport.java Button_1.actionPerformed e.save>");
				}
				System.out.println("Here");
			}
		});
	}
	
	public void respring()
	{
		new GetReport(e1).setVisible(true);
		g.dispose();
	}
	
}
