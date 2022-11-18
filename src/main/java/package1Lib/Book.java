package package1Lib;

import java.util.Objects;


public class Book {
    // +javadocs, rename values,
    public enum Genre {FANTASY, DETECTIVE, DRAMA, ROMANCE, THRILLER}

    private String ibsn;
    private String name;
    private boolean availability;
    private  Genre genre;

    public Book(String ibsn, String name, Genre genre){
        this.ibsn = ibsn;
        this.name = name;
//        this.genre = genre;
    }
    public Book(BookBuilder bookbuilder) {
        this.name = bookbuilder.name;
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

    @Override
    public boolean equals(Object o) {               // edit
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return isAvailability() == book.isAvailability() && Objects.equals(getIbsn(), book.getIbsn()) && Objects.equals(getName(), book.getName()) && getGenre() == book.getGenre();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIbsn(), getName(), isAvailability(), getGenre());
    }

    @Override
    public String toString() {
        return "id='" + ibsn + '\'' +
                ", name='" + name + '\'' +
                ", availability=" + availability +
                ", genre=" + genre +
                '}';
    }

    //Builder Class
    public static class BookBuilder {

        // required parameters
        private String ibsn;
        private String name;
        private boolean availability;
        private Genre genre;
        public BookBuilder(String ibsn, String name, Genre genre) {
            this.ibsn = ibsn;
            this.name = name;
            this.genre = genre;
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
        Book book = new Book("0054","OHiO", Genre.FANTASY);
        book.setGenre(Genre.FANTASY);
        book.setAvailability(true);
        book.toString();
        //book();
        Book book2 = new Book.BookBuilder("hello","there", Genre.ROMANCE).build();
        book2.toString();
        System.out.println(book2);
    }

}

