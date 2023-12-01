package training.travel.view;

import com.epam.training.travel.domain.*;
import training.travel.domain.*;

import java.util.List;
import java.util.Map;

public interface View {
    Credentials readCredentials();

    void printWelcomeMessage(User user);

    void printUserTrips(List<Trip> trips);

    Review askForReviewDetails(Visit visit);

    int chooseMenuItem(Role role);

    String chooseDestination();

    Destination askForNewDestinationDetails();

    void printAttractionsAndReviews(Map<Attraction, List<Review>> attractionListMap);

    Trip chooseTrip(List<Trip> trips);

    Visit chooseVisit(Trip trip);
}
