package main.java;

import com.google.gson.Gson;
import main.java.controller.TaskDataManager;
import main.java.controller.UsersController;
import main.java.model.RelationModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.java.TasksControlServlet.JSON_EXTENSION;
import static main.java.TasksControlServlet.USER_DATA_PATH;

@WebServlet(name = "RelationsControlServlet")
public class RelationsControlServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("<POST changeRelation request>");
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
        Gson g = new Gson();
        RelationModel model = g.fromJson(requestParamsString, RelationModel.class);
        manager.addSuccessor(model.getId(), model.getAnotherId());
        manager.addPredecessor(model.getAnotherId(), model.getId());
        System.out.println(manager.getTasks());
        response.getOutputStream().print("Success");
        System.out.println("</POST changeRelation request>");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("<DELETE changeRelation request>");
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
        Gson g = new Gson();
        RelationModel model = g.fromJson(requestParamsString, RelationModel.class);
        manager.removeSuccessor(model.getId(), model.getAnotherId());
        manager.removePredecessor(model.getAnotherId(), model.getId());
        System.out.println(manager.getTasks());
        response.getOutputStream().print("Success");
        System.out.println("</DELETE changeRelation request>");
    }
}
