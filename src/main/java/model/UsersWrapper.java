package main.java.model;

import java.util.ArrayList;

public class UsersWrapper implements Cloneable {
    private final ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users);
    }

    public UsersWrapper() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        if (user != null)
            users.add(user);
    }

    public void deleteUser(User user) {
        if (user != null)
            users.remove(user);
    }
}
