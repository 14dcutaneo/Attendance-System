import java.io.IOException;
import java.util.Scanner;

public class ConsoleCommands extends Thread
{
	boolean remote = false;
	Scanner scan;
	public void setRemote()
	{
		System.out.println("ConsoleCommands.java public void setRemote(): Data from remote Console allowed local Console unlock");
		remote = true;
	}
	public void run()
	{
		scan = new Scanner(System.in);
		
		while (true)
		{
			String input = scan.nextLine();
			if (input.equals("amdncOverride1423") || remote)
			{
				System.out.println("--Override Accepted--");
				while(!input.equalsIgnoreCase("Exit"))
				{
					if (!remote)
						input = scan.nextLine();
					remote = false;
					try {
						processCommand(input);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else
				System.out.println("ConsoleCommands.java public void run(): Error: Console locked, enter Override password to unlock");
		}
	}
	
	public void processCommand(String str) throws IOException
	{
		System.out.println("ConsoleCommands.java public void processCommand(String str) VVV");
		if (str.equalsIgnoreCase("help"))
		{
			System.out.println("gpass -- reset Gross's Password");
			System.out.println("forcesecure -- Force the program to start even if Secure.java says no.");
			System.out.println("pu -- Open Power Users GUI");
			System.out.println("newStudent -- create a new Student");
			System.out.println("events -- open ReportList");
		}
		if (str.equalsIgnoreCase("gpass"))
		{
			System.out.println("Enter new gPass");
			System.out.print("> ");
			String input = scan.nextLine();
			Main.secure.gPass = PowerUsers.encrypt(input);
			Main.secure.save();
		}
		if (str.equalsIgnoreCase("ForceSecure"))
		{
			Main.secure.securityCheckpoint = Main.secure.getNewPoint();
			Main.secure = new Security(true);
			Main.secure.save();
			Main.pu = new PowerUsers();
			Main.pu.save();
			Main.stHand = new StudentHandler();
			Main.stHand.Save();
			Main.eh = new EventHandler(Main.stHand);
			Main.eh.update();
			
			for (Event e : Main.eh.events)
				try { e.save(); } catch(Exception c) {System.out.println("Fail");}
			new TimeConfiguration().setVisible(true);
			
		}
		if (str.equalsIgnoreCase("newStudent"))
		{
			new AddStudent(null).setVisible(true);
		}
		if (str.equalsIgnoreCase("pu"))
		{
			new puGUI().setVisible(true);
		}
		if (str.equalsIgnoreCase("events"))
		{
			new ReportList(Main.eh).setVisible(true);
		}
		System.out.println("ConsoleCommands.java public void processCommand(String str) ^^^");
	}
}
