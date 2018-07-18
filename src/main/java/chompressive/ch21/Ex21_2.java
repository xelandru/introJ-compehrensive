package chompressive.ch21;


import java.util.*;

public class Ex21_2 {
    public static void main(String[] args) {
        String[] words= new String[]{"a","a","b","c","b"};
        Set<String> nonDuplicateWords = new TreeSet<>(Arrays.asList(words));
        Iterator iterator= ((TreeSet)nonDuplicateWords).descendingIterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());

    }
}
