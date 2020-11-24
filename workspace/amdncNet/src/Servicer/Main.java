package Servicer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) throws UnknownHostException, IOException
	{
		Socket s = new Socket("ados.zapto.org",443);
		new Recieve(s).start();
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		out.println("AMDNC::SERVE::AMDNC::TRANSEND");
		while(true)
		{
			System.out.print(">");
			out.println(scan.nextLine());
		}
	}
}
