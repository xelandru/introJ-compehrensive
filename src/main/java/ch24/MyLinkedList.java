package ch24;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head;
    private Node<E> tail;

    public MyLinkedList() {
    }

    public MyLinkedList(E[] objects) {
        for (E e : objects)
            add(e);
    }

    public void addFirst(E e) {

        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;

        if (tail == null)
            tail = head;
    }

    public void addLast(E e) {

        Node<E> newNode = new Node<>(e);
        if (head == null)
            head = tail = newNode;
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public E getFirst() {
        if (head == null)
            return null;
        else
            return head.element;

    }

    public E getLast() {
        if (tail == null)
            return null;

        return tail.element;
    }

    public E removeFirst() {

        if (head == null)
            throw new NoSuchElementException();

        if (head == tail)
            tail = null;

        Node<E> tmp = head;
        head = head.next;
        size--;
        return tmp.element;

    }

    public E removeLast() {

        Node<E> tmp;

        if (tail == null)
            throw new NoSuchElementException();

        else if (head == tail) {
            tmp = head;
            head = tail = null;
            size--;
            return tmp.element;
        } else {

            Node<E> current = head;
            while ((current.next).next != null)
                current = current.next;

            tmp = tail;
            tail = current;
            tail.next = null;
            size--;
            return tmp.element;
        }
    }

    @Override
    public void add(int index, E e) {
        if (index < 0)
            throw new IndexOutOfBoundsException("Index out of bound");
        else if (index == 0)
            addFirst(e);
        else if (index >= size)
            addLast(e);

        else {
            Node<E> newNode = new Node<>(e);
            Node<E> current = head;

            for (int i = 1; i < index; i++)
                current = current.next;

            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    @Override
    public void clear() {

        head = null;
        tail = null;
        size = 0;

    }

    @Override
    public boolean contains(E e) {

        Node<E> current = head;
        while (current != null) {
            if (current.element.equals(e))
                return true;
            current = current.next;
        }

        return false;
    }

    @Override
    public E get(int index) {

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index invalid");
        else if (index == 0)
            return getFirst();
        else if (index == size - 1)
            return getLast();
        else {
            Node<E> current = head;
            for (int i = 1; i <= index; i++)
                current = current.next;
            return current.element;
        }
    }


    @Override
    public int indexOf(E e) {

        int counter = 0;
        Node<E> current = head;
        while (current != null) {
            if (current.element.equals(e))
                return counter;
            counter++;
            current = current.next;
        }
        return -1;
    }

    @Override
    public E remove(int index) {

        if (index < 0) throw new IndexOutOfBoundsException();
        else if (index == 0)
            return removeFirst();
        else if (index >= size - 1)
            return removeLast();
        else {

            Node<E> current = head;
            for (int i = 1; i < index; i++)
                current = current.next;

            Node<E> tmp = current.next;
            current.next = (current.next).next;
            size--;

            return tmp.element;
        }

    }

    @Override
    public E set(int index, E e) {

        E old;
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index invalid");
        else if (index == 0) {
            old = head.element;
            head.element = e;

        } else if (index == size - 1) {
            old = tail.element;
            tail.element = e;

        } else {
            Node<E> current = head;
            for (int i = 1; i <= index; i++)
                current = current.next;

            old = current.element;
            current.element = e;
        }
        return old;
    }

    @Override
    public int lastIndexOf(E e) {
        Node<E> current = head;
        int index = -1;
        int counter = 0;
        while (current != null) {
            if (current.element.equals(e))
                index = counter;
            current = current.next;
            counter++;
        }
        return index;
    }

    @Override
    public Iterator<E> iterator() {


        return new MyLinkedListIterator();
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder("[");
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            if (i < size - 1)
                result.append(", ");
            current = current.next;
        }
        return result.toString() + "]";
    }

    private static class Node<E> {

        private E element;
        private Node<E> next;

        private Node(E element) {
            this.element = element;
        }
    }

    private class MyLinkedListIterator implements Iterator<E> {

        private Node<E> current = head;
        private int index = 0;


        @Override
        public boolean hasNext() {
            if (index != 0)
                return current.next != null;
            else
                return current != null;
        }

        @Override
        public E next() {

            if (index == 0 && head != null) {
                index++;
                return head.element;
            } else if (hasNext()) {
                index++;
                current = current.next;
                return current.element;

            } else
                throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            MyLinkedList.this.remove(current.element);


        }
    }



    public static void main(String[] args) {


    }
}
