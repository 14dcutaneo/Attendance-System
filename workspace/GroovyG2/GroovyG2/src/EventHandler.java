import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class EventHandler 
{
	ArrayList<Event> events = new ArrayList<Event>();
	StudentHandler stHand1;
	public EventHandler(StudentHandler stHand)
	{
		stHand1 = stHand;
		File f = new File(System.getenv("APPDATA") + "/amdnc/saves");
		new File(System.getenv("APPDATA") + "/amdnc/saves").mkdirs();
		PrintServ.print("EventHandler.java EventHandler(StudentHandler stHand) is %appdata%/amdnc/save a dir? :: " + f.isDirectory());
		if (f.isDirectory())
		{
			File[] fs = f.listFiles();
			for (File l : fs)
			{
				if (l.getName().startsWith("EVENT."))
				{
					String date = l.getName().substring(6,14);
					int index = -1;
					if (l.getName().length() > 20)
					{
						PrintServ.print("A dup file Detected");
						index = l.getName().charAt(15) - '0';
					}
					try
					{
						PrintServ.print("EventHandler.java public EventHandler(StudentHandler stHand) Load: " + date);
						if (index != -1)
							events.add(Event.load(date, stHand));
						else
							events.add(Event.load(date, stHand,index));
					}
					catch (Exception e)
					{
						//MARK 1
						new Errors.AMDNCError(
								Errors.EHAND.Type.WARNING, //TYPE
										"EventHandler.Java "//CLASS
											+ "public EventHandler(StudentHandler stHand) "//METHOD
											+ "MARK1"//<<EXTRA>>
										, "Unable to load event data for : " + date //DESCRIPTION
											);
					}
				}
			}
		}
		//else;
			//JOptionPane.showMessageDialog(null, "Error Occured, please contact Administrator! <Code: EventHandler.java f.isDirectory = false>");
	}
	public void update()
	{
		File f = new File(System.getenv("APPDATA") + "/amdnc/saves");
		events = new ArrayList<Event>();
		if (f.isDirectory())
		{
			File[] fs = f.listFiles();
			for (File l : fs)
			{
				if (l.getName().startsWith("EVENT."))
				{
					String date = l.getName().substring(6,14);
					try
					{
						events.add(Event.load(date, stHand1));
					}
					catch (Exception e)
					{
						//MARK 2
						new Errors.AMDNCError(
								Errors.EHAND.Type.WARNING, //TYPE
										"EventHandler.Java "//CLASS
											+ "public EventHandler(StudentHandler stHand) "//METHOD
											+ "MARK2"//<<EXTRA>>
										, "Unable to load event data for : " + date //DESCRIPTION
											);
					}
				}
			}
		}
		//else;
			//JOptionPane.showMessageDialog(null, "Error Occured, please contact Administrator! <Code: EventHandler.java f.isDirectory = false>");
	}
	
}
