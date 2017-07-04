package ch19;


import ch13.Circle;

public class GenericArrayStack<E> {

    private static final int DIMENSION = 2;
    private static final int MULTIPLIER = 2;
    private int head;
    private E[] array = (E[]) new Object[DIMENSION];

    public int getSize() {

        return head;
    }

    public void push(E value) {
        E[] tmp;
        if (!isFull()) {
            array[head] = value;
            head++;
        } else {

            tmp = (E[])new Object[MULTIPLIER * array.length];
            for (int i = 0; i < array.length; i++)
                tmp[i] = array[i];

            array = tmp;
            array[head] = value;
            head++;
        }
    }

    public E pop() {

        return array[--head];
    }

    public E peek() {

        int poz = head;
        return array[--poz];
    }

    private boolean isFull() {

        return head < array.length;
    }

    public static void main(String[] args) {
        GenericArrayStack<Circle> stack = new GenericArrayStack<>();
        stack.push(new Circle(3));
        stack.push(new Circle(-1));
        stack.push(new Circle(2));
        stack.pop();
        System.out.println(stack.peek());
    }
}
