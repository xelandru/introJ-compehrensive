package ch27;


import java.util.HashMap;
import java.util.Iterator;

public class MyHashSet2<E> implements MySet<E> {

    // Define the default hash table size. Must be a power of 2
    private static int DEFAULT_INITIAL_CAPACITY = 4;

    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static int MAXIMUM_CAPACITY = 1 << 30;

    // Current hash table capacity. Capacity is a power of 2
    private int capacity;

    // Define default load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

    // Hash table is an array with each cell that is a linked list
    private HashMap<E, E> table;

    /**
     * Construct a set with the default capacity and load factor
     */
    public MyHashSet2() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a set with the specified initial capacity and
     * default load factor
     */
    public MyHashSet2(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a set with the specified initial capacity
     * and load factor
     */
    public MyHashSet2(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        table = new HashMap<>(initialCapacity, loadFactorThreshold);
    }


    @Override
    public void clear() {
        removeElements();
    }

    private void removeElements() {
        table.clear();
    }

    @Override
    public boolean contains(E e) {
        return table.containsKey(e);
    }

    @Override
    public boolean add(E e) {
        return table.putIfAbsent(e, e) == null;
    }

    @Override
    public boolean remove(E e) {
        return (table.remove(e) != null);
    }

    @Override
    public boolean isEmpty() {
        return table.size() == 0;
    }

    @Override
    public int size() {

        return table.size();
    }

    @Override
    public Iterator<E> iterator() {

        return null;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("[");
        for (E e : table.keySet())
            sb.append(e).append(",");

        sb.deleteCharAt(sb.length() -1).append("]");
        return sb.toString();

    }

    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }

        return capacity;
    }

}