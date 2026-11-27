package QuestionLibrary;

import java.util.Locale;
import java.util.Objects;

interface Searchable{
    boolean matchesKeyword(String keyword);
}
public class Book implements Searchable  {
    private int isbn;
    private String name;
    private String author;
    private int burrowCount;

    @Override
    public boolean matchesKeyword(String kw){
        kw=kw.toLowerCase();//for case insensitive
        if(name.contains(kw)||author.contains(kw)) {
            return true;
        }
        return false;
    }

    public Book(int isbn, String name, String author, int burrowCount) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.burrowCount = burrowCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn == book.isbn;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBurrowCount() {
        return burrowCount;
    }

    public void setBurrowCount(int burrowCount) {
        this.burrowCount = burrowCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
