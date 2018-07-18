package chompressive.ch19;


import java.util.ArrayList;

public class MyStack<E> extends ArrayList<E>{

    public int getSize() {

        return super.size();
    }

    public E peek() {

        return super.get(getSize() - 1);
    }

    public void push(E value) {

        super.add(value);
    }

    public E pop() {

        E value = super.get(getSize() -1);
        super.remove(getSize() - 1);
        return value;
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        while (stack.size() > 0) {
            System.out.println(stack.peek());
            stack.pop();
        }
    }
}
