import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class transTest {

	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		//String fileIn = "C:/Users/Alan489/Desktop/DriveDesktop/Compiled programs/JAR/GroovyG/GrossClient.8.2.17.jar";
		//String fileOut = "C:/Users/Alan489/Desktop/GrossClient.8.2.17.jar";
		//String fileIn = "";
		//String fileOut = "";
		String fileIn = "C:/Users/Alan489/Desktop/g/BandTurnstyle.exe";
		String fileOut = "C:/Users/Alan489/Desktop/BandTurnstyle1.exe";
		File f1 = new File(fileIn);
		File f2 = new File(fileOut);
		
		f2.createNewFile();
		
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(f1));
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f2));
		
		int c = 0;
		
		//in.read
		
		while ((c =  in.read()) != -1)
			out.write(c);
		
		out.flush();
		
	}

}
