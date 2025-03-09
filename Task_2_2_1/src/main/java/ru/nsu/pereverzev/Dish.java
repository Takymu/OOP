package ru.nsu.pereverzev;

/**
 * Represents a Dish with a type and status.
 */
public class Dish {
    final String type;
    String status;

    /**
     * Constructs a Dish with the specified type and a default status.
     */
    Dish(String type) {
        this.status = "no status";
        this.type = type;
    }

    /**
     * Returns the current status of the dish.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the dish.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
