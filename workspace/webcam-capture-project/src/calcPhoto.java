import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class calcPhoto 
{
	int SIZE = 64;
	Color[][] curr;
	Color[][] last;
	int tolerence = 50;
	public boolean[][] detectRecolor(BufferedImage bi)
	{
		Graphics ga = bi.getGraphics();
		ga.setColor(Color.BLACK);
		int XCHUNKS = (bi.getWidth()-1)/SIZE;
		int YCHUNKS = (bi.getHeight()-1)/SIZE;
		curr = new Color[XCHUNKS][YCHUNKS];
		boolean[][] re = new boolean[XCHUNKS][YCHUNKS];
		int YCHUNKCOUNT = 0;
		int XCHUNKCOUNT = 0;
		int TOTALCOUNT= 0;
		//System.out.println(XCHUNKS + "  " + YCHUNKS);
		for (int xS = 0; xS <= bi.getWidth()-SIZE-1; xS += SIZE)
		{
			YCHUNKCOUNT = 0;
			for (int yS = 0; yS <= bi.getHeight()-SIZE-1; yS += SIZE)
			{
				
				int xE = xS + SIZE;
				int yE = yS + SIZE;
				int count,r,g,b;
				count = r = g = b = 0;
				for (int x = xS; x <= xE; x++)
					for (int y = yS; y <= yE; y++)
					{
						Color c = new Color(bi.getRGB(x, y));
						count++;
						r += c.getRed();
						g += c.getGreen();
						b += c.getBlue();
					}
				r = r/count;
				g = g/count;
				b = b/count;
				if (last == null)
					last = curr;
				curr[XCHUNKCOUNT][YCHUNKCOUNT] = new Color(r,g,b);
				int rLAST = last[XCHUNKCOUNT][YCHUNKCOUNT].getRed();
				int gLAST = last[XCHUNKCOUNT][YCHUNKCOUNT].getGreen();
				int bLAST = last[XCHUNKCOUNT][YCHUNKCOUNT].getBlue();
				int rDIF = Math.abs(rLAST-r);
				int gDIF = Math.abs(gLAST-g);
				int bDIF = Math.abs(bLAST-b);
				if (rDIF > tolerence || gDIF > tolerence || bDIF >tolerence)
				{
					if (r < 255-101)
						r += 100;
					else
						r = 255;
					ga.setColor(new Color(rDIF,gDIF,bDIF));
					//ga.setColor(Color.red);
					ga.fillRect(xS, yS, SIZE, SIZE);
					ga.setColor(Color.GREEN);
					ga.drawLine(xE, yS, xS, yE);
					ga.setColor(Color.RED);
					ga.drawLine(xS, yS, xE, yE);
					re[XCHUNKCOUNT][YCHUNKCOUNT] = true;
				}
				else
				{
					re[XCHUNKCOUNT][YCHUNKCOUNT] = false;
					ga.setColor(curr[XCHUNKCOUNT][YCHUNKCOUNT]);
					ga.fillRect(xS, yS, SIZE, SIZE);
				}
					
				
				//System.out.println("DRAW");
				TOTALCOUNT++;
				YCHUNKCOUNT++;
			}
			XCHUNKCOUNT++;
		}
		last = curr;
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("IMAGE PROCESS SELFCHECKING");
		System.out.print("TOTAL: " + TOTALCOUNT + "\nX: " + XCHUNKCOUNT + "\nY: " + YCHUNKCOUNT + "\nX*Y: " + (XCHUNKCOUNT*YCHUNKCOUNT) + "\nX*Y ?= TOTAL: " );
		boolean passAll = true;
		if (XCHUNKCOUNT * YCHUNKCOUNT == TOTALCOUNT)
			System.out.println("PASS");
		else
		{
			System.out.println("FAIL");
			passAll = false;
		}
		System.out.print("X(PREDICTED): " + XCHUNKS + "\nY(PREDICTED): " + YCHUNKS + "\nX=XPREDICTED && Y=YPREDICTED: ");
		if (XCHUNKS == XCHUNKCOUNT && YCHUNKCOUNT == YCHUNKS)
			System.out.println("PASS");
		else
		{
			System.out.println("FAIL");
			passAll = false;
		}
		if (passAll)
			
		{
			System.out.println("SELFCHECK: PASS");
			System.out.println("-----------------------------------------------------------------------------------------");
			return re;
		}
		else
		{
			System.out.println("SELFCHECK: FAIL");
			System.out.println("-----------------------------------------------------------------------------------------");
			return null;
		}
	}
	public boolean[][] detectNoRecolor(BufferedImage bi)
	{
		Graphics ga = bi.getGraphics();
		ga.setColor(Color.BLACK);
		int XCHUNKS = (bi.getWidth()-1)/SIZE;
		int YCHUNKS = (bi.getHeight()-1)/SIZE;
		curr = new Color[XCHUNKS][YCHUNKS];
		boolean[][] re = new boolean[XCHUNKS][YCHUNKS];
		int YCHUNKCOUNT = 0;
		int XCHUNKCOUNT = 0;
		int TOTALCOUNT= 0;
		System.out.println(XCHUNKS + "  " + YCHUNKS);
		for (int xS = 0; xS <= bi.getWidth()-SIZE-1; xS += SIZE)
		{
			YCHUNKCOUNT = 0;
			for (int yS = 0; yS <= bi.getHeight()-SIZE-1; yS += SIZE)
			{
				
				int xE = xS + SIZE;
				int yE = yS + SIZE;
				int count,r,g,b;
				count = r = g = b = 0;
				for (int x = xS; x <= xE; x++)
					for (int y = yS; y <= yE; y++)
					{
						Color c = new Color(bi.getRGB(x, y));
						count++;
						r += c.getRed();
						g += c.getGreen();
						b += c.getBlue();
					}
				r = r/count;
				g = g/count;
				b = b/count;
				if (last == null)
					last = curr;
				curr[XCHUNKCOUNT][YCHUNKCOUNT] = new Color(r,g,b);
				int rLAST = last[XCHUNKCOUNT][YCHUNKCOUNT].getRed();
				int gLAST = last[XCHUNKCOUNT][YCHUNKCOUNT].getGreen();
				int bLAST = last[XCHUNKCOUNT][YCHUNKCOUNT].getBlue();
				int rDIF = Math.abs(rLAST-r);
				int gDIF = Math.abs(gLAST-g);
				int bDIF = Math.abs(bLAST-b);
				if (rDIF > tolerence || gDIF > tolerence || bDIF >tolerence)
				{
					re[XCHUNKCOUNT][YCHUNKCOUNT] = true;
				}
				else
				{
					re[XCHUNKCOUNT][YCHUNKCOUNT] = false;
				}
					
				
				//System.out.println("DRAW");
				TOTALCOUNT++;
				YCHUNKCOUNT++;
			}
			XCHUNKCOUNT++;
		}
		last = curr;
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("Total: " + TOTALCOUNT + "\nX: " + XCHUNKCOUNT + "\nY: " + YCHUNKCOUNT + "\nX*Y: " + (XCHUNKCOUNT*YCHUNKCOUNT) + "\nSELFCHECK::" );
		boolean passAll = true;
		if (XCHUNKCOUNT * YCHUNKCOUNT == TOTALCOUNT)
			System.out.println("PASS");
		else
		{
			System.err.println("FAIL");
			passAll = false;
		}
		System.out.println("X(PREDICTED): " + XCHUNKS + "\nY(PREDICTED): " + YCHUNKS + "\nX=XPREDICTED && Y=YPREDICTED");
		if (XCHUNKS == XCHUNKCOUNT && YCHUNKCOUNT == YCHUNKS)
			System.out.println("PASS");
		else
		{
			System.err.println("FAIL");
			passAll = false;
		}
		if (passAll)
			return re;
		else
			return null;
	}
}
