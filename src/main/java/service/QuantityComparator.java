package service;

import package1Lib.Book;

import java.util.Comparator;

public class QuantityComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        return Double.compare(book1.getQuantity(), book2.getQuantity());
    }
}