import java.util.*;

public class CalendarModel {//extends Observer{
	public Iterator getEvents(int year, int month) {
		ArrayList<Events> eventMonthYear = new ArrayList<Events>();
		for(int i=0;i<events.size();i++) {
			if(events.get(i).findEventMY(year, month)){
				eventMonthYear.add(events.get(i));
			}
		}
		return eventMonthYear.iterator();
	}

	public void addEvent(Events event) {
		events.add(event);
		//setState();
	}

	public void addEvents(ArrayList<Events> event) {
		events.addAll(event);
		//setState();
	}

/*	public void attachIn(Observer newUser) {
		observers.add(newUser);
	}

	public void setState() {
		for(Observer obs: observers) {
			obs.update();
		}
	}

	public Iterator getState(GregorianCalendar date) {
		ArrayList<Events> evt = new ArrayList<Events>();
		for(int i=0;i<events.size();i++) {
			if(events.get(i).findEvent(date)){
				evt.add(events.get(i));
			}
		}
		//System.out.println(evt.toString());
		if (evt.size() == 0)
			return null;
		return evt.iterator();
	}*/


	private ArrayList<Events> events = new ArrayList<Events>();
	//private ArrayList<Observer> observers = new ArrayList<Observer>();
	private CalendarController controller;
}