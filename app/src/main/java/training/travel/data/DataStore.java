package training.travel.data;

import training.travel.domain.*;

import java.util.List;

public interface DataStore {

    void initialize();

    List<User> getUsers();

    List<Trip> getTrips();

    List<Visit> getVisits();

    List<Attraction> getAttractions();

    List<Review> getReviews();

    List<Destination> getDestinations();
}
