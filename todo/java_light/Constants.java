package todo.java_light;

import java.util.*;

enum Importance {
    FINISHED, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN;

    private static final Importance[] VALUES = Importance.values();
    private static final int NUM_IMPORTANCE = VALUES.length;
    private static final Map<Integer, Importance> MAP_INT_TO_IMPORTANCE = new HashMap<>() {
        {
            for (int i = 0; i < NUM_IMPORTANCE; i++) {
                put(i - 1, VALUES[i]);
            }
        }
    };
    private static final Map<Importance, Integer> MAP_IMPORTANCE_TO_INT = new HashMap<>() {
        {
            for (int i = 0; i < NUM_IMPORTANCE; i++) {
                put(VALUES[i], i - 1);
            }
        }
    };

    static Importance intToImportance(int importance) {
        return MAP_INT_TO_IMPORTANCE.get(importance);
    }

    static int importanceToInt(Importance importance) {
        return MAP_IMPORTANCE_TO_INT.get(importance);
    }
}


enum IsProceeding {
    PROCEEDING, UNPROCESSED;

    static IsProceeding booleanToIsProceeding(boolean isProcessing) {
        if (isProcessing) {
            return PROCEEDING;
        } else {
            return UNPROCESSED;
        }
    }

    static boolean isProceedingToBoolean(IsProceeding isProceeding) {
        return isProceeding == PROCEEDING;
    }
}
