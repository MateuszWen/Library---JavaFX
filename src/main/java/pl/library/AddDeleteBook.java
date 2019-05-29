package pl.library;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddDeleteBook implements Initializable {

    @FXML private TextField textFieldTitle;
    @FXML private TextField textFieldAuthor;
    @FXML private Button buttonAdd;
    @FXML private Button buttonDelete;
    @FXML private TableView<Books> tableBooks;
    @FXML private TableColumn<Books, Integer> col_booksID;
    @FXML private TableColumn<Books, String> col_title;
    @FXML private TableColumn<Books, String> col_author;
    @FXML private TableColumn<Books, Integer> col_borrowedOrNot;


    public static ObservableList<Books> books_list = Functions.getBooksFunction();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_booksID.setCellValueFactory(new PropertyValueFactory<Books, Integer>("books_ID"));
        col_title.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
        col_author.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        col_borrowedOrNot.setCellValueFactory(new PropertyValueFactory<Books, Integer>("borrowedOrNot"));
        tableBooks.setItems(books_list);

    }

    @FXML public void buttonAddClicked(){
        Functions.addBook(textFieldTitle.getText(), textFieldAuthor.getText(), books_list);

    }
    @FXML public void buttonDeleteClicked(){
        Books book = tableBooks.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Attention!");
        alert.setHeaderText("You just want to delete the book.");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            tableBooks.getItems().remove(book);
            Functions.deleteBook(book);
        } else {
            //do nothink
        }

    }
}