package org.apache.zookeeper;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class RandomRetrierTest {

    @Test
    public void testGetNextWaitMs() {
        // Provide random number generator with a deterministic seed
        int seed = 0;
        Random random = new Random(seed);

        // A retrier starting with an upper bound of 10.
        int bound = 10;
        Retrier r = new RandomRetrier(new Random(seed), bound);

        int expected = random.nextInt(bound);
        assertEquals(expected, r.getNextWaitMs());
    }
}
