package org.apache.zookeeper;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A {@link Retrier} that will generate wait times randomly between zero and an
 * upper bound.
 */
public class RandomRetrier implements Retrier {

    private final Random random;
    private final int bound;

    /**
     * Instantiate a default instance, backed by a {@link ThreadLocalRandom}, with
     * an upper bound of 1.
     */
    public RandomRetrier() {
        this(ThreadLocalRandom.current(), 1);
    }

    /**
     * Instantiate a new instance, backed by a given {@link Random} and upper bound.
     */
    public RandomRetrier(Random random, int bound) {
        this.random = random;
        this.bound = bound;
    }

    @Override
    public int getNextWaitMs() {
        return random.nextInt(bound);
    }

    @Override
    public void reset() {
        // do nothing
    }
}
