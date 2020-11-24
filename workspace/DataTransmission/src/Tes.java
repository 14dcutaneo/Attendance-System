
public class Tes 
{
	public static void main(String[] args)
	{
		System.out.println(addCheck(toBin("Hi!")));
		System.out.println(toBin("Hi!"));
		/*
		for (int i = (int)'!'; i <= (int)'!'; i++ )
		{
			String str = "" + (char) i;
			System.out.println(toBin(str) + " " + toBin(str).length());
		}*/
		
	}
	
	public static String addCheck(String str)
	{
		//Check if valid binary string:
		if ((str.length())%8 != 0)
		{
			new Errors.AMDNCError(Errors.EHAND.Type.OBJ_INCAPABLE, "public static String addCheck(String str)", "The passed string is not divisble by 8.", "Binary strings should be divisible by 8 and only contain ones and zeros.");
			return null;
		}
		for (int i = 0; i< str.length(); i++)
		{
			if (str.charAt(i) != '0' && str.charAt(i) != '1')
			{
				new Errors.AMDNCError(Errors.EHAND.Type.OBJ_INCAPABLE, "public static String addCheck(String str)", "The passed string is not a valid binary number.", "Binary strings should be divisible by 8 and only contain ones and zeros.");
				return null;
			}
		}
		String returned = "";
		boolean parodyOfParody = false;
		for (int i = 0; i < str.length(); i += 8)
		{
			boolean parody = false;
			for (int i1 = 0; i1 < 8; i1++)
			{
				System.out.print(str.charAt(i+i1));
				if (str.charAt(i+i1) == '1')
				{
					parody = !parody;
				}
				returned += str.charAt(i+i1);
			}
			System.out.print(" " + (parody ? "1" : "0"));
			returned += (parody ? "1" : "0");
			if (parody) parodyOfParody = !parodyOfParody;
			System.out.println();
			
		}
		System.out.println(parodyOfParody ? "1" : "0");
		returned += (parodyOfParody ? "1" : "0");
		return returned;
	}
	
	public static String toBin(String str)
	{
		String bin = "";
		for (int i = 0; i < str.length(); i++)
		{
			String T = Integer.toBinaryString(str.charAt(i));
			while (T.length() < 8)
			{
				T = "0" + T;
			}
			bin += T;
		}
		return bin;
	}
}
