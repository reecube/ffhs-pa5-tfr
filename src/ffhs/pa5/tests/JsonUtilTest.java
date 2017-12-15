package ffhs.pa5.tests;

import com.google.gson.annotations.Expose;
import ffhs.pa5.util.JsonUtil;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Tests for the JsonUtil class.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class JsonUtilTest extends TestingBase {

    /**
     * TODO
     */
    private class TestObject {

        @Expose
        String valString;

        @Expose
        boolean valBoolean;

        @Expose
        int valInteger;

        @Expose
        double valDouble;

        @Expose
        Date valDate;

        /**
         * TODO
         *
         * @param valString  TODO
         * @param valBoolean TODO
         * @param valInteger TODO
         * @param valDouble  TODO
         * @param valDate    TODO
         */
        TestObject(String valString, boolean valBoolean, int valInteger, double valDouble, Date valDate) {
            this.valString = valString;
            this.valBoolean = valBoolean;
            this.valInteger = valInteger;
            this.valDouble = valDouble;
            this.valDate = valDate;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof TestObject)) {
                return false;
            }

            TestObject testObject = (TestObject) obj;

            return testObject.valString.equals(valString)
                    && testObject.valBoolean == valBoolean
                    && testObject.valInteger == valInteger
                    && testObject.valDouble == valDouble
                    && testObject.valDate.equals(valDate);
        }
    }

    /**
     * TODO
     */
    @Test
    public void testStringifyAndParseLifecycle() {
        // Config variables
        final String expectedString = "string";
        final boolean expectedBoolean = true;
        final int expectedInt = 123;
        final double expectedDouble = 123.45;
        final Date expectedDate = new Date();
        final TestObject expectedResult = new TestObject(
                expectedString,
                expectedBoolean,
                expectedInt,
                expectedDouble,
                expectedDate
        );

        final JsonUtil jsonUtil = new JsonUtil(null);

        logTestCase("Stringify");
        final String json = jsonUtil.stringify(expectedResult);
        assertTrue(json.length() > 0);

        logTestCase("Parse");
        final TestObject result = jsonUtil.parse(json, TestObject.class);
        assertNotNull(result);
        assertEquals(expectedResult, result);
    }
}
