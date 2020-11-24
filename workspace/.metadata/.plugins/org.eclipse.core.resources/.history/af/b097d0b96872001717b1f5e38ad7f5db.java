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
		System.out.println(f.isDirectory());
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
						System.out.println("A dup file Detected");
						index = l.getName().charAt(15) - '0';
					}
					try
					{
						System.out.println("EventHandler.java public EventHandler(StudentHandler stHand) Load: " + date);
						if (index != -1)
							events.add(Event.load(date, stHand));
						else
							events.add(Event.load(date, stHand,index));
					}
					catch (Exception e)
					{
						
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
						
					}
				}
			}
		}
		//else;
			//JOptionPane.showMessageDialog(null, "Error Occured, please contact Administrator! <Code: EventHandler.java f.isDirectory = false>");
	}
	
}
