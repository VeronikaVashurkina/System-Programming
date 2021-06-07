import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
/**
 Work with database
 Model component in MVP pattern
 @author Veronika Vashurkina
 @version 1.0
  * */
public class SQLDatabaseConnection implements SQLDatebaseConnection_Model {

    /**
     * Create database
     * */
    public void create() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "Veronika");

            Logi.addLog("Работа в разделе База данных: База данных открыта");

            stmt = c.createStatement();
            String sql = "CREATE TABLE RECORD( " +
                    " PATH           TEXT    NOT NULL, " +
                    " SIZE        REAL    NOT NULL, " +
                    " DATE           TEXT, " +
                    " CONSTRAINT      UNIQUE_PAT UNIQUE(PATH)," +
                    " CONSTRAINT      UNIQUE_CONS UNIQUE(PATH, SIZE, DATE));";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            Logi.addLog("Работа в разделе База данных: Таблица уже создана");
        }


    }

    /**
     * Insert new record in database
     * @param path user path
     * @param size user size of file
     * @param date user date
     * */
     public void insert(String path, double size, String date) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "Veronika");
            Logi.addLog("Работа в разделе База данных: База данных открыта");

            stmt = c.createStatement();
            String sql = "INSERT INTO RECORD (PATH, SIZE, DATE) "
                    + "VALUES (" + "'" +path+ "'" + ", " + "'" +size + "'" +", " + "'" +date+ "'" + ");";
            stmt.executeUpdate(sql);

            c.setAutoCommit(false);
            stmt.close();
            c.commit();
            c.close();

            Logi.addLog("Работа в разделе База данных: Запись создана");

        } catch (Exception e) {
            String error = new String(e.getClass().getName() + ": " + e.getMessage());
            Logi.addLog(error);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /** Select all record from database
     * @return List of all record in database
     * */
    public ObservableList<FileRecord> selectAll() {
        ObservableList<FileRecord> records =  FXCollections.observableArrayList();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "Veronika");
            Logi.addLog("База данных открыта");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM RECORD;");
            while (rs.next()) {
                String path = rs.getString("PATH");
                double size = rs.getDouble("SIZE");
                String date = rs.getString("DATE");

                FileRecord record = new FileRecord(path,size,date);
                records.add(record);
            }
            rs.close();
            stmt.close();
            c.close();


            Logi.addLog("Записи загружены");
        } catch (Exception e) {
            String error = e.getClass().getName() + ": " + e.getMessage();
            Logi.addLog(error);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return records;
    }

    /**Update record in database
     * @param newDate String value of new data
     * @param newSize String value of new size
     * @param path user path
     * @return List of all record in database
     * */
      public ObservableList<FileRecord> update( String newDate,double newSize,String path){
        ObservableList<FileRecord> records =  FXCollections.observableArrayList();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "Veronika");
            Logi.addLog("База данных открыта");

            stmt = c.createStatement();


               String sql1 = "UPDATE RECORD set SIZE= " + "'" + newSize + "'" +" where PATH=" + "'" + path + "'" + ";";


                String sql2 = "UPDATE RECORD set DATE = " + "'" +newDate + "'" +" where PATH=" + "'" + path + "'" + ";";


            c.setAutoCommit(false);
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            c.commit();

            ResultSet rs = stmt.executeQuery("SELECT * FROM RECORD;");
            while (rs.next()) {
                String newPath = rs.getString("PATH");
                 newSize = rs.getDouble("SIZE");
                 newDate = rs.getString("DATE");

                FileRecord record = new FileRecord(newPath, newSize, newDate);
                records.add(record);
            }
            rs.close();
            stmt.close();
            c.close();

            Logi.addLog("Запись изменены");
        } catch (Exception e) {
            String error = new String(e.getClass().getName() + ": " + e.getMessage());
            Logi.addLog(error);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return records;
    }


    /**
     * Delete record from database
     * @param path user path
     * @return List of all record in database
     * */
    public ObservableList<FileRecord> delete(String path){
        ObservableList<FileRecord> records =  FXCollections.observableArrayList();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "Veronika");
            Logi.addLog("База данных открыта");

            stmt = c.createStatement();
            String sql;
            sql = "DELETE from RECORD where PATH= " + "'" + path + "'" + ";";
            stmt.executeUpdate(sql);
            c.setAutoCommit(false);
            c.commit();

            ResultSet rs = stmt.executeQuery("SELECT * FROM RECORD;");
            while (rs.next()) {
                String newPath = rs.getString("PATH");
                double newSize = rs.getDouble("SIZE");
                String newDate = rs.getString("DATE");

                FileRecord record = new FileRecord(newPath, newSize, newDate);
                records.add(record);
            }
            rs.close();
            stmt.close();
            c.close();

            Logi.addLog("Запись удалена");
            System.out.println("Operation done successfully");
        } catch (Exception e) {
            String error = new String(e.getClass().getName() + ": " + e.getMessage());
           Logi.addLog(error);
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return records;
    }


}