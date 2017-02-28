package tests.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import controller.Controller;

/**
 * Junit test for class Controller and all of his method.
 *
 */
public class ControllerTest {

    private static final int ZERO = 0;

    /**
     * Test for all method in Controller class.
     */

    @Test
    public void controllerTest() {
        final Controller controller = Controller.getController();
        //test to verify the counter for turn
        controller.giveUp();
        assertEquals(controller.getCounter(), ZERO);
        controller.restart();
        assertEquals(controller.getCounter(), ZERO);
    }

}
