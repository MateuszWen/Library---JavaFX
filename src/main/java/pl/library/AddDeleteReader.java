package pl.library;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AddDeleteReader implements Initializable {

        @FXML private TextField textFieldName;
        @FXML private TextField textFieldSurname;
        @FXML private Button buttonAdd;
        @FXML private Button buttonDelete;
        @FXML private TableView<Readers> tableReaders;
        @FXML private TableColumn<Readers, Integer> col_readersID;
        @FXML private TableColumn<Readers, String> col_name;
        @FXML private TableColumn<Readers, String> col_surname;


        public static ObservableList<Readers> readers_list = Functions.getReadersFunction();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_readersID.setCellValueFactory(new PropertyValueFactory<Readers, Integer>("readers_ID"));
        col_name.setCellValueFactory(new PropertyValueFactory<Readers, String>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<Readers, String>("surname"));
        tableReaders.setItems(readers_list);

    }

    @FXML public void buttonAddClicked(ActionEvent event) {
        Functions.addReader(textFieldName.getText(), textFieldSurname.getText(), readers_list);

    }
}
