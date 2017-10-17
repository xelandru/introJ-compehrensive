package ch24;



public interface MyList<E> extends java.lang.Iterable<E>{

    void add(E e);
    void add(int index, E e);
    void clear();
    boolean contains(E e);
    public E get(int index);
    int indexOf(E e);
    boolean isEmpty();
    boolean remove(E e);
    E remove(int index);
    E set(int index, E e);
    int size();
    int lastIndexOf(E e);

}
