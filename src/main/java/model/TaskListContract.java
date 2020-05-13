package main.java.model;

import java.util.ArrayList;
import java.util.Date;

public interface TaskListContract {
    void addTask(String name, String description, Date notificationDate, Task.SVGStyle style);
    void editTask(int id, String name, String description, Date notificationDate, String importance);
    void deleteTask(int id);
    ArrayList<Task> getTasks();
    Task getTaskById(int id);
    void addSuccessor(int id, int successorId);
    void addPredecessor(int id, int predecessorId);
    void removeSuccessor(int id, int successorId);
    void removePredecessor(int id, int predecessorId);
}
