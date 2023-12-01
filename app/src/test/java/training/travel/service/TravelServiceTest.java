package training.travel.service;

import training.travel.data.DataStore;
import training.travel.data.FileDataStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import training.travel.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TravelServiceTest {

    private TravelService testService;
    private final String TEST_DESTINATIONS_PATH = "../input/destinations.json";
    private final String TEST_REVIEWS_PATH = "../input/reviews.json";
    private final String TEST_TRIPS_PATH = "../input/trips.json";

    private final String TEST_TRIPS_FAULTY_PATH = "../input/trips_faulty.json";
    private final String TEST_USERS_PATH = "../input/users.json";

    @BeforeEach
    void setUp() throws Exception {
        DataStore dataStore = new FileDataStore(TEST_USERS_PATH, TEST_TRIPS_PATH, TEST_DESTINATIONS_PATH, TEST_REVIEWS_PATH);
        dataStore.initialize();
        testService = new TravelService(dataStore);
    }

    @Test
    @DisplayName("authenticateUser should return the correct user when given valid Credentials")
    void testAuthenticateUserWhenGivenValidCredentials() throws Exception{
        //GIVEN
        Credentials credentials = new Credentials("harold.holiday01", "123");
        User expectedUser = new User("Harold Holiday",credentials, Role.USER);

        //WHEN
        testService.authenticateUser(credentials);
        User actualUser = testService.getLoggedInUser();

        //THEN
        assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("authenticateUser should throw AuthenticationException when given invalid Credentials")
    void testAuthenticateUserWhenGivenInvalidCredentials() throws Exception{
        //GIVEN
        Credentials credentials = new Credentials("nonexistinguser", "abc");

        //WHEN

        //THEN
        assertThrows(AuthenticationException.class, () -> testService.authenticateUser(credentials),
                "When given Credentials with the username nonexistinguser and the password abc, " +
                        "AuthenticationException should be thrown!");
    }


    @Test
    @DisplayName("getAttractionsAndReviewsByDestinations should throw NoSuchElementException when given invalid parameter")
    void testGetAttractionsAndReviewsByDestinationsWhenGivenInvalidParameter() throws Exception{
        //GIVEN
        String testDestination = "Berlin";

        //WHEN

        //THEN
        assertThrows(NoSuchElementException.class, () -> testService.getAttractionsAndReviewsByDestination(testDestination),
                "When searching with the destination name Berlin, NoSuchElementException should be thrown!");
    }


    @Test
    @DisplayName("getAttractionsAndReviewsByDestinations should return the correct Map")
    void testGetAttractionsAndReviewsByDestination() throws Exception{
        //GIVEN
        Map<Attraction, List<Review>> expectedReviewsByAttraction = createExpectedAttractionReviewMapForBudapest();

        //WHEN
        Map<Attraction, List<Review>> actualReviewsByAttraction =
                testService.getAttractionsAndReviewsByDestination("Budapest");

        //THEN
        assertEquals(expectedReviewsByAttraction, actualReviewsByAttraction, "When using the destination " +
                "Budapest, the correct Map should be returned");
    }

    @Test
    @DisplayName("createReview should correctly add new Reviews")
    void testCreateNewReview() throws Exception{
        //GIVEN
        User testUser = createTestUser();
        loginAsUser();
        Attraction testAttraction = getFirstAttractionFromLoggedInUser();
        Review review = new Review(testUser, 5, "This is a test review", testAttraction);
        Map<Attraction, List<Review>> expectedReviewsByAttraction = createExpectedAttractionReviewMapForBudapest();
        expectedReviewsByAttraction.get(testAttraction).add(review);

        //WHEN
        testService.createReview(review);
        Map<Attraction, List<Review>> actualReviewsByAttraction =
                testService.getAttractionsAndReviewsByDestination("Budapest");

        //THEN
        assertEquals(expectedReviewsByAttraction, actualReviewsByAttraction);
    }

    @Test
    @DisplayName("createDestinationWithAttractions should correctly add new Destinations")
    void testCreateDestinationWithAttraction() throws Exception{
        //GIVEN
        Destination testDestination = createTestDestinationWithAttractions();
        loginAsAdmin();

        //WHEN
        testService.createDestinationWithAttractions(testDestination);

        //THEN
        assertEquals(createAttractionReviewMapWithoutReviews(testDestination),
                testService.getAttractionsAndReviewsByDestination("Amsterdam"));
    }

    @Test
    @DisplayName("createDestinationWithAttractions should throw NoPermissionException when the logged in user is not admin")
    void testCreateDestinationWithAttractionWhenLoggedInAsUser() throws Exception{
        //GIVEN
        Destination testDestination = createTestDestinationWithAttractions();
        loginAsUser();

        //WHEN

        //THEN
        assertThrows(NoPermissionException.class, () -> testService.createDestinationWithAttractions(testDestination));
    }

    private void loginAsUser(){
        Credentials credentials = new Credentials("harold.holiday01", "123");
        testService.authenticateUser(credentials);
    }

    private void loginAsAdmin(){
        Credentials credentials = new Credentials("realadminadam", "123");
        testService.authenticateUser(credentials);
    }

    private Attraction getFirstAttractionFromLoggedInUser(){
        Trip trip = testService.getTripsByUser().get(0);
        Visit visit = trip.getVisits().get(0);
        return visit.getAttraction();
    }

    private User createTestUser(){
        return new User("Harold Holiday", new Credentials("harold.holiday01", "123"),
                Role.USER);
    }

    private Map<Attraction, List<Review>> createExpectedAttractionReviewMapForBudapest() {
        User user = createTestUser();
        Attraction attraction1 = new Attraction(new Destination(0, "Budapest"), "Buda Castle",
                "Buda Castle is the historical castle and palace complex of the Hungarian Kings in Budapest.",
                Category.HISTORICAL);
        attraction1.setId(0);
        Attraction attraction2 = new Attraction(new Destination(0, "Budapest"), "Hungarian Parliament Building",
                "The Hungarian Parliament Building, also known as the Parliament of Budapest after its location, is the seat of the National Assembly of Hungary.",
                Category.HISTORICAL);
        attraction2.setId(1);
        Review review2 = new Review(user, 4, "Nice building.", attraction2);

        return Map.of(attraction1, new ArrayList<>(), attraction2, Arrays.asList(review2));
    }

    private Destination createTestDestinationWithAttractions(){
        Destination destination = new Destination(4, "Amsterdam");
        Attraction attraction1 = new Attraction(destination, "Anna Frank House",
                "A museum of Anna Frank, her life and her story.", Category.HISTORICAL);
        Attraction attraction2 = new Attraction(destination, "Vondelpark",
                "The most visited park in Amsterdam with more than 70 different types of flower.", Category.NATURE);

        destination.setAttractions(Arrays.asList(attraction1, attraction2));
        return destination;
    }

    private Map<Attraction, List<Review>> createAttractionReviewMapWithoutReviews(Destination destination){
        Map<Attraction, List<Review>> attractionReviewMap = new HashMap<>();
        List<Attraction> attractionList = destination.getAttractions();
        for (Attraction attraction : attractionList) {
            attractionReviewMap.put(attraction, new ArrayList<>());
        }
        return attractionReviewMap;
    }
}
