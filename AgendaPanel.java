import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;

public class AgendaPanel extends PanelFactory {
	public AgendaPanel(CalendarController cc){
		controller = cc;
	}
	
	protected JPanel makePanel() {
		// TODO Auto-generated method stub
		initComponents();
		addComponents();
		setPanel();
		setBounds();
		updateAgenda();
		return agendaPanel;
	}
	
	private void initComponents(){
		btnDelete = new JButton("DELETE DONE TO-DOS");
		agendaPanel = new JPanel(null);
		modelAgendaTable = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        agendaTable = new JTable(modelAgendaTable);
		taskscroll = new JScrollPane(agendaTable);
		toDoLeft = new JLabel(""+controller.getToDo());
		setTable();
	}
	
	private void setPanel() {
		agendaPanel.setBackground(Color.WHITE);
	}
	
	private void setTable() {
		agendaTable.setRowHeight(40);
		modelAgendaTable.addColumn("Event/Task");
		modelAgendaTable.setColumnCount(1);
		modelAgendaTable.setRowCount(20);
		agendaTable.getTableHeader().setResizingAllowed(false);
		agendaTable.getTableHeader().setReorderingAllowed(false);
		agendaTable.setColumnSelectionAllowed(true);
		agendaTable.setRowSelectionAllowed(true);
		agendaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		agendaTable.getColumnModel().getColumn(0).setPreferredWidth(120);
	}

	private void addComponents() {
		agendaPanel.add(taskscroll);
		agendaPanel.add(btnDelete);
		agendaPanel.add(toDoLeft);
	}
	private void setBounds(){
		agendaPanel.setBounds(300,100,595,795);
		taskscroll.setBounds(50,75, 500, 475);
		btnDelete.setBounds(300, 20, 250,40);
		toDoLeft.setBounds(50, 20, 100, 40);
	}
	
	private void updateAgenda() {
		/*Iterator events = controller.getTasks();
		if(!events.hasNext()){
			dayAgenda.setText("No events/tasks for today");
			//modelTimeDay.setValueAt("No events/tasks for today",0,1);
		} else {
			dayAgenda.setText("<html>");
			for (Iterator it = events; it.hasNext();) {
				System.out.println("sdasda"+dayAgenda.getText());
				Task t = (Task)it.next();
				//int j = 0;
				String eventName = "<font color ='" + t.getStrColor() + "'>" + 
								t.getName() + "</font>";
				//if(t.getStartMinute() == 30)
				//	j++;
				if(t.getStrType()=="event"){
					dayAgenda.setText(dayAgenda.getText()+((t.getStartHour()<10)? "0":"")+t.getStartHour()+":"+
						((t.getStartMinute()<10)? "0":"")+t.getStartMinute()+" - "+((t.getEndHour()<10)? "0":"")+
						t.getEndHour()+":"+((t.getEndMinute()<10)? "0":"")+t.getEndMinute()+" "+eventName+"<br>");
					System.out.println("event");
				}
				else if(t.getStrType()=="to do"){
					dayAgenda.setText(dayAgenda.getText()+"&emsp"+"&emsp"+"&emsp;"+"&ensp;"+((t.getStartHour()<10)? "0":"")
							+t.getStartHour()+":"+((t.getStartMinute()<10)? "0":"")+t.getStartMinute()+" "+eventName+"<br>");
					System.out.println("to do");
					}
				}
			dayAgenda.setText(dayAgenda.getText()+"</html>");
				//modelTimeDay.setValueAt(eventName,j + (t.getStartHour()*2),1);
			}
		//System.out.println(dayAgenda.getText());
		taskscroll.add(dayAgenda);
		}
	
	
	/*private ArrayList<JLabel> todolist(ArrayList<Task> tasks){
		for(Task task: tasks){
			//JLabel l = JLabel("<html>"+task.)
			
		}*/
		
	}
	
	
	private JPanel agendaPanel;
	private JScrollPane taskscroll;
	private JButton btnDelete;
	private JLabel toDoLeft;
	private JTable agendaTable;
	private DefaultTableModel modelAgendaTable;
	private CalendarController controller;
}
