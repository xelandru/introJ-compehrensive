package ch20;

import java.util.PriorityQueue;

/**
 * Created by alex on 7/10/17.
 */
public class Ex20_10 {

    public static void main(String[] args) {
        PriorityQueue<String> queue1 = new PriorityQueue<>();
        PriorityQueue<String> queue2 = new PriorityQueue<>();
        queue1.add("George");
        queue1.add("Jim");
        queue1.add("John");
        queue1.add("Blake");
        queue1.add("Kevin");
        queue1.add("Michael");

        queue2.add("George");
        queue2.add("Katie");
        queue2.add("Kevin");
        queue2.add("Michelle");
        queue2.add("Ryan");

        System.out.println(Intersection(queue1,queue2));
        System.out.println(Difference(queue1,queue2));
        System.out.println(Union(queue1,queue2));




    }

    public static PriorityQueue<String> Intersection(PriorityQueue<String> queue1, PriorityQueue<String> queue2 ) {

        PriorityQueue<String> result = new PriorityQueue<>();

        for (String value : queue1) {
            if (queue2.contains(value))
                result.add(value);
        }

        return result;
    }
    public static PriorityQueue<String> Union(PriorityQueue<String> queue1, PriorityQueue<String> queue2 ) {

        PriorityQueue<String> result = new PriorityQueue<>();
        result.addAll(Difference(queue1,queue2));
        result.addAll(Difference(queue2,queue1));
        result.addAll(Intersection(queue1,queue2));

        return result;

    }
    public static PriorityQueue<String> Difference(PriorityQueue<String> queue1, PriorityQueue<String> queue2 ) {

        PriorityQueue<String> result = new PriorityQueue<>();

        for (String value : queue1) {
            if (!queue2.contains(value))
                result.add(value);
        }

        return result;
    }
}
