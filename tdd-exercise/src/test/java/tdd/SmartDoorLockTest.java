package tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    @Test
    public void smartDoorLockShouldBeLockedBlocked() {
        SmartDoorLock lock = new SmartDoorLockImpl();
        assertFalse(lock.isLocked());
        assertFalse(lock.isBlocked());
    }

    @Test
    void smartDoorLockLockShouldThrowException(){
        SmartDoorLock lock = new SmartDoorLockImpl();
        assertThrows(IllegalStateException.class, lock::lock);
    }

    @Test
    void smartDoorLockShouldBeLockWithPin(){
        SmartDoorLock lock = new SmartDoorLockImpl();
        lock.setPin(1234);
        lock.lock();
        assertTrue(lock.isLocked());
    }

    @Test
    void smartDoorLockShouldBeUnlockAfterLock(){
        SmartDoorLock lock = new SmartDoorLockImpl();
        lock.setPin(1234);
        lock.lock();
        lock.unlock(1234);
        assertFalse(lock.isLocked());
    }






}
