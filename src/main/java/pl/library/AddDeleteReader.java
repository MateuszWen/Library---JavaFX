package pl.library;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**Control class to deleting and adding a reader function */
public class AddDeleteReader implements Initializable {

        @FXML private TextField textFieldName;
        @FXML private TextField textFieldSurname;
        @FXML private Button buttonAdd;
        @FXML private Button buttonDelete;
        @FXML private TableView<Readers> tableReaders;
        @FXML private TableColumn<Readers, Integer> col_readersID;
        @FXML private TableColumn<Readers, String> col_name;
        @FXML private TableColumn<Readers, String> col_surname;

        /**This readers_list contain all readers to display on TableView, all records from DB, are saveing there */
        public static ObservableList<Readers> readers_list = Functions.getReadersFunction();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_readersID.setCellValueFactory(new PropertyValueFactory<Readers, Integer>("readers_ID"));
        col_name.setCellValueFactory(new PropertyValueFactory<Readers, String>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<Readers, String>("surname"));
        tableReaders.setItems(readers_list);

    }

    @FXML public void buttonAddClicked(ActionEvent event) {

        if(textFieldName.getText().isEmpty() != true && textFieldSurname.getText().isEmpty() != true) {
            Functions.addReader(textFieldName.getText(), textFieldSurname.getText(), readers_list);        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION); //<-- if user would to add empty name or surname
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Type name and surname.");
            alert.showAndWait();
        }
    }

    @FXML public void buttonDeleteClicked(){
        Readers reader = tableReaders.getSelectionModel().getSelectedItem(); //<-- contain a selected reader to remove

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); //<-- display an alert before removing a reader
        alert.setTitle("Attention!");
        alert.setHeaderText("You just want to delete the Reader.");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            tableReaders.getItems().remove(reader);  //<-- remove reader from tableview
            Functions.deleteReader(reader);
        } else {
            //do nothink
        }

    }
}
