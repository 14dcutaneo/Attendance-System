package Errors;

import java.util.ArrayList;

import Errors.EHAND.Type;

public class EHAND 
{
	private static ArrayList<AMDNCError> Errors = new ArrayList<>();
	public static enum Type {FATAL, OBJ_INCAPABLE, WARNING, THROWN, NOTICE}
	public static int addError(AMDNCError er)
	{
		Errors.add(er);
		return Errors.size()-1;
	}
	
	public static AMDNCError[] getErrors()
	{
		AMDNCError[] er = new AMDNCError[Errors.size()]; 
		Errors.toArray(er);
		return er;
	}
}
