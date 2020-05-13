package main.java;

import main.java.controller.TaskDataManager;
import main.java.controller.UsersController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("DuplicatedCode")
public class TasksControlServlet extends javax.servlet.http.HttpServlet {
    public static final String USER_DATA_PATH = System.getProperty("catalina.home") + "\\user_data\\";
    public static final String JSON_EXTENSION = ".json";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("<POST addTask request>");
        response.setContentType("text/plain");
        String requestParamsString = request.getReader().readLine();
        var cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        UsersController controller = new UsersController();
        var user = controller.getUserByToken(token);
        TaskDataManager manager = new TaskDataManager(USER_DATA_PATH + user.getName() + JSON_EXTENSION);
        manager.addTask(requestParamsString);
        System.out.println(manager.getTasks());
        response.getOutputStream().print("Success");
        System.out.println("</POST addTask request>");
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.io.IOException {
        System.out.println("<GET request>");
        var cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        UsersController controller = new UsersController();
        var user = controller.getUserByToken(token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        TaskDataManager manager = new TaskDataManager(USER_DATA_PATH + user.getName() + JSON_EXTENSION);
        System.out.println(manager.getFileName());
        response.getOutputStream().print(manager.getTasks());
        System.out.println("</GET request>");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("<DELETE request>");
        String requestParamsString = request.getReader().readLine();
        response.setContentType("text/plain");
        var cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        UsersController controller = new UsersController();
        var user = controller.getUserByToken(token);
        TaskDataManager manager = new TaskDataManager(USER_DATA_PATH + user.getName() + JSON_EXTENSION);
        boolean res = manager.deleteTask(requestParamsString);
        System.out.println(manager.getTasks());
        if (res)
            response.getOutputStream().print("Success");
        else
            response.getOutputStream().print("Failure!");
        System.out.println("</DELETE request>");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("<PUT request>");
        response.setContentType("text/plain");
        String requestParamsString = request.getReader().readLine();
        var cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }
        UsersController controller = new UsersController();
        var user = controller.getUserByToken(token);
        TaskDataManager manager = new TaskDataManager(USER_DATA_PATH + user.getName() + JSON_EXTENSION);
        boolean res = manager.editTask(requestParamsString);
        System.out.println(manager.getTasks());
        if (res)
            response.getOutputStream().print("Success");
        else
            response.getOutputStream().print("Failure!");
        System.out.println("</PUT request>");
    }
}
