package pl.library;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController  {

    @FXML
    private Button buttonAddDeleteReader;
    @FXML
    private Button buttonAddDeleteBook;
    @FXML
    private Button buttonBorrowABook;
    @FXML
    private Button buttonReturnABook;


    //public static ObservableList<Readers> list = (ObservableList<Readers>) FXCollections.observableList(new Readers("Anna", "Grodzka"));


    @FXML public void buttonAddDeleteReaderClicked(ActionEvent actionEvent) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AddReader.fxml"));
//        Stage stage = new Stage();
//        stage.setTitle("Add Reader");
//        stage.setScene(new Scene(root));
//        stage.show();

//        FXMLLoader loader = new FXMLLoader((getClass().getResource("/resources/fxml/AddReader.fxml")));
//        Parent root1 = loader.load();
//        Stage stage = new Stage();
//        stage.setTitle("Library");
//        stage.setScene(new Scene(root1));
//        stage.show();

//        Parent root;
//        try {
//            root = FXMLLoader.load(getClass().getClassLoader().getResource("/resources/fxml/AddReader.fxml"));
//            Stage stage = new Stage();
//            stage.setTitle("Library");
//            stage.setScene(new Scene(root));
//            stage.show();
//            // Hide this current window (if this is what you want)
//            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/AddReader.fxml"));
        primaryStage.setTitle("Add or Delete Reader");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML public void buttonAddDeleteBookClicked() throws IOException{
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/AddDeleteBookFXML.fxml"));
        primaryStage.setTitle("Add or Delete Book");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML public void buttonBorrowABookClicked() throws IOException{
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/BorrowABookFXML.fxml"));
        primaryStage.setTitle("Borrow A Book");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML public void buttonReturnABookClicked() throws IOException{
        ReturnABook.outOfLibrary_list = Functions.getOutOfLibrary();
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/ReturnABookFXML.fxml"));
        primaryStage.setTitle("Return A Book");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
