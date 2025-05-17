package ru.nsu.pereverzev;

/**
 * Abstract base class for document elements.
 */
public abstract class Element {
    /**
     * Converts the element to a string representation.
     */
    public abstract String toString();

    /**
     * Checks equality with another object.
     */
    public abstract boolean equals(Object obj);
}
