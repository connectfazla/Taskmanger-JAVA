import java.util.ArrayList;

public class AuthManager {
    private ArrayList<User> users;

    public AuthManager() {
        users = new ArrayList<>();
        users.add(new User("admin", "password")); // Default user
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
