package org.apache.zookeeper;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A {@link Retrier} that will back off exponentially with each call to
 * {@link #getNextWaitMs()} up to a limit.
 * <p>
 * Additionally, the wait time is multiplied by a random number in the range
 * {@code [1, 0)} to provide additional "jitter". This is similar to the
 * approach outlined
 * <a href="https://www.awsarchitectureblog.com/2015/03/backoff.html">here</a>.
 */
public class JitteredExponentialBackoffRetrier extends ExponentialBackoffRetrier {

    private final Random random;

    /**
     * Instantiate a new {@link JitteredExponentialBackoffRetrier} with a given
     * initial and max delay, using the default exponent and
     * {@link ThreadLocalRandom} number generator.
     */
    public JitteredExponentialBackoffRetrier(int initialDelayMs, int maxDelayMs) {
        this(initialDelayMs, maxDelayMs, DEFAULT_BASE_EXPONENT, ThreadLocalRandom.current());
    }

    /**
     * Instantiate a new {@link JitteredExponentialBackoffRetrier} with a given
     * initial and max delay, base exponent and random number generator.
     */
    public JitteredExponentialBackoffRetrier(int initialDelayMs, int maxDelayMs, int exponentBase, Random random) {
        super(initialDelayMs, maxDelayMs, exponentBase);
        this.random = random;
    }

    /**
     * Return the next wait time, in milliseconds, generated from the underlying
     * exponential series, but converted to a "jittered" value, by multiplying by a
     * random double with value between zero and one.
     *
     * @return the next jittered wait time in the series, in milliseconds
     */
    @Override
    public int getNextWaitMs() {
        return (int) random.nextDouble() * super.getNextWaitMs();
    }
}
