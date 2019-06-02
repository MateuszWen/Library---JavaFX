package pl.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**All functions to service a program*/
public class Functions {

    /**Download all readers from DB*/
    public static ObservableList<Readers> getReadersFunction(){

        ObservableList<Readers> list_readers = FXCollections.observableArrayList();

        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db");
            try (Statement stm = c.createStatement()) {
                ResultSet rs = stm.executeQuery("SELECT * FROM Readers");
                while (rs.next()) {
                    list_readers.add(new Readers(rs.getInt("readers_ID"), rs.getString("name"), rs.getString("surname")));
                }
                //c.close();
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_readers;
    }

    /**Add new reader to list of readers and DB*/
    public static void addReader(String textfieldName, String textfieldSurname, ObservableList<Readers> list) {
        int lastReader = 0;

        String sqlOrder = "INSERT INTO Readers (name, surname) VALUES(' " + textfieldName + " ', ' " + textfieldSurname +" ') ";

        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db");
            try(Statement p = c.createStatement()){
                p.executeUpdate(sqlOrder);
            }
            try(Statement stm = c.createStatement()){
                ResultSet rs = stm.executeQuery("SELECT readers_ID, MAX (readers_ID) FROM Readers ");
                while(rs.next()){
                    lastReader = rs.getInt("readers_ID");
                }
            }
            c.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
            list.add(new Readers(lastReader, textfieldName, textfieldSurname)); //<-- adding reader to a list
    }

    /**Delete selected reader from list of readers and DB*/
    public static void deleteReader(Readers reader) {
        int deletingReaderID = reader.getReaders_ID();
        List<Books> booksToReturn = new LinkedList<>();
        try{
            Connection c= DriverManager.getConnection("jdbc:sqlite:Library_db.db");
            try(Statement p =c.createStatement()){
                p.executeUpdate("DELETE FROM Readers WHERE readers_ID = '" + reader.getReaders_ID() + " '");
            }
            try (Statement stm = c.createStatement()) {
                ResultSet rs = stm.executeQuery("SELECT book_ID FROM Loans WHERE reader_ID = " + deletingReaderID);
                while (rs.next()) {
                    booksToReturn.add(new Books(rs.getInt("book_ID")));
                }
                rs.close();
            }
            for(Books book : booksToReturn){ //<-- return all deleting user books
                returnABook(book);
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Download all books from DB*/
    public static ObservableList<Books> getBooksFunction(){

        ObservableList<Books> list_books = FXCollections.observableArrayList();

        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db");
            try (Statement stm = c.createStatement()) {
                ResultSet rs = stm.executeQuery("SELECT * FROM Books");
                while (rs.next()) {
                    list_books.add(new Books(rs.getInt("books_ID"), rs.getString("title"), rs.getString("author"), rs.getInt("borrowedOrNot")));
                }
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_books;
    }

    /**Add new book to list of books and DB*/
    public static void addBook(String textfieldTitle, String textfieldAuthor, ObservableList<Books> books_list) {
        int lastBook = 0;

        String sqlOrder = "INSERT INTO Books (title, author, borrowedOrNot) VALUES(' " + textfieldTitle + " ', ' " + textfieldAuthor +" ', 0) ";

        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db");
            try(Statement p = c.createStatement()){
                p.executeUpdate(sqlOrder);
            }
            try(Statement stm = c.createStatement()){
                ResultSet rs = stm.executeQuery("SELECT books_ID, MAX (books_ID) FROM Books ");
                while(rs.next()){
                    lastBook = rs.getInt("books_ID");
                }
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        books_list.add(new Books(lastBook, textfieldTitle, textfieldAuthor, 0));
    }

    /**Delete selected book from list of books and DB*/
    public static void deleteBook(Books book){
        int bookToDeleteForLoans = book.getBooks_ID();
        try{
            Connection c= DriverManager.getConnection("jdbc:sqlite:Library_db.db");
            try(Statement p =c.createStatement()){
                p.executeUpdate("DELETE FROM Books WHERE books_ID = '" + book.getBooks_ID() + "' ");
            }
            try (Statement p = c.createStatement()) {
                p.executeUpdate("DELETE FROM Loans WHERE book_ID = " + bookToDeleteForLoans);
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Download all loans from DB*/
    public static ObservableList<Loans> getLoansFunction() {

        ObservableList<Loans> list_loans = FXCollections.observableArrayList();

        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db");
            try (Statement stm = c.createStatement()) {
                ResultSet rs = stm.executeQuery("SELECT * FROM Loans");
                while (rs.next()) {
                    list_loans.add(new Loans(rs.getInt("loans_ID"), rs.getInt("reader_ID"), rs.getInt("book_ID")));
                }
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_loans;
    }

    /**Function saves the book as borrowed (set borrowedOrNot=0, and add loan to DB)*/
    public static void borrowABook(Readers reader, Books book, ObservableList<Loans> list) {
        int lastLoan = 0;

        String sqlOrder = "INSERT INTO Loans (reader_ID, book_ID) VALUES(' " + reader.getReaders_ID() + " ',  ' " + book.getBooks_ID() +" ' ) ";
        String sqlUpdate = "UPDATE Books SET borrowedOrNot = 1 WHERE books_ID = " + book.getBooks_ID() + " ";

        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db");
            try(Statement p = c.createStatement()){
                p.executeUpdate(sqlOrder);
                p.executeUpdate(sqlUpdate);
            }
            try(Statement stm = c.createStatement()){
                ResultSet rs = stm.executeQuery("SELECT loans_ID, MAX (loans_ID) FROM Loans ");
                while(rs.next()){
                    lastLoan = rs.getInt("loans_ID");
                }
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list.add(new Loans(lastLoan, reader.getReaders_ID(), book.getBooks_ID()));
        book.setBorrowedOrNot(1); //change to inform about borrowing in book_list
    }

    /**Download all loans from DB*/
    public static ObservableList<Books> getOutOfLibrary() {

        ObservableList<Books> list_outOfLibrarybooks = FXCollections.observableArrayList();

        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db");
            try (Statement stm = c.createStatement()) {
                ResultSet rs = stm.executeQuery("SELECT * FROM Books WHERE borrowedOrNot = 1");
                while (rs.next()) {
                    list_outOfLibrarybooks.add(new Books(rs.getInt("books_ID"), rs.getString("title"), rs.getString("author"), rs.getInt("borrowedOrNot")));
                }
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_outOfLibrarybooks;
    }

    /**Download reader from DB who borrow selected book*/
    public static void showReaderInTableNextTo(Books book, ObservableList<Readers> selectedBookReader_list) {
        Loans currentlySelectedLoan ;
        selectedBookReader_list.clear(); // <<-- to clearing selectedBookReader_list, it's necessary, because we want to show only one, who borrowed actually selected book

        try(Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db")){
            try(Statement stm = c.createStatement()){
                ResultSet rs = stm.executeQuery(" SELECT * FROM Loans WHERE book_ID = " + book.getBooks_ID() +" "); //<-- looking for selected book in DB
                    currentlySelectedLoan = new Loans(rs.getInt("loans_ID"), rs.getInt("reader_ID"), rs.getInt("book_ID")); //<-- there will be only one record
            }
            try(Statement stm = c.createStatement()){
                ResultSet rs = stm.executeQuery(" SELECT * FROM Readers WHERE readers_ID = " + currentlySelectedLoan.getReader_ID() +" ");   //<-- looking for reader who possess a book
                while(rs.next()) {
                    selectedBookReader_list.add(new Readers(rs.getInt("readers_ID"), rs.getString("name"), rs.getString("surname"))); //<-- there will be only one record
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Delete loans of returning book from datebase, and set up this book like available (borrowedOrNot = from 1 to 0) */
    public static void returnABook(Books book) {

        try {
            Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db");
            try (Statement p = c.createStatement()) {
                p.executeUpdate(" DELETE FROM Loans WHERE book_ID = " + book.getBooks_ID() + " ");
            }
            try (Statement p = c.createStatement()) {
                p.executeUpdate(" UPDATE Books SET borrowedOrNot = 0 WHERE books_ID = "+ book.getBooks_ID() +" ");
            }
            ReturnABook.outOfLibrary_list = Functions.getOutOfLibrary(); //<-- update outOfLibrary_list, to show actually borrowed books
            AddDeleteBook.books_list = Functions.getBooksFunction();  //<-- update outOfLibrary_list, to show that retaked book is currently available
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

