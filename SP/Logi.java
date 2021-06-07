import javafx.scene.control.TextArea;

import java.util.Date;
/**
 *Class for displaying logs
 * @author Veronika Vashurkina
 * @version 1.0
 */
public class Logi {
    /***
     *Text field for displaying logs
     */
    private static TextArea logiInput= MainView.getLogiInput();

    /***
     * Method for outputting logs to a text box
     * @param log user actions
     */
    public static void addLog(String log){

        logiInput.setText(logiInput.getText()+new Date() +"  "+log+System.lineSeparator());
    }
}
