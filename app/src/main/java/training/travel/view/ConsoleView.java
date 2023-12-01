package training.travel.view;

import com.epam.training.travel.domain.*;
import training.travel.domain.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ConsoleView implements View {

    private final Scanner reader = new Scanner(System.in);

    @Override
    public Credentials readCredentials() {
        System.out.print("Enter Username: ");
        String email = reader.nextLine();
        System.out.print("Enter Password: ");
        String password = reader.nextLine();
        return new Credentials(email, password);
    }

    @Override
    public void printWelcomeMessage(User user) {
        printEmptyLine();
        System.out.println("Welcome " + user.getRole() + ", " + user.getFullName() + "!");
    }

    @Override
    public void printUserTrips(List<Trip> trips) {
        printEmptyLine();
        System.out.println("Here are all your trips: ");
        printTrips(trips);
    }

    @Override
    public Review askForReviewDetails(Visit visit) {
        Review review = new Review();
        printEmptyLine();
        System.out.print("Write the review text for " + visit.getAttraction().getName() + " attraction: ");
        review.setAttraction(visit.getAttraction());
        reader.nextLine();
        String comment = reader.nextLine();
        review.setComment(comment);
        System.out.print("How satisfied are you with the attraction?(1-5): ");
        int rating = reader.nextInt();
        reader.nextLine();
        review.setRating(rating);
        return review;
    }

    @Override
    public int chooseMenuItem(Role role) {
        int activity = 2;
        int chosenActivity;
        do {
            printEmptyLine();
            System.out.println("1. Destination attractions and reviews.");
            System.out.println("2. Write visit review.");
            if (role.equals(Role.ADMIN)) {
                activity = 3;
                System.out.println("3. Create destination with attractions.");
            }
            System.out.print("Choose an activity (1-" + activity + "): ");
            chosenActivity = reader.nextInt();
        } while (!(chosenActivity >= 0 && chosenActivity <= activity));
        return chosenActivity;
    }

    @Override
    public String chooseDestination() {
        printEmptyLine();
        System.out.print("Choose a destination to see the attractions and their reviews: ");
        reader.nextLine();
        return reader.nextLine();
    }

    @Override
    public Destination askForNewDestinationDetails() {
        Destination destination = new Destination();
        Attraction attraction = new Attraction();
        System.out.print("Enter location: ");
        destination.setLocation(reader.next());
        String quit;
        do {
            System.out.println("Enter attraction details.");
            System.out.print("Name: ");
            attraction.setName(reader.next());
            System.out.print("Description: ");
            attraction.setDescription(reader.next());
            System.out.print("Type: ");
            attraction.setCategory(Category.valueOf(reader.next().toUpperCase()));
            System.out.println("Press 'q' to quit or any other key to continue: ");
            quit = reader.next();
            destination.addAttraction(attraction);
        } while (!quit.equals("q"));
        return destination;
    }

    @Override
    public void printAttractionsAndReviews(Map<Attraction, List<Review>> attractionListMap) {
        attractionListMap.forEach((attraction, reviews) -> {
            System.out.println("Name: " + attraction.getName() +
                    "\nCategory: " + attraction.getCategory() +
                    "\nDescription: " + attraction.getDescription());
            System.out.println("Review(s):");
            if (!reviews.isEmpty()) {
                reviews.forEach(r ->
                        System.out.println("Username: " + r.getUser().getCredentials().getUsername()
                                + "\nRating: " + r.getRating()
                                + "\nComment: " + r.getComment()));
            }
        });
    }

    @Override
    public Trip chooseTrip(List<Trip> trips) {
        printTrips(trips);
        System.out.print("Choose a trip(1-" + trips.size() + "): ");
        reader.nextLine();
        int tripNumber = reader.nextInt();
        return trips.get(tripNumber - 1);
    }

    @Override
    public Visit chooseVisit(Trip trip) {
        List<Visit> visits = trip.getVisits();
        printVisits(visits);
        System.out.print("Choose an attraction(1-" + trip.getVisits().size() + "): ");
        reader.nextLine();
        int visitNumber = reader.nextInt();
        return trip.getVisits().get(visitNumber - 1);
    }

    private void printTrips(List<Trip> trips) {
        AtomicInteger counter = new AtomicInteger(1);
        trips.forEach(t -> {
                    System.out.println(counter + ". Location: " + t.getDestination().getLocation() +
                            "\n   Duration: " + t.getStartDate() + " - " + t.getEndDate());
                    printEmptyLine();
                    counter.getAndIncrement();
                }
        );
    }

    private void printVisits(List<Visit> visits) {
        AtomicInteger counter = new AtomicInteger(1);
        visits.forEach(v -> {
                    System.out.println(counter + ". Attraction: " + v.getAttraction().getName() +
                            "\n   Visit Date: " + v.getVisitDate());
                    printEmptyLine();
                    counter.getAndIncrement();
                }
        );
    }

    private void printEmptyLine() {
        System.out.println();
    }

}
