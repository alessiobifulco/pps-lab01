package tdd;

import java.util.Optional;

public class SmartDoorLockImpl implements SmartDoorLock {
    private Optional<Integer> pin = Optional.empty();
    private boolean isLock;
    private int countFailedAttempt = 0;

    @Override
    public void setPin(int pin) {
        this.pin = (this.isLock) ? this.pin : Optional.of(pin);
    }

    @Override
    public void unlock(int pin) {
        this.isLock = !(this.pin.isPresent() && this.pin.get().equals(pin) && this.countFailedAttempt < getMaxAttempts());
        if (this.isLock){
            countFailedAttempt++;
        }
    }

    @Override
    public void lock() {
        if (pin.isEmpty()) {
            throw new IllegalStateException();
        }
        this.isLock = true;
    }

    @Override
    public boolean isLocked() {
        return this.isLock;
    }

    @Override
    public boolean isBlocked() {
        return countFailedAttempt >= getMaxAttempts();
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return countFailedAttempt;
    }

    @Override
    public void reset() {
        pin = Optional.empty();
        countFailedAttempt = 0;
    }
}
