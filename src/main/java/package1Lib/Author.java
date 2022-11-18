package package1Lib;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Author {
    private String name; // rename
    private LocalDate date; // LocalDate
    private List<Book> books; // books

    public Author(AuthorBuilder authorbuilder) {
        this.name = authorbuilder.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return Objects.equals(getName(), author.getName()) && Objects.equals(getDate(), author.getDate()) && Objects.equals(getBooks(), author.getBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDate(), getBooks());
    }

    @Override
    public String toString() {
        return "AuthorName='" + name + '\'' +
                ", dateOfBirth=" + date +
                ", AuthorBook=" + books +
                '}';
    }

    //Builder Class
    public static class AuthorBuilder {

        // required parameters
        private String name;
        private LocalDate date;
        private List<Book> books;

        public AuthorBuilder(String name, LocalDate date, List<Book> books) {
            this.name = name;
            this.date = date;
            this.books = books;
        }

        public Author build() {
            return new Author(this);
        }

    }
}
