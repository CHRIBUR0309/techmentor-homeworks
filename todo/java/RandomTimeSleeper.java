package todo.java;

import java.util.*;
import java.util.concurrent.*;

/**
 * RandomTimeSleeper class determines how long to sleep with a PRNG.
 */
class RandomTimeSleeper {
    private static final SplittableRandom RANDOM = new SplittableRandom();

    private int randInt(int rangeOrigin, int rangeBound) {
        if (rangeOrigin >= rangeBound) {
            throw new IllegalArgumentException("Error: Must be rangeOrigin < rangeBound");
        }
        return RANDOM.nextInt(rangeBound - rangeOrigin) + rangeOrigin;
    }

    /**
     * Gets {@code rangeOrigin} and {@code rangeBound}, gets a random natural number from a discrete
     * uniform distribution on {@code {rangeOrigin, ..., rangeBound - 1}}, and sleep.
     * 
     * @param rangeOrigin the minimum time [ms] to sleep. This must be positive.
     * @param rangeBound the (maximum time + 1) [ms] to sleep
     * @throws IllegalArgumentException when {@code rangeOrigin < 0} or
     *         {@code rangeOrigin >= rangeBound}
     */
    void sleep(int rangeOrigin, int rangeBound) {
        if (rangeOrigin < 0) {
            throw new IllegalArgumentException("Error: rangeOrigin must be positive.");
        }
        try {
            TimeUnit.MILLISECONDS.sleep(randInt(rangeOrigin, rangeBound));
        } catch (InterruptedException e1) {
            System.err.println(e1);
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }
}
