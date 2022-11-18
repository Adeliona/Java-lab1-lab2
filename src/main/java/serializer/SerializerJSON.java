package serializer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import package1Lib.Book;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class SerializerJSON implements Serializer {

    @Override
    public List<Book> listFromFile(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(fileName), new TypeReference<>() {
            });
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public Book fromFile(String fileName){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(fileName), Book.class);
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public void toFile(Book book, String fileName){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(fileName), book);
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public void listToFile(List<Book> book, String fileName){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(fileName), book);
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    public static void main(String args[]) {

        Book book = new Book();
        book.setIbsn("11477855419");
        book.setName("The art of love");
        book.setAvailability(true);
        book.setGenre(Book.Genre.ROMANCE);
        Serializer serializer = new SerializerJSON();
        serializer.toFile(book, "BookJSON");

        List<Book> books = new ArrayList<>();
        books.add(book);
        book = new Book();
        book.setIbsn("11558866339");
        book.setName("Aragon");
        book.setAvailability(false);
        book.setGenre(Book.Genre.FANTASY);
        books.add(book);
        serializer.listToFile(books, "BooksJSON");

        book = serializer.fromFile("BookJSON");
        System.out.println(book);
        books = serializer.listFromFile("BooksJSON");
        System.out.println(books);
    }
}
