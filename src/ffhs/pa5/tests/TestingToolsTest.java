package ffhs.pa5.tests;

import static org.junit.Assert.assertArrayEquals;

/**
 * the tests for the TestingTools class
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class TestingToolsTest {

    /**
     * will test if the function invertField is doing what it should
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testInvertField() throws Exception {
        assertArrayEquals(new int[]{
                1, 2, 3, 4, 5, 6,
                12, 11, 10, 9, 8, 7
        }, TestingTools.invertField(new int[]{
                1, 2, 3, 4, 5, 6,
                7, 8, 9, 10, 11, 12
        }));
    }
}