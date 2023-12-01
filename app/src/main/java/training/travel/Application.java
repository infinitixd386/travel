package training.travel;

import training.travel.data.DataStore;
import training.travel.data.FileDataStore;
import training.travel.service.AuthenticationException;
import training.travel.service.NoPermissionException;
import training.travel.service.TravelService;
import training.travel.view.ConsoleView;
import training.travel.view.View;

import java.time.format.DateTimeParseException;

public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private final String USERS_PATH = "input/users.json";
    private final String TRIPS_PATH = "input/trips.json";
    private final String DESTINATIONS_PATH = "input/destinations.json";
    private final String REVIEWS_PATH = "input/reviews.json";
    DataStore dataStore = new FileDataStore(USERS_PATH, TRIPS_PATH, DESTINATIONS_PATH, REVIEWS_PATH);
    TravelService service = new TravelService(dataStore);
    View view = new ConsoleView();

    public void run() {
        initializeData();
        welcomeUser();
        userInteraction();
    }

    private void initializeData() {
        try {
            dataStore.initialize();
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private void welcomeUser() {
        try {
            service.authenticateUser(view.readCredentials());
        } catch (AuthenticationException ex) {
            System.out.println(ex.getMessage());
        }
        view.printWelcomeMessage(service.getLoggedInUser());
        view.printUserTrips(service.getTripsByUser());
    }

    private void userInteraction() {
        boolean shouldRun = true;
        while (shouldRun) {
            int activity = view.chooseMenuItem(service.getLoggedInUser().getRole());
            switch (activity) {
                case 1:
                    view.printAttractionsAndReviews(service.getAttractionsAndReviewsByDestination(view.chooseDestination()));
                    break;
                case 2:
                    service.createReview(view.askForReviewDetails(view.chooseVisit(view.chooseTrip(service.getTripsByUser()))));
                    break;
                case 3:
                    try {
                        service.createDestinationWithAttractions(view.askForNewDestinationDetails());
                    } catch (NoPermissionException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                default:
                    shouldRun = false;
                    break;
            }
        }
    }

}