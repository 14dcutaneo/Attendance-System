import java.util.ArrayList;
import java.util.Date;

public class PrintServ 
{
	public static ArrayList<Exception> exc = new ArrayList<>();
	public static ArrayList<String> ConsolePrint = new ArrayList<>();
	
	public static void print(String arg)
	{
		
		System.out.println(new Date().toString() + " :: " + arg);
		ConsolePrint.add(new Date().toString() + " :: " + arg);
	}
	
	public static void dumpPrintToRemote()
	{
		Main.r.out.println("IN ORDER: OUTPUT");
		for (String x: ConsolePrint)
			Main.r.out.println(x);
		Main.r.out.println("------ IN ORDER: ERRORS ------");
		System.out.println(exc.size());
		for (Errors.AMDNCError e: Errors.EHAND.getErrors())
		{
			for (String s:e.get())
				Main.r.out.println(s);
			
		}
		Main.r.out.println("VERSION: " + Main.vID);
	}

	public static void print(int arg) {
		System.out.println(arg);
		ConsolePrint.add("" + arg);
	}
	
}
