import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Event 
{
	Date t;
	int TimeStart;
	DateFormat day = new SimpleDateFormat("yyyyMMdd");
	DateFormat dayOut = new SimpleDateFormat("dd/MM");
	DateFormat time = new SimpleDateFormat("HHmm");
	StudentMarker[] stM;
	File f;
	StudentHandler stHand;
	Event e;
	
	String saveAs;
	
	//StudentMarker: Contains system ID for student and a int marker
	// 0 = Absent unexcused
			// 1 = Absent excused
			// 2 = Present
			// 3 = Late
	
	public Event(int TimeStart,StudentHandler stHand) throws IOException
	{
		
		this.stHand = stHand;
		t = new Date();
		this.TimeStart = TimeStart;
		
		saveAs = day.format(t);
		
		
		
		f = new File(System.getenv("APPDATA") + "/amdnc/saves/EVENT." + saveAs + ".amdnc");
		
		
		
		if(!f.exists())
			f.createNewFile();
		else
		{
			System.out.println("Event.java public Event(int TimeStart,StudentHandler stHand): Previous found");
			 e = Event.load(saveAs, stHand);
		}
		
		Student[] st = stHand.getAllStudents();
		
		stM = new StudentMarker[st.length];
		
		for (int i = 0; i < st.length; i++)
		{
			stM[i] = new StudentMarker();
			stM[i].id = st[i].assignmentID;
			stM[i].status = 0;
		}
		
		save();
		
	}
	
	public Event(ArrayList<StudentMarker> hello, String Date, StudentHandler stHand) throws IOException
	{
		saveAs = Date;
		this.stHand = stHand;
		t = new Date();
		int Day,Month,Year;
		Day = Integer.parseInt(Date.substring(4,6));
		Year = Integer.parseInt(Date.substring(0,4));
		Month = Integer.parseInt(Date.substring(6,8));
		
		//System.out.println(Day + " / " + Month + " / " + Year);
		
		t.setDate(Day);
		t.setYear(Year);
		t.setMonth(Month);
		
		//System.out.println(t.getYear());
		
		f = new File(System.getenv("APPDATA") + "/amdnc/saves/EVENT." + saveAs + ".amdnc");
		
		stM = new StudentMarker[hello.size()];
		for (int i = 0; i<hello.size(); i++)
		{
			stM[i] = (StudentMarker)hello.get(i);
		}
		save();
	}
	
	public Event(ArrayList<StudentMarker> hello, String Date, StudentHandler stHand, int Index) throws IOException
	{
		saveAs = Date+"."+Index;
		this.stHand = stHand;
		t = new Date();
		int Day,Month,Year;
		Day = Integer.parseInt(Date.substring(4,6));
		Year = Integer.parseInt(Date.substring(0,4));
		Month = Integer.parseInt(Date.substring(6,8));
		
		//System.out.println(Day + " / " + Month + " / " + Year);
		
		t.setDate(Day);
		t.setYear(Year);
		t.setMonth(Month);
		
		//System.out.println(t.getYear());
		
		f = new File(System.getenv("APPDATA") + "/amdnc/saves/EVENT." + saveAs + ".amdnc");
		
		stM = new StudentMarker[hello.size()];
		for (int i = 0; i<hello.size(); i++)
		{
			stM[i] = (StudentMarker)hello.get(i);
		}
		save();
	}
	
	public void Patch() throws IOException
	{	//stM = array of StudentMarkers
		Student[] studs = stHand.getAllStudents();
		for (int i = 0; i < studs.length; i++)
		{
			boolean flag = false;
			for (int i1 = 0; i1 < stM.length; i1++)
			{
				if (stM[i1].id == studs[i].assignmentID)
					flag = true;
			}
			
			if (!flag)
			{
				stM = addToSTM (new StudentMarker(studs[i].assignmentID,0));
				System.out.println("Event.java public void Patch(): Added assignment ID :|:" + studs[i].assignmentID);
			}
			
		}
		this.save();
	}
	
	public StudentMarker[] addToSTM(StudentMarker adder)
	{
		StudentMarker[] sm = new StudentMarker[stM.length + 1];
		for (int i = 0; i < stM.length; i++)
		{
			sm[i] = stM[i];
		}
		sm[stM.length] = adder;
		return sm;
	}
	
	public boolean overrideStatus (long ID, int Set)
	{
		for (int i = 0; i < stM.length; i++)
		{
			if (stM[i].id == ID)
			{
				stM[i].status = Set;
				return true;
			}
		}
		return false;
	}
	
	public StudentMarker getMarker(int index)
	{
		return stM[index];
	}
	
	// 0 = Absent unexcused
				// 1 = Absent excused
				// 2 = Present
				// 3 = Late
	
	public int Checkin (long ID)
	{
		
		for (int i = 0; i < stM.length; i++)
		{
			if (stM[i].id == ID)
			{
				if (stM[i].status != 0)
					return -19;
				if (Integer.parseInt(time.format(new Date())) > TimeStart)
					stM[i].status = 3;
				else
					stM[i].status = 2;
				return stM[i].status;
			}
		}
		return -1;
	}
	
	public String getFriendlyDate()
	{
		String Date = saveAs;
		int Day,Month,Year;
		Day = Integer.parseInt(Date.substring(4,6));
		Year = Integer.parseInt(Date.substring(0,4));
		Month = Integer.parseInt(Date.substring(6,8));
		
		return ( Day + "/" + Month + "/" + Year);
	}
	
	public static Event load(String date, StudentHandler stHand) throws IOException
	{
		System.out.println("Loading event " + date);
		File d = new File(System.getenv("APPDATA") + "/amdnc/saves/EVENT." + date + ".amdnc");
		
		if (!d.exists())
		{
			return null;
		}
		
		BufferedReader br = new BufferedReader(new FileReader(d));
		
		ArrayList<StudentMarker> arM = new ArrayList<StudentMarker>();
		
		String read = "";
		
		while ((read = br.readLine()) != null)
		{
			if (!read.equals(Main.secure.securityCheckpoint))
			{
				try{
				int id = Integer.parseInt(read.substring(0, read.length()-2));
				int type = Integer.parseInt(""+ read.charAt(read.length()-1));
				arM.add(new StudentMarker(id,type));
				}
				catch(Exception e)
				{}
			}
		}
		
		Event r = new Event(arM,date,stHand);
		
		if (r!= null)
			System.out.println("Event.java public static Event load(String date, StudentHandler stHand): Success in creating new event!");
		else
			System.out.println("Event.java public static Event load(String date, StudentHandler stHand): Nope");
		
		
		return r;
	}
	
	public static Event load(String date, StudentHandler stHand, int Index) throws IOException
	{
		File d = new File(System.getenv("APPDATA") + "/amdnc/saves/EVENT." + date + "." + Index  +  ".amdnc");
		
		if (!d.exists())
		{
			return null;
		}
		
		BufferedReader br = new BufferedReader(new FileReader(d));
		
		ArrayList<StudentMarker> arM = new ArrayList<StudentMarker>();
		
		String read = "";
		
		while ((read = br.readLine()) != null)
		{
			if (!read.equals(Main.secure.securityCheckpoint))
			{
				int id = Integer.parseInt(read.substring(0, read.length()-2));
				int type = Integer.parseInt(""+ read.charAt(read.length()-1));
				arM.add(new StudentMarker(id,type));
			}
		}
		
		
		return new Event(arM,date,stHand,Index);
	}
	
	public void save() throws IOException
	{
		System.out.println("Event.java public void save()");
		//f = new File("EVENT." + day.format(t) + ".amdnc");
		if (!f.exists())
		{
			f.createNewFile();
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		for (int i = 0; i < stM.length; i++)
		{
			System.out.println("Event.java public void save() saving StudentMarker.object: " + stM[i]);
			bw.write(stM[i].toString());
			bw.newLine();
			bw.flush();
		}
		bw.write(Main.secure.securityCheckpoint);
		bw.flush();
		bw.close();
	}
	
	
	//StudentMarker: Contains system ID for student and a int marker
	// 0 = Absent unexcused
				// 1 = Absent excused
				// 2 = Present
				// 3 = Late
	
	public String[] beautify()
	{
		System.out.println("Event.java public String[] beautify()");
		String[] st = new String[stM.length];
		for (int i = 0; i< stM.length; i++)
		{
			try{
			Student s = 
					stHand.
					getStudent(
							stM[i].id);
			if (s != null)
			{
				String name = s.lFirst + " " + s.lLast;
				String type = "<Error In Type Decomposition>";
				
				if (stM[i].status == 0) type = "Absent (Unexcused)";
				if (stM[i].status == 1) type = "Absent (Excused)";
				if (stM[i].status == 2) type = "Present";
				if (stM[i].status == 3) type = "Late";
				
				while (name.length() < 30)
				{
					System.out.println(name.length());
					name += " ";
				}
				
				
				st[i] = name + type;
			}
			else
				st[i] = "Event.java public String[] beautify() Error :: not in stHand";
			}
			catch(Exception c)
			{c.printStackTrace();}
			//System.out.println(stM[i].id);
			
		}
		
		return st;
	}
}
