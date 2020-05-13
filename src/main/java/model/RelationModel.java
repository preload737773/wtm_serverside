package main.java.model;

public class RelationModel {
    private final int id;
    private final int anotherId;

    public RelationModel(int id, int anotherId) {
        this.id = id;
        this.anotherId = anotherId;
    }

    public int getId() {
        return id;
    }

    public int getAnotherId() {
        return anotherId;
    }
}
