import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayPhotos extends Applet
{
	BufferedImage dis;
	Photo getPhotos;
	Graphics p;
	Color[][] last;
	Color[][] curr;
	int tolerence = 50;
	int SIZE = 20;
	calcPhoto cp;
	public DisplayPhotos() {
	}
	public void init()
	{
		
		cp = new calcPhoto();
		getPhotos = new Photo(750,this);
		getPhotos.start();
		setSize(1024,768);
	}
	public void paint(Graphics ga)
	{
		ga.drawImage(getPhotos.latest, 0, 0, null);
		cp.detectRecolor(getPhotos.latest);
		ga.drawImage(getPhotos.latest, 0, 0, null);
	}
	public void run()
	{
		p.drawImage(getPhotos.latest, 0, 0, null);
	}
}
