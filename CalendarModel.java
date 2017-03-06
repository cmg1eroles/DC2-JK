import java.util.*;

public class CalendarModel {//extends Observer{
	public Iterator getTasks(int year, int month) {
		ArrayList<Task> taskMonthYear = new ArrayList<Task>();
		for(int i=0;i<allTasks.size();i++) {
			if(allTasks.get(i).findEventMY(year, month)) {
				taskMonthYear.add(allTasks.get(i));
			}
		}
		return taskMonthYear.iterator();
	}

	public void addTask(Task nTask) {
		boolean noOverlap = true;
		for(int i=0;i<allTasks.size() && noOverlap;i++) {
			if (allTasks.get(i).checkOverlap(nTask))
				noOverlap = false;
		}
		if (noOverlap) {
			allTasks.add(nTask);
			System.out.println("Successfully added! Num = " + allTasks.size());
		}
		else
			System.out.println("Sorry! Todo or Event already there!");
		//setState();
	}

	private ArrayList<Task> allTasks = new ArrayList<Task>();
	private CalendarController controller;
}