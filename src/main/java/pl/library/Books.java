package pl.library;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Books {

    private SimpleIntegerProperty books_ID;
    private SimpleStringProperty title;
    private SimpleStringProperty author;
    private SimpleIntegerProperty borrowedOrNot;

    public Books(SimpleIntegerProperty books_ID, SimpleStringProperty title, SimpleStringProperty author, SimpleIntegerProperty borrowedOrNot) {
        this.books_ID = books_ID;
        this.title = title;
        this.author = author;
        this.borrowedOrNot = borrowedOrNot;
    }

    public Books(Integer books_ID, String title, String author, Integer borrowedOrNot) {
        this.books_ID = new SimpleIntegerProperty(books_ID);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.borrowedOrNot = new SimpleIntegerProperty(borrowedOrNot);
    }
    public Books(Integer books_ID) {
        this.books_ID = new SimpleIntegerProperty(books_ID);
        this.title = null;
        this.author = null;
        this.borrowedOrNot = null;
    }

    public int getBooks_ID() {
        return books_ID.get();
    }

    public SimpleIntegerProperty books_IDProperty() {
        return books_ID;
    }

    public void setBooks_ID(int books_ID) {
        this.books_ID.set(books_ID);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public int getBorrowedOrNot() {
        return borrowedOrNot.get();
    }

    public SimpleIntegerProperty borrowedOrNotProperty() {
        return borrowedOrNot;
    }

    public void setBorrowedOrNot(int borrowedOrNot) {
        this.borrowedOrNot.set(borrowedOrNot);
    }
}
