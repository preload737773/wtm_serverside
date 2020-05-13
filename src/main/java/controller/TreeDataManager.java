//package main.java.controller;

import com.google.gson.Gson;
import main.java.model.ClientTask;
import main.java.model.TreeTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Stack;

//public class TreeDataManager implements DataManager {
   // private final String fileName;
  //  private TreeTask serverTreeTask;
    //private final Gson g = new Gson();

    /**
     * The Controller of the Data stored in the server's location
     * @param fileName Specifies the location of the data
     */
    /*
    public TreeDataManager(String fileName) throws IOException {
        this.fileName = fileName;
        var isExists = new File(fileName).isFile();
        if (!isExists) {
            new FileWriter(fileName).write(" ");
        }
       // this.serverTreeTask = JsonIO.readTasksFromJsonFile(fileName);
    }
*/
    /**
     * Get the real file path
     * @return Returns the file's specified location on the server
     */
    /*
    public String getFileName() {
        return fileName;
    }
*/
    /**
     * Gets the stored Tasks from the server database
     * @return Returns Tasks in the JSON format string
     */
    /*
    public String getTasks() {
        return "[" + g.toJson(serverTreeTask) + "]";
    }
*/
    /**
     * Deletes the specified through request parameters Task from the server database
     * @param requestParameters The parameters string from the request
     * @return Returns if the removal was successful
     * @throws IOException FileWriter might cause an error
     */
    /*
    public boolean deleteTask(String requestParameters) throws IOException {
        boolean res = false;
        var taskToDelete = g.fromJson(requestParameters, ClientTask.class);
        Stack<TreeTask> stack = new Stack<>();
        stack.push(serverTreeTask);
        TreeTask root;
        while (!stack.isEmpty()) {
            root = stack.pop();
            if (root != null) {
                if (!root.getName().equals(taskToDelete.getName())) {
                    if ((root.getChildren() != null) && (!root.getChildren().isEmpty())) {
                        for (var i = 0; i < root.getChildren().size(); i++) {
                            if (!root.getChild(i).getName().equals(taskToDelete.getName())) {
                                stack.push(root.getChild(i));
                            } else {
                                res = true;
                                root.removeChild(root.getChild(i));
                            }
                        }
                    }
                }
                else {
                    serverTreeTask = null;
                    res = true;
                }
            } else {
                res = true;
            }
        }
        if (res)
            JsonIO.writeToJsonFile(fileName, serverTreeTask);
        return res;
    }
*/
    /**
     * Edits the specified in the request parameters Task in the server database
     * @param requestParameters The parameters string from the request
     * @return Returns if the edit was successful
     * @throws IOException FileWriter might cause an error
     */
/*    public boolean editTask(String requestParameters) throws IOException {
        var taskToEdit = g.fromJson(requestParameters, ClientTask.class);
        System.out.println(taskToEdit.getName() + ' ' + taskToEdit.getContacts() + ' ' + taskToEdit.getId());
        TreeTask treeTask = new TreeTask(taskToEdit.getId(), taskToEdit.getName(), taskToEdit.getContacts(), new Date());
        TreeTask serverTreeTask = JsonIO.readTasksFromJsonFile(fileName);
        boolean res = false;
        Stack<TreeTask> stack = new Stack<>();
        stack.push(serverTreeTask);
        TreeTask root;
        while (!stack.isEmpty()) {
            root = stack.pop();
            if (root != null) {
                if (!root.getName().equals(taskToEdit.getAncestorName())) {
                    if ((root.getChildren() != null) && (!root.getChildren().isEmpty())) {
                        for (var child : root.getChildren()) {
                            stack.push(child);
                        }
                    }
                }
                else {
                    root.setName(treeTask.getName());
                    root.setContacts(treeTask.getContacts());
                    res = true;
                }
            }
        }
        if (res)
            JsonIO.writeToJsonFile(fileName, serverTreeTask);
        return res;
    }
*/
    /**
     * Adds the specified in the request parameters Task to the server database
     * @param requestParameters The parameters string from the request
     * @throws IOException FileWriter might cause an error
     */
    /*
    public void addTask(String requestParameters) throws IOException {
        ClientTask a = g.fromJson(requestParameters, ClientTask.class);
        TreeTask treeTaskToAdd = new TreeTask(a.getId(), a.getName(), a.getContacts(), new Date());
        TreeTask serverTreeTask = JsonIO.readTasksFromJsonFile(fileName);
        Stack<TreeTask> stack = new Stack<>();
        stack.push(serverTreeTask);
        boolean res = false;
        TreeTask root;
        while (!stack.isEmpty()) {
            root = stack.pop();
            if (root != null) {
                if (root.getName().equals(a.getAncestorName())) {
                    if ((root.getChildren() != null) && (!root.getChildren().isEmpty())) {
                        root.addChild(treeTaskToAdd);
                    }
                    else {
                        root.addChild(treeTaskToAdd);
                    }
                    res = true;
                }
                else {
                    if ((root.getChildren() != null) && (!root.getChildren().isEmpty())) {
                        for (var child : root.getChildren()) {
                            stack.push(child);
                        }
                    }
                }
            }
        }
        if (!res) {
            serverTreeTask = treeTaskToAdd;
        }
        if (serverTreeTask != null)
            JsonIO.writeToJsonFile(fileName, serverTreeTask);
    }
}
*/