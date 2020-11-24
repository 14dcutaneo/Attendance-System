package Objects;

public class DateTime 
{
	protected int datetime = -1;
	public DateTime(String date, String time) // FMT DATE: "YYYY/MM/DD" FMT TIME: "HH:MM:SS" WITH HH IN MILITARY
	{
		
		//**CATCH 1**\\
		
		if
		(
			date.length() != 10 || 
			time.length() != 8
		)
			
		{
			new Errors.AMDNCError(Errors.EHAND.Type.OBJ_INCAPABLE, "DateTime.java DateTime(String date, String time) CATCH 1", "Unable to parse data due to mal"
					+ "formed date or time Variables", "Date.length: " + date.length() + " Time.length: " + time.length() + " Expected: Date.length == 10,"
					+ " Time.length == 8");
			return;
		}
		
		//**CATCH 2**\\
		
		if
		(
			date.charAt(4) != '/' || 
			date.charAt(7) != '/'
		)
		{
			int loc = 4;
			if (date.charAt(4) == '/')
				loc = 7;
			
			String eDesc = date.substring(0, loc);
			eDesc +=  "<<" + date.charAt(loc) + ">>" + date.substring(loc+1);
			
			new Errors.AMDNCError(Errors.EHAND.Type.OBJ_INCAPABLE, "DateTime.java DateTime(String date, String time) CATCH 2", "Unable to parse date due to mal"
					+ "formed date Variable", eDesc);
			return;
		}
	}
}
