package Errors;

import java.util.Date;
public class AMDNCError 
{
	
	protected String errorLocation;
	protected String errorShortDescription;
	protected String miscInfo = null;
	protected EHAND.Type type;
	protected int EID;
	private int length = 0;
	private String[] builtStrings;
	private String timeThrown = new Date().toString();
	
	/*
	new Errors.AMDNCError(
	Errors.EHAND.Type.THROWN, //TYPE
			""//CLASS
				+ ""//METHOD
				+ ""//<<EXTRA>>
			, "" //DESCRIPTION
				);
	 */
	
	
	public AMDNCError(EHAND.Type t, String erLoc, String desc)
	{
		
		type = t;
		errorLocation = erLoc;
		errorShortDescription = desc;
		EID = EHAND.addError(this);
		buildStrings();
		printError();
	}
	public AMDNCError(EHAND.Type t, String erLoc, String desc, String miscInfo)
	{
		type = t;
		errorLocation = erLoc;
		errorShortDescription = desc;
		this.miscInfo = miscInfo;
		EID = EHAND.addError(this);
		buildStrings();
		printError();
	}
	
	public String[] get()
	{
		return builtStrings;
	}
	private void buildStrings()
	{
		if (miscInfo != null)
			builtStrings = new String[6];
		else
			builtStrings = new String[5];
		
		builtStrings[1] = "E" + EID + " " + type + " ERROR THROWN AT " + timeThrown;
		builtStrings[2] = "E" + EID + " " + errorLocation;
		builtStrings[3] = "E" + EID + " " + errorShortDescription;
		
		if (miscInfo != null)
			builtStrings[4] = "E" + EID + " " + miscInfo;
		
		for (String s: builtStrings)
			if (s != null && s.length() > length)
				length = s.length();
		
		String buildStars = "";
		
		String EID1 = "E" + EID + " ";
		
		buildStars += EID1;
		
		for (int i = EID1.length(); i < length; i++)
			buildStars += "-";
		
		
		
		builtStrings[0] = builtStrings[builtStrings.length - 1] = buildStars;
		
	}
	
	public void printError()
	{
		for (String s : builtStrings)
			System.err.println(s);
	}

}
