package ch11;

import java.util.ArrayList;

public class MyStack implements Cloneable{

    private ArrayList<Object> list = new ArrayList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getSize() {
        return list.size();
    }

    public Object peek() {
        return list.get(getSize() - 1);
    }

    public Object pop() {
        Object o = list.get(getSize() - 1);
        list.remove(getSize() - 1);
        return o;
    }

    public void push(Object o) {
        list.add(o);
    }

    public Object clone() {

        MyStack stack = null;
        try {
            stack = (MyStack) super.clone();
            stack.list = (ArrayList<Object>) list.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return stack;

    }

    @Override
    /** Override the toString in the Object class */
    public String toString() {
        return "stack: " + list.toString();
    }


    public static void main(String[] args) {
        MyStack stack1 = new MyStack();
        stack1.push("item1");
        stack1.push("item2");
        MyStack stack2 = (MyStack) stack1.clone();
        stack2.pop();
        System.out.println(stack1);
        System.out.println(stack2);
        System.out.println(stack1);
    }

}

