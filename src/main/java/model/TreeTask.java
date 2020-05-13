package main.java.model;

import java.util.ArrayList;
import java.util.Date;

public class TreeTask {
    private final int id;
    private String name;
    private String contacts;
    private Date createdAt;
    private ArrayList<TreeTask> children;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ArrayList<TreeTask> getChildren() {
        return new ArrayList<>(children);
    }

    public TreeTask(int id, String name, String contacts, Date createdAt) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
        this.createdAt = createdAt;
        this.children = new ArrayList<>();
    }

    public TreeTask(TreeTask newTreeTask) {
        this.id = newTreeTask.getId();
        this.name = newTreeTask.getName();
        this.contacts = newTreeTask.getContacts();
        this.createdAt = newTreeTask.getCreatedAt();
        this.children = newTreeTask.getChildren();
    }

    public void addChild(TreeTask treeTask) {
        if (treeTask != null)
            this.children.add(treeTask);
    }

    public void removeChild(TreeTask child) {
        if (child != null)
            this.children.remove(child);
    }

    public TreeTask getChild(int index) {
        return this.children.get(index);
    }
}
