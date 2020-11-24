package amdncNet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientServicer extends Thread
{
	public ArrayList<Client> clients = new ArrayList<Client>();
	public ArrayList<Client> service = new ArrayList<Client>();
	public void run()
	{
		while (true){
		ServerSocket serverSocket;
			try {
				serverSocket = new ServerSocket(443);
				Socket clientSocket = serverSocket.accept();
				Client c = new Client(clientSocket);
				
				serverSocket.close();
				
			} 
			catch (IOException e1) 
			{
				
				e1.printStackTrace();
			}
		}
	}
	public void printToService(String str)
	{
			for (Client c:service)
				try
				{
					c.printTo(str);
				}
				catch(Exception ca)
				{}
	}
	public void printToClient(String str)
	{
			for (Client c:clients)
				try
				{
					c.printTo(str);
				}
				catch(Exception ca)
				{}
	}
}
