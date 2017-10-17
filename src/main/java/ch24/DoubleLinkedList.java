package ch24;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head;
    private Node<E> tail;

    public DoubleLinkedList() {
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;

        if (tail == null)
            tail = head;
        else
            (newNode.next).previous = newNode;
        size++;

    }

    public void addLast(E e) {

        Node<E> newNode = new Node<>(e);
        newNode.previous = tail;
        tail = newNode;
        if (head == null)
            head = tail;
        else
            (newNode.previous).next = newNode;
        size++;

    }

    public E removeFirst() {

        if (head == null)
            throw new NoSuchElementException();

        Node<E> tmp = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            (head.next).previous = null;
            head = head.next;
        }

        size--;
        return tmp.element;
    }

    public E removeLast() {

        if (tail == null)
            throw new NoSuchElementException();
        Node<E> tmp = tail;
        if (tail == head) {
            tail = null;
            head = null;
        } else {
            (tail.previous).next = null;
            tail = tail.previous;
        }
        size--;
        return tmp.element;
    }

    public E getFirst() {

        if (head == null)
            return null;

        return head.element;
    }

    public E getLast() {
        if (tail == null)
            return null;

        return tail.element;
    }

    public E setFirst(E e) {
        if (head == null)
            throw new NoSuchElementException();
        E tmp = head.element;
        head.element = e;
        return tmp;
    }

    public E setLast(E e) {
        if (tail == null)
            throw new NoSuchElementException();
        E tmp = tail.element;
        tail.element = e;
        return tmp;
    }

    public static void main(String[] args) {

        DoubleLinkedList<String> list = new DoubleLinkedList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        System.out.println(list.set(0, "A"));
        System.out.println(list);

        System.out.println(list.set(3, "D"));
        System.out.println(list);

        System.out.println(list.set(1, "B"));
        System.out.println(list);

        System.out.println(list.set(2, "C"));
        System.out.println(list);


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

            newNode.previous = current;
            newNode.next = current.next;
            (current.next).previous = newNode;
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
            throw new NoSuchElementException();

        if (index == 0)
            return getFirst();
        else if (index == size - 1)
            return getLast();

        else {
            Node<E> current = head;
            for (int i = 1; index >= i; i++)
                current = current.next;

            return current.element;
        }
    }

    @Override
    public int indexOf(E e) {
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.element.equals(e))
                return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        if (index == 0)
            return removeFirst();
        else if (index == size - 1)
            return removeLast();
        else {
            Node<E> current = head;
            for (int i = 1; i <= index; i++)
                current = current.next;

            Node<E> tmp = current;
            (current.previous).next = current.next;
            (current.next).previous = current.previous;
            size--;
            return tmp.element;
        }
    }

    @Override
    public E set(int index, E e) {

        if (index < 0 || index >= size)
            throw new NoSuchElementException();

        if (index == 0)
            return setFirst(e);
        else if (index == size - 1)
            return setLast(e);

        else {
            Node<E> current = head;
            for (int i = 1; i <= index; i++)
                current = current.next;

            E element = current.element;
            current.element = e;
            return element;
        }
    }


    @Override
    public int lastIndexOf(E e) {

        Node<E> current = tail;
        int index = 0;
        while (current != null) {
            if (current.element.equals(e))
                return size - index - 1;
            current = current.previous;
            index++;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new DoubleLinkedListIterator();
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
        private Node<E> previous;

        private Node(E element) {
            this.element = element;
        }
    }

    private class DoubleLinkedListIterator implements Iterator<E> {


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
            DoubleLinkedList.this.remove(current.element);


        }
    }
}
