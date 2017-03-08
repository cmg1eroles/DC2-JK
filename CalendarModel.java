import java.util.*;

public class CalendarModel {//extends Observer{
	public CalendarModel() {
		allTasks = store.read();
	}
	public Iterator getTasks(GregorianCalendar day, int viewType) {
		ArrayList<Task> taskMonthYear = new ArrayList<Task>();
		if(viewType == 0)
			return taskMonthYear.iterator();
		for(int i=0;i<allTasks.size();i++) {
			if(allTasks.get(i).findEvent(day,viewType)) {
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

	public void saveEvents(){
		store.write(allTasks);
	}

	public void deleteTD() {
		for(int i=0;i<allTasks.size();i++) {
			if(allTasks.get(i).getDone())
				allTasks.remove(i);
		/*	if (allTasks.get(i).getName() == td.getName() &&
				allTasks.get(i).findEvent(td.getStartDT(),2) &&
				allTasks.get(i).findEvent(td.getEndDT(),2)) 
			{
				allTasks.remove(i);
				break;
			}*/
		}
	}

	private ArrayList<Task> allTasks = new ArrayList<Task>();
	private CalendarController controller;
	private Storage store = new Storage();
}