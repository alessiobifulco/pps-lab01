package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLock lock;
    private static final int PIN = 1234;
    private static final int WRONG_PIN = 1235;

    @BeforeEach
    void init(){
        lock = new SmartDoorLockImpl();
    }

    @Test
    public void smartDoorLockShouldBeLockedBlocked() {
        assertFalse(lock.isLocked());
        assertFalse(lock.isBlocked());
    }

    @Test
    void smartDoorLockLockShouldThrowException(){
        assertThrows(IllegalStateException.class, lock::lock);
    }

    @Test
    void smartDoorLockShouldBeLockWithPin(){
        lock.setPin(PIN);
        lock.lock();
        assertTrue(lock.isLocked());
    }

    @Test
    void smartDoorLockShouldBeUnlockAfterLock(){
        lock.setPin(PIN);
        lock.lock();
        lock.unlock(PIN);
        assertFalse(lock.isLocked());
    }

    @Test
    void smartDoorLockShouldBeBlock(){
        lock.setPin(PIN);
        lock.lock();
        lock.unlock(WRONG_PIN);
        assertTrue(lock.isLocked());
        assertEquals(1, lock.getFailedAttempts());
        assertTrue(lock.isBlocked());
    }


    @Test
    void smartDoorLockShouldBeReset(){
        lock.setPin(PIN);
        lock.lock();
        lock.unlock(WRONG_PIN);
        lock.reset();
        assertThrows(IllegalStateException.class, lock::lock);
        assertFalse(lock.isBlocked());
        assertEquals(0, lock.getFailedAttempts());
    }

}
