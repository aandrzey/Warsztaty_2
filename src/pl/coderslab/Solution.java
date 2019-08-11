package pl.coderslab;

public class Solution {

    private int id;
    private String created;
    private String updated;
    private String description;
    private Exercise excercise;
    private User user;

    public Solution() {
    }

    public Solution(String created, String updated, String description, Exercise excercise, User user) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.excercise = excercise;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Exercise getExcercise() {
        return excercise;
    }

    public void setExcercise(Exercise excercise) {
        this.excercise = excercise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", excercise=" + excercise +
                ", user=" + user +
                '}';
    }
}
