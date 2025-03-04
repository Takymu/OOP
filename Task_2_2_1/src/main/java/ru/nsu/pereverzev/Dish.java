package ru.nsu.pereverzev;

public class Dish {
    String status;
    final String type;
    Dish(String type) {
        this.status = "no status";
        this.type = type;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
