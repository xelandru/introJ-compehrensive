package ch24;


import java.util.Iterator;

public class MyArrayList<E> extends MyAbstractList<E> {

    private static final int INITIAL_CAPACITY = 16;
    private E[] data = (E[]) new Object[INITIAL_CAPACITY];

    public MyArrayList() {
    }

    public MyArrayList(E[] objects) {
        for (E object : objects)
            add(object);
    }

    public void trimToSize() {

        if( size != data.length) {
            E[] newData = (E[]) new Object[size];
            for(int i=0;i<size;i++)
                newData[i] = data[i];
            data = newData;
        }
    }

    @Override
    public void add(int index, E e) {

        ensureCapacity();
        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];
        data[index] = e;
        size++;
    }

    @Override
    public void clear() {
        data = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;

    }

    @Override
    public boolean contains(E e) {

        for (E e1:data)
        if(e1.equals(e))
            return true;

        return false;
    }

    @Override
    public E get(int index) {

        checkIndex(index);
        return data[index];
    }


    @Override
    public int indexOf(E e) {
        for(int i=0;i<size;i++)
            if(e.equals(data[i]))
                return i;
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for(int i=size - 1;i>=0;i--)
            if(e.equals(data[i]))
                return i;
        return -1;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E e = get(index);

        for(int i=index;i<size-1;i++)
            data[i] = data[i+1];

        data[size] = null;
        size--;
        return e;

    }

    @Override
    public E set(int index, E e) {

        checkIndex(index);
        E old = data[index];
        data[index] = e;
        return old;
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i < size - 1) result.append(", ");
        }
        return result.toString() + "]";
    }

    @Override
    public Iterator<E> iterator() {

        return new ArrayListIterator();
    }

    private void ensureCapacity() {
        if (size >= data.length) {
            E[] newData = (E[]) new Object[size * 2 + 1];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
    }
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException
                    ("index " + index + " out of bounds");
    }

    private class ArrayListIterator implements Iterator<E>{


        private int current = 0;

        @Override
        public boolean hasNext() {

            return current < size;

        }

        @Override
        public E next() {

            return data[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(current);

        }
    }
}
