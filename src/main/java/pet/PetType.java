package entities;

import java.sql.Timestamp;

public class PetType {

    private int id;
    private String type;
    private Timestamp createdAt;
    private Timestamp lastUpdated;

    public PetType(String type) {
        this.type = type;
    }

    public PetType(){
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public PetType(int id, String type, Timestamp createdAt, Timestamp lastUpdated) {
        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
        this.lastUpdated = lastUpdated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PetType: " +
                "id = " + id +
                ", type = " + type +
                ", created At = " + createdAt +
                ", last updated = " + lastUpdated;
    }
}
