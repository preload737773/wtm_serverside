package main.java;

import com.google.gson.Gson;
import main.java.controller.UsersController;
import main.java.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var loginInformation = request.getReader().readLine();
        response.setContentType("text/plain");
        Gson g = new Gson();
        UsersController controller = new UsersController();
        User user = g.fromJson(loginInformation, User.class);
        String token = controller.generateToken(user.getName(), user.getPassword());
        System.out.println(token);
        var writer = response.getWriter();
        if (controller.getUserByToken(token) != null)
            writer.println(token);
        else
            writer.println("Login error");
    }
}
