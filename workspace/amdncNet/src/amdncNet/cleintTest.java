package amdncNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class cleintTest 
{
	public static void main(String[] args)
	{
		try {
			Socket l = new Socket("127.0.0.1", 443);
			BufferedReader in = new BufferedReader(new InputStreamReader(l.getInputStream()));
			PrintWriter out = new PrintWriter(l.getOutputStream(), true);
			
			
			
			String fromServer = "";
			while ((fromServer = in.readLine()) != null) 
			{
			    System.out.println("Server: " + fromServer);
			    if (fromServer.equals("AMDNCTEST"))
			    {
			    	out.println("Hello world!");
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block

		}
	}
}
