import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;

public class DayPanels extends ViewPanels {
	public DayPanels(CalendarController cc) {
		super.controller = cc;
	}
	protected void updatePanel() {
		Iterator events = controller.getTasks(false);
		if(!events.hasNext()){
			if(controller.getView() == 1)
				modelViewTable.setValueAt("No events for today",0,1);
			else if(controller.getView() == 2)
				modelViewTable.setValueAt("No tasks for today",0,1);
			else modelViewTable.setValueAt("No events/tasks for today",0,1);
		} else {
			for (Iterator it = events; it.hasNext();) {
				Task t = (Task)it.next();
				int j = 0;
				String eventName = "<html><font color='" + t.getStrColor() + "'";
				if (t.getDone() && t.getType() == Type.TO_DO)
					eventName += " style='text-decoration:line-through;'";
				eventName += ">" + t.getName() + "</font></html>";
				if(t.getStartMinute() == 30)
					j++;

				int k = ((t.getEndHour() - t.getStartHour()) * 2) + (t.getEndMinute() / 30);

				for(int l = j; l < k; l++)
					modelViewTable.setValueAt(eventName,l + (t.getStartHour()*2),1);
			}
		}
	}

	protected void additionalComponents() {
		int j = 0;
		modelViewTable.setRowCount(48);
		for(int i = 0; i < 48; i++){
			String time = Integer.toString(j);
			if(j < 10)
				time = "0" + time;
			if(i % 2 == 0){
				modelViewTable.setValueAt(time,i,0);
				j++;
			}
		}	
	}
}