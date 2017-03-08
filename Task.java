import java.util.*;
import java.io.*;

public class Task implements Serializable{
	public Task(Type type, GregorianCalendar startDT, GregorianCalendar endDT, String name, String color) {
		this.type = type;
		this.name = name;
		this.color = color;
		startDateTime = startDT;
		endDateTime = endDT;
		done = false;
	}

	public Task(Type type, GregorianCalendar startDT, GregorianCalendar endDT, String name) {
		this.type = type;
		this.name = name;
		this.color = "white";
		startDateTime = startDT;
		endDateTime = endDT;
		done = false;
	}

	public Type getType() {
		return type;
	}

	public String getStrType() {
		return type.toString();
	}

	public String getStrColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public void setDone(boolean truthVal) {
		done = truthVal;
	}

	public boolean getDone() {
		return done;
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

	public boolean findEventMY(int year, int month) {
		if(month == getMonth() && year == getYear())
			return true;
		return false;
	}

	public boolean findEvent(GregorianCalendar date, int viewType) {
		if((date.get(GregorianCalendar.MONTH) == getMonth() &&
			date.get(GregorianCalendar.DATE) == getDay() &&
			date.get(GregorianCalendar.YEAR) == getYear()))
			if(viewType == 3)
				return true;
			else if(viewType == 1){
				if(type == Type.EVENT)
					return true;
			}
			else {
				if(type == Type.TO_DO)
					return true;
			}

		return false;
	}

	public boolean checkOverlap(Task cmpDT) {
		int baseMinStart = (getStartHour() * 60) + getStartMinute();
		int baseMinEnd = (getEndHour() * 60) + getEndMinute();
		int cmpMinStart = (cmpDT.getStartHour() * 60) + cmpDT.getStartMinute();
		int cmpMinEnd = (cmpDT.getEndHour() * 60) + cmpDT.getEndMinute();

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
	private boolean done;
}