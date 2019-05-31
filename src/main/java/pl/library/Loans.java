package pl.library;

import javafx.beans.property.SimpleIntegerProperty;

public class Loans {

    private SimpleIntegerProperty loans_ID;
    private SimpleIntegerProperty reader_ID;
    private SimpleIntegerProperty book_ID;

    public Loans(SimpleIntegerProperty loans_ID, SimpleIntegerProperty reader_ID, SimpleIntegerProperty book_ID) {
        this.loans_ID = loans_ID;
        this.reader_ID = reader_ID;
        this.book_ID = book_ID;
    }

    public Loans(Integer loans_ID, Integer reader_ID, Integer book_ID) {
        this.loans_ID = new SimpleIntegerProperty(loans_ID);
        this.reader_ID = new SimpleIntegerProperty(reader_ID);
        this.book_ID = new SimpleIntegerProperty(book_ID);
    }

    public Loans() {

    }

    public int getLoans_ID() {
        return loans_ID.get();
    }

    public SimpleIntegerProperty loans_IDProperty() {
        return loans_ID;
    }

    public void setLoans_ID(int loans_ID) {
        this.loans_ID.set(loans_ID);
    }

    public int getReader_ID() {
        return reader_ID.get();
    }

    public SimpleIntegerProperty reader_IDProperty() {
        return reader_ID;
    }

    public void setReader_ID(int reader_ID) {
        this.reader_ID.set(reader_ID);
    }

    public int getBook_ID() {
        return book_ID.get();
    }

    public SimpleIntegerProperty book_IDProperty() {
        return book_ID;
    }

    public void setBook_ID(int book_ID) {
        this.book_ID.set(book_ID);
    }
}
