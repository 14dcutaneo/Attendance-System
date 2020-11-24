import java.awt.Dimension;
import java.awt.image.BufferedImage;
import com.github.sarxos.webcam.Webcam;
public class Photo extends Thread
{
	long d;
	Webcam wc;
	BufferedImage latest;
	DisplayPhotos ud;
	public Photo(long delay, DisplayPhotos ud)
	{
		d = delay;
		Webcam.setAutoOpenMode(true);
		this.ud = ud;
		wc = Webcam.getDefault();
		wc.setViewSize(new Dimension(1024,768));
	}
	public void run()
	{
		while(true)
		{
			latest = wc.getImage();
			ud.repaint();

			waitDelay();
			
		}
	}
	public void waitDelay()
	{
		long sysStart = System.currentTimeMillis();
		while (System.currentTimeMillis() - sysStart < d);
	}
}
