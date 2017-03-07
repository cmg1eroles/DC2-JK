import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class Storage {
	private static final String filename = "Event_Task.csv";
	public Storage(){
		path = new File(filename).getAbsolutePath();
		
	}
	
	public ArrayList<Task> Read(){
		ArrayList<Task> tasks = new ArrayList<Task>();
			try {
				//else System.out.println(content);
				br = new BufferedReader(new FileReader(path));
				System.out.println(path);
				try {
					while ((in = br.readLine()) != null) {
						input.add(in);
						System.out.println(in);
					}
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("there is an error");
					}
					
				for(int i =0;i<input.size();i++){
					String[]info = input.get(i).split(",");
					String[]date = info[1].split("/");
					GregorianCalendar start = new GregorianCalendar(Integer.parseInt(date[0]),
							Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(info[2])
							,Integer.parseInt(info[3]));
					GregorianCalendar end = new GregorianCalendar(Integer.parseInt(date[0]),
							Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(info[4])
							,Integer.parseInt(info[5]));
					tasks.add(new Task((info[0]=="event")?Type.EVENT:Type.TO_DO,start,end,info[6],info[7]));
					
				}
				} catch (FileNotFoundException e1) {
					System.out.println("there is an error");
					e1.printStackTrace();	
			}
			return tasks;	
	}
	
	
	public void Write(ArrayList<Task> tasks){
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(path,true);
			for(Task task: tasks){
				fileWriter.append(task.getStrType()+separator);
				fileWriter.append(String.valueOf(task.getYear())+"/"+String.valueOf(task.getMonth())+"/"+
						String.valueOf(task.getDay())+",");
				fileWriter.append(String.valueOf(task.getStartHour())+",");
				fileWriter.append(String.valueOf(task.getStartMinute())+",");
				fileWriter.append(String.valueOf(task.getEndHour())+",");
				fileWriter.append(String.valueOf(task.getEndMinute())+",");
				fileWriter.append(task.getName()+",");
				fileWriter.append(task.getStrColor());
				fileWriter.append("\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				fileWriter.flush();
				fileWriter.close();
			}catch(IOException e){
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
		
			
			
		
		

		
	}
	String dateseparator = "/";
	String separator = ",";
	String path;
	FileWriter fileWriter = null;
	BufferedReader br = null;
	String in="";
	ArrayList<Task> tasks;
	ArrayList<String> input = new ArrayList<String>();
}
