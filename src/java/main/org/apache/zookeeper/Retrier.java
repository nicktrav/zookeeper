package org.apache.zookeeper;

/**
 * An interface that can be implemented to provide a means for generating wait
 * times in situations that require a series of backoff times (i.e. random,
 * exponential, jittered, etc.).
 */
public interface Retrier {

    /**
     * Generate a time, in milliseconds, the caller should wait for.
     *
     * @return the wait time, in milliseconds
     */
    public int getNextWaitMs();

    /**
     * Reset the sequence of values for the retrier, if applicable.
     */
    public void reset();
}
