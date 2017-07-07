package ch20;

import java.util.*;
/**
 * Created by alex on 7/6/17.
 */
public class TestIterator {
	
	public static void main(String[] args) {
	
	Collection<String> collection = new ArrayList<>();
	collection.add("New York");
	collection.add("Atalanta");
	collection.add("Dallas");
	collection.add("Madison");

	Iterator<String> iterator=  collection.iterator();
	while(iterator.hasNext()) {
		System.out.print(iterator.next() + " ");
	}

	System.out.println();
	}
}