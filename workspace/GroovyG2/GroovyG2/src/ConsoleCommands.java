import java.io.IOException;
import java.util.Scanner;

public class ConsoleCommands extends Thread
{
	boolean remote = false;
	Scanner scan;
	public void setRemote()
	{
		PrintServ.print("ConsoleCommands.java public void setRemote(): Data from remote Console allowed local Console unlock");
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
				PrintServ.print("--Override Accepted--");
				while(!input.equalsIgnoreCase("Exit"))
				{
					if (!remote)
						input = scan.nextLine();
					remote = false;
					try {
						processCommand(input);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						new Errors.AMDNCError(
								Errors.EHAND.Type.THROWN, //TYPE
										"ConsoleCommands.java "//CLASS
											+ "run()"//METHOD
											+ ""//<<EXTRA>>
										, "Unable to process command.",//DESCRIPTION
										e.getMessage()
											);
					}
				}
			}
			else
				PrintServ.print("ConsoleCommands.java public void run(): Error: Console locked, enter Override password to unlock");
		}
	}
	
	public void processCommand(String str) throws IOException
	{
		PrintServ.print("ConsoleCommands.java public void processCommand(String str) VVV");
		if (str.equalsIgnoreCase("help"))
		{
			PrintServ.print("gpass -- reset Gross's Password");
			PrintServ.print("forcesecure -- Force the program to start even if Secure.java says no.");
			PrintServ.print("pu -- Open Power Users GUI");
			PrintServ.print("newStudent -- create a new Student");
			PrintServ.print("events -- open ReportList");
		}
		if (str.equalsIgnoreCase("gpass"))
		{
			PrintServ.print("Enter new gPass");
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
				try { e.save(); } catch(Exception c) {PrintServ.print("Fail"); PrintServ.exc.add(c);}
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
		if (str.equals("SELECT"))
		{
			PrintServ.print("SELECT > Select Obj Type?");
			String newInput = scan.nextLine();
			if (newInput.equals("STUDENT"))
			{
				PrintServ.print("SELECT > Enter first last?");
				newInput = scan.nextLine();
				Student Selection = Main.stHand.getStudent(newInput);
				PrintServ.print(Selection.toString());
				if (Selection != null)
				{
					PrintServ.print("SELECT > Enter command?");
					newInput = scan.nextLine();
					if (newInput.equals("RENAME"))
					{
						PrintServ.print("SELECT > Enter new First Last");
						newInput = scan.nextLine();
						String first = newInput.substring(0, newInput.indexOf(' '));
						String last = newInput.substring(newInput.indexOf(' ') + 1);
						Selection.lFirst = first;
						Selection.lLast = last;
						Main.stHand.Save();
					}
				}
			}
		}
		PrintServ.print("ConsoleCommands.java public void processCommand(String str) ^^^");
	}
}
