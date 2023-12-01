package training.travel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DestinationTest {

    @Test
    @DisplayName("Equals should return true when both Destinations are the same")
    void testEqualsShouldReturnTrueWhenDestinationsAreTheSame() throws Exception{
        //GIVEN
        Destination destination1 = new Destination(1, "Budapest");
        Destination destination2 = new Destination(1, "Budapest");

        //WHEN
        boolean result = destination1.equals(destination2);

        //THEN
        assertTrue(result, "Equals method is not implemented properly!");
        assertEquals(destination1.hashCode(), destination2.hashCode(), "If two object is equal than their hashCode should be equal too!");
    }

    @Test
    @DisplayName("Equals should return false when destination Id is different")
    void testEqualsShouldReturnFalseWhenDestinationIdIsDifferent() throws Exception{
        //GIVEN
        Destination destination1 = new Destination(1, "Budapest");
        Destination destination2 = new Destination(2, "Budapest");

        //WHEN
        boolean result = destination1.equals(destination2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when locations are different")
    void testEqualsShouldReturnFalseWhenDestinationLocationNamesAreDifferent() throws Exception{
        //GIVEN
        Destination destination1 = new Destination(1, "Budapest");
        Destination destination2 = new Destination(1, "Warsaw");

        //WHEN
        boolean result = destination1.equals(destination2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }
}
