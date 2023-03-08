package battleship;

/**
 * Class that verifies certain conditions for the good of the execution of certain methods
 */
public final class Preconditions {

    private Preconditions() {
    }

    /**
     * Throws an exception if the given parameter is false
     *
     * @param shouldBeTrue (boolean) parameter to test
     * @throws IllegalArgumentException if shouldBeTrue is false
     */
    public static void checkArgument(boolean shouldBeTrue) {
        if (!shouldBeTrue) {
            throw new IllegalArgumentException();
        }
    }
}
