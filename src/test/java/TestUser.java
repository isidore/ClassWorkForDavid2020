import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUser {
    @BeforeEach
    void setUp(){
        Users.clear();
    }
    @Test
    void testUser() {
        User uut = createUser();
        assertEquals("Jacob", uut.getFirstName());
        assertEquals("Nash", uut.getLastName());
        assertEquals("Jacob.Nash@intel.com", uut.getEmail());
        assertEquals("JNashty", uut.getUsername());
        assertEquals("12345", uut.getPassword());
    }

    @Test
    void testRegisterUser() {
        User testUser = createUser();
        Users.getInstance().register(testUser);
        assertEquals(false, testUser.isLoggedIn());
        User somebodyWhoLoggedIn = Users.getInstance().logIn("JNashty", "12345");
        assertEquals(true, somebodyWhoLoggedIn.isLoggedIn());
        Users.getInstance().logOut(somebodyWhoLoggedIn);
        assertEquals(false, somebodyWhoLoggedIn.isLoggedIn());
    }

    @Test
    void testRegisteredSeller() {
        User jacobNash = createAndLogInUser();
        assertEquals(false, jacobNash.isSeller());
        jacobNash.makeSeller();
        assertEquals(true, jacobNash.isSeller());
    }

    private User createAndLogInUser() {
        User testUser = createUser();
        Users.getInstance().register(testUser);
        return Users.getInstance().logIn("JNashty", "12345");
    }

    @Test
    void testUnregisteredUser() {
        User somebodyWhoLoggedIn = Users.getInstance().logIn("UnregisteredUser", "badpassword");
        assertEquals(null, somebodyWhoLoggedIn);
    }

    @Test
    void testWrongPassword() {
        Users users = Users.getInstance();
        users.register(createUser());
        User somebodyWhoLoggedIn = users.logIn("JNashty", "badpassword");
        assertEquals(null, somebodyWhoLoggedIn);
    }

    private User createUser() {
        String firstName = "Jacob";
        String lastName = "Nash";
        String email = "Jacob.Nash@intel.com";
        String username = "JNashty";
        String password = "12345";
        return new User(firstName, lastName, email, username, password);
    }


}
