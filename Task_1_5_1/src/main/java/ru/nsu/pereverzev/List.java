package ru.nsu.pereverzev;

import java.util.ArrayList;

/**
 * Represents a list of elements in a document.
 */
public class List extends Element {
    private ArrayList<Element> elements;

    /**
     * Constructs an empty list.
     */
    public List() {
        this.elements = new ArrayList<>();
    }

    /**
     * Constructs a list with given elements.
     */
    public List(ArrayList<Element> elements) {
        this.elements = elements;
    }

    /**
     * Adds an element to the list.
     */
    public void add(Element element) {
        elements.add(element);
    }

    /**
     * Builder for creating List instances.
     */
    public static class Builder implements ru.nsu.pereverzev.Builder {
        private List list;

        /**
         * Initializes a new List builder.
         */
        public Builder() {
            list = new List();
        }
        
        /**
         * Adds a text element to the list.
         */
        public Builder add(String text) {
            list.add(new Text.Builder().setText(text).build());
            return this;
        }

        /**
         * Adds an element to the list.
         */
        public Builder add(Element element) {
            list.add(element);
            return this;
        }

        /**
         * Builds the list.
         */
        public List build() {
            return new List(list.elements);
        }
    }

    /**
     * Returns the list as a formatted string.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < elements.size(); i++) {
            result.append((i + 1)).append(". ").append(elements.get(i)).append("\n");
        }
        return result.toString().trim();
    }

    /**
     * Checks equality with another object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof List)) return false;
        
        List other = (List) obj;
        if (elements.size() != other.elements.size()) return false;
        
        for (int i = 0; i < elements.size(); i++) {
            if (!elements.get(i).equals(other.elements.get(i))) {
                return false;
            }
        }
        return true;
    }
}
