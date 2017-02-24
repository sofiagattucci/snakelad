package tests.model;

import static org.junit.Assert.fail;
import org.junit.Test;
import model.Model;
import model.ModelImpl;
import utilities.ConsoleLog;

/**
 * Junit test used in order to test ModelImpl class.
 * This class has to achieve success in all its tests.
 */
public class ModelImplTest {

    /**
     * Tests methods inside ModelImpl class.
     */
    @Test
    public void testModelImpl() {
        final Model model = new ModelImpl();

        //call methods without calling startGame() method. It must throw IllegalStateException
        try {
            model.getPlayerPositionAndJump(0);
            fail("cannot call getPlayerPosition() because there is no last number relased from dice.");
        } catch (final IllegalStateException e) {
            final ConsoleLog log = ConsoleLog.get();
            log.print("IllegalStateException thrown with success.");
        } catch (final Exception e) {
            fail("should throw an IllegalStateException, not a " + e.getClass());
        }
    }

}
