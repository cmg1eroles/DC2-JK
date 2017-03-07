import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.reflect.Field;

public class CalendarController {//extends Observer {
	private CalendarModel model;
	private CalendarView view;
	private int monthToday, yearToday, yearBound;

	public CalendarController(CalendarView screen, CalendarModel shape) {
		GregorianCalendar cal = new GregorianCalendar();
		model = shape;
		view = screen;
		monthToday = cal.get(GregorianCalendar.MONTH);
		yearToday = cal.get(GregorianCalendar.YEAR);
		yearBound = yearToday;
	}

/*	public void showEvents(Iterator event) {
		System.out.println("halooooo");
		view.refreshCalendar(monthToday, yearToday,event);
	}*/

	public Iterator getEvents(){
		GregorianCalendar cal = new GregorianCalendar(yearToday, 
				monthToday,Integer.parseInt(view.getDaylbl()));
		return model.getTasks(cal);
	}

	public int getYear() {
		return yearToday;
	}

	public int getMonth() {
		return monthToday;
	}

	public int getYearBound() {
		return yearBound;
	}

	public void changePanel(ActionEvent e) {
		JButton b = (JButton)e.getSource();
		JPanel nPanel = PanelFactory.determine(b.getText(),CalendarController.this);
		view.addPaneltoPane(nPanel);
	}

	class btnPrev_Action implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (monthToday == 0) {
				monthToday = 11;
				yearToday -= 1;
			} else {
				monthToday -= 1;
			}
			//model.setState();
			view.refreshCalendar(monthToday, yearToday);
		}
	}

	class btnNext_Action implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (monthToday == 11) {
				monthToday = 0;
				yearToday += 1;
			} else {
				monthToday += 1;
			}
			//model.setState();
			view.refreshCalendar(monthToday, yearToday);
		}
	}
	class cmbYear_Action implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (view.getCmbYr() != null) {
				String currYear = view.getCmbYr().toString();
				yearToday = Integer.parseInt(currYear);
				//model.setState();
				view.refreshCalendar(monthToday, yearToday);
			}
		}
	}

	class btnToday_Action implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			String dteToday = view.getDateToday();
			view.setDate(dteToday);
			view.refreshCalendar(monthToday, yearToday);
		}
	}

	class btnView_Action implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			changePanel(e);
		}
	}



	public void addNewTask(String name, String hourStart, String minStart,
						   String hourEnd, String minEnd, Type type) 
	{
		String day = view.getDaylbl(),
			   month = view.getMonthlbl(), 
			   year = view.getCmbYr().toString();
		int equivMthNum = 0;
		int startTotalMinutes = (Integer.parseInt(hourStart) * 60) + Integer.parseInt(minStart);
		int endTotalMinutes = (Integer.parseInt(hourEnd) * 60) + Integer.parseInt(minEnd);

		for(Months m: Months.values()) {
			if(m.toString().equals(month))
				equivMthNum = m.toInt();
		}

		GregorianCalendar testStartDate = new GregorianCalendar(Integer.parseInt(year),equivMthNum,
								Integer.parseInt(day), Integer.parseInt(hourStart), Integer.parseInt(minStart));
		GregorianCalendar testEndDate = new GregorianCalendar(Integer.parseInt(year),equivMthNum,
								Integer.parseInt(day), Integer.parseInt(hourEnd), Integer.parseInt(minEnd));
		
		Task newTask = null;
		if (type == Type.EVENT)
			newTask = new Task(Type.EVENT, testStartDate, testEndDate, name, "blue");
		else if (type == Type.TO_DO)
			newTask = new Task(Type.TO_DO, testStartDate, testEndDate, name, "green");

		//System.out.println("End Min = " + endTotalMinutes + " Start Min = " + startTotalMinutes);
		if (endTotalMinutes > startTotalMinutes)
			model.addTask(newTask);
		else
			System.out.println("Sorry invalid time!");
	}


/*	class btnEvent_Action implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (view.getDaylbl() != "null" && view.getDaylbl() != "") {
				String day = view.getDaylbl(),
					   month = view.getMonthlbl(), 
					   year = view.getCmbYr().toString();
				
				int equivMthNum = 0;
				for(Months m: Months.values()) {
					if(m.toString().equals(month))
						equivMthNum = m.toInt();
				}
				String currColor = view.getColor();

				GregorianCalendar selectedDate = new GregorianCalendar(Integer.parseInt(year),equivMthNum,Integer.parseInt(day));
				Events ev = new Events(selectedDate, view.getTextEvent(), currColor);
				model.addEvent(ev);
				String event = "<html>" + day + " <font color='" + currColor + "'>" + view.getTextEvent() + "</font></html>";
				view.setCell(event);
			}
		}
	}


	class btnImport_Action implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			String fileName = view.getFileName();
			ImportFile importer = null;
			ArrayList<Events> newEvents = null;
			String ext = fileName.substring(fileName.length()-3, fileName.length());
			System.out.println(ext);
			
			switch(ext) {
				case "csv": importer = new CSV();
				newEvents = importer.imp(view.getFileName());
					break;
				case "psv": importer = new PSV();
				newEvents = importer.imp(view.getFileName());
					break;
				default: newEvents = null;
			}

			if(newEvents != null) {
				model.addEvents(newEvents);
			}

				view.refreshCalendar(monthToday, yearToday);
			
		}
	}*/
}
