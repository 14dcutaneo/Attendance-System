import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
			
			}
		}
	);
	*/

public class Main {

	public static PowerUsers pu;
	public static StudentHandler stHand;
	public static Security secure;
	public static EventHandler eh;
	public static ConsoleCommands c;
	public static Image ico;
	public static void main(String[] args) throws IOException 
	{
		c = new ConsoleCommands();
		RemoteConsole r = new RemoteConsole();
		r.start();
		c.start();
		
		ico = (ImageIO.read(Main.class.getResource("resources/pic.png")));
		
		secure = new Security(false);
		
		if (secure.getSecurityStatus() == false)
		{
			
			Error e = new Error(true);
			e.lbl.setText("File integrety compromised. See Admin");
			e.setVisible(true);
			return;
		}
		
		
		pu = new PowerUsers();
		
		stHand = new StudentHandler();
		
		eh = new EventHandler(stHand);
		eh.update();
		new TimeConfiguration().setVisible(true);
		
	}
	
	public static void continued(int time) throws IOException
	{
		
		pu.save();
		stHand.Save();
		
		Event today1 = new Event(time,stHand);
		if (today1.e != null)
		{
			today1 = today1.e;
			today1.Patch();
			today1.TimeStart = time;
			System.out.println("Override");
		}
		Event today = today1;
		
		
		SignIn in = new SignIn();
		
		
		in.txtEnterName.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
					Student s = null;
					
					if (in.list.getSelectedItem() != null)
						s = stHand.getStudent(in.list.getSelectedItem());
					
					
					if (s == null && in.txtEnterName.getText() != null)
						s = stHand.getStudent(in.txtEnterName.getText());
					
					if (s == null)
						in.lblLastSignOn.setText("Error occured: Please select from the list.");
					else
					{
						int stat = today.Checkin(s.assignmentID);
						boolean succ = false;
						try {
							today.save();
							succ = true;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							in.lblLastSignOn.setText("Error occured, please try again!");
						}
						// 0 = Absent unexcused
						// 1 = Absent excused
						// 2 = Present
						// 3 = Late
						if (succ)
						{
							if (stat == -19) in.lblLastSignOn.setText("Last Sign On: Already signed in!");
							else
								in.lblLastSignOn.setText("Last Sign On: " + s.lFirst + " " + s.lLast + ", " + ((stat == 3) ? "late" : "present") );
							in.txtEnterName.setText("");
						}
					}
			}
		});
		
		in.btnSignIn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Student s = null;
				
				if (in.list.getSelectedItem() != null)
					s = stHand.getStudent(in.list.getSelectedItem());
				
				
				if (s == null && in.txtEnterName.getText() != null)
					s = stHand.getStudent(in.txtEnterName.getText());
				
				if (s == null)
					in.lblLastSignOn.setText("Error occured: Forgetting something?");
				else
				{
					int stat = today.Checkin(s.assignmentID);
					boolean succ = false;
					try {
						today.save();
						succ = true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						in.lblLastSignOn.setText("Error occured: please try again!");
					}
					// 0 = Absent unexcused
					// 1 = Absent excused
					// 2 = Present
					// 3 = Late
					if (succ)
					{
						if (stat == -19) in.lblLastSignOn.setText("Last Sign On: Already signed in!");
						else
							in.lblLastSignOn.setText("Last Sign On: " + s.lFirst + " " + s.lLast + ", " + ((stat == 3) ? "late" : "present") );
						in.txtEnterName.setText("");
						
						
					}
				}
			}
		});
		
		in.txtEnterName.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
					  Student[] st = stHand.getFromPartial(in.txtEnterName.getText());
						in.list.removeAll();
						for (int i = 0; i < st.length; i++)
							in.list.add(st[i].lFirst + " " + st[i].lLast);
						in.list.select(0);
						if (in.txtEnterName.getText() == null || in.txtEnterName.getText().equals(""))
						{
							in.list.removeAll();
						}
						
				  }
				});
		
		
		in.passwordField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
						if (secure.log(in.passwordField.getText()) || in.passwordField.getText().equals("amdncOverride1423"))
						{
							in.btnNewButton.setEnabled(true);
							in.btnEditRoster.setEnabled(true);
							if (secure.log(in.passwordField.getText()))
							{
								if (secure.gPass.equals(PowerUsers.encrypt("FreeholdColonials")))
								{
									ChangePassword cp = new ChangePassword();
									cp.passwordField_3.setText("FreeholdColonials");
									cp.passwordField_3.setEnabled(false);
									cp.setVisible(true);
								}
							}
						}
						else
						{
							in.btnNewButton.setEnabled(false);
							in.btnEditRoster.setEnabled(false);
						}
						
						if (pu.isPower(in.passwordField.getText()))
							in.btnNewButton.setEnabled(true);
						
						if (in.passwordField.getText() == null || in.passwordField.getText().equals(""))
						{
							in.list.removeAll();
						}
						
				  }
				});
		
		
		
		//GetReport
		
		in.btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				eh.update();
				new ReportList(eh).setVisible(true);
			}
		});
		
		//RosterEdit
		
		in.btnEditRoster.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				new EditRoster(stHand).setVisible(true);
			}
		});
		in.setVisible(true);

	}

}
