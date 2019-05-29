package pl.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Functions {

    public static ObservableList<Readers> getReadersFunction(){

        ObservableList<Readers> list = FXCollections.observableArrayList();

        try (Connection c = DriverManager.getConnection("jdbc:sqlite:Library_db.db")) {
            try (Statement stm = c.createStatement()) {
                ResultSet rs = stm.executeQuery("SELECT * FROM Readers");
                while (rs.next()) {
                    list.add(new Readers(rs.getInt("readers_ID"), rs.getString("name"), rs.getString("surname")));
                }
                //c.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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
}

