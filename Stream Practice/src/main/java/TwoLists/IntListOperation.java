package TwoLists;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class IntListOperation {

    public static void main(String args[]){
        List<Integer> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();

            Random rand=new Random();
            int size1 = 10 + rand.nextInt(3);
            int size2 = 10 + rand.nextInt(3);

            // rand.nextInt(100) gives 0 to 99 -> + 1 gives 1 to 100
            for (int i = 0; i < size1; i++) {
                list1.add(rand.nextInt(100) + 1);
            }

            // Populate list2 with numbers from 1 to 100
            for (int i = 0; i < size2; i++) {
                list2.add(rand.nextInt(100) + 1);
            }
            //Displaying lists
            list1.forEach(x->System.out.print(x+" "));
            System.out.printf("\n");
            list2.forEach(x->System.out.print(x+" "));
            System.out.printf("\n");
            //Operations on each list seprately
            //Minimum in each list
//            findMin(list1);
//            findMin(list2);
//            findMin(new ArrayList<>());
            //MAX in each list
//            findMax(list1);
//            findMax(list2);
//            findMax(new ArrayList<>());
        //Average of all integers in the list
//        findAvg(list1);
//        findAvg(list2);
        //findAvg(new ArrayList<>());
       // concatWords(new ArrayList<>(Arrays.asList("Hi","this","is","bala","!")));

        // K-most frequent elements
//        list1.add(21);
//        list1.add(21);
//        list1.add(10);
        list1.add(18);
//        list2.add(11);
//        list2.add(11);
        list2.add(18);
//        list2.add(18);
//        findMostK(list1,2);
//        findMostK(list2,2);
        //Merging two lists
       // mergeTwoLists(list1,list2);
        //common elements in both lists
        findCommon(list1,list2);
        //Prime numbers in a list using streams
        //findPrimes(list1);
        //findPrimes(list2);
        findNonPrimes(list1);
        findNonPrimes(list2);

    }
    static void findNonPrimes(List<Integer> list){
        List<Integer> ans=list.stream()
                .filter(
                        x->{
                            for(int i=2;i*i<=x;i++){
                                if(x%i==0)
                                    return true;
                            }
                            return false;
                        }
                )
                .collect(Collectors.toList());
        System.out.println("Non-Primes are:"+ans);

    }
    static void findPrimes(List<Integer> list){
        List<Integer> ans=list.stream()
                .filter(
                        x->{
                            for(int i=2;i*i<=x;i++){
                                if(x%i==0)
                                    return false;
                            }
                            return true;
                        }
                )
                .collect(Collectors.toList());
        System.out.println("Primes are:"+ans);

    }
    static void findCommon(List<Integer> lis1,List<Integer> lis2){
        List<Integer> commonElements=lis1.stream()
                .filter(lis2::contains)
                .distinct()
                .toList();
        System.out.println(commonElements);
//        Set<Integer> set2 = new HashSet<>(lis2);
//        List<Integer> commonElements = lis1.stream()
//                .filter(set2::contains)
//                .distinct() // Optional: prevents duplicate common entries
//                .toList();
//        System.out.println(commonElements);
    }
    static void mergeTwoLists(List<Integer> lis1,List<Integer> lis2){
        List<List<Integer>> nestedList=new ArrayList<>();
        nestedList.add(lis1);
        nestedList.add(lis2);
        List<Integer> mergedList=nestedList.stream()
                .flatMap(List::stream)
                .toList();
        System.out.println(mergedList);
        //Also this
        //List<Integer> mergedList = Stream.concat(lis1.stream(), lis2.stream())
        //                                     .toList();
    }
    static void findMostK(List<Integer> list,int k){
        Map<Integer,Long> topK=list.
                stream().
                collect(Collectors.groupingBy(
                        num->num,Collectors.counting()
                        )
                );
        List<Integer> topKList=topK.entrySet().stream()
                .sorted(Map.Entry.<Integer,Long>comparingByValue().reversed())
                .limit(k)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("Top " + k + " most frequent: " + topKList);
    }
    static void concatWords(List<String> lis){
        String ans=lis.stream().reduce("Welcome:",(a,b)->a+" "+b);
        System.out.println(ans);
    }
    static void findAvg(List<Integer> list){
//        int sum=list.stream()
//                .reduce(0,
//                        (a,b)->
//                                a+b
//                );
//        //  System.out.println("Sum of all the elements in list is: "+sum);
//        System.out.println("Average: "+sum/list.size());
        double ans=list.stream().
                mapToInt(Integer::intValue)
                .average()
                .orElse(-1) ;
        if(ans!=-1){
            System.out.println("Average:"+ans);
        }

    }
    static void findMax(List<Integer> list){
        Optional<Integer> maxAns=list.stream()
                .max(Integer::compareTo);
        if(!maxAns.isEmpty())
            System.out.println("Maximum of the list:"+maxAns.get().intValue());
        else
            System.out.println("List is empty");

//        Optional<Integer> max = list.stream()
//                .reduce(Integer::max);
//
//        max.ifPresent(m -> System.out.println("Max is: " + m));
    }
    static void findMin(List<Integer> list){
        Optional<Integer> minAns=list.stream()
               // .map(Integer::intValue)// works without mapping also
                .min(Comparator.naturalOrder());
        if(!minAns.isEmpty())
        System.out.println("Minimum of the list:"+minAns.get().intValue());
        else
            System.out.println("List is empty");
    }




}
