package entities;

import java.sql.Time;
import java.sql.Timestamp;

public class Pet {
    private int id;
    private String petName;
    private int age;
    private float weight;
    private int ownerID;
    private int petTypeId;
    private Timestamp createdAt;
    private Timestamp lastUpdated;

    public Pet(){

    }
    public Pet(String petName, int age, float weight, int ownerID, int petTypeId) {
        this.petName = petName;
        this.age = age;
        this.weight = weight;
        this.ownerID = ownerID;
        this.petTypeId = petTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(int petTypeId) {
        this.petTypeId = petTypeId;
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

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", petName='" + petName + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", ownerID=" + ownerID +
                ", petTypeId=" + petTypeId +
                ", createdAt=" + createdAt +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
