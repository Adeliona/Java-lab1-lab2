package package1Lib;

import java.util.List;
import java.util.Objects;


public class Book {

    // +javadocs, rename values,
    public enum Genre {FANTASY, DETECTIVE, DRAMA, ROMANCE, THRILLER}

    private String ibsn;
    private String name;
    private boolean availability;
    private  Genre genre;
    private int quantity;

    public Book(String ibsn, String name, Genre genre, int quantity){
        this.ibsn = ibsn;
        this.name = name;
        this.genre = genre;
        this.quantity = quantity;
    }
    public Book(BookBuilder bookbuilder) {
        this.ibsn = bookbuilder.ibsn;
        this.name = bookbuilder.name;
        this.genre = bookbuilder.genre;
        this.quantity = bookbuilder.quantity;
        this.availability = bookbuilder.availability;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getIbsn() {
        return ibsn;
    }

    public void setIbsn(String ibsn) {
        this.ibsn = ibsn; //isbn
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) { this.quantity = quantity;}
    @Override
    public boolean equals(Object o) {               // edit
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return getQuantity() == book.getQuantity()  && isAvailability() == book.isAvailability() && Objects.equals(getIbsn(), book.getIbsn()) && Objects.equals(getName(), book.getName()) && getGenre() == book.getGenre();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIbsn(), getName(), isAvailability(), getGenre(), getQuantity());
    }

    @Override
    public String toString() {
        return "{ IBSN='" + ibsn + '\'' +
                ", name='" + name + '\'' +
                ", availability=" + availability +
                ", genre=" + genre +
                ", quantity=" + quantity +
                '}';
    }

    //Builder Class
    public static class BookBuilder {

        // required parameters
        private String ibsn;
        private String name;
        private boolean availability;
        private Genre genre;

        private int quantity;
        public BookBuilder(String ibsn, String name, Genre genre, int quantity) {
            this.ibsn = ibsn;
            this.name = name;
            this.genre = genre;
            this.quantity = quantity;
            this.availability = quantity > 0;
        }

        public BookBuilder setAvailability(boolean value) {
            availability = value;
            return this;
        }

        public Book build() {
            return new Book(this);
        }

    }

    public Book(){}

    public static void main(String[] args) {
       /* Book book = new Book("0054","OHiO", Genre.FANTASY,44);
        book.setGenre(Genre.FANTASY);
        book.setAvailability(true);
        book.toString();
        System.out.println(book);
        //book();
        Book book2 = new Book.BookBuilder("hello","there", Genre.ROMANCE, 55).build();
        book2.toString();
        System.out.println(book2);*/
    }

}

