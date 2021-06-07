import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Record data files
 *
 * @author Veronika Vashurkina
 * @version 1.0
 */
public class FileRecord {
    /***
     * path to file
     */
    private SimpleStringProperty path;
    /***
     * file size
     */
    private SimpleDoubleProperty size;
    /***
     * date of file
     */
    private SimpleStringProperty date;

    /**
     * Initialize FileRecord {@link FileRecord#path}, {@link FileRecord#size} {@link FileRecord#date}
     * @see FileRecord
     * */
    public FileRecord(String path, double size, String date) {
        this.path = new SimpleStringProperty(path);
        this.date = new SimpleStringProperty(date);
        this.size = new SimpleDoubleProperty(size);
    }
    /**
     * Get path
     * @return path
     * */
    public String getPath(){
        return path.get();
    }
    /**
     * Get size
     * @return size
     * */
    public double getSize(){
        return size.get();
    }
    /**
     * Set path
     * @param path new path value
     * */
    public void setPath(String path){
        this.path.set(path);
    }
    /**
     * Set size
     * @param size new size value
     * */
    public void setSize(double size){
        this.size.set(size);
    }
    /**
     * Get date
     * @return date
     * */
    public String getDate(){
        return date.get();
    }
    /**
     * Set date
     * @param date new date value
     * */
    public void setDate(String date){
        this.date.set(date);
    }
}
