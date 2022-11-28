package package1Lib;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class BookStream {
    private PublishingHouse publishingHouse;
    public BookStream(PublishingHouse publishingHouse){this.publishingHouse = publishingHouse;}
    public PublishingHouse getPublishingHouse(){return publishingHouse;}
    public  void setPublishingHouse(PublishingHouse publishingHouse){this.publishingHouse = publishingHouse;}

    public List<Book> sortQuantityUp(int quantity){
        return publishingHouse.getBooks().stream()
                .filter(book -> book.getQuantity()>=quantity)
                .sorted(Comparator.comparing(Book::getQuantity))
                .collect(Collectors.toList());
    }


    public List<Book> sortQuantityDown(int quantity){
        return publishingHouse.getBooks().stream()
                .filter(car -> car.getQuantity()<=quantity)
                .sorted(Comparator.comparing(Book::getQuantity))
                .collect(Collectors.toList());
    }


    public List<Book> middleSort(int fromQuantity, int toQuantity){
        return publishingHouse.getBooks().stream()
                .filter(book -> book.getQuantity()>=fromQuantity && book.getQuantity()<=toQuantity)
                .sorted(Comparator.comparing(Book::getQuantity))
                .collect(Collectors.toList());
    }

}
