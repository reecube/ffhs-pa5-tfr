package ffhs.pa5.tests;

/**
 * Testing base with helper functions for the testing environment.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
abstract class TestingBase {

    /**
     * Shows the defined testing case in the console.
     *
     * @param testingCase the testing case description
     */
    void logTestCase(String testingCase) {

        // Get the parent function
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        final String testingGroup = ste[2].getMethodName();

        String className = this.getClass().getSimpleName();

        System.out.format("%s: %s: %s", className, testingGroup, testingCase);
        System.out.println();
    }
}
