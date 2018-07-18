package chompressive.ch21;

import java.util.*;

public class TestHashSet {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("London");
        set.add("Paris");
        set.add("New York");
        set.add("San Francisco");
        set.add("Beijing");
        set.add("New York");

        for(String item: set) {
        	System.out.printf(item + " ");
        }

        set.remove("London");
        System.out.println();
        System.out.println(set.contains("London"));

    }
}
