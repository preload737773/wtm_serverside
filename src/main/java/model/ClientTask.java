package main.java.model;

import java.util.Date;

public class ClientTask {
    private final int id;
    private final String name;
    private final String description;
    private final Date notificationDate;
    private final String importance;
    private final Task.SVGStyle style;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Date getNotificationDate() {
        return notificationDate;
    }
    public String getImportance() {
        return importance;
    }
    public Task.SVGStyle getStyle() {
        return style;
    }

    public ClientTask(String id, String name, String description, String notificationDate, String importance, Task.SVGStyle style) {
        this.id = Integer.parseInt(id);
        this.name = name;
        this.description = description;
        this.notificationDate = new Date(notificationDate);
        this.importance = importance;
        this.style = style;
    }
}
