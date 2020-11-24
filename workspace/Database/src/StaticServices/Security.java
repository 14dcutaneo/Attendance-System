package StaticServices;

public class Security 
{
	public static String encryptLossy(String str)
	{
		String s1 = "";
		
		for (int i = 0; i < str.length(); i++)
		{
			s1 += (int) (str.charAt(i) + 1) *2;
		}
		
		return s1;
		
	}
}
