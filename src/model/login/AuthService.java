package model.login;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private List<User> users = new ArrayList<>();
    private User loggedInUser = null;

    public AuthService() {
        // Simulasi data user (ganti dengan koneksi DB)
        users.add(new User("admin", "1234", "ADMIN"));
        users.add(new User("user1", "abcd", "USER"));
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        loggedInUser = null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}