package training.travel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("Equals should return true when both Users are the same")
    void testEqualsShouldReturnTrueWhenUsersAreTheSame() throws Exception{
        //GIVEN
        User user1 = new User("TestName", new Credentials("username", "password"), Role.USER);
        User user2 = new User("TestName", new Credentials("username", "password"), Role.USER);

        //WHEN
        boolean result = user1.equals(user2);

        //THEN
        assertTrue(result, "Equals method is not implemented properly!");
        assertEquals(user1.hashCode(), user2.hashCode(), "If two object is equal than their hashCode should be equal too!");
    }

    @Test
    @DisplayName("Equals should return false when usernames are different")
    void testEqualsShouldReturnFalseWhenUserNamesAreDifferent() throws Exception{
        //GIVEN
        User user1 = new User("TestName", new Credentials("username", "password"), Role.USER);
        User user2 = new User("TestName2", new Credentials("username", "password"), Role.USER);

        //WHEN
        boolean result = user1.equals(user2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when Credentials are different")
    void testEqualsShouldReturnFalseWhenCredentialsAreDifferent() throws Exception{
        //GIVEN
        User user1 = new User("TestName", new Credentials("username", "password"), Role.USER);
        User user2 = new User("TestName", new Credentials("username2", "password2"), Role.USER);

        //WHEN
        boolean result = user1.equals(user2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }
}
