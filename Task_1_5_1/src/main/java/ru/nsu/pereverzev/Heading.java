package ru.nsu.pereverzev;

public class Heading extends Text {
    int level;

    public static class Builder extends Text.Builder { 
        private int level;

        public Builder() {
            super();
            level = 0;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setLevel(int level) {
            if (level < 0 || level > 6) {
                throw new IllegalArgumentException("Level must be between 0 and 6");
            }
            this.level = level;
            return this;
        }

        public Heading build() {
            return new Heading(text, level);
        }
    };

    public Heading(String text, int level) {
        super(text);
        this.level = level;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < level; i++) {
            result += "#";
        }
        result += " " + super.toString() + "\n";
        return result;
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
        Heading other = (Heading) obj;
        if (level != other.level) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return true;
    }
}
