package ru.nsu.pereverzev;

public class Text extends Element {
    
    public static class Builder implements ru.nsu.pereverzev.Builder {
        protected String text;
        
        public Builder() {
            this.text = "";
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }
        
        public Text build() {
            return new Text(text);
        }
        
    }

    private final String text;

    public static Text of(int number) {
        return new Text(String.valueOf(number));
    }

    public static Text of(String text) {
        return new Text(text);
    }


    public Text(String text) {
        this.text = text;
    }

    public static class Bold extends Text {
        public Bold(String text) {
            super(text);
        }

        @Override
        public String toString() {
            String result = "**" + super.toString() + "**";
            return result;
        }
    }
    public static class Italic extends Text {
        public Italic(String text) {
            super(text);
        }

        @Override
        public String toString() {
            String result = "*" + super.toString() + "*";
            return result;
        }
    }
    public static class Crossed extends Text {
        public Crossed(String text) {
            super(text);
        }

        @Override
        public String toString() {
            String result = "~~" + super.toString() + "~~";
            return result;
        }
    }
    public static class Code extends Text {
        public Code(String text) {
            super(text);
        }

        @Override
        public String toString() {
            String result = "`" + super.toString() + "`";
            return result;
        }
    }
    
    @Override
    public String toString() {
        return text;
    }

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