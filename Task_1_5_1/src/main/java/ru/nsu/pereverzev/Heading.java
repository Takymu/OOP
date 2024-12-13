package ru.nsu.pereverzev;

/**
 * Represents a document heading with a specific level.
 */
public class Heading extends Text {
    private int level;

    /**
     * Constructs a Heading with text and level.
     */
    public Heading(String text, int level) {
        super(text);
        this.level = level;
    }

    /**
     * Builder for creating Heading instances.
     */
    public static class Builder extends Text.Builder { 
        private int level;

        /**
         * Initializes a new Heading builder.
         */
        public Builder() {
            super();
            level = 0;
        }

        /**
         * Sets the heading text.
         */
        @Override
        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        /**
         * Sets the heading level (0-6).
         */
        public Builder setLevel(int level) {
            if (level < 0 || level > 6) {
                throw new IllegalArgumentException("Level must be between 0 and 6");
            }
            this.level = level;
            return this;
        }

        /**
         * Builds the Heading instance.
         */
        public Heading build() {
            return new Heading(text, level);
        }
    }

    /**
     * Returns the heading as a formatted string.
     */
    @Override
    public String toString() {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < level; i++) {
            prefix.append("#");
        }
        return prefix + " " + super.toString();
    }

    /**
     * Checks equality with another object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Heading)) {
            return false;
        }
        
        Heading other = (Heading) obj;

        return (this.toString().equals(other.toString()));
    }
}
