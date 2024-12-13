package ru.nsu.pereverzev;

/**
 * Represents a text element in a document.
 */
public class Text extends Element {
    
    /**
     * Builder for creating Text instances.
     */
    public static class Builder implements ru.nsu.pereverzev.Builder {
        protected String text;
        
        /**
         * Initializes an empty text builder.
         */
        public Builder() {
            this.text = "";
        }

        /**
         * Sets the text content.
         * 
         * @param text the text content
         * @return this builder
         */
        public Builder setText(String text) {
            this.text = text;
            return this;
        }
        
        /**
         * Builds the Text instance.
         * 
         * @return the Text instance
         */
        public Text build() {
            return new Text(text);
        }
    }

    private final String text;

    /**
     * Creates a Text from an integer.
     * 
     * @param number the integer value
     * @return the Text instance
     */
    public static Text of(int number) {
        return new Text(String.valueOf(number));
    }

    /**
     * Creates a Text from a string.
     * 
     * @param text the string value
     * @return the Text instance
     */
    public static Text of(String text) {
        return new Text(text);
    }

    /**
     * Constructs a Text with given content.
     * 
     * @param text the text content
     */
    public Text(String text) {
        this.text = text;
    }

    /**
     * Represents bold text.
     */
    public static class Bold extends Text {
        /**
         * Creates bold text.
         * 
         * @param text the text content
         */
        public Bold(String text) {
            super(text);
        }

        /**
         * Returns bold-formatted text.
         * 
         * @return the formatted text
         */
        @Override
        public String toString() {
            return "**" + super.toString() + "**";
        }
    }

    /**
     * Represents italic text.
     */
    public static class Italic extends Text {
        /**
         * Creates italic text.
         * 
         * @param text the text content
         */
        public Italic(String text) {
            super(text);
        }

        /**
         * Returns italic-formatted text.
         * 
         * @return the formatted text
         */
        @Override
        public String toString() {
            return "*" + super.toString() + "*";
        }
    }

    /**
     * Represents crossed-out text.
     */
    public static class Crossed extends Text {
        /**
         * Creates crossed-out text.
         * 
         * @param text the text content
         */
        public Crossed(String text) {
            super(text);
        }

        /**
         * Returns crossed-out formatted text.
         * 
         * @return the formatted text
         */
        @Override
        public String toString() {
            return "~~" + super.toString() + "~~";
        }
    }

    /**
     * Represents code text.
     */
    public static class Code extends Text {
        /**
         * Creates code text.
         * 
         * @param text the text content
         */
        public Code(String text) {
            super(text);
        }

        /**
         * Returns code-formatted text.
         * 
         * @return the formatted text
         */
        @Override
        public String toString() {
            return "`" + super.toString() + "`";
        }
    }

    /**
     * Represents strikethrough text.
     */
    public static class Strikethrough extends Text {
        /**
         * Creates strikethrough text.
         * 
         * @param text the text content
         */
        public Strikethrough(String text) {
            super(text);
        }

        /**
         * Returns strikethrough-formatted text.
         * 
         * @return the formatted text
         */
        @Override
        public String toString() {
            return "~" + super.toString() + "~";
        }
    }

    /**
     * Represents underlined text.
     */
    public static class Underlined extends Text {
        /**
         * Creates underlined text.
         * 
         * @param text the text content
         */
        public Underlined(String text) {
            super(text);
        }

        /**
         * Returns underlined-formatted text.
         * 
         * @return the formatted text
         */
        @Override
        public String toString() {
            return "<u>" + super.toString() + "</u>";
        }
    }

    /**
     * Returns the text content.
     * 
     * @return the text content
     */
    @Override
    public String toString() {
        return text;
    }

    /**
     * Checks if this text is equal to the given object.
     * 
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Text other = (Text) obj;
        if (!text.equals(other.text)) {
            return false;
        }
        return true;
    }
}