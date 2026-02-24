package tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinMaxStackImpl implements MinMaxStack {

    private final List<Integer> stack = new ArrayList<>();

    @Override
    public void push(int value) {
        stack.add(value);
    }

    @Override
    public int pop() {
        checkIfStackEmpty();
        int tmp = stack.get(stack.size() -1);
        stack.remove(stack.size() -1);
        return tmp;
    }

    @Override
    public int peek() {
        checkIfStackEmpty();
        return stack.get(stack.size() -1);
    }

    @Override
    public int getMin() {
        checkIfStackEmpty();
        return Collections.min(stack);
    }

    @Override
    public int getMax() {
        checkIfStackEmpty();
        return Collections.max(stack);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    private void checkIfStackEmpty(){
        if (stack.isEmpty()){
            throw new IllegalStateException();
        }
    }
}
