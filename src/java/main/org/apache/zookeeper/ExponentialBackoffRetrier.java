package org.apache.zookeeper;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A {@link Retrier} that will back off exponentially with each call to
 * {@link #getNextWaitMs()}.
 */
public class ExponentialBackoffRetrier implements Retrier {

    /* Increment, by default, in powers of two */
    public static final int DEFAULT_BASE_EXPONENT = 2;

    private final int initialDelayMs;
    private final int maxDelayMs;
    private final int exponentBase;
    private final AtomicInteger count;

    /**
     * Instantiate a new {@link JitteredExponentialBackoffRetrier} with a given
     * initial and max delay, using the default exponent.
     */
    public ExponentialBackoffRetrier(int initialDelayMs, int maxDelayMs) {
        this(initialDelayMs, maxDelayMs, DEFAULT_BASE_EXPONENT);
    }

    /**
     * Instantiate a new {@link JitteredExponentialBackoffRetrier} with a given
     * initial and max delay, and base exponent.
     */
    public ExponentialBackoffRetrier(int initialDelayMs, int maxDelayMs, int exponentBase) {
        this.initialDelayMs = initialDelayMs;
        this.maxDelayMs = maxDelayMs;
        this.exponentBase = exponentBase;
        this.count = new AtomicInteger(0);
    }

    /**
     * Return the next wait time, in milliseconds, generated from an exponential
     * series.
     *
     * @return the wait time in the series, in milliseconds
     */
    @Override
    public int getNextWaitMs() {
        int increment = BigInteger.valueOf(exponentBase)
                .pow(count.getAndIncrement())
                .multiply(BigInteger.valueOf(initialDelayMs))
                .intValue();
        return Math.min(increment, maxDelayMs);
    }

    /**
     * Reset the underlying exponential series back to its initial value, defined as
     * {@link this#initialDelayMs}
     */
    @Override
    public void reset() {
        count.set(0);
    }
}
