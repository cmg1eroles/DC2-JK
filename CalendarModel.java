import java.util.*;

public class CalendarModel {//extends Observer{
	public CalendarModel() {
		allTasks = store.read();
	}
	public Iterator getTasks(GregorianCalendar day, int viewType, boolean sort) {
		ArrayList<Task> taskMonthYear = new ArrayList<Task>();
		if(viewType == 0)
			return taskMonthYear.iterator();
		for(int i=0;i<allTasks.size();i++) {
			if(allTasks.get(i).findEvent(day,viewType)) {
					taskMonthYear.add(allTasks.get(i));
				
			}
		}
		if(sort){
			System.out.println("sort");
			taskMonthYear = sortTasks(taskMonthYear);
			for(int k = 0; k < taskMonthYear.size(); k++)
				System.out.println(taskMonthYear.get(k).getName());
		}
		return taskMonthYear.iterator();
	}

	public String addTask(Task nTask) {
		boolean noOverlap = true;
		for(int i=0;i<allTasks.size() && noOverlap;i++) {
			if (allTasks.get(i).checkOverlap(nTask))
				noOverlap = false;
		}
		if (noOverlap) {
			allTasks.add(nTask);
			return "Successfully added!";
		}
		else
			return "Sorry! Todo or Event already there!";
		//setState();
	}

	public void saveEvents(){
		store.write(allTasks);
	}

	public void deleteTD() {
		for(int i=0;i<allTasks.size();i++) {
			if(allTasks.get(i).getDone())
				allTasks.remove(i);
		}
	}

	public int getToDoLeft() {
		int left = 0;
		for(int i=0;i<allTasks.size();i++) {
			if(allTasks.get(i).getType() == Type.TO_DO &&
				!allTasks.get(i).getDone())
					left++;
		}
		return left;
	}

	private ArrayList<Task> sortTasks(ArrayList<Task> toSort) {
		Collections.sort(toSort);
		return toSort;

	}

	private ArrayList<Task> allTasks = new ArrayList<Task>();
	private CalendarController controller;
	private Storage store = new Storage();
}