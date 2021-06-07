import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * View component in MVP pattern
 * @author Veronika Vashurkina
 * @version 1.0
 */
public class MainView extends Application implements IMainView{
    /**Text field for logs*/
    private static TextArea logiInput = new TextArea(new Date()+"  Старт программы" + System.lineSeparator());
    /**Text field for user code*/
   private static TextArea aCodeInput = new TextArea();
    /** Check code button*/
    private static Button aCheckButton = new Button("Проверить");
    /** Insert code button*/
    private static Button aInsertButton = new Button("Вставить готовый код");
    /** Result label*/
    private static Label aResultLabel = new Label("");
    /** Task label*/
    private Label aTaskLabel = new Label("Введите код с конструкцией: int x=<число>;  do {x=x<сложение, умножение,деление, вычитание с целыми числами>;}"
            + System.lineSeparator() + "while (x<больше меньше или равно какого либо целого числа>);. ");
    /** Add record button*/
    private static Button bAddButton = new Button("Добавить");
    /** Edit record button*/
    private static Button bEditButton = new Button("Редактировать");
    /** Delete record button*/
    private static Button bDeleteButton = new Button("Удалить");
    /** Save record button*/
   // private static Button bSaveButton = new Button("Сохранить");
    /**Collection of records*/
    private static ObservableList<FileRecord> records = FXCollections.observableArrayList();
    /** TableView to visualize element in database*/
    private static TableView<FileRecord> bTable = new TableView<FileRecord>(records);
    /** Edit file TextArea*/
   // private static TextArea bTextInput = new TextArea("Введите текст");
    /** Insert first argument TextArea*/
    private static TextArea cNumberInputA = new TextArea("");
    /** Insert second argument TextArea*/
    private static TextArea cNumberInputB = new TextArea("");
    /** And label*/
    private static Label cAndLabel = new Label("&");
    /** Equal label*/
    private static Label cEquLabel = new Label("=");
    /** Result label*/
    private static Label cResultLabel = new Label("");
    /** Task label*/
    private static Label cTaskLabel = new Label("Введите два числа для вычисления побитового И ");
    /** Calculate function button*/
    private static Button cCalculateButton = new Button("Вычислить");
    /** Add record in the database button*/
    private static Button dAddButton = new Button("Добавить");
    /** Edit record in the database button*/
    private static Button dEditButton = new Button("Редактировать");
    /** Delete recordin the database  button*/
    private static Button dDeleteButton = new Button("Удалить");
    /**Collection of records in the database */
    private static ObservableList<FileRecord> recordsBD = FXCollections.observableArrayList();
    /** TableView to visualize element in database*/
    private static TableView<FileRecord> dTable = new TableView<FileRecord>(recordsBD);
    /** Edit file in the database TextArea*/
    //private static TextArea dTextInput = new TextArea("Введите текст");
    /** Refresh record in the database button*/
    private static Button dRefreshButton = new Button("Загрузить ");
    /** Save record in the database button*/
    //private static Button dSaveButton = new Button("Сохранить");
    /**
     * Get aResultLabel
     * @return aResultLabel
     * */
    public static Label getaResultLabel() {
        return aResultLabel;
    }
    /**
     * Get aCodeInput
     * @return aCodeInput
     * */
    public static TextArea getaCodeInput() {
        return aCodeInput;
    }
    /**
     * Get bTable
     * @return bTable
     * */
    public static TableView<FileRecord> getbTable() { return bTable; }
    /**
     * Get bRecords
     * @return bRecords
     * */
    public static ObservableList<FileRecord> getbRecords() { return records; }
    /**
     * Get LogiInput
     * @return LogiInput
     * */
    public static TextArea getLogiInput() {
        return logiInput;
    }
    /**
     * Get bTextInput
     * @return bTextInput
     * */
    //public static TextArea getbTextInput() {
   //     return bTextInput;
   // }
    /**
     * Get cResultLabel
     * @return cResultLabel
     * */
    public static Label getcResultLabel() { return cResultLabel; }
    /**
     * Get cNumberInputA
     * @return cNumberInputA
     * */
    public static TextArea getcNumberInputA() {
        return cNumberInputA;
    }
    /**
     * Get cNumberInputB
     * @return cNumberInputB
     * */
    public static TextArea getcNumberInputB() {
        return cNumberInputB;
    }
    /**
     * Get dTable
     * @return dTable
     * */
    public static TableView<FileRecord> getdTable() { return dTable; }
    /**
     * Get dRecord
     * @return dRecord
     * */
    public static ObservableList<FileRecord> getdRecords() { return recordsBD; }
    /**
     * Get dTextInput
     * @return dTextInput
     * */
   // public static TextArea getdTextInput() {
   //     return dTextInput;
   // }
    /**
     * Load graphic
     * @param primaryStage represents a window in a JavaFX desktop application
     * @exception Exception Wrong load JavaFX  application
     **/
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("SP");
        GridPane gridpane = new GridPane();
        TabPane tabpane = new TabPane();
        logiInput.setEditable(false);

        Tab tab1 = new Tab("Реализуемая конструкция языка");
        GridPane gridpane1 = new GridPane();
        gridpane1.add(aTaskLabel, 0, 0, 4, 1);
        gridpane1.add(aCodeInput, 0, 1, 4, 3);
        gridpane1.add(aResultLabel, 0, 4, 2, 1);
        gridpane1.add(aInsertButton, 2, 4);
        gridpane1.add(aCheckButton, 3, 4);
        aCodeInput.setMinWidth(880);
        aCodeInput.setMinHeight(100);
        aCodeInput.setMaxHeight(100);
        aInsertButton.setMinWidth(100);
        aInsertButton.setMinHeight(40);
        aCheckButton.setMinWidth(100);
        aCheckButton.setMinHeight(40);
        aResultLabel.setMinWidth(600);
        aResultLabel.setMinHeight(40);
        gridpane.setMargin(aTaskLabel, new Insets(10.0, 10.0, 0.0, 50.0));
        gridpane.setMargin(aCodeInput, new Insets(10.0, 10.0, 0.0, 10.0));
        gridpane.setMargin(aInsertButton, new Insets(10.0, 10.0, 0.0, 35.0));
        gridpane.setMargin(aCheckButton, new Insets(10.0, 10.0, 0.0, 0.0));
        gridpane.setMargin(aResultLabel, new Insets(10.0, 0.0, 0.0, 10.0));

        tab1.setContent(gridpane1);
        tabpane.getTabs().add(tab1);

        aInsertButton.setOnAction(event -> { Presenter.setCode(); });
        aCheckButton.setOnAction(event -> {
            try {
                Presenter.checkCode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



        Tab tab2 = new Tab("Работа с файлами");
        GridPane gridpane2 = new GridPane();

        bTable.setPrefWidth(250);
        bTable.setPrefHeight(200);
        bTable.setEditable(true);
        // столбец для вывода пути к файлу
        TableColumn<FileRecord, String> pathColumn = new TableColumn<FileRecord, String>("Путь к файлу");
        // определяем фабрику для столбца с привязкой к свойству name
        pathColumn.setCellValueFactory(new PropertyValueFactory<FileRecord, String>("path"));
        // добавляем столбец
        bTable.getColumns().add(pathColumn);
        pathColumn.setCellFactory(p -> new CustomTableCell<>());
        pathColumn.setOnEditCommit(event -> {
            String newPath = event.getNewValue();
            FileRecord record = event.getRowValue();
            record.setPath(newPath);
            record.setDate(new Date().toString());
        });


        // столбец для вывода размера файла
        TableColumn<FileRecord, Double> sizeColumn = new TableColumn<FileRecord, Double>("Размер файла, МБ");
        sizeColumn.setCellValueFactory(new PropertyValueFactory<FileRecord, Double>("size"));
        bTable.getColumns().add(sizeColumn);
        sizeColumn.setCellFactory(p -> new CustomTableCellD<>());
        sizeColumn.setOnEditCommit(event -> {
            double newSize = event.getNewValue();
            if(Presenter.isDouble(String.valueOf(newSize))) {
                FileRecord record = event.getRowValue();
                record.setSize(newSize);
                record.setDate(new Date().toString());
            }
            else {
                Logi.addLog("Работа в разделе Работа с файлами: Введена хотя бы одна буква");
            }
        });

        // столбец для вывода даты последнего редактирования
        TableColumn<FileRecord, String> dateColumn = new TableColumn<FileRecord, String>("Дата последнего редактирования");
        dateColumn.setCellValueFactory(new PropertyValueFactory<FileRecord, String>("date"));
        bTable.getColumns().add(dateColumn);

        pathColumn.prefWidthProperty().bind(bTable.widthProperty().multiply(0.35));
        sizeColumn.prefWidthProperty().bind(bTable.widthProperty().multiply(0.25));
        dateColumn.prefWidthProperty().bind(bTable.widthProperty().multiply(0.4));

        gridpane2.add(bTable,0,0,4,1);
       // gridpane2.add(bTextInput,1,0,3,1);
       // gridpane2.add(bSaveButton,0,1);
        gridpane2.add(bAddButton,1,1);
        gridpane2.add(bEditButton,2,1);
        gridpane2.add(bDeleteButton,3,1);
        bTable.setMinWidth(880);
        bTable.setMaxWidth(880);
        bTable.setMaxHeight(180);
       // bTextInput.setMinWidth(320);
     //  bTextInput.setMaxWidth(320);
     //  bTextInput.setMaxHeight(180);
        bAddButton.setMinWidth(100);
        bAddButton.setMinHeight(40);
        //bSaveButton.setMinWidth(100);
       // bSaveButton.setMinHeight(40);
        bEditButton.setMinWidth(100);
        bEditButton.setMinHeight(40);
        bDeleteButton.setMinWidth(100);
        bDeleteButton.setMinHeight(40);
        gridpane.setMargin(bTable, new Insets(0,10,0,10));
       // gridpane.setMargin(bSaveButton, new Insets(10.0, 10.0, 10.0, 460.0));
        gridpane.setMargin(bAddButton, new Insets(10.0, 0.0, 10.0, 570.0));
        gridpane.setMargin(bEditButton, new Insets(10.0, 10.0, 10.0, 10.0));
        gridpane.setMargin(bDeleteButton, new Insets(10.0, 10.0, 10.0, 0.0));


        tab2.setContent(gridpane2);
        tabpane.getTabs().add(tab2);

        bAddButton.setOnAction(event -> {
            try {
                Presenter.addRecord();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bEditButton.setOnAction(event -> {
            try {
                Presenter.editRecord();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bDeleteButton.setOnAction(event -> {
            try {
                Presenter.deleteRecord();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
       // bSaveButton.setOnAction(event -> {
       //     try {
       //         Presenter.saveRecord();
       //     } catch (IOException e) {
       //         e.printStackTrace();
       //     }
      //  });

        TableView.TableViewSelectionModel<FileRecord> selectionModel = bTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<FileRecord>() {

            @Override
            public void changed(ObservableValue<? extends FileRecord> observable, FileRecord oldValue, FileRecord newValue) {
                if(bTable.getSelectionModel().getSelectedItem() != null) {
                    FileRecord record = bTable.getSelectionModel().getSelectedItem();
                    Presenter.selectedRecord(record);
                }
            }
        });



        Tab tab3 = new Tab("Низкоуровневая функция");
        GridPane gridpane3 = new GridPane();

        gridpane3.add(cTaskLabel,0,0,5,1);
        gridpane3.add(cNumberInputA,0,1);
        gridpane3.add(cAndLabel,1,1);
        gridpane3.add(cNumberInputB,2,1);
        gridpane3.add(cEquLabel,3,1);
        gridpane3.add(cResultLabel,4,1);
        gridpane3.add(cCalculateButton,0,2);
        cNumberInputB.setMaxWidth(100);
        cNumberInputB.setMaxHeight(40);
        cEquLabel.setMaxWidth(100);
        cEquLabel.setMaxHeight(40);
        cAndLabel.setMaxWidth(100);
        cAndLabel.setMaxHeight(40);
        cNumberInputA.setMaxWidth(100);
        cNumberInputA.setMaxHeight(40);
        cResultLabel.setMaxWidth(100);
        cResultLabel.setMaxHeight(40);
        cCalculateButton.setMinWidth(100);
        cCalculateButton.setMinHeight(40);
        gridpane.setMargin(cTaskLabel, new Insets(20,0,0,20));
        gridpane.setMargin(cNumberInputA, new Insets(20.0, 0.0, 0.0, 20.0));
        gridpane.setMargin(cAndLabel, new Insets(20.0, 0.0, 0.0, 10.0));
        gridpane.setMargin(cNumberInputB, new Insets(20.0, 0.0, 0.0, 10.0));
        gridpane.setMargin(cEquLabel, new Insets(20.0, 10.0, 0.0, 10.0));
        gridpane.setMargin(cCalculateButton, new Insets(20.0, 0.0, 0.0, 20.0));
        gridpane.setMargin(cResultLabel, new Insets(20.0, 10.0, 0.0, 0.0));

        tab3.setContent(gridpane3);
        tabpane.getTabs().add(tab3);

        cCalculateButton.setOnAction(event -> {
            try {
                Presenter.caiculateFunction();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        });



        Tab tab4 = new Tab("База данных");
        GridPane gridpane4 = new GridPane();
        dTable.setPrefWidth(250);
        dTable.setPrefHeight(200);
        dTable.setEditable(true);



        TableColumn<FileRecord, String> pathColumnBD = new TableColumn<FileRecord, String>("Путь к файлу");
        pathColumnBD.setCellValueFactory(new PropertyValueFactory<FileRecord, String>("path"));
        dTable.getColumns().add(pathColumnBD);
        pathColumnBD.setCellFactory(p -> new CustomTableCell<>());
        pathColumnBD.setOnEditCommit(event -> {
            String newPath = event.getNewValue();
            FileRecord record = event.getRowValue();
            record.setPath(newPath);
            record.setDate(new Date().toString());
        });

        TableColumn<FileRecord, Double> sizeColumnBD = new TableColumn<FileRecord, Double>("Размер файла, МБ");
        sizeColumnBD.setCellValueFactory(new PropertyValueFactory<FileRecord, Double>("size"));
        dTable.getColumns().add(sizeColumnBD);
        sizeColumnBD.setCellFactory(p -> new CustomTableCellD<>());
        sizeColumnBD.setOnEditCommit(event -> {
            double newSize = event.getNewValue();
                    if(Presenter.isDouble(String.valueOf(newSize))) {
            FileRecord record = event.getRowValue();
            record.setSize(newSize);
            record.setDate(new Date().toString());}
                    else {
                        Logi.addLog("Работа в разделе База данных: Введена хотя бы одна буква");
                    }
        });


        TableColumn<FileRecord, String> dateColumnBD = new TableColumn<FileRecord, String>("Дата последнего редактирования");
        dateColumnBD.setCellValueFactory(new PropertyValueFactory<FileRecord, String>("date"));
        dTable.getColumns().add(dateColumnBD);


        pathColumnBD.prefWidthProperty().bind(dTable.widthProperty().multiply(0.35));
        sizeColumnBD.prefWidthProperty().bind(dTable.widthProperty().multiply(0.25));
        dateColumnBD.prefWidthProperty().bind(dTable.widthProperty().multiply(0.4));

        gridpane4.add(dTable,0,0,5,1);
       // gridpane4.add(dTextInput,2,0,3,1);
        gridpane4.add(dRefreshButton,0,1);
        //gridpane4.add(dSaveButton,1,1);
        gridpane4.add(dAddButton,2,1);
        gridpane4.add(dEditButton,3,1);
        gridpane4.add(dDeleteButton,4,1);
        dTable.setMinWidth(880);
        dTable.setMaxWidth(880);
        dTable.setMaxHeight(180);
     //  dTextInput.setMinWidth(320);
     //  dTextInput.setMaxWidth(320);
      // dTextInput.setMaxHeight(180);
        dAddButton.setMinWidth(100);
        dAddButton.setMinHeight(40);
       // dSaveButton.setMinWidth(100);
        //dSaveButton.setMinHeight(40);
        dRefreshButton.setMinWidth(100);
        dRefreshButton.setMinHeight(40);
        dEditButton.setMinWidth(100);
        dEditButton.setMinHeight(40);
        dDeleteButton.setMinWidth(100);
        dDeleteButton.setMinHeight(40);
        gridpane.setMargin(dTable, new Insets(0,10,0,10));
       // gridpane.setMargin(dSaveButton, new Insets(10.0, 10.0, 10.0, 0.0));
        gridpane.setMargin(dRefreshButton, new Insets(10.0, 10.0, 10.0, 460.0));
        gridpane.setMargin(dAddButton, new Insets(10.0, 0.0, 10.0, 0.0));
        gridpane.setMargin(dEditButton, new Insets(10.0, 10.0, 10.0, 10.0));
        gridpane.setMargin(dDeleteButton, new Insets(10.0, 10.0, 10.0, 0.0));


        tab4.setContent(gridpane4);
        tabpane.getTabs().add(tab4);
        dRefreshButton.setOnAction(event -> { Presenter.refreshBD(); });
        //dSaveButton.setOnAction(event -> {
        //    try {
        //        Presenter.saveRecordBD();
        //    } catch (IOException e) {
        //        e.printStackTrace();
        //    }
       // });
        dAddButton.setOnAction(event -> { Presenter.addRecordBD(); });
        dEditButton.setOnAction(event -> {

            try {
                Presenter.editRecordBD();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        dDeleteButton.setOnAction(event -> {

            try {
                Presenter.deleteRecordBD();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


        TableView.TableViewSelectionModel<FileRecord> selectionModelBD = dTable.getSelectionModel();
        selectionModelBD.selectedItemProperty().addListener(new ChangeListener<FileRecord>() {

            @Override
            public void changed(ObservableValue<? extends FileRecord> observable, FileRecord oldValue, FileRecord newValue) {
                if(dTable.getSelectionModel().getSelectedItem() != null) {
                    FileRecord record = dTable.getSelectionModel().getSelectedItem();
                    Presenter.selectedRecordBD(record);
                }
            }
        });





        gridpane.add(tabpane, 0, 0, 4, 3);
        gridpane.add(logiInput, 0, 4, 4, 2);
        logiInput.setMinWidth(880);
        logiInput.setMaxWidth(880);
        logiInput.setMinHeight(200);
        logiInput.setMaxHeight(200);
        gridpane.setMargin(logiInput, new Insets(0.0, 10.0, 0.0, 10.0));
        tabpane.setMinWidth(900);
        tabpane.setMinHeight(250);


        primaryStage.setScene(new Scene(gridpane, 900, 480));
        primaryStage.show();


    }

    /***
     * Program entry point
     * @param args Array of strings
     */
    public static void main(String[] args) {
        launch(args);
    }
}

/**
 * class to edit record in table
 * @param <T> user record
 */
class CustomTableCell<T> extends TableCell<T, String> {
    private TextField textField;

    @Override
    public void startEdit() {
        super.startEdit();
        if (textField == null) {
            createTextField();
        }
        textField.setText(getString());
        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getString());
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s, b);

        if (b || s == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(getString());
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
    }

    private String getString() {
        return String.valueOf(getItem());
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setOnKeyPressed(t -> {
            if (t.getCode() == KeyCode.ENTER || t.getCode() == KeyCode.TAB) {
                commitEdit(textField.getText());
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
    }
}
/**
 * class to edit record in table
 * @param <T> user record
 */
class CustomTableCellD<T> extends TableCell<T, Double> {
    private TextField textField;

    @Override
    public void startEdit() {
        super.startEdit();
        if (textField == null) {
            createTextField();
        }
        textField.setText(getString());
        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getString());
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    protected void updateItem(Double s, boolean b) {
        super.updateItem(s, b);

        if (b || s == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(getString());
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
    }

    private String getString() {
        return String.valueOf(getItem());
    }

    private void createTextField() {
        textField = new TextField(getString());
        textField.setOnKeyPressed(t -> {
            if (t.getCode() == KeyCode.ENTER || t.getCode() == KeyCode.TAB) {
                commitEdit(Double.parseDouble(textField.getText()));
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
    }
}
