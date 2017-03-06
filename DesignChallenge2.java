import java.util.*;

public class DesignChallenge2 {
	public static void main(String[] args) {
		GregorianCalendar testStartDate = new GregorianCalendar(2017, 3, 5, 12, 30);
		GregorianCalendar testEndDate = new GregorianCalendar(2017, 3, 5, 14, 30);
		Task newTask = new Task(Type.EVENT, testStartDate, testEndDate, "Hello World", "blue");

		GregorianCalendar testSDate = new GregorianCalendar(2017, 3, 5, 12, 00);
		GregorianCalendar testEDate = new GregorianCalendar(2017, 3, 5, 12, 30);
		Task newTasks = new Task(Type.EVENT, testSDate, testEDate, "Hello World", "blue");
		if (newTasks.checkOverlap(newTask))
			System.out.println("Tama");
		else
			System.out.println("Mali");
	}
}