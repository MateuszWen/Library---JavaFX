package pl.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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
}

