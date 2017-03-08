import java.awt.Color;
import java.util.*;

import javax.swing.*;

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
		agendaPanel = new JPanel(null);
		taskscroll = new JScrollPane();
		dayAgenda = new JLabel();
	}
	
	private void setPanel() {
		agendaPanel.setBackground(Color.WHITE);
	}
	
	private void addComponents() {
		agendaPanel.add(taskscroll);
		
		
	}
	private void setBounds(){
		agendaPanel.setBounds(300,100,595,795);
		taskscroll.setBounds(50,50, 500, 530);
		dayAgenda.setBounds(0,0,495, 525);
		dayAgenda.setVerticalAlignment(dayAgenda.TOP);
	}
	
	private void updateAgenda() {
		Iterator events = controller.getTasks();
		System.out.println("HALLOOOOO");
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
			
		}
		
	}*/
	
	
	private JPanel agendaPanel;
	private JLabel dayAgenda;
	private JScrollPane taskscroll;
	private CalendarController controller;
}
