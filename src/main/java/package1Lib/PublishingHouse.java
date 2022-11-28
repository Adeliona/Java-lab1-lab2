package package1Lib;

import java.util.List;
import java.util.Objects;
public class PublishingHouse {
    private String name; // rename
    private String country;
    private String city;
    private List<Book> books;


    public PublishingHouse(PublishingHouseBuilder publishinghousebuilder) {
        this.name = publishinghousebuilder.name;
        this.country = publishinghousebuilder.country;
        this.city = publishinghousebuilder.city;
        this.books = publishinghousebuilder.books;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        if (!(o instanceof PublishingHouse that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getCountry(), that.getCountry()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getBooks(), that.getBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCountry(), getCity(), getBooks());
    }

    @Override
    public String toString() {
        return "PublishingHouseName='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", PublishingHouseBook=" + books +
                '}';
    }

    public static class PublishingHouseBuilder {

        // required parameters
        private String name;
        private String country;
        private String city;
        private List<Book> books;

        public PublishingHouseBuilder(String name, String country, String city, List<Book> books) {
            this.name = name;
            this.country = country;
            this.city = city;
            this.books = books;
        }

        public PublishingHouse build() {
            return new PublishingHouse(this);
        }

    }
}
