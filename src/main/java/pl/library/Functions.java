package pl.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

import static java.sql.Types.NULL;

public class Functions {

    public static ObservableList<Readers> getReadersFunction(){

        ObservableList<Readers> list_readers = FXCollections.observableArrayList();

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db")) {
            try (Statement stm = c.createStatement()) {
                ResultSet rs = stm.executeQuery("SELECT * FROM Readers");
                while (rs.next()) {
                    list_readers.add(new Readers(rs.getInt("readers_ID"), rs.getString("name"), rs.getString("surname")));
                }
                //c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_readers;
    }

    public static void addReader(String textfieldName, String textfieldSurname, ObservableList<Readers> list) {

        String sqlOrder = "INSERT INTO Readers (name, surname) VALUES(' " + textfieldName + " ', ' " + textfieldSurname +" ') ";
//        try (Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db")) {
//            try(PreparedStatement p = c.prepareStatement(sqlOrder)){
//                System.out.println("jestem tu");
//
//                p.setString(1, "'" + textfieldName + "'");
//                p.setString(2, "'" + textfieldSurname + "'");
//                System.out.println("jestem tu");
//
//                //c.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db")) {
            try(Statement p = c.createStatement()){
                p.executeUpdate(sqlOrder);
//                p.setString(1, "'" + textfieldName + "'");
//                p.setString(2, "'" + textfieldSurname + "'");

                //c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            int lastReaderID = list.get(list.size() - 1).getReaders_ID();
                list.add(new Readers(lastReaderID + 1, textfieldName, textfieldSurname));
    }

    public static void deleteReader(Readers reader) {
        try(Connection c= DriverManager.getConnection("jdbc:sqlite:Library_db.db")){
            try(Statement p =c.createStatement()){
                p.executeUpdate("DELETE FROM Readers WHERE readers_ID = '" + reader.getReaders_ID() + " '");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Books> getBooksFunction(){

        ObservableList<Books> list_books = FXCollections.observableArrayList();

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db")) {
            try (Statement stm = c.createStatement()) {
                ResultSet rs = stm.executeQuery("SELECT * FROM Books");
                while (rs.next()) {
                    list_books.add(new Books(rs.getInt("books_ID"), rs.getString("title"), rs.getString("author"), rs.getInt("borrowedOrNot")));
                }
                //c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_books;
    }

    public static void addBook(String textfieldTitle, String textfieldAuthor, ObservableList<Books> books_list) {

        String sqlOrder = "INSERT INTO Books (title, author, borrowedOrNot) VALUES(' " + textfieldTitle + " ', ' " + textfieldAuthor +" ', 0) ";

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db")) {
            try(Statement p = c.createStatement()){
                p.executeUpdate(sqlOrder);

                //c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int lastBookID = books_list.get(books_list.size() - 1).getBooks_ID();
        books_list.add(new Books(lastBookID + 1, textfieldTitle, textfieldAuthor, 0));
    }

    public static void deleteBook(Books book){
        try(Connection c= DriverManager.getConnection("jdbc:sqlite:Library_db.db")){
            try(Statement p =c.createStatement()){
                p.executeUpdate("DELETE FROM Books WHERE books_ID = '" + book.getBooks_ID() + "' ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ObservableList<Loans> getLoansFunction() {

        ObservableList<Loans> list_loans = FXCollections.observableArrayList();

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db")) {
            try (Statement stm = c.createStatement()) {
                ResultSet rs = stm.executeQuery("SELECT * FROM Loans");
                while (rs.next()) {
                    list_loans.add(new Loans(rs.getInt("loans_ID"), rs.getInt("reader_ID"), rs.getInt("book_ID")));
                }
                //c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_loans;
    }

    public static void borrowABook(Readers reader, Books book, ObservableList<Loans> list) {/////////////////////////////////////////////////////////////////////////////

        String sqlOrder = "INSERT INTO Loans (reader_ID, book_ID) VALUES(' " + reader.getReaders_ID() + " ',  ' " + book.getBooks_ID() +" ' ) ";
        String sqlUpdate = "UPDATE Books SET borrowedOrNot = 1 WHERE books_ID = " + book.getBooks_ID() + " ";

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db")) {
            try(Statement p = c.createStatement()){
                p.executeUpdate(sqlOrder);
                p.executeUpdate(sqlUpdate);
                //c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(list.size() == NULL){
            list.add(new Loans(1, reader.getReaders_ID(), book.getBooks_ID()));
            book.setBorrowedOrNot(1); //change to inform about borrowing in book_list
        }else{
            int lastLoanID = list.get(list.size()-1).getLoans_ID();
            list.add(new Loans(lastLoanID + 1, reader.getReaders_ID(), book.getBooks_ID()));
            book.setBorrowedOrNot(1); //change to inform about borrowing in book_list
        }

    }


    public static ObservableList<Books> getOutOfLibrary() {

        ObservableList<Books> list_outOfLibrarybooks = FXCollections.observableArrayList();

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db")) {
            try (Statement stm = c.createStatement()) {
                ResultSet rs = stm.executeQuery("SELECT * FROM Books WHERE borrowedOrNot = 1");
                while (rs.next()) {
                    list_outOfLibrarybooks.add(new Books(rs.getInt("books_ID"), rs.getString("title"), rs.getString("author"), rs.getInt("borrowedOrNot")));
                }
                //c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_outOfLibrarybooks;
    }


    public static void showReaderInTableNextTo(Books book, ObservableList<Readers> selectedBookReader_list) {
        Loans currentlySelectedLoan = null;
        selectedBookReader_list.clear(); // <<-- to clearing selectedBookReader_list

        try(Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db")){
            try(Statement stm = c.createStatement()){
                ResultSet rs = stm.executeQuery(" SELECT * FROM Loans WHERE book_ID = " + book.getBooks_ID() +" ");
                while (rs.next()) {
                    currentlySelectedLoan = new Loans(rs.getInt("loans_ID"), rs.getInt("reader_ID"), rs.getInt("book_ID"));
                }
            }
            try(Statement stm = c.createStatement()){
                ResultSet rs = stm.executeQuery(" SELECT * FROM Readers WHERE readers_ID = " + currentlySelectedLoan.getReader_ID() +" ");
                while (rs.next()) {
                    selectedBookReader_list.add(new Readers(rs.getInt("readers_ID"), rs.getString("name"), rs.getString("surname")));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

