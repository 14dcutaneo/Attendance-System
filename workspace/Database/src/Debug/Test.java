package Debug;

public class Test 
{
	public static void main(String[] args)
	{
		System.out.println(new Objects.DateTime("2017/07/08", "HH:MM:SS").toString());
		new Objects.DateTime("2017\\07/08", "HH:MM:SS");
		new Objects.DateTime("2017\\07/08", "HH:MM:SSLOL");
	}
}
