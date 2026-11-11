package StringOperations;
import javax.swing.text.html.Option;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
public class StringList {
    public static void  main  (String args[]){
        List<List<String>> lis=new ArrayList<>();
        lis= Arrays.asList(Arrays.asList("Windows","CHROMEOS","Android","Linux"),
                Arrays.asList("C","JAVA","python","JS","C"),
                Arrays.asList("Oracle","NoSql","SQLite"),
                Arrays.asList("ChatGPT","Groq","GEMINI","CLAUDE","Groq"));


//        Map<String,Long> wordCount=lis.stream()
//                .flatMap(List::stream)
//                .collect(
//                Collectors.groupingBy(
//                        str->str,
//                        HashMap::new,
//                        Collectors.counting()
//                )
//        );
//        System.out.println(wordCount);

        //Longest word from given list

        Optional<String> longest=lis.stream()
                .flatMap(List::stream)
                .collect(Collectors.toMap(
                        str->str,
                        String::length,
                        (prev,curr)->curr
                )).
                entrySet().stream()
                //.map(Integer::intValue)
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .stream()
                .map(Map.Entry::getKey)
                .findFirst();
        System.out.println(longest);

        Optional<String> longest1 = lis.stream()
                .flatMap(List::stream)
                .collect(Collectors.toMap(
                        str -> str,
                        String::length,
                        (prev, curr) -> curr
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey); // Calls Optional.map() directly!

        //Finding first repeating string
//        Optional<String> ans=lis.stream()
//                .flatMap(List::stream)
//                .collect(Collectors.groupingBy(
//                        //classifier
//                        str->str,
//                        LinkedHashMap::new,
//                        Collectors.counting()
//                ))
//                .entrySet().stream()
//                .filter(entry->entry.getValue()>1)
//                .map(Map.Entry::getKey)
//                .findFirst();
//        System.out.println(ans.get());
//        //string with more than one repetions
//        List<String> ans1=lis.stream()
//                .flatMap(List::stream)
//                .collect(Collectors.groupingBy(
//                        //classifier
//                        str->str,
//                        LinkedHashMap::new,
//                        Collectors.counting()
//                ))
//                .entrySet().stream()
//                .filter(entry->entry.getValue()>1)
//                .map(Map.Entry::getKey)
//                .toList();
//        System.out.println(ans1);
        //finding repeating characters in a given string
        //String strex="aabbbbcdeef";
        // Finding repeating characters
//        List<Character> repeatingChars = strex.chars()
//                .mapToObj(c -> (char) c) // Box int code points to Character
//                .collect(Collectors.groupingBy(
//                        c -> c,
//                        LinkedHashMap::new,  // Preserves encounter order ('a', 'b', 'e')
//                        Collectors.counting()
//                ))
//                .entrySet().stream()
//                .filter(entry -> entry.getValue() > 1) // Keep only characters appearing more than once
//                .map(Map.Entry::getKey)
//                .toList();
//        System.out.println(repeatingChars);

    //mappping each string to string value of length
//        Map<String, Integer> stringValues = lis.stream()
//                .flatMap(List::stream)
//                .collect(Collectors.toMap(
//                        str -> str,
//                        String::length,
//                        (prev,curr)->curr
//                ));
        //System.out.println(stringValues);



//      lis.stream()
//             .flatMap(List::stream)
//             .forEach(System.out::println);

        //Starting with specific letter
        Scanner sc=new Scanner(System.in);
        //System.out.println("Enter starting character:");
        //char start=sc.next().charAt(0);

//        lis.stream()
//                .flatMap(List::stream)
//                .filter(str->str.startsWith(start+""))
//                .forEach(System.out::println);
//

        //Starting with "J" and ends with "a" both case sensitive
       /* lis.stream()
                .flatMap(List::stream)
                .filter(str->(str.startsWith("J")&&str.endsWith("a")))
                .forEach(System.out::println);

        lis.stream()
                .flatMap(List::stream)
                .map(str1->str1.toLowerCase(Locale.ROOT))
                .filter(str->(str.startsWith("j")&&str.endsWith("a")))
                .map(str->str.toUpperCase(Locale.ROOT))
                .forEach(System.out::println);*/

//        List<String> names = List.of("Aman",  "Rahul", "Gita","Sam","Sita");
//
//        String first = names.stream()
//                .filter(name -> {
//                    System.out.println("Checking: " + name);
//                    return name.startsWith("S");
//                })
//                .findFirst()
//                .orElse("None");

        //capital all letters
        /*lis.stream()
                .flatMap(List::stream)
                .filter(str->(str.startsWith("C")))
                .filter(str->{
                    String temp=str;
                    return str.toUpperCase(Locale.ROOT).equals(temp);
                })
                .forEach(System.out::println);
        */
        //regular expression by llm
//        lis.stream()
//                .flatMap(List::stream)
//                .filter(str -> str.matches("^C[A-Z]*$"))
//                .forEach(System.out::println);
//
        //distinct
//        lis.stream()
//                .flatMap(List::stream)
//                .distinct()
//                .forEach(System.out::println);
        //First character uppercase

//        lis.stream()
//                .flatMap(List::stream)
//                .distinct()
//                .filter(str->{
//                   return Character.isUpperCase(str.charAt(0));
//                })
//                .forEach(System.out::println);
//        lis.stream()
//                .flatMap(List::stream)
//                .map(str->str.toLowerCase(Locale.ROOT))
//                .sorted()
//                .map(str->str.toUpperCase(Locale.ROOT))
//                .forEach(System.out::println);
//        lis.stream()
//                .flatMap(List::stream)
//                .sorted(String.CASE_INSENSITIVE_ORDER)
//                .filter(str->str.startsWith("C"))
//                .forEach(System.out::println);

        //Count
//        int countans=(int)lis.stream()
//                .flatMap(List::stream)
//                .distinct()
//                .filter(str-> {
//                    str=str.toLowerCase(Locale.ROOT);
//                    return str.startsWith("j");
//                })
//                .count();
//        System.out.println(countans);
//        Optional<String> ans=lis.stream()
//                .flatMap(List::stream)
//                .filter(str->{
//                    str=str.toLowerCase();
//                    return str.startsWith("j");
//                })
//                .findFirst();
        //Startwith
        //System.out.println(ans);



    }
}
