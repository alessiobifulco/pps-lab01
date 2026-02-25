package tdd;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {

    List<Integer> queue = new ArrayList<>();
    private static final int MAX_CAPACITY = 3;
    private static final int INDEX_ZERO = 0;

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void add(int elem) {
        if (size() == MAX_CAPACITY) {
            remove();
        }
        queue.add(elem);
    }

    @Override
    public int remove(){
        checkIfQueueIsEmpty();
        return queue.remove(INDEX_ZERO);
    }

    @Override
    public int maxCapacity(){
        return MAX_CAPACITY;
    }

    @Override
    public int peek(){
        checkIfQueueIsEmpty();
        return queue.get(INDEX_ZERO);
    }

    private void checkIfQueueIsEmpty(){
        if (queue.isEmpty()){
            throw new IllegalStateException();
        }
    }

}
