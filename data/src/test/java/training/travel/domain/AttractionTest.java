package training.travel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttractionTest {

    @Test
    @DisplayName("Equals should return true when both Attractions are the same")
    void testEqualsShouldReturnTrueWhenAttractionsAreTheSame() throws Exception{
        //GIVEN
        Attraction attraction1 = new Attraction(new Destination(1, "Budapest"), "Test Attraction",
                "This is a description", Category.MODERN);
        Attraction attraction2 = new Attraction(new Destination(1, "Budapest"), "Test Attraction",
                "This is a description", Category.MODERN);

        //WHEN
        boolean result = attraction1.equals(attraction2);

        //THEN
        assertTrue(result, "Equals method is not implemented properly!");
        assertEquals(attraction1.hashCode(), attraction2.hashCode(), "If two object is equal than their hashCode should be equal too!");
    }

    @Test
    @DisplayName("Equals should return false when attraction names are different")
    void testEqualsShouldReturnFalseWhenAttractionNamesAreDifferent() throws Exception{
        //GIVEN
        Attraction attraction1 = new Attraction(new Destination(1, "Budapest"), "Test Attraction",
                "This is a description", Category.MODERN);
        Attraction attraction2 = new Attraction(new Destination(1, "Budapest"), "Test Attraction2",
                "This is a description", Category.MODERN);

        //WHEN
        boolean result = attraction1.equals(attraction2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when descriptions are different")
    void testEqualsShouldReturnFalseWhenDescriptionsAreDifferent() throws Exception{
        //GIVEN
        Attraction attraction1 = new Attraction(new Destination(1, "Budapest"), "Test Attraction",
                "This is a description", Category.MODERN);
        Attraction attraction2 = new Attraction(new Destination(1, "Budapest"), "Test Attraction",
                "This is a different description", Category.MODERN);

        //WHEN
        boolean result = attraction1.equals(attraction2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when categories are different")
    void testEqualsShouldReturnFalseWhenCategoriesAreDifferent() throws Exception{
        //GIVEN
        Attraction attraction1 = new Attraction(new Destination(1, "Budapest"), "Test Attraction",
                "This is a description", Category.MODERN);
        Attraction attraction2 = new Attraction(new Destination(1, "Budapest"), "Test Attraction",
                "This is a description", Category.HISTORICAL);

        //WHEN
        boolean result = attraction1.equals(attraction2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }
}
