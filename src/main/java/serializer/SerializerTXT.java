package serializer;

import package1Lib.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializerTXT implements Serializer {

    private String toString(Book bk) {
        return
                "IBSN-" + bk.getIbsn() + "-" +
                        "nameOfBook-" + bk.getName() + "-" +
                        "Availability-" + bk.isAvailability() + "-" +
                        "Genre-" + bk.getGenre()
                ;
    }

    private String toString(List<Book> bk) {
        String res = "";
        for (int i=0; i<bk.size(); i++) {
            res += toString(bk.get(i));
            if (i+1 < bk.size()) {
                res += "||";
            }
           System.out.println();
        }
        return res;
    }

    private  Book fromString(String s){
        String [] obj = s.split("/");
        Book res = new Book();
        res.setIbsn(obj[1]);
        res.setName(obj[3]);
        return res;
    }

    private  List<Book> fromStringList(String s){
        String [] obj = s.split("/");
        List<Book> res = new ArrayList<>();
        for(int i = 0; i < obj.length; i += 6){
            Book bk = new Book();
            bk.setIbsn(obj[i+1]);
            bk.setName(obj[i+3]);
            res.add(bk);
        }
        return res;
    }

    @Override
    public List<Book> listFromFile(String fileName) {
        try (
                FileReader fw = new FileReader(new File(fileName));
                BufferedReader bw = new BufferedReader(fw)) {
            return fromStringList(bw.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book fromFile(String fileName) {
        try (
                FileReader fw = new FileReader(new File(fileName));
                BufferedReader bw = new BufferedReader(fw)) {
            return fromString(bw.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void toFile(Book book, String fileName) {
        try (
                FileWriter fw = new FileWriter(new File(fileName));
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(toString(book));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void listToFile(List<Book> book, String fileName) {
        try(
                FileWriter fw = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fw)){
            bw.write(toString(book));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) {

        Book book = new Book();
        book.setIbsn("001548553002");
        book.setName("Dreaming Bandy");
        book.setAvailability(false);
        book.setGenre(Book.Genre.FANTASY);
        Serializer serializer = new SerializerTXT();
        serializer.toFile(book, "bookTXT");


        List<Book> books = new ArrayList<>();
        books.add(book);
        book = new Book();
        book.setIbsn("5586329110");
        book.setName("be Productive");
        book.setAvailability(true);
        book.setGenre(Book.Genre.DRAMA);
        books.add(book);
        serializer.listToFile(books, "booksTXT");

        book = serializer.fromFile("bookTXT");
        System.out.println(book);
        books = serializer.listFromFile("booksTXT");
        System.out.println(books);
    }
}
