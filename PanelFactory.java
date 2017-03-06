import java.awt.*;
import javax.swing.*;
public abstract class PanelFactory {
	public static JPanel determine(String btnName, CalendarController cc) {
		PanelFactory panel = null;
		switch(btnName) {
			case "CREATE":  panel = new AddPanel(cc);
			break;
			case "DAY": panel = new DayPanel(cc);
			break;
			case "AGENDA": panel = new AgendaPanel(cc);
		}
		return panel.makePanel();
	}
	protected abstract JPanel makePanel();
}