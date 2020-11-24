
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RemoteConsole extends Thread
{
	boolean needsUpdate = false;
	String it = "";
	PrintWriter out;
	boolean HACF = false;
	boolean HACF2 = false;
	public RemoteConsole()
	{
		PrintServ.print("Created");
	}
	public void run()
	{
		PrintServ.print("Connecting to remote console.");
		int try1 = 0;
		while (true && try1 < 10)
		{
			try {
				Socket l = new Socket("ados.zapto.org", 80);
				BufferedReader in = new BufferedReader(new InputStreamReader(l.getInputStream()));
				out = new PrintWriter(l.getOutputStream(), true);
				
				try
				{
					out.println("AMDNC::CLIENT::AMDNC::TRANSEND");
					out.println("VID::" + Main.vID);
					PrintServ.print("Sent identifier to Remote Console.");
				}
				catch(Exception c)
				{
					new Errors.AMDNCError(
							Errors.EHAND.Type.THROWN, //TYPE
									"RemoteConsole.java "//CLASS
										+ "run() "//METHOD
										+ ""//<<EXTRA>>
									, "Not able to connect to remote console" //DESCRIPTION
										);
				}
				
				String fromServer = "";
				while ((fromServer = in.readLine()) != null) 
				{
				    PrintServ.print("Server: " + fromServer);
				    if (fromServer.equals("AMDNCTEST"))
				    {
				    	out.println("Good day to SERVER from " + this);
				    }
				    
				    if (fromServer.endsWith("AMDNC|CONNECTED|INTEGRATED|LIFESTYLE"))
				    {
				    	int newVer = Integer.parseInt(fromServer.substring(0, 9));
				    	fromServer = fromServer.substring(0, fromServer.length()-new String("AMDNC|CONNECTED|INTEGRATED|LIFESTYLE").length());
				    	PrintServ.print("New ver: " + newVer);
				    	PrintServ.print("URL: "  + fromServer.substring(13));
				    	if (newVer > Main.vID)
				    	{
				    		it = fromServer.substring(13);
				    		needsUpdate = true;
				    	}
				    }
				    
				    process(fromServer,in,out);
				}
			} catch (Exception e) {
				PrintServ.print("Unable to connect to remote console.");
				PrintServ.exc.add(e);
				try1 ++;
			}
		}
	}
	
	private void process(String fromServer, BufferedReader in, PrintWriter out)
	{
		if (fromServer.equalsIgnoreCase("HELP"))
		{
			PrintServ.print("Command recieved from Remote Console: HELP");
			out.println("REMOTE COMMANDS:");
			out.println("- RESETPASS: RESET GROSS'S PASSWORD TO: 'FreeholdColonials'");
			out.println("- OVERRIDE: UNLOCKS REMOTE CONSOLE");
			out.println("- PRINT: prints entire output and error");
			out.println("- HACF: Requires authentication");
		}
		if (fromServer.equalsIgnoreCase("RESETPASS"))
		{
			PrintServ.print("Command recieved from Remote Console: RESETPASS");
			Main.secure.gPass = PowerUsers.encrypt("FreeholdColonials");
			try {
				Main.secure.save();
			} catch (IOException e) {
				PrintServ.exc.add(e);
			}
			PrintServ.print("Successfully reset password");
			out.println("SUCCESSFULLY RESET GROSS PASS");
		}
		if (fromServer.equalsIgnoreCase("OVERRIDE"))
		{
			PrintServ.print("Command recieved from Remote Console: OVERRIDE");
			Main.c.setRemote();
			out.print("SUCCESSFULLY UNLOCKED CONSOLE");
		}
		if (fromServer.equalsIgnoreCase("PRINT"))
		{
			PrintServ.print("Command recieved from Remote Console: PRINT");
			PrintServ.dumpPrintToRemote();
		}
		if (HACF && fromServer.equals("amdncOverride1423"))
		{
			out.println("Halt and Catch Fire: T R U E");
			Main.TerminateAndStayResident();
			HaltAndCatchFire.go();
		}
		HACF = false;
		if (fromServer.equalsIgnoreCase("HALT AND CATCH FIRE") || fromServer.equalsIgnoreCase("HACF"))
		{
			if (HACF2 == true)
				HACF2 = false;
			else
			{
				out.println("Authenticate.");
				HACF = true;
			}
		}
		out.flush();
		
	}
	public void ud()
	{
		if (needsUpdate)
			new Update(it).setVisible(true);
	}
}
