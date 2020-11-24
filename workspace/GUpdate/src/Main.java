import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame 
{
	public static BufferedWriter out;
	public static void main(String[] args) throws IOException
	{
		Main m = new Main();
		m.setVisible(true);
		System.out.println("yep");
		
		
		String meString = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().toString();
		
		System.out.println(meString);
		
		String[] bits = meString.split("/");
		String dirBefore = "";
		
		
		for (int i = 0; i < bits.length - 1; i++)
			dirBefore += bits[i] + "/";
		
		String tst = dirBefore + "BandTurnstyle.exe";
		
		
		
		File tstF2 = new File(tst+"NEW");
		tstF2.createNewFile();
		
		out = new BufferedWriter(new FileWriter(tstF2));
		
		int r = 0;
		File tstF = new File (tst);
		while (!tstF.renameTo(new File(dirBefore + "BandTurnstyle.exe.OLD." + r)))
			r++;
		
		
		
		
		
		
	}
	
	public static void error()
	{
		
	}
	
	public Main() throws IOException
	{
		setTitle("AMDNC UPDATE SERVICE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image logo;
		
			logo = (ImageIO.read(Main.class.getResource("resources/Poss.png")));
			int y = logo.getHeight(null);
			int x = logo.getWidth(null);
			
			JFrame jf = new JFrame();
			
			setSize(x, y);
			setResizable(false);
			JLabel picLabel = new JLabel(new ImageIcon(logo));
			add(picLabel);

	}
	
	 

}
