import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Security 
{

	String gPass;
	String secQU;
	String secAN;
	private boolean secure = true;
	String securityCheckpoint;
	
	public Security(boolean over) throws IOException
	{
		//Line 1: GPassword
				//Line 2: Security question
				//Line 3: Security answer
				//Line 4: Security check
		
		//////////////////////////////SYSTEM INTEGTRETY CHECK
		
		File f = new File(System.getenv("APPDATA") + "/amdnc/saves/secure.amdnc");
		
		if (new File(System.getenv("APPDATA") + "/amdnc/saves").isDirectory() && !f.exists() && over == false)
		{
			System.out.println("INSECURE");
			secure = false;
			return;
		}
		
		new File(System.getenv("APPDATA") + "/amdnc/saves").mkdirs();
		
		gPass = "";
		secQU = "";
		secAN = "";
		
		
		if (!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String read = "";
		
		read = br.readLine();
		
		if (read != null)
		{
			gPass = read;
		}
		
		read = br.readLine();
		
		if (read != null)
		{
			secQU = read;
		}
		
		read = br.readLine();
		
		if (read != null)
		{
			secAN = read;
		}
		
		read = br.readLine();
		
		if (read != null)
		{
			securityCheckpoint = read;
			
			File dir = new File(System.getenv("APPDATA") + "/amdnc/saves");
			
			File[] dirL = dir.listFiles();
			
			
			
			for (File s : dirL)
			{
				br = new BufferedReader(new FileReader(s));
				String r = "";
				boolean fileSecure = false;
				while ((r=br.readLine()) != null)
				{
					if (r.equals(securityCheckpoint))
						fileSecure = true;
				}
				if (!fileSecure)
				{
					secure = false;
					return;
				}
			}
			
			
		}
		else
		{
			securityCheckpoint = getNewPoint();
			save();
		}
		if (gPass.equals("") || gPass == null)
		{
			gPass = Main.pu.encrypt("FreeholdColonials");
			save();
		}
	}
	
	public String getNewPoint()
	{
		return "S" + ((int)(Math.random()*100000)) + "AMD" + ((int) (Math.random()*20031)) + "NC";
	}
	
	public boolean getSecurityStatus()
	{
		return secure;
	}
	
	//Line 1: GPassword
			//Line 2: Security question
			//Line 3: Security answer
			//Line 4: Security check
	
	
	public void save() throws IOException
	{
		File f = new File(System.getenv("APPDATA") + "/amdnc/saves/secure.amdnc");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		//System.out.println(gPass);
		//System.out.println(secQU);
		//System.out.println(secAN);
		//System.out.println(securityCheckpoint);
		bw.write(gPass);
		bw.newLine();
		bw.write(secQU);
		bw.newLine();
		bw.write(secAN);
		bw.newLine();
		bw.write(securityCheckpoint);
		bw.flush();
		bw.close();
	}
	
	public boolean log(String str)
	{
		if (gPass.equals("") || gPass == null)
			return str.equals("FreeholdColonials");
		else
			return PowerUsers.encrypt(str).equals(gPass);
	}
	
	public void resetPassword()
	{
		
	}
	
}
