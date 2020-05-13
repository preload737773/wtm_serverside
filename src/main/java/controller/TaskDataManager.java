package main.java.controller;

import com.google.gson.Gson;
import main.java.model.ClientTask;
import main.java.model.Tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskDataManager implements DataManager {
    private final String fileName;
    private final Tasks tasks;
    private final Gson g = new Gson();

    public TaskDataManager(String fileName) throws IOException {
        this.fileName = fileName;
        var isExists = new File(fileName).isFile();
        if (!isExists) {
           var fileWriter = new FileWriter(fileName);
           fileWriter.write("{tasks: []}");
           fileWriter.close();
        }
        this.tasks = JsonIO.readTasksFromJsonFile(fileName);
        System.out.println(tasks);
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public String getTasks() {
        return g.toJson(tasks);
    }

    @Override
    public void addTask(String requestParameters) throws IOException {
        System.out.println(requestParameters);
        ClientTask task = g.fromJson(requestParameters, ClientTask.class);
        tasks.addTask(task.getName(), task.getDescription(), task.getNotificationDate(), task.getStyle());
        JsonIO.writeToJsonFile(fileName, tasks);
    }

    @Override
    public boolean editTask(String requestParameters) throws IOException {
        var task = g.fromJson(requestParameters, ClientTask.class);
        if (task != null) {
            tasks.editTask(task.getId(), task.getName(), task.getDescription(), task.getNotificationDate(), task.getImportance());
            JsonIO.writeToJsonFile(fileName, tasks);
        }
        return false;
    }

    @Override
    public boolean deleteTask(String requestParameters) throws IOException {
        var task = g.fromJson(requestParameters, ClientTask.class);
        if (task != null) {
            tasks.deleteTask(task.getId());
            JsonIO.writeToJsonFile(fileName, tasks);
            return true;
        }
        return false;
    }

    public void addSuccessor(int id, int successorId) throws IOException {
        var task = tasks.getTaskById(id);
        if (task != null) {
            tasks.addSuccessor(task.getId(), successorId);
            JsonIO.writeToJsonFile(fileName, tasks);
        }
    }

    public void addPredecessor(int id, int predecessorId) throws IOException {
        var task = tasks.getTaskById(id);
        if (task != null) {
            tasks.addPredecessor(task.getId(), predecessorId);
            JsonIO.writeToJsonFile(fileName, tasks);
        }
    }

    public void removeSuccessor(int id, int successorId) throws IOException {
        var task = tasks.getTaskById(id);
        if (task != null) {
            tasks.removeSuccessor(task.getId(), successorId);
            JsonIO.writeToJsonFile(fileName, tasks);
        }
    }

    public void removePredecessor(int id, int predecessorId) throws IOException {
        var task = tasks.getTaskById(id);
        if (task != null) {
            tasks.removePredecessor(task.getId(), predecessorId);
            JsonIO.writeToJsonFile(fileName, tasks);
        }
    }
}
