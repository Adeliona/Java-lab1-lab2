package database;

import java.sql.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static java.sql.DriverManager.getConnection;

public class BookJDBC {
    Connection connection;

    public BookJDBC() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("C:\\Users\\User\\IdeaProjects\\lab1Library\\src\\main\\resources\\jdbc.properties"));
        try {
            connection = getConnection(
                    properties.getProperty("db.host"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean createBook() throws SQLException, IOException {
        Statement statement = connection.createStatement();
        String book = "CREATE TABLE book(" +
                "id serial PRIMARY KEY, " +
                "ibsn VARCHAR(11) NOT NULL, " +
                "genre VARCHAR(30) NOT NULL, " +
                "quantity INT DEFAULT NULL, " +
                "id_author INT," +
                "id_publishing_house INT," +
                "FOREIGN KEY (id_author) REFERENCES  author(id)," +
                "FOREIGN KEY (id_publishing_house) REFERENCES  publishing_house(id)" +
                ");";
        return statement.execute(book);
    }

    public Boolean  createAuthor() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String author = "CREATE TABLE author(" +
                    "id serial PRIMARY KEY," +
                    "dateOfBirth DATE NOT NULL" +
                    ");";
            return statement.execute(author);
        }
    }

    public Boolean  createPublishingHouse() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String publishing_house = "CREATE TABLE publishing_house(" +
                    "id serial PRIMARY KEY," +
                    "name DATE NOT NULL," +
                    "country VARCHAR(30) NOT NULL," +
                    "city VARCHAR(30) NOT NULL" +
                    ");";
            return statement.execute(publishing_house);
        }
    }

    public void  dropPublishingHouse() throws SQLException {
        Statement statement = connection.createStatement();
        String dropPublishingHouse = "DROP TABLE IF EXISTS publishing_house;";
        statement.execute(dropPublishingHouse);
    }
    public void  dropBook() throws SQLException {
        Statement statement = connection.createStatement();
        String dropBook = "DROP TABLE IF EXISTS book;";
        statement.execute(dropBook);
    }

    public void  dropAuthor() throws SQLException {
        Statement statement = connection.createStatement();
        String dropAuthor = "DROP TABLE IF EXISTS author;";
        statement.execute(dropAuthor);
    }

    public Boolean createTables() throws SQLException, IOException {
        return  createAuthor() && createPublishingHouse() && createBook();
    }

    public  void dropTables() throws SQLException, IOException {
        dropBook();
        dropPublishingHouse();
        dropAuthor();
    }
    public static void main(String[] args) throws SQLException, IOException {
        BookJDBC bookJDBC = new BookJDBC();

//        bookJDBC.dropBook();
//        bookJDBC.dropAuthor();
//        bookJDBC.dropPublishingHouse();
//
//        bookJDBC.createAuthor();
//        bookJDBC.createPublishingHouse();
//        bookJDBC.createBook();

        bookJDBC.dropTables();
        bookJDBC.createTables();
    }

}
