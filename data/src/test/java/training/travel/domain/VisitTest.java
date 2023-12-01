package training.travel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VisitTest {

    @Test
    @DisplayName("Equals should return true when both Visits are the same")
    void testEqualsShouldReturnTrueWhenVisitsAreTheSame() throws Exception{
        //GIVEN
        Visit visit1 = createDefaultVisit();
        Visit visit2 = createDefaultVisit();

        //WHEN
        boolean result = visit1.equals(visit2);

        //THEN
        assertTrue(result, "Equals method is not implemented properly!");
        assertEquals(visit1.hashCode(), visit2.hashCode(), "If two object is equal than their hashCode should be equal too!");
    }

    @Test
    @DisplayName("Equals should return false when Attractions are different")
    void testEqualsShouldReturnFalseWhenAttractionsAreDifferent() throws Exception{
        //GIVEN
        Visit visit1 = createDefaultVisit();
        Visit visit2 = createDefaultVisit();
        visit2.getAttraction().setDescription("This is a different description");

        //WHEN
        boolean result = visit1.equals(visit2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when dates are different")
    void testEqualsShouldReturnFalseWhenDatesAreDifferent() throws Exception{
        //GIVEN
        Visit visit1 = createDefaultVisit();
        Visit visit2 = createDefaultVisit();
        visit2.setVisitDate(LocalDate.of(2020,10,20));

        //WHEN
        boolean result = visit1.equals(visit2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    private Visit createDefaultVisit(){
        Attraction attraction = new Attraction(new Destination(1, "Budapest"), "Test Attraction",
                "This is a description", Category.MODERN);
        Visit visit = new Visit(attraction, LocalDate.of(2020,10,19));
        return visit;
    }
}
