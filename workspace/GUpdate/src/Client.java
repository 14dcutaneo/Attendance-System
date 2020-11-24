

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client
{
	public Client()
	{
		try {
			Socket l = new Socket("ados.zapto.org", 443);
			DataInputStream in = new DataInputStream(new BufferedInputStream(l.getInputStream()));
			PrintWriter out = new PrintWriter(l.getOutputStream(), true);
			
			out.println("CLIENTDOWNLOAD :: AMDNC :: CONNECTED | INTEGRATED | LIFESTYLE");
			
			String fromServer = "";
			fromServer = in.readLine();
			
			
			
			byte ffss = 0x0;
			
			while (l.isConnected())
			{
				int count;
				byte[] buffer = new byte[8192]; // or 4096, or more
				while ((count = in.read(buffer)) > 0)
				{
				 // Main.out.write(buffer, 0, count);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Main.error();
		}
	}
}
