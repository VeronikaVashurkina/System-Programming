import javafx.collections.ObservableList;
import javafx.scene.control.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
/**
 Presenter component in MVP pattern
 @author Veronika Vashurkina
 @version 1.0
  * */
public class Presenter {
    /**object of Model*/
    private static SQLDatabaseConnection BD =new SQLDatabaseConnection();
    /**object of View*/
    private static MainView main =new MainView();
    /**Text field for user code*/
    private static TextArea aCodeInput = main.getaCodeInput();
    /** Result label*/
    private static Label aResultLabel = main.getaResultLabel();
    /** TableView to visualize element in database*/
    private static TableView<FileRecord> bTable= main.getbTable();
    /**Collection of records*/
    private static ObservableList<FileRecord> records= main.getbRecords();
    /**Text field for logs*/
    private static TextArea logiInput= main.getLogiInput();
    /** Edit file TextArea*/
    //private static TextArea bTextInput= main.getbTextInput();
    /**Object of class FileRecord*/
    private static FileRecord record;
    /** Insert first argument TextArea*/
    private static TextArea cNumberInputA = main.getcNumberInputA();
    /** Insert second argument TextArea*/
    private static TextArea cNumberInputB = main.getcNumberInputB();
    /** Result label*/
    private static Label cResultLabel = main.getcResultLabel();
    /** TableView to visualize element in database*/
   private static TableView<FileRecord> dTable= main.getdTable();
    /**Collection of records in the database */
    private static ObservableList<FileRecord> recordsBD= main.getdRecords();
    /** Edit file in the database TextArea*/
    //private static TextArea dTextInput= main.getdTextInput();
    /**Object of class FileRecord*/
    private static FileRecord recordBD;
    /***
     * Method for outputting the result
     * @param flag check variable
     */
    public static void setLabelResult(int flag) {

        if (flag > 1) {
            aResultLabel.setText(" Цикл выполнился более 1 раза.");

        } else {
            aResultLabel.setText("Цикл выполнится один раз.");

        }
    }
    /***
     * Calls the code loading method
     * @throws Exception Wrong construction input
     */
    public void getCode() throws Exception {
        Downloader.task(aCodeInput.getText());
    }
    /***
     * Method for filling a text field with a finished structure
     */
    public static void setCode() {
        Logi.addLog("Работа в разделе Реализуемая конструкция языка: Вставлен готовый код");
        aCodeInput.setText("int x=3; do x=x+4; while (x<8);");
    }

    /**
     * The method checks the code accordingly
     * @throws Exception Wrong code
     */
    public static void checkCode() throws Exception {
        aResultLabel.setText(" ");
        Logi.addLog("Работа в разделе Реализуемая конструкция языка: Идет проверка кода");
        Graf graf =new Graf();
      if(graf.TestString(aCodeInput.getText())){
          Logi.addLog("Работа в разделе Реализуемая конструкция языка: Код верный");
          Logi.addLog("Работа в разделе Реализуемая конструкция языка: Идет загрузка результата");
          Downloader.task(aCodeInput.getText());
          Logi.addLog("Работа в разделе Реализуемая конструкция языка: Результат загружен");
      }
      else{Logi.addLog("Работа в разделе Реализуемая конструкция языка: "+graf.GetError());}

    }

    /**
     * The method shows the selected record
     * @param newRecord  selected record
     */
    public static void selectedRecord (FileRecord newRecord){
        record=newRecord;
        Logi.addLog("Работа в разделе Работа с файлами: Запись выбрана");
   }

    /**
     * The method open the selected record
     * @throws IOException Wrong reading file
     */
    public static void editRecord() throws IOException {////////////////////////////////////
        bTable.refresh();
        Logi.addLog("Работа в разделе Работа с файлами: Информация в таблице обновлена");
        FileWriter out = new FileWriter("file.csv");
        PrintWriter outp = new PrintWriter(out);
        Logi.addLog("Работа в разделе Работа с файлами: Поток записи в файл открыт");
        String path;
        String date;
        double size;
        Logi.addLog("Работа в разделе Работа с файлами: Запись изменена");
        for(int i=0;i<records.size();i++){
           path=records.get(i).getPath();
           date=records.get(i).getDate();
           size=records.get(i).getSize();
            outp.print(path);
            outp.print(" ");
            outp.print(size);
            outp.print(" ");
            outp.print(date);

            outp.println();

        }
        outp.flush();
        outp.close();
        Logi.addLog("Работа в разделе Работа с файлами: Поток записи в файл закрыт");






        /*
        String path = record.getPath();
        Logi.addLog("Работа в разделе Работа с файлами: Путь загружен");
        FileReader in= new FileReader(path);
        Logi.addLog("Работа в разделе Работа с файлами: Выполняется открытие файла");
        char [] a = new char[200];
        in.read(a);

        for(char c : a)
            //bTextInput.setText(bTextInput.getText()+c);///////////////////////////////////
        in.close();
        Logi.addLog("Работа в разделе Работа с файлами: Файл открыт");

         */

    }
    /**
     * The method edit the selected record
     * @throws IOException Wrong saving file
     */
   /* public static void saveRecord() throws IOException {
        FileWriter out = new FileWriter(record.getPath());
        Logi.addLog("Работа в разделе Работа с файлами: Поток записи в файл открыт");
        //out.write(bTextInput.getText());//////////////////////////////////////////////////////////////
        Logi.addLog("Работа в разделе Работа с файлами: Идет запись информации в файл");
        out.flush();
        out.close();
        Logi.addLog("Работа в разделе Работа с файлами: Поток записи в файл закрыт");
        record.setDate(new Date().toString());
        record.setSize((double)record.getPath().length()/1024.0/1024.0);
        bTable.refresh();
        Logi.addLog("Работа в разделе Работа с файлами: Информация в таблице обновлена");
        //bTextInput.clear();//////////////////////////////////////////////////////////////////////
        Logi.addLog("Работа в разделе Работа с файлами: Текстовое окно отчищено");
    }
*/

    /**
     * The method delete the selected record
     * @throws IOException Wrong deleting file
     */
    public static void deleteRecord() throws IOException {
        bTable.getItems().remove(record);
        Logi.addLog("Работа в разделе Работа с файлами: Запись удалена из таблицы");
        //Files.delete(Paths.get(record.getPath()));
        Logi.addLog("Работа в разделе Работа с файлами: Запись удалена из файла");
        bTable.refresh();
        FileWriter out = new FileWriter("file.csv");
        PrintWriter outp = new PrintWriter(out);
        String path;
        String date;
        double size;
        for(int i=0;i<records.size();i++){
            path=records.get(i).getPath();
            date=records.get(i).getDate();
            size=records.get(i).getSize();
            outp.print(path);
            outp.print(" ");
            outp.print(size);
            outp.print(" ");
            outp.print(date);
            outp.println();

        }
        outp.flush();
        bTable.refresh();
        Logi.addLog("Работа в разделе Работа с файлами: Информация в таблице обновлена");
    }

    /**
     * The method adding new record
     */
    public static void addRecord() throws IOException {
        FileWriter out = new FileWriter("file.csv",true);
        PrintWriter outp = new PrintWriter(out);
        //FileNameExtensionFilter filter = new FileNameExtensionFilter("*.CSV","*.*");
        JFileChooser fc = new JFileChooser();
        //fc.setFileFilter(filter);
        Logi.addLog("Работа в разделе Работа с файлами: Накладывается фильтр на отображаемые файлы");
        if ( fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
            Logi.addLog("Работа в разделе Работа с файлами: Открытие окна добавления");
            try ( FileWriter fw = new FileWriter(fc.getSelectedFile()) ) {
                Logi.addLog("Работа в разделе Работа с файлами: Окно добавления открыто");

                Logi.addLog("Работа в разделе Работа с файлами: Информация добавлена в файл");
                double size =(double)fc.getSelectedFile().getAbsoluteFile().toString().length()/1024.0/1024.0;
                String path=fc.getSelectedFile().getAbsoluteFile().toString();
                String date=new Date().toString();
                Logi.addLog("Работа в разделе Работа с файлами: Идет расчет размера файла");
                records.add(new FileRecord(path,size,date));
                Logi.addLog("Работа в разделе Работа с файлами: Расчет размера файла закончен");
                Logi.addLog("Работа в разделе Работа с файлами: Запись добавлена в таблицы");
                //bTable.getItems().addAll(records);
                bTable.refresh();
                Logi.addLog("Работа в разделе Работа с файлами: Информация в таблице обновлена");
               // bTextInput.clear();/////////////////////////////////////////////////////////////////////
               // Logi.addLog("Работа в разделе Работа с файлами: Текстовое поле отчищено");
                outp.print(path);
                outp.print(" ");
                outp.print(size);
                outp.print(" ");
                outp.print(date);
                outp.println();
                outp.flush();
            }
            catch ( IOException e ) {
                Logi.addLog(e.toString());
            }
        }


    }


    /**
     * The method calculates the value of the function
     * @throws InvocationTargetException
     * @throws IllegalAccessException Execption on dowloading method
     * @throws NoSuchMethodException Wrong method name
     * @throws InstantiationException Execption of instantiation
     */
    public static void caiculateFunction() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        LogicalAnd function = new LogicalAnd();
        Logi.addLog("Работа в разделе Низкоуровневая функция: Идет проверка введенных данных на корректность");
        if(cNumberInputA.getText().contains(".")||cNumberInputB.getText().contains(".")){

            Logi.addLog("Работа в разделе Низкоуровневая функция: Числа введены неправильно присутствует точка");

        }
        else {
            if(!isNumeric(cNumberInputA.getText())||(!isNumeric(cNumberInputB.getText()))){

                Logi.addLog("Работа в разделе Низкоуровневая функция: Числа введены неправильно присутствует хотя бы одна буква");

            }
            else {
                if((Integer.parseInt(cNumberInputA.getText())>2147483647)||((Integer.parseInt(cNumberInputA.getText())<-2147483647))||((Integer.parseInt(cNumberInputB.getText())>2147483647))||((Integer.parseInt(cNumberInputB.getText())<-2147483647))){

                    Logi.addLog("Работа в разделе Низкоуровневая функция: Числа введены неправильно слишком большоее число " +
                            "введите число от -2147483648 до 2147483647");

                }
                else{
                    Logi.addLog("Работа в разделе Низкоуровневая функция: Данные проверены идет подсчет ответа");

                   /// cResultLabel.setText(String.valueOf(function.assemblerFunction(Integer.parseInt(cNumberInputA.getText()),Integer.parseInt(cNumberInputB.getText()))));
                    cResultLabel.setText(String.valueOf(function.getFunction(Integer.parseInt(cNumberInputA.getText()),Integer.parseInt(cNumberInputB.getText()))));

                    Logi.addLog("Работа в разделе Низкоуровневая функция: Ответ посчитан");
                }



            }

        }


    }
    /**
     * The method shows the selected record
     * @param newRecord  selected record
     */
    public static void selectedRecordBD (FileRecord newRecord){
        recordBD=newRecord;
        Logi.addLog("Работа в разделе База данных: Запись выбрана");
    }

    /**
     * The method loads records from the database
     */
    public static void refreshBD(){
        BD.create();
        recordsBD= BD.selectAll();
        dTable.getItems().clear();
        dTable.getItems().addAll(recordsBD);
        dTable.refresh();
        Logi.addLog("Работа в разделе База данных: Информация в базе данных обновлена");
    }
    /**
     * The method open the selected record
     * @throws IOException Wrong reading file
     */
    public static void editRecordBD() throws IOException {
        bTable.refresh();
        Logi.addLog("Работа в разделе База данных: Информация в таблице обновлена");
        //recordBD.setDate(new Date().toString());
        //recordBD.setSize((double)recordBD.getPath().length()/1024.0/1024.0);
        //recordsBD=BD.update(new Date().toString(),(double)recordBD.getPath().length()/1024.0/1024.0,recordBD.getPath());
        String path=recordBD.getPath();
         String date=recordBD.getDate();
        double size=recordBD.getSize();
        recordsBD=BD.update(date,size,path);
        dTable.refresh();
        Logi.addLog("Работа в разделе База данных: Информация в базе данных обновлена");


        /*FileReader in= new FileReader(recordBD.getPath());
        Logi.addLog("Работа в разделе База данных: Путь загружен");
        Logi.addLog("Работа в разделе База данных: Выполняется открытие файла");
        char [] a = new char[200];
        in.read(a);

        for(char c : a)
            //dTextInput.setText(dTextInput.getText()+c);//////////////////////////////////////////////////
        in.close();
        Logi.addLog("Работа в разделе База данных: Файл открыт");
*/
    }

    /**
     * The method save the selected record
     * @throws IOException Wrong saving file
     */
  /*  public static void saveRecordBD() throws IOException {
        Logi.addLog("Работа в разделе База данных: Поток записи в файл открыт");
        FileWriter out = new FileWriter(recordBD.getPath());
      // out.write(dTextInput.getText());/////////////////////////////////////////////////////////////////////
        Logi.addLog("Работа в разделе База данных: Идет запись информации в файл");
        out.flush();
        out.close();
        Logi.addLog("Работа в разделе База данных: Поток записи в файл закрыт");
        recordBD.setDate(new Date().toString());
        recordBD.setSize((double)recordBD.getPath().length()/1024.0/1024.0);
        recordsBD=BD.update(new Date().toString(),(double)recordBD.getPath().length()/1024.0/1024.0,recordBD.getPath());
        dTable.refresh();
        Logi.addLog("Работа в разделе База данных: Информация в базе данных обновлена");
      // dTextInput.clear();///////////////////////////////////////////////////////////////////////////
        Logi.addLog("Работа в разделе База данных: Текстовое окно отчищено");
    }
*/

    /**
     * The method delete the selected record
     * @throws IOException Wrong deleting file
     */

    public static void deleteRecordBD() throws IOException {
        BD.delete(recordBD.getPath());
        Logi.addLog("Работа в разделе База данных: Запись удалена из базы данных");
        //Files.delete(Paths.get(recordBD.getPath()));
        //Logi.addLog("Работа в разделе База данных: Запись удалена из памяти компьютера");
        dTable.getItems().remove(recordBD);
        dTable.refresh();
        Logi.addLog("Работа в разделе База данных: Информация в таблице обновлена");


    }
    /**
     * The method adding new record
     */
    public static void addRecordBD(){
        BD.create();
        //FileNameExtensionFilter filter = new FileNameExtensionFilter("*.CSV","*.*");
        JFileChooser fc = new JFileChooser();
        //fc.setFileFilter(filter);
        //Logi.addLog("Работа в разделе База данных: Накладывается фильтр на отображаемые файлы");
        if ( fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
            Logi.addLog("Работа в разделе База данных: Открытие окна добавления");
        }
            try ( FileWriter fw = new FileWriter(fc.getSelectedFile()) ) {
                Logi.addLog("Работа в разделе База данных: Окно добавления открыто");
               // fw.write(dTextInput.getText());//////////////////////////////////////////////////////////////////////
                //Logi.addLog("Работа в разделе База данных: Информация добавлена в файл");
                //dTextInput.clear();///////////////////////////////////////////////////////////////////////////////////
                //Logi.addLog("Работа в разделе База данных: Текстовое поле отчищено");
                String size =fc.getSelectedFile().getAbsoluteFile().toString();
                Logi.addLog("Работа в разделе База данных: Идет расчет размера файла");
                recordsBD.add(new FileRecord(fc.getSelectedFile().getAbsoluteFile().toString(),(double)size.length()/1024.0/1024.0,new Date().toString()));
                Logi.addLog("Работа в разделе База данных: Расчет размера файла закончен");
                Logi.addLog("Работа в разделе База данных: Запись добавлена в таблицы");
                BD.insert(fc.getSelectedFile().getAbsoluteFile().toString(),(double)size.length()/1024.0/1024.0,new Date().toString());
                //bTable.getItems().addAll(records);
                recordsBD= BD.selectAll();
                dTable.getItems().clear();
                dTable.getItems().addAll(recordsBD);
                dTable.refresh();
                Logi.addLog("Работа в разделе База данных: Информация в таблице обновлена");
            }
            catch ( IOException e ) {
                Logi.addLog(e.toString());
            }
        }

    /**
     * The method checks if the string is a number of type int
     * @param s Input string
     * @return True if the input string is a number and false if vice versa
     */
    private static boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * The method checks if the string is a dooble of type int
     * @param s Input string
     * @return True if the input string is a number and false if vice versa
     */
    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
