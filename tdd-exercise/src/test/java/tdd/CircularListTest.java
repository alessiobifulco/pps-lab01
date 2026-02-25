package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularQueue queue;
    private static final int DEFAULT_SIZE = 0;
    private static final int DEFAULT_VALUE = 1;

    @BeforeEach
    void init(){
        queue = new CircularQueueImpl();
    }

    @Test
    void testQueueInitialSize(){
        assertEquals(0, queue.size());
    }
    @Test
    void testQueueInitialStateIsEmpty(){
        assertTrue(queue.isEmpty());
    }

    @Test
    void testQueueAddChangesStateAndSize(){
        queue.add(DEFAULT_VALUE);
        assertEquals(DEFAULT_VALUE, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    void testQueueRemoveChangesStateAndSize(){
        queue.add(DEFAULT_VALUE);
        queue.remove();
        assertEquals(DEFAULT_SIZE, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    void testQueueRemoveThrowExceptionIfEmpty(){
        assertThrows(IllegalStateException.class, this.queue::remove);
    }

    @Test
    void testRemoveOrderFirstInFirstOut(){
        queue.add(DEFAULT_VALUE);
        queue.add(DEFAULT_VALUE +1);
        assertEquals(DEFAULT_VALUE, queue.remove());
    }

    @Test
    void testSizeDoesNotExceedCapacityWhenFull(){
        fillQueueTillMaxCapacity();
        assertEquals(queue.maxCapacity(), queue.size());
        queue.add(DEFAULT_VALUE);
        assertEquals(queue.maxCapacity(), queue.size());
    }

    @Test
    void testPeekThrowExceptionIfQueueIsEmpty(){
        assertThrows(IllegalStateException.class, this.queue::peek);
    }

    @Test
    void testAddWhenFullOverwriteOldestElement(){
        fillQueueTillMaxCapacity();
        queue.add(DEFAULT_VALUE -1);
        assertEquals(DEFAULT_VALUE +1, queue.peek());
    }

    private void fillQueueTillMaxCapacity(){
        for (int i = 0; i < queue.maxCapacity(); i++){
            queue.add(DEFAULT_VALUE + i);
        }
    }

}
