import java.util.*;

public class Task {
	public Task(Type type, GregorianCalendar startDT, GregorianCalendar endDT, String name, String color) {
		this.type = type;
		this.name = name;
		this.name = color;
		startDateTime = startDT;
		endDateTime = endDT;
	}

	public Type getStrType() {
		return type;
	}

	public String getStrColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public GregorianCalendar getStartDT() {
		return startDateTime;
	}

	public GregorianCalendar getEndDT() {
		return endDateTime;
	}

	public int getMonth() {
		return startDateTime.get(GregorianCalendar.MONTH);
	}

	public int getDay() {
		return startDateTime.get(GregorianCalendar.DATE);
	}

	public int getYear() {
		return startDateTime.get(GregorianCalendar.YEAR);
	}

	public int getStartHour() {
		return startDateTime.get(GregorianCalendar.HOUR_OF_DAY);
	} 

	public int getStartMinute() {
		return startDateTime.get(GregorianCalendar.MINUTE);
	}

	public int getEndHour() {
		return endDateTime.get(GregorianCalendar.HOUR_OF_DAY);
	}

	public int getEndMinute() {
		return endDateTime.get(GregorianCalendar.MINUTE);
	}

	public boolean checkOverlap(Task cmpDT) {
		int baseMinStart = (getStartHour() * 60) + getStartMinute();
		int baseMinEnd = (getEndHour() * 60) + getEndMinute();
		int cmpMinStart = (cmpDT.getStartHour() * 60) + cmpDT.getStartMinute();
		int cmpMinEnd = (cmpDT.getEndHour() * 60) + cmpDT.getEndMinute();

		System.out.println("BMS = " + baseMinStart + "\nBME = " + baseMinEnd);
		System.out.println("CMS = " + cmpMinStart + "\nCME = " + cmpMinEnd);

		if ((cmpDT.getMonth() == getMonth()) &&
			(cmpDT.getDay() == getDay()) &&
			(cmpDT.getYear() == getYear()))
		{
			if (cmpMinStart == baseMinStart && cmpMinEnd == baseMinEnd)
				return true;
			else if (cmpMinStart >= baseMinStart && cmpMinStart < baseMinEnd)
				return true;
			else if (cmpMinEnd > baseMinStart && cmpMinEnd <= baseMinEnd)
				return true;
		}
		return false;
	}	

	private Type type;
	private String name;
	private String color;
	private GregorianCalendar startDateTime;
	private GregorianCalendar endDateTime;
}