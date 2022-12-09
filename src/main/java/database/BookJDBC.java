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
                "id_book serial PRIMARY KEY, " +
                "ibsn VARCHAR(11) NOT NULL, " +
                "genre VARCHAR(30) NOT NULL, " +
                "quantity INT DEFAULT NULL, " +
                "id_author INT," +
                "FOREIGN KEY (id_author) REFERENCES  author(id_author) " +
                ");";
        return statement.execute(book);
    }

    public Boolean  createAuthor() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String author = "CREATE TABLE author(" +
                    "id_author serial PRIMARY KEY," +
                    "dateOfBirth DATE NOT NULL" +
                    ");";
            return statement.execute(author);
        }
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

    public static void main(String[] args) throws SQLException, IOException {
        BookJDBC bookJDBC = new BookJDBC();
        bookJDBC.dropAuthor();
        bookJDBC.dropBook();
        bookJDBC.createAuthor();
        bookJDBC.createBook();

    }

}
