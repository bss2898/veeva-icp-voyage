package QuestionLibrary;

import java.util.*;

public class Catalog {
    public static void main(String[] args) {
        Set<Book> catalog=new HashSet<>();
        Scanner sc=new Scanner(System.in);
        int exit=0;
        while(exit!=-1){
            System.out.println("Enter -1 to exit or other key to enter book:");
            int ch=sc.nextInt();
            if (ch==-1){
                break;
            }
            System.out.println("Enter isbn:");
            int isbn=sc.nextInt();
            System.out.println("Enter book name:");
            String name=sc.next().trim();

            System.out.println("Enter author name:");
            String author=sc.next().trim();
            System.out.println("Enter borrowed count:");
            int borrowCount=sc.nextInt();

            Book book=new Book(isbn,name,author,borrowCount);
            if(catalog.add(book)){
                System.out.println("Book added successfully!");
            }
            else{
                System.out.println("Duplicate ISBN! Book already exists in catalog.");
            }
        }
        System.out.println("Entered books are:");
        catalog.forEach(System.out::println);
        System.out.println("Enter keyword to search:");
        String kw=sc.next().trim();
        List<Book> list1=catalog.stream()
                .filter(book->{
                    return book.matchesKeyword(kw);
                })
                .toList();
        System.out.println("List of Books matching are:");
        list1.forEach(System.out::println);

        //Most burrowed book
        Optional<Book> ansBook=catalog
                .stream()
                .max(Comparator.comparingInt(Book::getBurrowCount));

        System.out.println("Highest burrowed book is:"+ansBook);

    }
}
