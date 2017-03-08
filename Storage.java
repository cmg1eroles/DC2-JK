import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
public class Storage {
	
	public ArrayList<Task> read(){
		ArrayList<Task> tasks = new ArrayList<Task>();
		try
        {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tasks = (ArrayList<Task>) ois.readObject();
            ois.close();
            fis.close();
         }catch(IOException ioe){
             ioe.printStackTrace();
          }catch(ClassNotFoundException c){
             System.out.println("Class not found");
             c.printStackTrace();
          }
        for(Task task: tasks){
            System.out.println(task.getName());
        }
			return tasks;	

	}
	
	
	public void write(ArrayList<Task> tasks){

		ArrayList<Task> append = tasks;
	       try{
	         FileOutputStream fos= new FileOutputStream(filename);
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(append);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	    	   System.out.println("WROJ");
	            ioe.printStackTrace();
	        }
			
			
		
		

		
	}
	private static final String filename = "Event_Task.ser";
}
