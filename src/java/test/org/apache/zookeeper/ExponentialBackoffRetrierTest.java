package org.apache.zookeeper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExponentialBackoffRetrierTest {

    @Test
    public void testWaitMs() {
        // Retrier with base retry of 1ms and max of 10ms
        Retrier r = new ExponentialBackoffRetrier(1, 10);

        // The series is exponential, starting from the base, up to the max
        assertEquals(1, r.getNextWaitMs());
        assertEquals(2, r.getNextWaitMs());
        assertEquals(4, r.getNextWaitMs());
        assertEquals(8, r.getNextWaitMs());
        assertEquals(10, r.getNextWaitMs());
    }

    @Test
    public void testReset() {
        // Retrier with base retry of 1ms and max of 10ms
        Retrier r = new ExponentialBackoffRetrier(1, 10);

        // The wait times are reset when reset is called
        assertEquals(1, r.getNextWaitMs());
        r.reset();
        assertEquals(1, r.getNextWaitMs());
    }

    @Test
    public void testOverrideExponent() {
        // Retrier with base retry of 1ms, max of 10ms, and base exponent 3
        ExponentialBackoffRetrier r = new ExponentialBackoffRetrier(1, 10, 3);

        assertEquals(1, r.getNextWaitMs());
        assertEquals(3, r.getNextWaitMs());
        assertEquals(9, r.getNextWaitMs());
        assertEquals(10, r.getNextWaitMs());
    }
}
