package main.java.model;

import java.util.ArrayList;
import java.util.Date;

public class Task {
    public enum SVGStyle {startNode, endNode, processNode, ifNode}
    private final int id;
    private String name;
    private String description;
    private Date notificationDate;
    private ArrayList<Integer> predecessors;
    private ArrayList<Integer> successors;
    private String importance;
    private final SVGStyle style;

    public Task(int id, String name, String description, Date notificationDate, ArrayList<Integer> predecessors, ArrayList<Integer> successors, String importance, SVGStyle style) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.notificationDate = notificationDate;
        this.predecessors = predecessors;
        this.successors = successors;
        this.importance = importance;
        this.style = style;
    }

    public SVGStyle getStyle() {
        return style;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public ArrayList<Integer> getPredecessors() {
        return new ArrayList<>(predecessors);
    }

    public ArrayList<Integer> getSuccessors() {
        return new ArrayList<>(successors);
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public void addSuccessor(int successorId) {
        if (successors != null) {
            successors.add(successorId);
        }
        else {
            successors = new ArrayList<>();
            successors.add(successorId);
        }
    }

    public void removeSuccessor(int successorId) {
        if (successors != null) {
            successors.remove(new Integer(successorId));
        }
    }

    public void addPredecessor(int predecessorId) {
        if (predecessors != null) {
            predecessors.add(predecessorId);
        }
        else {
            predecessors = new ArrayList<>();
            predecessors.add(predecessorId);
        }
    }

    public void removePredecessor(int predecessorId) {
        if (predecessors != null) {
            predecessors.remove(new Integer(predecessorId));
        }
    }
}
