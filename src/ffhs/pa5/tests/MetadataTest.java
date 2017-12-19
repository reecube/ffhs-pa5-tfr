package ffhs.pa5.tests;

import ffhs.pa5.model.*;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Tests for the Metadata class.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class MetadataTest extends TestingBase {

    /**
     * Tests the id generation function.
     */
    @Test
    public void testGenerateId() {

        // Config variables
        final int amountOfIds = 10;

        // Variable declaration
        String[] ids;

        logTestCase("Generated id (test if they are unique)");
        ids = new String[amountOfIds];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = Metadata.generateId();
        }
        for (int i = 0; i < ids.length; i++) {
            for (int j = 0; j < ids.length; j++) {
                if (i == j) {
                    continue;
                }

                assertFalse(ids[i].equals(ids[j]));
            }
        }
    }
}