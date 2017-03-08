import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
public class DayPanel extends PanelFactory {
	public DayPanel(CalendarController cc) {
		controller = cc;
	}

	protected JPanel makePanel() {
		initComponents();
		addComponents();
		setPanel();
		setBoundsDP();
		setListeners();
		updateDay();
		//addListeners();
		return dayPanel;
	}

	private void initComponents() {
		btnDelete = new JButton("DELETE DONE TO-DOS");
		modelTimeDay = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		timeDay = new JTable(modelTimeDay);
		scrollDay = new JScrollPane(timeDay);
		dayPanel = new JPanel(null);
		toDoLeft = new JLabel(""+controller.getToDo());
		
		setTimeTable();
		int j = 0;
		for(int i = 0; i < 48; i++){
			String time = Integer.toString(j);
			if(j < 10)
				time = "0" + time;
			if(i % 2 == 0){
				modelTimeDay.setValueAt(time,i,0);
				j++;
			}
		}
	}

	private void addComponents() {
		dayPanel.add(scrollDay);
		dayPanel.add(btnDelete);
		dayPanel.add(toDoLeft);
	}

	private void setTimeTable() {
		timeDay.setRowHeight(40);
		modelTimeDay.addColumn("Time");
		modelTimeDay.addColumn("Event/Task");
		modelTimeDay.setColumnCount(2);
		modelTimeDay.setRowCount(48);
		timeDay.getTableHeader().setResizingAllowed(false);
		timeDay.getTableHeader().setReorderingAllowed(false);
		timeDay.setColumnSelectionAllowed(true);
		timeDay.setRowSelectionAllowed(true);
		timeDay.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		timeDay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		timeDay.getColumnModel().getColumn(0).setPreferredWidth(120);
		timeDay.getColumnModel().getColumn(1).setPreferredWidth(350);
	}

	private void setPanel() {
		dayPanel.setBackground(Color.WHITE);
		dayPanel.setName("DAY");
	}

	private void setBoundsDP() {
		dayPanel.setBounds(300,100,595,795);
		scrollDay.setBounds(50,75, 500, 475);
		btnDelete.setBounds(300, 20, 250,40);
		toDoLeft.setBounds(50, 20, 100, 40);
	}

	private void setListeners() {
		timeDay.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				clickToDo();
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteDoneToDo();
			}
		});
	}

	public void clickToDo() {
		int dayCol = timeDay.getSelectedColumn();
		int dayRow = timeDay.getSelectedRow();
		try {
			String oldDay = (String)modelTimeDay.getValueAt(dayRow, dayCol);
			String actualTask = oldDay.contains("text-decoration:line-through;") 
								? oldDay.replaceAll("<html><font color='green' style='text-decoration:line-through;'>", "")
								: oldDay.replaceAll("<html><font color='green'>", "");
			actualTask = actualTask.replaceAll("</font></html>", "");
			Iterator dayIterator = controller.getTasks();
			if (dayIterator.hasNext()) {
				for (Iterator it=dayIterator; it.hasNext();) {
					Task t = (Task)it.next();
					int j = 0;
					if (t.getName().equals(actualTask) && t.getStrType().equals("to do")) {
						oldDay = oldDay.contains("text-decoration:line-through;")
								 ? oldDay.replaceAll("<font color='green' style='text-decoration:line-through;'>", "<font color='green'>")
								 : oldDay.replaceAll("<font color='green'>", "<font color='green' style='text-decoration:line-through;'>");
						modelTimeDay.setValueAt(oldDay, dayRow, dayCol);
						t.setDone(!t.getDone());
					}
				}
			}
			else {
				System.out.println("No");
			}
			toDoLeft.setText(""+controller.getToDo());
		} catch(Exception e){}
	}

	public void deleteDoneToDo() {
		controller.deleteTD();
			toDoLeft.setText(""+controller.getToDo());
		
	}

	private void updateDay() {
		Iterator events = controller.getTasks();
		if(!events.hasNext()){
			if(controller.getView() == 1)
				modelTimeDay.setValueAt("No events for today",0,1);
			else if(controller.getView() == 2)
				modelTimeDay.setValueAt("No tasks for today",0,1);
			else modelTimeDay.setValueAt("No events/tasks for today",0,1);
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
					modelTimeDay.setValueAt(eventName,l + (t.getStartHour()*2),1);
			}
		}
	}

	private JPanel dayPanel;
	private JButton btnDelete;
	private JLabel toDoLeft;
	private JScrollPane scrollDay;
	private JTable timeDay;
	private DefaultTableModel modelTimeDay;
	private CalendarController controller;
}
