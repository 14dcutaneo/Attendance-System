package amdncNet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client 
{
	int id;
	Socket cl;
	PrintWriter out;
	BufferedReader in;
	boolean ready = false;
	String lastTransmissionIn;
	reader r;
	boolean isService = false;
	Client Parent = this;
	public Client (Socket client)
	{
		cl = client;
		try
		{
			out = new PrintWriter(cl.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(cl.getInputStream()));
			String str = "";
			ArrayList<String> coms = new ArrayList<>();
			r = new reader(in,cl,this);
			r.start();
			ready = true;
		}
		catch(Exception c)
		{
			
		}
	}
	
	public boolean printTo(String str)
	{
		if (ready)
		try 
		{
			out.println(str);
			return true;
		} 
		catch (Exception e) 
		{

			ready = false;
			System.out.println(e);
			return false;
		}
		else
			return false;
	}
	
	public String readLine()
	{
		try
		{
			lastTransmissionIn = in.readLine();
			return lastTransmissionIn;
		}
		catch (Exception c)
		{
			ready = false;
			return null;
		}
	}
	
	private class reader extends Thread
	{
		BufferedReader br;
		ArrayList<String> reads = new ArrayList<>();
		Socket client;
		Client Parent;
		public reader(BufferedReader in, Socket cl, Client Parent)
		{

			this.Parent = Parent;
			client = cl;
			br = in;
		}
		public void run()
		{
			String str = "";
			try {
				while ((str = in.readLine()) != null && client.isConnected())
				{
							if (str != null)
							{
								System.out.print("Data recieved from a ");
								if (isService)
									System.out.println("Servicer");
								else
									System.out.println("Client");
								
								System.out.println("From: " + cl.getRemoteSocketAddress() + " is " + str);
								if (str.equals("AMDNC::SERVE::AMDNC::TRANSEND"))
								{
									System.out.println("Servicer added...");
									Test.cs.service.add(Parent);
									isService = true;
								}
								else
								if (str.equals("AMDNC::CLIENT::AMDNC::TRANSEND"))
								{
									System.out.println("Client added...");
									Test.cs.clients.add(Parent);
									Test.cs.printToService("Client added...");
									out.println(Test.BaseVersion + " :: " + Test.baseURL + "AMDNC|CONNECTED|INTEGRATED|LIFESTYLE");
									//Characters 0 - 8 = VERSION -- 9 - 12 = GARBAGE -- 13+ = URL
								}
								else
								if (isService)
								{
									if (str.startsWith("SETVERSION "))
									{
										System.out.println("COMMAND RECEIVED TO SET NEW BASE VERSION");
										System.out.println("SETTING BASE VERSION TO: " + str.substring(11));
										Test.BaseVersion = Integer.parseInt(str.substring(11));
									}else
									if (str.equals("SERVICEHELP"))
									{
										System.out.println("SERVICE COMMAND RECEIVED TO SEND HELP");
										out.println("You may: \"SETVERSION [INT]\"");
										out.println("You may: \"FORCESTOP\"");
										out.println("You may: \"SETURL [STRING]\"");
									}else
									if (str.startsWith("SETURL "))
									{
										System.out.println("COMMAND RECIEVED TO SET NEW BASE URL");
										System.out.println("SETTING BASE URL TO: " + str.substring(7));
										Test.baseURL = str.substring(7);
									}
									Test.cs.printToClient(str);
									
								}
								else
									Test.cs.printToService(str);
						}
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
