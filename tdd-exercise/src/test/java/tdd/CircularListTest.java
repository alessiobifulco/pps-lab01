package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularQueue queue;
    private static final int DEFAULT_SIZE = 0;
    private static final int DEFAULT_VALUE = 1;
    private static final int EXPECTED_SIZE = 1;
    private static final int ADDITIONAL_TEN = 10;

    @BeforeEach
    void init(){
        queue = new CircularQueueImpl();
    }

    @Test
    void testQueueInitialSize(){
        assertEquals(DEFAULT_SIZE, queue.size());
    }
    @Test
    void testQueueInitialStateIsEmpty(){
        assertTrue(queue.isEmpty());
    }

    @Test
    void testQueueAddChangesStateAndSize(){
        queue.add(DEFAULT_VALUE);
        assertEquals(EXPECTED_SIZE, queue.size());
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
        queue.add(DEFAULT_VALUE + DEFAULT_VALUE);
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
        queue.add(DEFAULT_VALUE);
        assertEquals(DEFAULT_VALUE + DEFAULT_VALUE, queue.peek());
    }

    @Test
    void testMultiplePeekSameValue(){
        fillQueueTillMaxCapacity();
        assertEquals(DEFAULT_VALUE, queue.peek());
        assertEquals(DEFAULT_VALUE, queue.peek());
        assertEquals(DEFAULT_VALUE, queue.peek());
    }

    @Test
    void testMultipleAddWhenFullOverwriteOldestElem(){
        fillQueueTillMaxCapacity();
        IntStream.range(0, queue.maxCapacity())
                .forEach(i -> queue.add(DEFAULT_VALUE + ADDITIONAL_TEN + i));
        assertEquals(DEFAULT_VALUE + ADDITIONAL_TEN, queue.peek());
        assertEquals(queue.maxCapacity(), queue.size());
    }

    private void fillQueueTillMaxCapacity(){
        IntStream.range(0, queue.maxCapacity())
                .forEach(i -> queue.add(DEFAULT_VALUE + i));
    }
}
