package ch27;


import java.util.Iterator;
import java.util.LinkedList;

public class MyHashSet<E> implements MySet<E> {

    // Define the default hash table size. Must be a power of 2
    private static int DEFAULT_INITIAL_CAPACITY = 4;

    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static int MAXIMUM_CAPACITY = 1 << 30;

    // Current hash table capacity. Capacity is a power of 2
    private int capacity;

    // Define default load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

    // Specify a load factor threshold used in the hash table
    private float loadFactorThreshold;

    // The number of elements in the set
    private int size = 0;

    // Hash table is an array with each cell that is a linked list
    private LinkedList<E>[] table;

    /**
     * Construct a set with the default capacity and load factor
     */
    public MyHashSet() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a set with the specified initial capacity and
     * default load factor
     */
    public MyHashSet(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a set with the specified initial capacity
     * and load factor
     */
    public MyHashSet(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        this.loadFactorThreshold = loadFactorThreshold;
        table = new LinkedList[capacity];
    }


    @Override
    public void clear() {

        size = 0;
        removeElements();
    }

    private void removeElements() {

        for (int i = 0; i < capacity; i++)
            if (table[i] != null)
                table[i].clear();
    }

    @Override
    public boolean contains(E e) {

        int bucketIndex = hash(e.hashCode());
        if (table[bucketIndex] != null) {
            LinkedList<E> bucket = table[bucketIndex];
            return bucket.contains(e);
        }
        return false;
    }

    @Override
    public boolean add(E e) {

        if (contains(e)) {
            return false;
        }
        if (size >= capacity * loadFactorThreshold)
            if (size == MAXIMUM_CAPACITY)
                throw new RuntimeException("Capacity exceeded");
        rehash();

        int bucketIndex = hash(e.hashCode());
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new LinkedList<>();
        }

        table[bucketIndex].add(e);
        size++;
        return true;
    }

    private void rehash() {

        LinkedList<E> list = setToList();
        capacity <<= 1;
        table = new LinkedList[capacity];
        size = 0;

        for (E e : list)
            add(e);
    }

    private LinkedList<E> setToList() {
        LinkedList<E> list = new LinkedList<>();
        for (int i = 0; i < capacity; i++)
            if (table[i] != null)
                for (E e : table[i])
                    list.add(e);
        return list;
    }

    @Override
    public boolean remove(E e) {

        if (!contains(e))
            return false;

        int bucketIndex = hash(e.hashCode());
        if (table[bucketIndex] != null) {
            LinkedList<E> bucket = table[bucketIndex];
            bucket.remove(e);
            size--;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }


    private int hash(int hashCode) {

        return supplementalHash(hashCode) & (capacity - 1);
    }

    /**
     * Ensure the hashing is evenly distributed
     */
    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }

        return capacity;
    }

}
