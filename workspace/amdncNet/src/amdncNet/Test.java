package amdncNet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*Code :
 * ServerSocket serverSocket = new ServerSocket(1);
			Socket clientSocket = serverSocket.accept();
		    PrintWriter out =
		        new PrintWriter(clientSocket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(
		        new InputStreamReader(clientSocket.getInputStream()));
 */

public class Test 
{
	public static ClientServicer cs;
	public static int BaseVersion = 0;
	public static String baseURL = "ados.zapto.org";
	public static void main(String[] args)
	{
		try 
		{ 
			
			cs = new ClientServicer();
			cs.start();
			System.out.println("Ready to service.");
		}
		catch(Exception c)
		{
			
		}
	}
}
