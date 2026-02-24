package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {

    private MinMaxStack stack;
    private static final int INITIAL_SIZE = 0;
    private static final int BASE_VALUE = 1;
    private static final int BASE_NEGATIVE_VALUE = -1;
    private static final int BIGGER_VALUE = 2;
    private static final int BIGGER_NEGATIVE_VALUE = -2;

    @BeforeEach
    void init(){
        stack = new MinMaxStackImpl();
    }

    @Test
    void testInitialStackIsEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void testInitialStackSizeIsZero() {
        assertEquals(INITIAL_SIZE, stack.size());
    }

    @Test
    void testPopOnEmptyStackThrowsException() {
        assertThrows(IllegalStateException.class, stack::pop);
    }

    @Test
    void testPeekOnEmptyStackThrowsException() {
        assertThrows(IllegalStateException.class, stack::peek);
    }

    @Test
    void testGetMinOnEmptyStackThrowsException() {
        assertThrows(IllegalStateException.class, stack::getMin);
    }

    @Test
    void testGetMaxOnEmptyStackThrowsException() {
        assertThrows(IllegalStateException.class, stack::getMax);
    }

    @Test
    void testPushChangesIsEmptyToFalse() {
        stack.push(BASE_VALUE);
        assertFalse(stack.isEmpty());
    }

    @Test
    void testPeekReturnsLastPushedValue() {
        stack.push(BASE_VALUE);
        assertEquals(BASE_VALUE, stack.peek());
    }

    @Test
    void testPeekReturnsNegativeValue() {
        stack.push(BASE_NEGATIVE_VALUE);
        assertEquals(BASE_NEGATIVE_VALUE, stack.peek());
    }

    @Test
    void testPopRemovesElementMakingStackEmpty() {
        stack.push(BASE_VALUE);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPopRestoresPreviousElementAtPeek() {
        stack.push(BASE_VALUE);
        stack.push(BIGGER_VALUE);
        stack.pop();
        assertEquals(BASE_VALUE, stack.peek());
    }

    @Test
    void testPushIncreasingValuesUpdatesMax() {
        stack.push(BASE_VALUE);
        stack.push(BIGGER_VALUE);
        assertEquals(BIGGER_VALUE, stack.getMax());
    }

    @Test
    void testPushDecreasingValuesUpdatesMin() {
        stack.push(BASE_VALUE);
        stack.push(BASE_NEGATIVE_VALUE);
        assertEquals(BASE_NEGATIVE_VALUE, stack.getMin());
    }

    @Test
    void testPushNegativeValuesUpdatesMin() {
        stack.push(BASE_NEGATIVE_VALUE);
        stack.push(BIGGER_NEGATIVE_VALUE);
        assertEquals(BIGGER_NEGATIVE_VALUE, stack.getMin());
    }

    @Test
    void testPopUpdatesMaxCorrect() {
        stack.push(BASE_VALUE);
        stack.push(BIGGER_VALUE);
        stack.pop();
        assertEquals(BASE_VALUE, stack.getMax());
    }

    @Test
    void testPopUpdatesMinCorrect() {
        stack.push(BASE_VALUE);
        stack.push(BASE_NEGATIVE_VALUE);
        stack.pop();
        assertEquals(BASE_VALUE, stack.getMin());
    }
}