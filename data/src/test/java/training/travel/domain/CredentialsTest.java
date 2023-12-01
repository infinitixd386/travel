package training.travel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CredentialsTest {

    @Test
    @DisplayName("Equals should return true when both Credentials are the same")
    void testEqualsShouldReturnTrueWhenCredentialsAreTheSame() throws Exception{
        //GIVEN
        Credentials credentials1 = new Credentials("username", "password");
        Credentials credentials2 = new Credentials("username", "password");

        //WHEN
        boolean result = credentials1.equals(credentials2);

        //THEN
        assertTrue(result, "Equals method is not implemented properly!");
        assertEquals(credentials1.hashCode(), credentials2.hashCode(), "If two object is equal than their hashCode should be equal too!");
    }

    @Test
    @DisplayName("Equals should return false when usernames are different")
    void testEqualsShouldReturnFalseWhenUsernamesAreDifferent() throws Exception{
        //GIVEN
        Credentials credentials1 = new Credentials("username", "password");
        Credentials credentials2 = new Credentials("username2", "password");

        //WHEN
        boolean result = credentials1.equals(credentials2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }

    @Test
    @DisplayName("Equals should return false when passwords are different")
    void testEqualsShouldReturnFalseWhenPasswordsAreDifferent() throws Exception{
        //GIVEN
        Credentials credentials1 = new Credentials("username", "password");
        Credentials credentials2 = new Credentials("username", "password2");

        //WHEN
        boolean result = credentials1.equals(credentials2);

        //THEN
        assertFalse(result, "Equals method is not implemented properly!");
    }
}
