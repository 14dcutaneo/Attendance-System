import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PowerUsers 
{
	ArrayList<String> psw = new ArrayList<String>();
	ArrayList<String> n = new ArrayList<String>();
	File f = new File(System.getenv("APPDATA") + "/amdnc/saves/pu.amdnc");
	File f1 = new File(System.getenv("APPDATA") + "/amdnc/saves/puN.amdnc");
	public PowerUsers() throws IOException
	{
		if (!f.exists())
			f.createNewFile();
		else
		{
			String r;
			BufferedReader br = new BufferedReader(new FileReader(f));
			while ((r=br.readLine()) != null)
			{
				if (!r.equals(Main.secure.securityCheckpoint))
				psw.add(r);
			}
		}
		if (!f1.exists())
			f1.createNewFile();
		else
		{
			String r;
			BufferedReader br1 = new BufferedReader(new FileReader(f1));
			while ((r=br1.readLine()) != null)
			{
				if (!r.equals(Main.secure.securityCheckpoint))
				n.add(r);
			}
		}
		
	}
	public String[] getList()
	{
		String[] s = new String[n.size()];
		
		for (int i = 0; i < n.size(); i++)
		{
			s[i] = n.get(i);
		}
		return s;
	}
	public void add(String psw, String n) throws IOException
	{
		this.psw.add(encrypt(psw));
		this.n.add(n);
		save();
	}
	public void remove(String ps) throws IOException
	{
		for (int i = 0; i<n.size(); i++)
		{
			if (n.get(i).equals(ps))
			{
				n.remove(i);
				psw.remove(i);
			}
		}
		save();
	}
	
	public static String encrypt(String s)
	{
		String s1 = "";
		
		for (int i = 0; i < s.length(); i++)
		{
			s1 += (int) (s.charAt(i) + 1) *2;
		}
		
		return s1;
		
	}
	
	public void change(String oldP, String newP)
	{
		for (int i = 0; i < psw.size(); i++)
		{
			if (encrypt(oldP).equals(psw.get(i)))
				psw.set(i, encrypt(newP));
		}
	}
	
	public void save() throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		for (int i = 0; i<psw.size(); i++)
		{
			bw.write(psw.get(i));
			bw.newLine();
			bw.flush();
		}
		bw.write(Main.secure.securityCheckpoint);
		bw.flush();
		bw.close();
		bw = new BufferedWriter(new FileWriter(f1));
		for (int i = 0; i<psw.size(); i++)
		{
			bw.write(n.get(i));
			bw.newLine();
			bw.flush();
		}
		bw.write(Main.secure.securityCheckpoint);
		bw.flush();
		bw.close();
	}
	public boolean isPower(String str)
	{
		return psw.contains(encrypt(str));
	}
}
