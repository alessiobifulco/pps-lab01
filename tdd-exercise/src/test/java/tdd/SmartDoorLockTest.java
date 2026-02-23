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




}
