package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLock lock;

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
        lock.setPin(1234);
        lock.lock();
        assertTrue(lock.isLocked());
    }

    @Test
    void smartDoorLockShouldBeUnlockAfterLock(){
        lock.setPin(1234);
        lock.lock();
        lock.unlock(1234);
        assertFalse(lock.isLocked());
    }

    @Test
    void smartDoorLockShouldIncrementCountAfterFailedUnlock(){
        lock.setPin(1234);
        lock.lock();
        lock.unlock(1235);
        assertTrue(lock.isLocked());
        assertEquals(1, lock.getFailedAttempts());
    }






}
