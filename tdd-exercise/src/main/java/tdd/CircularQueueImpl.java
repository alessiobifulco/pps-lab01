package tdd;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {

    List<Integer> queue = new ArrayList<>();
    private static final int MAX_CAPACITY = 3;

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
        int tmp = queue.get(0);
        queue.remove(0);

        return tmp;
    }

    @Override
    public int maxCapacity(){
        return MAX_CAPACITY;
    }

    @Override
    public int peek(){
        checkIfQueueIsEmpty();
        return queue.get(0);
    }

    private void checkIfQueueIsEmpty(){
        if (queue.isEmpty()){
            throw new IllegalStateException();
        }
    }

}
