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
		updateDay();
		//addListeners();
		return dayPanel;
	}

	private void initComponents() {
		btnDelete = new JButton("DELETE");
		modelTimeDay = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
		timeDay = new JTable(modelTimeDay);
		scrollDay = new JScrollPane(timeDay);
		dayPanel = new JPanel(null);
		
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
		scrollDay.setBounds(50,50, 500, 530);
		//btnDelete.setBounds(0, 0, 100,100);
	}

	private void updateDay() {
		Iterator events = controller.getEvents();
		System.out.println("HALLOOOOO");
		if(!events.hasNext()){
			modelTimeDay.setValueAt("No events/tasks for today",0,1);
		} else {
			for (Iterator it = events; it.hasNext();) {
				Task t = (Task)it.next();
			}
		}
	}

	private JPanel dayPanel;
	private JButton btnDelete;
	private JScrollPane scrollDay;
	private JTable timeDay;
	private DefaultTableModel modelTimeDay;
	private CalendarController controller;
}
