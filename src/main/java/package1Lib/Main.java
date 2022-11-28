package package1Lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book.BookBuilder("00245866421", "Lora in the beauty world",Book.Genre.DETECTIVE, 11).build();
        Book book2 = new Book.BookBuilder("55864423900", "James", Book.Genre.DRAMA, 32).build();
        Book book3 = new Book.BookBuilder("55584684115", "ODA ore me", Book.Genre.ROMANCE, 15).build();
        Book book4 = new Book.BookBuilder("88514521452", "Selani ova ke teo", Book.Genre.ROMANCE, 5).build();
        Book book5 = new Book.BookBuilder("03589526845", "Chemi onae", Book.Genre.THRILLER, 0).build();

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        PublishingHouse publishingHouse1 = new PublishingHouse.PublishingHouseBuilder("Antario","Poland", "Warsaw", books).build();

        BookQuantityService bookQuantityService = new BookQuantityService(publishingHouse1);

        BookStream bookStream = new BookStream(publishingHouse1);

        //books.forEach(System.out::println);
        System.out.println("\n quantityUp SERVICE");
        Set<Book> printBooksList1 = bookQuantityService.quantityUp(12, Sort.ASC);
        printBooksList1.forEach(System.out::println);

        System.out.println("\n quantityDown SERVICE");
        Set<Book> printBooksList2 =  bookQuantityService.quantityDown(20, Sort.DSC);
        printBooksList2.forEach(System.out::println);

        System.out.println("\n from number to number SERVICE");
        Set<Book> printBooksList3 = bookQuantityService.middleSort(3, 30, Sort.DSC);
        printBooksList3.forEach(System.out::println);

        System.out.println("\n Sort STREAM sortQuantityUp");
        List<Book> printBooksList4 = bookStream.sortQuantityUp(12);
        printBooksList4.forEach(System.out::println);

        System.out.println("\n Sort STREAM sortQuantityDown");
        List<Book> printBooksList5 = bookStream.sortQuantityDown(20);
        printBooksList5.forEach(System.out::println);

        System.out.println("\n Sort STREAM middleSort");
        List<Book> printBooksList6 = bookStream.middleSort(14, 35);
        printBooksList6.forEach(System.out::println);

    }
}