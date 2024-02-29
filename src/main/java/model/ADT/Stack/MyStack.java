package model.ADT.Stack;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack; //a field whose model.type CollectionType is an appropriate

    public MyStack(){
        stack = new Stack<>();
    }
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }

    @Override
    public void push(T v) {
        stack.add(v);
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public Stack<T> getStack(){
        return stack;
    }

}