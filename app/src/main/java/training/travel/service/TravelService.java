package training.travel.service;

import training.travel.data.DataStore;
import training.travel.domain.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TravelService {
    private final DataStore dataStore;
    private User loggedInUser;

    public TravelService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void authenticateUser(Credentials credentials) throws AuthenticationException {
        Optional<User> user = dataStore.getUsers().stream().filter(u -> u.getCredentials().equals(credentials)).findAny();
        loggedInUser = user.orElseThrow(() -> new AuthenticationException("Wrong Credentials"));
    }

    private Destination searchDestination(String readDestination) {
        Destination destination = dataStore.getDestinations()
                .stream()
                .filter(d -> d.getLocation().equals(readDestination))
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Destination not found."));
        return destination;
    }

    public void createReview(Review review) {
        review.setId(dataStore.getReviews().size());
        review.setUser(loggedInUser);
        dataStore.getReviews().add(review);
    }

    private User searchUserByUsername(String username) {
        return dataStore.getUsers().stream().filter(u -> u.getCredentials().getUsername().equals(username)).findAny().orElseThrow(() -> new NoSuchElementException("No user find by " + username + " username."));
    }

    public List<Trip> getTripsByUser() {
        System.out.println(dataStore.getTrips().get(0).getVisits().size());
        return dataStore.getTrips().stream().filter(t -> t.getUser().equals(loggedInUser)).toList();
    }

    public Map<Attraction, List<Review>> getAttractionsAndReviewsByDestination(String readDestination) {
        Destination destination = searchDestination(readDestination);
        Map<Attraction, List<Review>> attractionReviewMap = new HashMap<>();
        for (Attraction attraction : destination.getAttractions()) {
            attractionReviewMap.put(attraction, dataStore.getReviews()
                    .stream()
                    .filter(r -> r.getAttraction().equals(attraction))
                    .toList());
        }
        return attractionReviewMap;
    }

    public void createDestinationWithAttractions(Destination destination) {
        checkPermission();
        destination.setId(dataStore.getDestinations().size());
        AtomicInteger attractionsId = new AtomicInteger(dataStore.getAttractions().size());
        destination.getAttractions().forEach(a -> a.setId(attractionsId.getAndIncrement()));
        dataStore.getDestinations().add(destination);
    }

    private void checkPermission() throws NoPermissionException {
        if (!loggedInUser.isAdmin()) {
            throw new NoPermissionException("You don't have permission to do this action.");
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

}
