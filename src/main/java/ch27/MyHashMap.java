package ch27;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> implements MyMap<K, V> {

    // Define the default hash table size. Must be a power of 2
    private static int DEFAULT_INITIAL_CAPACITY = 4;

    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static int MAXIMUM_CAPACITY = 1 << 30;

    // Current hash table capacity. Capacity is a power of 2
    private int capacity;

    // Define default load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

    // Specify a load factor used in the hash table
    private float loadFactorThreshold;

    // The number of entries in the map
    private int size = 0;

    // Hash table is an array with each cell that is a linked list
    private LinkedList<Entry<K, V>>[] table;

    /**
     * Construct a map with the default capacity and load factor
     */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a map with the specified initial capacity and
     * default load factor
     */
    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a map with the specified initial capacity
     * and load factor
     */
    public MyHashMap(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        this.loadFactorThreshold = loadFactorThreshold;
        table = new LinkedList[capacity];
    }

    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }

        return capacity;
    }


    @Override
    public void clear() {
        removeEntries();
        size = 0;

    }

    private void removeEntries() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null)
                table[i].clear();
        }
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket)
                    if (entry.getValue().equals(value))
                        return true;

            }
        }
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {

        Set<Entry<K, V>> entries = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket)
                    entries.add(entry);
            }
        }
        return entries;
    }

    @Override
    public V get(K key) {

        int bucketIndex = hash(key.hashCode());

        if (table[bucketIndex] != null) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket)
                if (entry.getKey().equals(key))
                    return entry.getValue();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    @Override
    public Set<K> keySet() {

        Set<K> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket)
                    set.add(entry.getKey());
            }
        }
        return set;
    }


    @Override
    public V put(K key, V value) {

        if (get(key) != null) {
            int bucketIndex = hash(key.hashCode());

            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    V oldValue = entry.getValue();
                    entry.value = value;
                    return oldValue;
                }
            }

        }

        if (size >= capacity * loadFactorThreshold)
            if (capacity == MAXIMUM_CAPACITY)
                throw new RuntimeException("MAX capacity exceeded!");

        rehash();

        int bucketIndex = hash(key.hashCode());

        if (table[bucketIndex] == null) {
            LinkedList<Entry<K, V>> bucket = new LinkedList<>();
            table[bucketIndex] = bucket;
        }

        table[bucketIndex].add(new Entry<K, V>(key, value));
        size++;
        return value;
    }

    @Override
    public void remove(K key) {

        int bucketIndex = hash(key.hashCode());
        if (table[bucketIndex] != null) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    bucket.remove(entry);
                    size--;
                    break;
                }
            }
        }
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public Set<V> values() {

        Set<V> set = new HashSet<V>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket)
                    set.add(entry.getValue());
            }
        }
        return set;
    }

    /**
     * Hash function
     */
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

    private void rehash() {

        Set<Entry<K, V>> set = entrySet();
        size = 0;
        capacity <<= 1;
        table = new LinkedList[capacity];

        for (Entry<K, V> entry : set)
            put(entry.getKey(), entry.getValue());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i].size() > 0)
                for (Entry<K, V> entry: table[i])
                    builder.append(entry);
        }

        builder.append("]");
        return builder.toString();
    }
}
