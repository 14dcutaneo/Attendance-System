import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class test {

	public static void main(String[] args) throws IOException 
	{
		Scanner in = new Scanner(System.in);
		while (true)
		{
			System.out.println(PowerUsers.encrypt(in.nextLine()));
		}
	}

}
