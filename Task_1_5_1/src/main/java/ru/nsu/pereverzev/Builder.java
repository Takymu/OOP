package ru.nsu.pereverzev;

/**
 * Generic builder interface for creating document elements.
 */
public interface Builder {
    /**
     * Builds and returns an element.
     * @return the constructed element
     */
    Element build();
}