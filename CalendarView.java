import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarView {
	public CalendarView() {
		//controller = control;
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        
        initFrmMain();
		initCalendarComponents();
		addCalendarComponents();
		setBoundsCalendarComponents();
		setCalendarTable();
		setCalendarPanel();

		initViewComponents();
		addViewComponents();
		setBoundsViewComponents();
		setViewPanel();

		initVChoiceComponents();
		addVChoiceComponents();
		setBoundsVChoiceComponents();
		setVChoicePanel();
	}

	public void initFrmMain() {
		frmMain = new JFrame ("My Productivity Tool");
        frmMain.setSize(900, 740);
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.setResizable(false);
		frmMain.setVisible(true);
	}

	public void initVChoiceComponents() {
		cbEvent = new JCheckBox("Event");
		cbTask = new JCheckBox("Task");
		vChoicePanel = new JPanel(null);
		//temp = new JPanel(null);
	}

	public void addVChoiceComponents() {
		pane.add(vChoicePanel);
		//pane.add(temp);
		vChoicePanel.add(cbTask);
		vChoicePanel.add(cbEvent);
	}

	public void setVChoicePanel() {
		vChoicePanel.setBackground(Color.DARK_GRAY);
		vChoicePanel.setBorder(BorderFactory.createTitledBorder("View"));
	}

	public void setBoundsVChoiceComponents() {
		//temp.setBounds(300,100,595,795);
		vChoicePanel.setBounds(0,400,300,330);
		cbEvent.setBounds(100, 70, 100, 40);
		cbTask.setBounds(100, 140, 100, 40);
	}

	public String getDateToday() {
		Months[] months = Months.values();
		GregorianCalendar dateToday = new GregorianCalendar();
		String dteTdy = "" + months[dateToday.get(GregorianCalendar.MONTH)].toString() + " " + 
						dateToday.get(GregorianCalendar.DATE) +  ", " + dateToday.get(GregorianCalendar.YEAR);
		return dteTdy; 
	}

	public void initViewComponents() {
		dateLabel = new JLabel(getDateToday());
		btnToday = new JButton("TODAY");
		btnDay = new JButton("DAY");
		btnAgenda = new JButton("AGENDA");
		viewPanel = new JPanel(null);
	}

	public void addViewComponents() {
		pane.add(viewPanel);
		viewPanel.add(dateLabel);
		viewPanel.add(btnToday);
		viewPanel.add(btnDay);
		viewPanel.add(btnAgenda);
	}

	public void setViewPanel() {
		viewPanel.setBackground(Color.CYAN);
	}

	public void setBoundsViewComponents() {
		viewPanel.setBounds(300,0,595, 100);
		btnToday.setBounds(20, 30, 100, 40);
		btnAgenda.setBounds(480, 30, 100, 40);
		btnDay.setBounds(380, 30, 100, 40);
		dateLabel.setBounds(150, 30, 200, 40);
	}

	public void initCalendarComponents() {
		monthLabel = new JLabel ("January");
		yearLabel = new JLabel ("Change year:");
		dayLabel = new JLabel("");
		cmbYear = new JComboBox();
		btnPrev = new JButton ("<");
		btnNext = new JButton (">");
		addTask = new JButton("CREATE");
		modelCalendarTable = new DefaultTableModel() {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
                
		calendarTable = new JTable(modelCalendarTable);
        calendarTable.addMouseListener(new MouseAdapter() {  
            public void mouseClicked(MouseEvent evt) {  
                setDate();
            }
        });
                
		scrollCalendarTable = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);
	}

	public void addCalendarComponents() {
		pane.add(calendarPanel);
		calendarPanel.add(monthLabel);
		calendarPanel.add(yearLabel);
		calendarPanel.add(dayLabel);
		calendarPanel.add(cmbYear);
		calendarPanel.add(btnPrev);
		calendarPanel.add(btnNext);
		calendarPanel.add(addTask);
		calendarPanel.add(scrollCalendarTable);
		String[] headers = {"S", "M", "T", "W", "T", "F", "S"}; //All headers
		for (int i=0; i<7; i++)
			modelCalendarTable.addColumn(headers[i]);
		
	}

	public void setCalendarPanel() {
		calendarPanel.setBorder(BorderFactory.createTitledBorder("Calendar"));
		calendarPanel.setBackground(Color.PINK);
	}

	public void setCalendarTable(){
		calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background
		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);
		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		calendarTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		calendarTable.setRowHeight(30);
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(6);
	}
	public void setBoundsCalendarComponents() {
		calendarPanel.setBounds(0, 0, 300, 400);
        monthLabel.setBounds(20, 15, 200, 50);
       // dayLabel.setBounds(110,55,50, 50);
		cmbYear.setBounds(20, 55, 80, 35);
		btnPrev.setBounds(180, 50, 45, 40);
		btnNext.setBounds(225, 50, 45, 40);
		addTask.setBounds(100,340, 100, 40);
		scrollCalendarTable.setBounds(20, 100, 250, 220);
	}

	public void setDate() {
		col = calendarTable.getSelectedColumn();  
        row = calendarTable.getSelectedRow();  
        dayLabel.setText(""+modelCalendarTable.getValueAt(row,col));
        dateLabel.setText(getMonthlbl() + " " + getDaylbl() + ", " + getCmbYr().toString());
	}

   	public void refreshCalendar(int month, int year) {
		Months[] months =  Months.values();
		int numOfDays, dayOfWeek, i, j;
			
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= controller.getYearBound()-10)
            btnPrev.setEnabled(false);
		if (month == 11 && year >= controller.getYearBound()+100)
            btnNext.setEnabled(false);
                
		monthLabel.setText(months[month].toString());
                
		cmbYear.setSelectedItem(""+year);
		
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		numOfDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		dayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK);

		for (i = 0; i < 6; i++)
			for (j = 0; j < 7; j++)
				modelCalendarTable.setValueAt(null, i, j);

		for (i = 1; i <= numOfDays; i++) {
			int rows = new Integer((i+dayOfWeek-2)/7);
			int column  =  (i+dayOfWeek-2)%7;
			modelCalendarTable.setValueAt(i, rows, column);
		}
		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer());
	}

	public void setController(CalendarController cc) {
		controller = cc;
		btnPrev.addActionListener(controller.new btnPrev_Action());
		btnNext.addActionListener(controller.new btnNext_Action());
		cmbYear.addActionListener(controller.new cmbYear_Action());
		btnToday.addActionListener(controller.new btnToday_Action());
		addTask.addActionListener(controller.new btnCreate_Action());

		for (int i = controller.getYear()-100; i <= controller.getYear()+100; i++) {
			cmbYear.addItem(String.valueOf(i));
		}
		refreshCalendar (controller.getMonth(), controller.getYearBound()); 
	}

	public void setDate(String date) {
		dateLabel.setText(date);
	}

	public Object getCmbYr() {
		return cmbYear.getSelectedItem();
	}

	public String getDaylbl() {
		return dayLabel.getText();
	}

	public String getMonthlbl() {
		return monthLabel.getText();
	}

	public void setCell(String event) {
		modelCalendarTable.setValueAt(event,row,col);
		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer());
	}

	public void addPaneltoPane(JPanel newpanel) {
		newpanel.setVisible(true);
		newpanel.setBounds(300,100,595,795);
		pane.add(newpanel);
		pane.repaint();
	}
	private CalendarController controller;

	/**** Day Components ****/
	//public int yearBound, monthBound, dayBound, yearToday, monthToday;
	private int col, row;

    /**** Calendar Swing Components ****/
    private JLabel monthLabel, yearLabel, dayLabel;
	private JButton btnPrev, btnNext, addTask;
    private JComboBox cmbYear;
	private JFrame frmMain;
	private Container pane;
	private JScrollPane scrollCalendarTable;
	private JPanel calendarPanel;
    
	/**** View Swing Components ****/
	private JPanel viewPanel;
	private JLabel dateLabel;
	private JButton btnToday, btnDay, btnAgenda;

	/**** View Choice Components ****/
	private JPanel vChoicePanel;
	private JCheckBox cbEvent, cbTask;
	private JPanel temp;
    /**** Calendar Table Components ***/
	private JTable calendarTable;
    private DefaultTableModel modelCalendarTable;
}