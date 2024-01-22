package todo.java;

enum Importance {
    FINISHED, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN;

    private static final Importance[] VALUES = Importance.values();

    static Importance intToImportance(int importance) {
        if (importance >= -1 && importance <= 10) {
            return VALUES[importance + 1];
        }
        return null;
    }

    static int importanceToInt(Importance importance) {
        for (int i = 0; i < VALUES.length; i++) {
            if (VALUES[i] == importance) {
                return i - 1;
            }
        }
        return -2;// If this one line is removed, the editor throws an error.
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
