package service;

import package1Lib.Book;
import package1Lib.PublishingHouse;

import java.util.*;


public class BookQuantityService {
    private PublishingHouse publishingHouse;
    private QuantityComparator quatityComparator;

    public BookQuantityService(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
        quatityComparator = new QuantityComparator();
    }

    public Set<Book> aboveQuantity(int quantity, Sort type) {
        TreeSet<Book> books = new TreeSet<Book>(quatityComparator);
        for (Book book : publishingHouse.getBooks()) {
            if (book.getQuantity() >= quantity) {
                books.add(book);
            }
        }
        if (type == Sort.ASC){
            return books;
        }
        return books.descendingSet();
    }

    public Set<Book> belowQuantity(int quantity, Sort type) {
        TreeSet<Book> books = new TreeSet<Book>(quatityComparator);
        for (Book book : publishingHouse.getBooks()) {
            if (book.getQuantity() <= quantity) {
                books.add(book);
            }
        }
        if (type == Sort.DSC){
            return books;
        }
        return books.descendingSet();
    }


    public Set<Book> betweenQuantity(int fromQuantity, int toQuantity, Sort type) {
        TreeSet<Book> books = new TreeSet<Book>(quatityComparator);
        for (Book book : publishingHouse.getBooks()) {
            if (book.getQuantity() >= fromQuantity && book.getQuantity()<=toQuantity) {
                books.add(book);
            }
        }
        if (type==Sort.ASC){
            return books;
        }
        return books.descendingSet();
    }

}


