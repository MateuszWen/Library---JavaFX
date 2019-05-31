package pl.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ReturnABook implements Initializable {

    @FXML
    private TableView<Readers> tableReaders;
    @FXML private TableColumn<Readers, Integer> col_readersID;
    @FXML private TableColumn<Readers, String> col_name;
    @FXML private TableColumn<Readers, String> col_surname;

    @FXML private TableView<Books> tableBooks;
    @FXML private TableColumn<Books, Integer> col_booksID;
    @FXML private TableColumn<Books, String> col_title;
    @FXML private TableColumn<Books, String> col_author;
    @FXML private TableColumn<Books, Integer> col_borrowedOrNot;

    public static ObservableList<Books> outOfLibrary_list = Functions.getOutOfLibrary();
    public static ObservableList<Readers> selectedBookReader_list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_readersID.setCellValueFactory(new PropertyValueFactory<Readers, Integer>("readers_ID"));
        col_name.setCellValueFactory(new PropertyValueFactory<Readers, String>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<Readers, String>("surname"));
        tableReaders.setItems(selectedBookReader_list); //coś do zmieny

        col_booksID.setCellValueFactory(new PropertyValueFactory<Books, Integer>("books_ID"));
        col_title.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
        col_author.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        col_borrowedOrNot.setCellValueFactory(new PropertyValueFactory<Books, Integer>("borrowedOrNot"));
        tableBooks.setItems(outOfLibrary_list);

    }

    @FXML public void mouseRowClicked(javafx.scene.input.MouseEvent mouseEvent) {
        Books book = tableBooks.getSelectionModel().getSelectedItem();
        Functions.showReaderInTableNextTo(book, selectedBookReader_list);
    }


    @FXML public void buttonReturnClicked(){

    }


}
