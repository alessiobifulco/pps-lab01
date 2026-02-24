package tdd;

import java.util.ArrayList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue {

    List<Integer> queue = new ArrayList<>();

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
        queue.add(elem);
    }

    @Override
    public void remove(){
        if (queue.isEmpty()){
            throw new IllegalStateException();
        }
        queue.remove(0);
    }

}
