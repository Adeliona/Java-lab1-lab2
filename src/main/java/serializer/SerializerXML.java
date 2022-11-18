package serializer;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import package1Lib.Book;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerializerXML implements Serializer{
    @Override
    public List<Book> listFromFile(String fileName) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(new File(fileName), new TypeReference<>() {});
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public Book fromFile(String fileName){
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(new File(fileName), Book.class);
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public void toFile(Book book, String fileName){
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File(fileName), book);
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public void listToFile(List<Book> book, String fileName){
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.writeValue(new File(fileName), book);
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    public static void main(String args[]) {

        Book book = new Book();
        book.setIbsn("77885544110");
        book.setName("Sweet Dreams");
        book.setAvailability(true);
        book.setGenre(Book.Genre.FANTASY);
        Serializer serializer = new SerializerXML();
        serializer.toFile(book, "BookXML");

        List<Book> books = new ArrayList<>();
        books.add(book);
        book = new Book();
        book.setIbsn("00255006893");
        book.setName("Taub Morry");
        book.setAvailability(false);
        book.setGenre(Book.Genre.ROMANCE);
        books.add(book);
        serializer.listToFile(books, "BooksXML");

        book = serializer.fromFile("BookXML");
        System.out.println(book);
        books = serializer.listFromFile("BooksXML");
        System.out.println(books);
    }

}
