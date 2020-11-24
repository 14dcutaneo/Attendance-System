
public class Student 
{
	String lFirst, lLast;
	int ID = 1;
	long assignmentID;
	public Student(String firstName, String lastName, int ID, long assignmentI)
	{
		this.ID = ID;
		lFirst = firstName;
		lLast = lastName;
		assignmentID = assignmentI;
	}
	public Student(String firstName, String lastName, long assignmentI)
	{
		lFirst = firstName;
		lLast = lastName;
		assignmentID = assignmentI;
	}
	
	public String toString()
	{
		String re = assignmentID + "::::" + lFirst + "::::" + lLast + "::::" + ID;
		return re;
	}
	
	
	public static Student getFromString(String s)
	{
		String[] s2 = new String[4];
		int set = 0;
		String tmp = "";
		for (int i = 0 ; i < s.length() ; i ++ )
		{
			if (s.charAt(i) == ':' && s.charAt(i+1) == ':' && s.charAt(i + 2)  == ':' && s.charAt(i+3) == ':' )
			{
				s2[set] = tmp;
				set++; 
				tmp = "";
				i = i+3;
			}
			else
			{
				tmp += s.charAt(i);
			}
				
			if (i == s.length()-1)
			{
				s2[3] = tmp;
			}
		}
		return new Student(s2[1], s2[2], 
				Integer.parseInt(s2[3]), 
				(long)Integer.parseInt(s2[0]));
	}
	
}