package ch27;


import java.util.*;

public  abstract class LinearProbingMap<K, V> implements MyMap<K, V> {

    // Define the default hash table size. Must be a power of 2
    private static int DEFAULT_INITIAL_CAPACITY = 4;

    // Define the maximum hash table size. 1 << 30 is same as 2^30
    private static int MAXIMUM_CAPACITY = 1 << 30;

    // Current hash table capacity. Capacity is a power of 2
    private int capacity;

    // Define default load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f;

    // Specify a load factor used in the hash table
    private float loadFactorThreshold;

    // The number of entries in the map
    private int size = 0;

    // Hash table is an array with each cell that is a linked list
    Entry<K, V>[] table;

    /**
     * Construct a map with the default capacity and load factor
     */
    public LinearProbingMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a map with the specified initial capacity and
     * default load factor
     */
    public LinearProbingMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * Construct a map with the specified initial capacity
     * and load factor
     */
    public LinearProbingMap(int initialCapacity, float loadFactorThreshold) {
        if (initialCapacity > MAXIMUM_CAPACITY)
            this.capacity = MAXIMUM_CAPACITY;
        else
            this.capacity = trimToPowerOf2(initialCapacity);

        this.loadFactorThreshold = loadFactorThreshold;
        table = new Entry[capacity];
    }


    @Override
    public void clear() {

        removeEntries();
        size = 0;
    }

    private void removeEntries() {

        for (int i = 0; i < capacity; i++)
            if (table[i] != null)
                table[i] = null;
    }

    @Override
    public boolean containsKey(K key) {

        int increment = 0;
        int startIndex = hash(key.hashCode());
        int index = startIndex;

        if (table[startIndex] == null)
            return false;

        do {
            if (table[index].getKey().equals(key))
                return true;

            increment++;
            index = hash(key.hashCode() + increment);
        }
        while (startIndex != index && table[index] != null);

        return false;
    }

    private int hash(int i) {
        if (i < 0)
            return (-i) % capacity;
        else
            return i % capacity;
    }

    @Override
    public boolean containsValue(V value) {

        for (int i = 0; i < capacity; i++)
            if (table[i] != null)
                if (table[i].getValue().equals(value))
                    return true;

        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new LinkedHashSet<>();
        for (Entry<K, V> entry : table)
            if (entry != null)
                set.add(entry);

        return set;
    }

    @Override
    public V get(K key) {

        int startIndex = hash(key.hashCode());
        int index = startIndex;
        if (table[index] == null)
            return null;

        int increment = 0;

        do {
            if (table[index].getKey().equals(key))
                return table[index].getValue();

            increment++;
            index = hash(key.hashCode() + increment);
        }
        while (index != startIndex && table[index] != null);

        return null;
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < capacity; i++)
            if (table[i] != null)
                set.add(table[i].getKey());
        return set;
    }

    @Override
    public V put(K key, V value) {

        int index = hash(key.hashCode());
        int increment = 0;

        if (containsKey(key)) {

            while (!table[index].getKey().equals(key)) {
                increment++;
                index = hash(key.hashCode() + increment);
            }

            V oldValue = table[index].getValue();
            table[index].value = value;
            return oldValue;

        } else {

            if (size >= capacity * loadFactorThreshold) {
                if (capacity == MAXIMUM_CAPACITY)
                    throw new RuntimeException("Maximum capacity exceeded");
                rehash();
            }
            index = hash(key.hashCode() + increment);
            while (table[index] != null) {
                increment++;
                index = hash(key.hashCode() + increment);
            }

            table[index] = new Entry<>(key, value);
            size++;
        }
        return value;
    }

    private void rehash() {

        List<Entry<K, V>> list = new LinkedList<>();
        size = 0;

        for (int i = 0; i < capacity; i++)
            if (table[i] != null) {
                list.add(table[i]);
            }
        capacity <<= 1;
        table = new Entry[capacity];
        for (Entry<K, V> entry : list)
            put(entry.getKey(), entry.getValue());


    }

    @Override
    public void remove(K key) {

        int index = hash(key.hashCode());
        int increment = 0;
        int startIndex = index;

        if (table[index] == null)
            return;

        do {
            if (table[index].getKey().equals(key)) {
                table[index] = null;
                break;
            }
            increment++;
            index = hash(key.hashCode() + increment);
        }
        while (index != startIndex || table[index] != null);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<V> values() {

        Set<V> set = new HashSet<>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null)
                set.add(table[i].getValue());
        }
        return set;
    }

    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1;
        }

        return capacity;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null)
                builder.append(table[i]);
        }
        builder.append("]");
        return builder.toString();
    }

}
