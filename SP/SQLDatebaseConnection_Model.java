import javafx.collections.ObservableList;


/**
 Interface of Model component in MVP pattern
 @author Veronika Vashurkina
 @version 1.0
  * */
public interface SQLDatebaseConnection_Model {
    /**
     * Create database
     * */
    void create();

    /**
     * Insert new record in database
     * @param path user path
     * @param size user size of file
     * @param date user date
     * */
    void insert(String path, double size, String date);

    /** Select all record from database
     * @return List of all record in database
     * */
    ObservableList<FileRecord> selectAll();

    /**Update record in database
     * @param newDate String value of new data
     * @param newSize String value of new size
     * @param path user path
     * @return List of all record in database
     * */
    ObservableList<FileRecord> update(String newDate,double newSize,String path);

    /**
     * Delete record from database
     * @param path user path
     * @return List of all record in database
     * */
    ObservableList<FileRecord> delete(String path);
}

