package tdd;

import java.util.ArrayList;
import java.util.List;

public class MinMaxStackImpl implements MinMaxStack {

    private final List<Integer> stack = new ArrayList<>();
    private int currentMin;
    private int currentMax;

    @Override
    public void push(int value) {
        if (stack.isEmpty()) {
            currentMin = value;
            currentMax = value;
        } else {
            currentMin = Math.min(currentMin, value);
            currentMax = Math.max(currentMax, value);
        }
        stack.add(value);
    }

    @Override
    public int pop() {
        checkIfStackEmpty();
        int tmp = stack.remove(stack.size() - 1);
        if (!stack.isEmpty()) {
                currentMin = stack.stream().min(Integer::compare).orElseThrow();
                currentMax = stack.stream().max(Integer::compare).orElseThrow();
        }
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
        return currentMin;
    }

    @Override
    public int getMax() {
        checkIfStackEmpty();
        return currentMax;
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
