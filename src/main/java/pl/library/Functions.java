package pl.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Functions {

    public static ObservableList<Readers> getReadersFunction(){
        ObservableList<Readers> list = FXCollections.observableArrayList();
        for(int i = 0; i<=250; i++) {
            list.add();
        }
        return list;
    }

    }
}
