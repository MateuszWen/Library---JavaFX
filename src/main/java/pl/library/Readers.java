package pl.library;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

public class Readers {

    private SimpleIntegerProperty readers_ID;
    private SimpleStringProperty name;
    private SimpleStringProperty surname;

    public Readers(SimpleIntegerProperty readers_ID,SimpleStringProperty name, SimpleStringProperty surname) {
        this.readers_ID = readers_ID;
        this.name = name;
        this.surname = surname;
    }
    public Readers(Integer readers_ID, String name, String surname){
        this.readers_ID = new SimpleIntegerProperty(readers_ID);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
    }

    public int getReaders_ID() {
        return readers_ID.get();
    }

    public SimpleIntegerProperty readers_IDProperty() {
        return readers_ID;
    }

    public void setReaders_ID(int readers_ID) {
        this.readers_ID.set(readers_ID);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }
}
