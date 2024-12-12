package ru.nsu.pereverzev;

public class Quote extends Text {
    
    public static class Builder implements ru.nsu.pereverzev.Builder {
        private String text;
        public Builder setText(String text) {
            this.text = text;
            return this;
        }
        public Quote build() {
            return new Quote(text);
        }
    }

    Quote(String text) {
        super(text);
    }

    @Override
    public String toString() {
        String result = "> " + super.toString() + "\n";
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
        Text other = (Text) obj;
        if (!super.equals(other)) {
            return false;
        }
        return true;
    }
}
