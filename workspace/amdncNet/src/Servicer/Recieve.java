package Servicer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Recieve extends Thread
{
	Socket cl;
	public Recieve(Socket c)
	{
		cl = c;
	}
	public void run()
	{
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(cl.getInputStream()));
			while(true)
			{
				String in1 = "";
				while ((in1=in.readLine()) != null)
					System.out.println(in1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
