package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {

    MinMaxStack stack;

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
        assertEquals(0, stack.size());
    }
}