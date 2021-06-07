import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
/**
 *Interface of View component in MVP pattern
 * @author Veronika Vashurkina
 * @version 1.0
 */
public interface IMainView {
    /**
     * Get aResultLabel
     * @return aResultLabel
     * */
    static Label getaResultLabel() {
        return null;
    }
    /**
     * Get aCodeInput
     * @return aCodeInput
     * */
    static TextArea getaCodeInput() {
        return null;
    }
    /**
     * Get bTable
     * @return bTable
     * */
    static TableView<FileRecord> getbTable() {
        return null;
    }
    /**
     * Get bRecords
     * @return bRecords
     * */
    static ObservableList<FileRecord> getbRecords() {
        return null;
    }
    /**
     * Get LogiInput
     * @return LogiInput
     * */
    static TextArea getLogiInput() {
        return null;
    }
    /**
     * Get bTextInput
     * @return bTextInput
     * */
    static TextArea getbTextInput() {
        return null;
    }
    /**
     * Get cResultLabel
     * @return cResultLabel
     * */
    static Label getcResultLabel() {
        return null;
    }
    /**
     * Get cNumberInputA
     * @return cNumberInputA
     * */
    static TextArea getcNumberInputA() {
        return null;
    }
    /**
     * Get cNumberInputB
     * @return cNumberInputB
     * */
    static TextArea getcNumberInputB() {
        return null;
    }
    /**
     * Get dTable
     * @return dTable
     * */
    static TableView<FileRecord> getdTable() {
        return null;
    }
    /**
     * Get dRecord
     * @return dRecord
     * */
    static ObservableList<FileRecord> getdRecords() {
        return null;
    }
    /**
     * Get dTextInput
     * @return dTextInput
     * */
    static TextArea getdTextInput() {
        return null;
    }
}
