package amdncNetAuth;

import java.util.Scanner;

public class Main {

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
	}

	public static String encrypt(String ecr)
	{
		String r = "";
		for (char c : ecr.toCharArray())
		{
			r += (c + 1) * 12 % 6400;
		}
		return r;
	}
}
