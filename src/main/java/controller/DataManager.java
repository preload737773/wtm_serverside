package main.java.controller;

import java.io.IOException;

public interface DataManager {
    String getFileName();
    String getTasks();
    void addTask(String requestParameters) throws IOException;
    boolean editTask(String requestParameters) throws IOException;
    boolean deleteTask(String requestParameters) throws IOException;
}
