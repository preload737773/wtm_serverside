package main.java.controller;

import main.java.model.User;
import main.java.model.UsersWrapper;

import java.io.IOException;

public class UsersController {
    private final String usersDatabasePath = System.getProperty("catalina.home") + "\\user_data\\users.json";

    /**
     * Returns the users list from the users database
     * @return ArrayList of users
     * @throws IOException Might get a File Reader error
     */
    public UsersWrapper getUsers() throws IOException {
        return JsonIO.readUsersFromJsonFile(usersDatabasePath);
    }

    /**
     * Adds the specified user to the users database
     * @param userName User's name
     * @param password User's pass
     * @return Returns true if the user was added to the users database
     * @throws IOException Might get a File Reader error
     */
    public boolean addUser(String userName, String password) throws IOException {
        boolean res = false;
        UsersWrapper users = getUsers();
        if (users != null) {
            if (!isUser(userName)) {
                users.addUser(new User(userName, password));
                res = true;
            }
        }
        else
            users = new UsersWrapper();
        if (res)
            JsonIO.writeToJsonFile(usersDatabasePath, users);
        return res;
    }

    /**
     * Deletes the user from the users database
     * @param userName User's name
     * @return Returns true if the user was found and deleted
     * @throws IOException Might get a File Reader error
     */
    public boolean deleteUser(String userName) throws IOException {
        boolean res = false;
        UsersWrapper users = getUsers();
        for (var user : users.getUsers()) {
            var currentUserName = user.getName();
            if ((currentUserName != null) && (currentUserName.equals(userName))) {
                users.deleteUser(user);
                res = true;
                break;
            }
        }
        if (res)
            JsonIO.writeToJsonFile(usersDatabasePath, users);
        return res;
    }

    /**
     * Check if user with such name is already exists
     * @param name User's name
     * @return Returns true if there is a user with such username in the users database
     * @throws IOException Might get a File Reader error
     */
    public boolean isUser(String name) throws IOException {
        boolean res = false;
        var users = getUsers();
        if (users != null) {
            for (var user : users.getUsers()) {
                var userName = user.getName();
                if ((userName != null) && (userName.equals(name))) {
                    res = true;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * Generates the token for the client
     * @param userName User's name
     * @param password User's pass
     * @return Returns the token string
     */
    public String generateToken(String userName, String password) {
        String str = userName+"."+password;
        return AES.encrypt(str);
    }

    /**
     * Gets the user from users database
     * @param token The generated user token
     * @return Returns the user with the same username and password
     * @throws IOException Might get a File Reader error
     */
    public User getUserByToken(String token) throws IOException {
        User result = null;
        var decipheredToken = AES.decrypt(token);
        assert decipheredToken != null;
        var dotIndex = decipheredToken.indexOf(".");
        var part1 = decipheredToken.substring(0, dotIndex);
        var part2 = decipheredToken.substring(dotIndex + 1);
        User user = new User(part1, part2);
        UsersWrapper users = getUsers();
        for (var currentUser : users.getUsers()) {
                if ((currentUser != null) &&(currentUser.getName() != null) && (currentUser.getPassword() != null)) {
                    if ((currentUser.getName().equals(user.getName())) && (currentUser.getPassword().equals(user.getPassword())))
                        result = currentUser;
                }
        }
        return result;
    }
}
