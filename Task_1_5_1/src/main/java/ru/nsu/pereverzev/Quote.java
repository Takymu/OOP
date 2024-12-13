package ru.nsu.pereverzev;

/**
 * Represents a quote element in a document.
 */
public class Quote extends Text {
    
    /**
     * Builder for creating Quote instances.
     */
    public static class Builder implements ru.nsu.pereverzev.Builder {
        private String text;

        /**
         * Sets the quote text.
         */
        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        /**
         * Builds the Quote instance.
         */
        public Quote build() {
            return new Quote(text);
        }
    }

    /**
     * Constructs a Quote with given text.
     */
    Quote(String text) {
        super(text);
    }

    /**
     * Returns the quote as a formatted string.
     */
    @Override
    public String toString() {
        return "> " + super.toString();
    }

    /**
     * Checks equality with another object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Quote)) {
            return false;
        }
        
        Quote other = (Quote) obj;
        return this.toString().equals(other.toString());
    }
}
