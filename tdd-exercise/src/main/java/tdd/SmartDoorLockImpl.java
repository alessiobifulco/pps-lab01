package tdd;

import java.util.Optional;

public class SmartDoorLockImpl implements SmartDoorLock {

    private static final int MAX_ATTEMPTS = 3;
    private Optional<Integer> pin = Optional.empty();
    private boolean locked;
    private int countFailedAttempt = 0;
    private static final int MIN_PIN = 0;
    private static final int MAX_PIN = 9999;

    @Override
    public void setPin(int pin) {
        this.pin = (!locked && pin >= MIN_PIN && pin <= MAX_PIN) ? Optional.of(pin) : this.pin;
    }

    @Override
    public void unlock(int pin) {
        if (this.pin.isPresent() && this.locked && !isBlocked()) {
            if (this.pin.get().equals(pin)) {
                this.locked = false;
                this.countFailedAttempt = 0;
            } else {
                this.countFailedAttempt++;
            }
        }
    }

    @Override
    public void lock() {
        if (pin.isEmpty()) {
            throw new IllegalStateException();
        }
        this.locked = true;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public boolean isBlocked() {
        return countFailedAttempt >= getMaxAttempts();
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return countFailedAttempt;
    }

    @Override
    public void reset() {
        pin = Optional.empty();
        countFailedAttempt = 0;
        this.locked = false;
    }
}
