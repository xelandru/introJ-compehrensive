package ch20;

import java.util.*;
/**
 * Created by alex on 7/6/17.
 */
public class TestIterator {
	
	public static void main(String[] args) {

	    LinkedList<Double> list = new LinkedList<>();
        int i=0;
        long trashHold = 5_000_000L;

        while (i < trashHold) {
            list.add(Math.random());
            i++;
        }

        long t1 = System.currentTimeMillis();
        for(int j=0;j<trashHold;j++){
            list.get(j);
            if(j % 100_000L == 0)
                System.out.println(j);
        }
        long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);

        long t3 = System.currentTimeMillis();
        for(Double d:list)
            continue;
        long t4 = System.currentTimeMillis();

        System.out.println(t4 -t3);


	}
}