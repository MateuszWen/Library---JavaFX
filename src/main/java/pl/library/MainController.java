package pl.library;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**Main Controller class of program. */
public class MainController  {

    @FXML private Button buttonAddDeleteReader;
    @FXML private Button buttonAddDeleteBook;
    @FXML private Button buttonBorrowABook;
    @FXML private Button buttonReturnABook;


    @FXML public void buttonAddDeleteReaderClicked() throws IOException {
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
        ReturnABook.outOfLibrary_list = Functions.getOutOfLibrary(); //<-- without this instruction, after borrowed some books, in ReturnABookFXML window we can't see possible books to give away
        Stage primaryStage = new Stage();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/ReturnABookFXML.fxml"));
        primaryStage.setTitle("Return A Book");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
