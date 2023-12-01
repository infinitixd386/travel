package training.travel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TripTest {

    @Test
    @DisplayName("Equals should return true when both Trips are the same")
    void testEqualsShouldReturnTrueWhenTripsAreTheSame() throws Exception{
        //GIVEN
        Trip trip1 = createDefaultTrip();
        Trip trip2 = createDefaultTrip();

        //WHEN
        boolean result = trip1.equals(trip2);

        //THEN
        assertTrue(result, "Equals method is not implemented properly!");
        assertEquals(trip1.hashCode(), trip2.hashCode(), "If two object is equal than their hashCode should be equal too!");
    }

    @Test
    @DisplayName("Equals should return false when Users are different")
    void testEqualsShouldReturnFalseWhenUsersAreDifferent(){
        //GIVEN
        Trip trip1 = createDefaultTrip();
        Trip trip2 = createDefaultTrip();
        trip2.setUser(new User("testname", new Credentials("username2", "password2"), Role.USER));

        //WHEN
        boolean result = trip1.equals(trip2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when Destinations are different")
    void testEqualsShouldReturnFalseWhenDestinationsAreDifferent(){
        //GIVEN
        Trip trip1 = createDefaultTrip();
        Trip trip2 = createDefaultTrip();
        trip2.setDestination(new Destination(2, "Warsaw"));

        //WHEN
        boolean result = trip1.equals(trip2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when Visits are different")
    void testEqualsShouldReturnFalseWhenVisitsAreDifferent(){
        //GIVEN
        Trip trip1 = createDefaultTrip();
        Trip trip2 = createDefaultTrip();
        Attraction attraction = new Attraction(new Destination(1, "Budapest"), "Test Attraction",
                "This is a description", Category.MODERN);
        Visit newVisit = new Visit(attraction, LocalDate.of(2021,10,19));
        trip2.getVisits().add(newVisit);

        //WHEN
        boolean result = trip1.equals(trip2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when start dates are different")
    void testEqualsShouldReturnFalseWhenStartDatesAreDifferent(){
        //GIVEN
        Trip trip1 = createDefaultTrip();
        Trip trip2 = createDefaultTrip();
        trip2.setStartDate(LocalDate.of(2020,10,11));

        //WHEN
        boolean result = trip1.equals(trip2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when end dates are different")
    void testEqualsShouldReturnFalseWhenEndDatesAreDifferent(){
        //GIVEN
        Trip trip1 = createDefaultTrip();
        Trip trip2 = createDefaultTrip();
        trip2.setEndDate(LocalDate.of(2020,10,21));

        //WHEN
        boolean result = trip1.equals(trip2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    private Trip createDefaultTrip(){
        User user = new User("TestName", new Credentials("username", "password"), Role.USER);
        Destination destination = new Destination(1, "Budapest");
        Attraction attraction = new Attraction(destination, "Test Attraction",
                "This is a description", Category.MODERN);
        Visit visit = new Visit(attraction, LocalDate.of(2020,10,19));
        List<Visit> visits = new ArrayList<>();
        visits.add((visit));
        Trip trip = new Trip(user, destination, visits, LocalDate.of(2020,10,10),
                LocalDate.of(2020,10,20));
        return trip;
    }
}
