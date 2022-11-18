package serializer;

import package1Lib.Book;
import java.util.List;

public interface Serializer {
    List<Book> listFromFile(String fileName);
    Book fromFile(String fileName);
    void toFile(Book book, String fileName);
    void listToFile(List<Book> books, String fileName);
}
