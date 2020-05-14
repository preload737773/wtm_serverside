package main.java.model;

import java.util.ArrayList;
import java.util.Date;

public class Tasks implements TaskListContract {
    private ArrayList<Task> tasks;
    private static class IdResolver {
        private static int getNewId(ArrayList<Task> tasks) {
            int result;
            ArrayList<Integer> blackList = new ArrayList<>();
            for (Task task : tasks)
                blackList.add(task.getId());
            result = tasks.size();
            while (blackList.contains(result))
                result++;
            return result;
        }
    }

    public Tasks() {
        tasks = new ArrayList<>();
    }

    public Tasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void addTask(String name, String description, Date notificationDate, Task.SVGStyle style) {
        tasks.add(new Task(IdResolver.getNewId(tasks), name, description, notificationDate, null, null, "lightgreen", style));
    }

    @Override
    public void editTask(int id, String name, String description, Date notificationDate, String importance) {
        var task = getTaskById(id);
        if (task != null) {
            task.setName(name);
            task.setDescription(description);
            task.setNotificationDate(notificationDate);
            task.setImportance(importance);
        }
    }

    @Override
    public void deleteTask(int id) {
        var task = getTaskById(id);
        if (task != null) {
            if (task.getPredecessors() != null) {
                for (int predecessor :
                        task.getPredecessors()) {
                    Task pred = getTaskById(predecessor);
                    if (pred != null) {
                        pred.removeSuccessor(task.getId());
                    }
                }
            }
            if (task.getSuccessors() != null) {
                for (int successor :
                        task.getSuccessors()) {
                    Task succ = getTaskById(successor);
                    if (succ != null) {
                        succ.removePredecessor(task.getId());
                    }
                }
            }
            tasks.remove(task);
        }
    }

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public Task getTaskById(int id) {
        for (var task : tasks) {
            if (task.getId() == id)
                return task;
        }
        return null;
    }

    @Override
    public void addSuccessor(int id, int successorId) {
        var task = getTaskById(id);
        if (task != null) {
            task.addSuccessor(successorId);
        }
    }

    @Override
    public void addPredecessor(int id, int predecessorId) {
        var task = getTaskById(id);
        if (task != null) {
            task.addPredecessor(predecessorId);
        }
    }

    @Override
    public void removeSuccessor(int id, int successorId) {
        var task = getTaskById(id);
        if (task != null) {
            task.removeSuccessor(successorId);
        }
    }

    @Override
    public void removePredecessor(int id, int predecessorId) {
        var task = getTaskById(id);
        if (task != null) {
            task.removePredecessor(predecessorId);
        }
    }
}
