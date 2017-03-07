import java.awt.Color;
import java.util.*;

import javax.swing.*;

public class AgendaPanel extends PanelFactory {
	public AgendaPanel(CalendarController cc){
		
	}
	
	protected JPanel makePanel() {
		// TODO Auto-generated method stub
		initComponents();
		addComponents();
		setPanel();
		setBounds();
		return Agenda;
	}
	
	private void initialize(){
		
	}
	private void initComponents(){
		Agenda = new JPanel(null);
		taskscroll = new JScrollPane();
	}
	
	private void setPanel() {
		Agenda.setBackground(Color.WHITE);
	}
	
	private void addComponents() {
		Agenda.add(taskscroll);
		
	}
	private void setBounds(){
		Agenda.setBounds(300,100,595,795);
		taskscroll.setBounds(50,50, 500, 530);
	}
	
	/*private ArrayList<JLabel> todolist(ArrayList<Task> tasks){
		for(Task task: tasks){
			//JLabel l = JLabel("<html>"+task.)
			
		}
		
	}*/
	
	
	private JPanel Agenda;
	//private JLabel ;
	private JScrollPane taskscroll;

}
