package training.travel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {

    @Test
    @DisplayName("Equals should return true when both Reviews are the same")
    void testEqualsShouldReturnTrueWhenReviewsAreTheSame() throws Exception{
        //GIVEN
        Review review1 = createDefaultReview();
        Review review2 = createDefaultReview();

        //WHEN
        boolean result = review1.equals(review2);

        //THEN
        assertTrue(result, "Equals method is not implemented properly!");
        assertEquals(review1.hashCode(), review2.hashCode(), "If two object is equal than their hashCode should be equal too!");
    }

    @Test
    @DisplayName("Equals should return false when Users are different")
    void testEqualsShouldReturnFalseWhenUsersAreDifferent() throws Exception{
        //GIVEN
        Review review1 = createDefaultReview();
        Review review2 = createDefaultReview();
        review2.setUser(new User("testname", new Credentials("username2", "password2"), Role.USER));

        //WHEN
        boolean result = review1.equals(review2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when ratings are different")
    void testEqualsShouldReturnFalseWhenRatingsAreDifferent() throws Exception{
        //GIVEN
        Review review1 = createDefaultReview();
        Review review2 = createDefaultReview();
        review2.setRating(4);

        //WHEN
        boolean result = review1.equals(review2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when comments are different")
    void testEqualsShouldReturnFalseWhenCommentsAreDifferent() throws Exception{
        //GIVEN
        Review review1 = createDefaultReview();
        Review review2 = createDefaultReview();
        review2.setComment("Different Test Review");

        //WHEN
        boolean result = review1.equals(review2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when Attractions are different")
    void testEqualsShouldReturnFalseWhenAttractionsAreDifferent() throws Exception{
        //GIVEN
        Review review1 = createDefaultReview();
        Review review2 = createDefaultReview();
        review2.setAttraction(new Attraction(new Destination(1, "Budapest"), "Test Attraction",
                "This is a description", Category.HISTORICAL));

        //WHEN
        boolean result = review1.equals(review2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    private Review createDefaultReview(){
        User user = new User("testname", new Credentials("username", "password"), Role.USER);
        Attraction attraction = new Attraction(new Destination(1, "Budapest"), "Test Attraction",
                "This is a description", Category.MODERN);
        Review review = new Review(user, 5, "Test Review", attraction);

        return review;
    }

}
