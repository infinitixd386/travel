package training.travel.data;

import training.travel.service.InvalidReferenceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import training.travel.domain.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDataStore implements DataStore {

    private String usersPath;
    private String tripsPath;
    private String destinationsPath;
    private String reviewsPath;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<User> users;
    private List<Trip> trips;
    private List<Visit> visits;
    private List<Destination> destinations;
    private List<Attraction> attractions;
    private List<Review> reviews;

    public FileDataStore(String usersPath, String tripsPath, String destinationsPath, String reviewsPath) {
        this.usersPath = usersPath;
        this.tripsPath = tripsPath;
        this.destinationsPath = destinationsPath;
        this.reviewsPath = reviewsPath;
    }

    @Override
    public void initialize() {
        readUsers();
        readTrips();
        readDestinations();
        readReviews();
        wireRelations();
    }

    private void readUsers() {
        try (FileReader fileReader = new FileReader(usersPath)) {
            users = objectMapper.readValue(fileReader, objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, User.class));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void readTrips() {
        try (FileReader fileReader = new FileReader(tripsPath)) {
            trips = objectMapper.readValue(fileReader, objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Trip.class));
            visits = new ArrayList<>();
            for (Trip trip : trips) {
                visits.addAll(trip.getVisits());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void readDestinations() {
        try (FileReader fileReader = new FileReader(destinationsPath)) {
            destinations = objectMapper.readValue(fileReader, objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Destination.class));
            attractions = new ArrayList<>();
            for (Destination destination : destinations) {
                attractions.addAll(destination.getAttractions());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void readReviews() {
        try (FileReader fileReader = new FileReader(reviewsPath)) {
            reviews = objectMapper.readValue(fileReader, objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Review.class));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void wireRelations() {
        for (int i = 0; i < 1; i++) {
            wireAttractions();
            wireDestinations();
            wireVisits();
            wireTrips();
            wireReviews();
        }
    }

    private void wireDestinations() {
        destinations.forEach(d -> d.setAttractions(attractions
                .stream()
                .filter(a -> a.getDestination().getId() == d.getId())
                .toList()));
    }


    private void wireReviews() {
        reviews.forEach(r -> r.setUser(users
                .stream()
                .filter(u -> u.getId() == r.getUser().getId())
                .findAny()
                .orElseThrow(() -> new InvalidReferenceException("User cannot be found for id: " + r.getUser().getId()))));
        reviews.forEach(r -> r.setAttraction(attractions
                .stream()
                .filter(a -> a.getId() == r.getAttraction().getId())
                .findAny()
                .orElseThrow(() -> new InvalidReferenceException("Attraction cannot be found for id: " + r.getAttraction().getId()))));
    }

    private void wireTrips() {
        try {
            trips.forEach(t -> t.setUser(users
                    .stream()
                    .filter(u -> u.getId() == t.getUser().getId())
                    .findAny()
                    .orElseThrow(() -> new InvalidReferenceException("User cannot be found for id: " + t.getUser().getId()))));
            trips.forEach(t -> t.setDestination(destinations
                    .stream()
                    .filter(d -> d.getId() == t.getDestination().getId())
                    .findAny()
                    .orElseThrow(() -> new InvalidReferenceException("Destination cannot be found for id: " + t.getDestination().getId()))));
        } catch (NullPointerException ex) {
            System.out.println("Invalid date format.");
        }
    }

    private void wireVisits() {
        try {
            visits.forEach(v -> v.setAttraction(attractions
                    .stream()
                    .filter(a -> a.getId() == v.getAttraction().getId())
                    .findAny()
                    .orElseThrow(() -> new InvalidReferenceException("Attraction cannot be found for id: " + v.getAttraction().getId()))));
        } catch (NullPointerException ex) {
            System.out.println("Invalid date format.");
        }
    }

    private void wireAttractions() {
        destinations.forEach(
                destination -> destination.getAttractions().forEach(
                        attraction -> attraction.setDestination(destination)
                )
        );
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public List<Trip> getTrips() {
        return trips;
    }

    @Override
    public List<Visit> getVisits() {
        return visits;
    }

    @Override
    public List<Attraction> getAttractions() {
        return attractions;
    }

    @Override
    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public List<Destination> getDestinations() {
        return destinations;
    }

}
