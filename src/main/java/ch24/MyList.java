package ch24;


public interface MyList<E> extends java.lang.Iterable<E> {

    void add(E e);

    void add(int index, E e);

    void clear();

    boolean contains(E e);

     E get(int index);

    int indexOf(E e);

    boolean isEmpty();

    boolean remove(E e);

    E remove(int index);

    E set(int index, E e);

    int size();

    int lastIndexOf(E e);

    /**
     * Adds the elements in otherList to this list.
     * Returns true if this list changed as a result of the call
     */
    public boolean addAll(MyList<E> otherList);

    /**
     * Removes all the elements in otherList from this list
     * Returns true if this list changed as a result of the call
     */
    public boolean removeAll(MyList<E> otherList);

    /**
     * Retains the elements in this list that are also in otherList
     * Returns true if this list changed as a result of the call
     */
    public boolean retainAll(MyList<E> otherList);

}