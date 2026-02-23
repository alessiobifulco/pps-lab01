package tdd;

import javax.swing.text.html.Option;
import java.util.Optional;

public class SmartDoorLockImpl implements SmartDoorLock {
    private Optional<Integer> pin = Optional.empty();
    private boolean isLock;

    @Override
    public void setPin(int pin) {
        this.pin = Optional.of(pin);
    }

    @Override
    public void unlock(int pin) {
        this.isLock = !(this.pin.isPresent() && this.pin.get() == pin);
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
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
