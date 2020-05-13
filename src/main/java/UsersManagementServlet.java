package main.java;

import com.google.gson.Gson;
import main.java.controller.UsersController;
import main.java.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersManagementServlet extends HttpServlet {
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var loginInformation = request.getReader().readLine();
        response.setContentType("text/plain");
        Gson g = new Gson();
        UsersController controller = new UsersController();
        User user = g.fromJson(loginInformation, User.class);
        var writer = response.getWriter();
        if (!controller.addUser(user.getName(), user.getPassword())) {
            writer.println("Username is taken");
        }
        else {
            writer.println("Success");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var loginInformation = request.getReader().readLine();
        response.setContentType("text/plain");
        Gson g = new Gson();
        UsersController controller = new UsersController();
        User user = g.fromJson(loginInformation, User.class);
        var writer = response.getWriter();
        if (!controller.deleteUser(user.getName())) {
            writer.println("No such user");
        }
        else {
            writer.println("Success");
        }
    }
}
