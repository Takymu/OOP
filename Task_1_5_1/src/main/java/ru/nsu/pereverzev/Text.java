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
         */
        public Builder setText(String text) {
            this.text = text;
            return this;
        }
        
        /**
         * Builds the Text instance.
         */
        public Text build() {
            return new Text(text);
        }
    }

    private final String text;

    /**
     * Creates a Text from an integer.
     */
    public static Text of(int number) {
        return new Text(String.valueOf(number));
    }

    /**
     * Creates a Text from a string.
     */
    public static Text of(String text) {
        return new Text(text);
    }

    /**
     * Constructs a Text with given content.
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
         */
        public Bold(String text) {
            super(text);
        }

        /**
         * Returns bold-formatted text.
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
         */
        public Italic(String text) {
            super(text);
        }

        /**
         * Returns italic-formatted text.
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
         */
        public Crossed(String text) {
            super(text);
        }

        /**
         * Returns crossed-out formatted text.
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
         */
        public Code(String text) {
            super(text);
        }

        /**
         * Returns code-formatted text.
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
         */
        public Strikethrough(String text) {
            super(text);
        }

        /**
         * Returns strikethrough-formatted text.
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
         */
        public Underlined(String text) {
            super(text);
        }

        /**
         * Returns underlined-formatted text.
         */
        @Override
        public String toString() {
            return "<u>" + super.toString() + "</u>";
        }
    }

    /**
     * Returns the text content.
     */
    @Override
    public String toString() {
        return text;
    }

    /**
     * Checks if this text is equal to the given object.
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