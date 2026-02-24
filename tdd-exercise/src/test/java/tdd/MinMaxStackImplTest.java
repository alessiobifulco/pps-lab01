package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {

    MinMaxStack stack;
    private static final int INITIAL_SIZE = 0;
    private static final int BASE_VALUE = 1;

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
    void testStackThrowExceptionPeekAndPop(){
        assertThrows(IllegalStateException.class, this.stack::pop);
        assertThrows(IllegalStateException.class, this.stack::peek);
    }

    @Test
    void testStackThrowExceptionGetMinAndGetMax(){
        assertThrows(IllegalStateException.class, this.stack::getMin);
        assertThrows(IllegalStateException.class, this.stack::getMax);
    }

    @Test
    void testStackPushAndPeekAndPop(){
        stack.push(BASE_VALUE);
        assertFalse(stack.isEmpty());
        assertEquals(BASE_VALUE, stack.peek());
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}