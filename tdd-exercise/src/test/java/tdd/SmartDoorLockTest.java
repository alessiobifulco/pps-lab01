package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLock lock;
    private static final int BASE_PIN = 1234;
    private static final int WRONG_PIN = 1235;
    private static final int INVALID_PIN_TOO_HIGH = 12345;
    private static final int INVALID_PIN_NEGATIVE = -1;

    @BeforeEach
    void init(){
        lock = new SmartDoorLockImpl();
    }

    @Test
     void smartDoorLockShouldNotBeLockedAndBlocked() {
        assertFalse(lock.isLocked());
        assertFalse(lock.isBlocked());
    }

    @Test
    void smartDoorLockLockShouldThrowException(){
        assertThrows(IllegalStateException.class, lock::lock);
    }

    @Test
    void smartDoorLockShouldLockWithPin(){
        lock.setPin(BASE_PIN);
        lock.lock();
        assertTrue(lock.isLocked());
    }

    @Test
    void smartDoorLockShouldUnlockAfterLock(){
        setBasePinAndLock();
        lock.unlock(BASE_PIN);
        assertFalse(lock.isLocked());
    }

    @Test
    void smartDoorLockShouldBlock(){
        setBasePinAndLock();
        failUntilMaxAttempts();
        assertTrue(lock.isLocked());
        assertEquals(lock.getMaxAttempts(), lock.getFailedAttempts());
        assertTrue(lock.isBlocked());
    }


    @Test
    void smartDoorLockShouldReset(){
        setBasePinAndLock();
        lock.unlock(WRONG_PIN);
        lock.reset();
        assertThrows(IllegalStateException.class, lock::lock);
        assertFalse(lock.isBlocked());
        assertEquals(0, lock.getFailedAttempts());
        lock.unlock(BASE_PIN);
        assertFalse(lock.isLocked());
    }

    @Test
    void smartDoorLockPinShouldNotBeSet(){
        setBasePinAndLock();
        lock.setPin(WRONG_PIN);
        lock.unlock(WRONG_PIN);
        assertTrue(lock.isLocked());
    }

    @Test
    void smartDoorLockPinMaxAndMin(){
        lock.setPin(INVALID_PIN_TOO_HIGH);
        assertThrows(IllegalStateException.class, lock::lock);
        lock.setPin(INVALID_PIN_NEGATIVE);
        assertThrows(IllegalStateException.class, lock::lock);
        setBasePinAndLock();
        assertTrue(lock.isLocked());
    }

    @Test
    void smartDoorLockUnlockWithoutLockShouldDoNothing(){
        lock.setPin(BASE_PIN);
        lock.unlock(BASE_PIN);
        assertFalse(lock.isLocked());
        assertEquals(0, lock.getFailedAttempts());
    }

    @Test
    void smartDoorLockShouldRemainBlockedAfterMaxAttempts() {
        setBasePinAndLock();
        failUntilMaxAttempts();
        assertTrue(lock.isBlocked());
        assertTrue(lock.isLocked());
        assertEquals(lock.getMaxAttempts(), lock.getFailedAttempts());
        lock.unlock(BASE_PIN);
        assertTrue(lock.isBlocked());
        assertTrue(lock.isLocked());
        assertEquals(lock.getMaxAttempts(), lock.getFailedAttempts());
    }

    private void setBasePinAndLock(){
        lock.setPin(BASE_PIN);
        lock.lock();
    }

    private void failUntilMaxAttempts(){
        IntStream.range(0, lock.getMaxAttempts())
                .forEach(i -> lock.unlock(WRONG_PIN));
    }
}
