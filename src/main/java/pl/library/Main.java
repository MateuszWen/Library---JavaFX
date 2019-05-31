package pl.library;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application{



    @Override
    public void start(Stage primaryStage) throws Exception{

//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(this.getClass().getResource("/fxml/Main.fxml"));
//        Parent anchorPane = loader.load();
//
//        Scene scene = new Scene(anchorPane);
//
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Library");
//        primaryStage.show();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        primaryStage.setTitle("Library");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
