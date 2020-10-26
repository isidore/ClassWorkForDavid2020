import java.util.ArrayList;

public class Users {
    private ArrayList<User> registeredUsers = new ArrayList<>();

    public void register(User user) {
        if (findByUserName(user.getUsername()) == null){
            registeredUsers.add(user);
        }
    }

    public User logIn(String username, String password) {
        User user = findByUserName(username);
        if ((user != null) && (user.verifyPassword(password))){
            user.logIn();
            return user;
        } else {
            return null;
        }
    }

    private User findByUserName(String username) {
        for (User user: registeredUsers) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }
}