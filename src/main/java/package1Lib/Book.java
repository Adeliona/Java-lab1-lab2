package package1Lib;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.*;


public class Book implements Comparable<Book> {
    public enum Genre {FANTASY, DETECTIVE, DRAMA, ROMANCE, THRILLER}

    @Pattern(regexp = "[0-9]{11}]", message = "IBSN must contains 11 digitals." )
    private String ibsn;
    private String name;
    private boolean availability;
    private Genre genre;
    @Positive(message = "Quantity should be positive.")
    private int quantity;

    public Book(String ibsn, String name, Genre genre, int quantity) {
        this.ibsn = ibsn;
        this.name = name;
        this.genre = genre;
        this.quantity = quantity;
    }

    public Book() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {               // edit
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return getQuantity() == book.getQuantity() && isAvailability() == book.isAvailability() && Objects.equals(getIbsn(), book.getIbsn()) && Objects.equals(getName(), book.getName()) && getGenre() == book.getGenre();
    }

    @Override
    public int compareTo(@NotNull Book book) {
        if (this.quantity == book.quantity) {
            return 0;
        }
        if (this.quantity > book.quantity) {
            return 1;
        } else {
            return -1;
        }

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

        private Book book;

        public BookBuilder() {
            book = new Book();
        }

        public BookBuilder setAvailability(boolean value) {
            book.availability = value;
            return this;
        }

       /* public Book build() {
            return new Book(this);
        }*/

        public BookBuilder setIbsn(String ibsn) {
            book.ibsn = ibsn;
            return this;
        }

        public BookBuilder setName(String name) {
            book.name = name;
            return this;
        }

        public BookBuilder setGenre(Genre genre) {
            book.genre = genre;
            return this;
        }

        public BookBuilder setQuantity(int quantity) {
            book.quantity = quantity;
            return this;
        }

        public Book build() {
            validate(book);
            return book;
        }
    }

    private static void validate(Book book) throws IllegalArgumentException {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        StringBuilder errorMessage = new StringBuilder();

        for(ConstraintViolation<Book> e : violations){
            errorMessage.append("Error. " + "Input value: " + e.getInvalidValue()+ " is incorrect. "+ e.getMessage()); //
            errorMessage.append("\n");
        }

        if(errorMessage.length() > 0){
            throw new IllegalArgumentException(errorMessage.toString());
        }
    }

    public static void main(String args[]){
        try{
            Book book = new BookBuilder().setIbsn("2b457").setName("Jerry and Ted").setGenre(Book.Genre.DETECTIVE).setQuantity(-6).build();
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

}

