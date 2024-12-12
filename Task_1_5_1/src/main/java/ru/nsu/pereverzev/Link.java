package ru.nsu.pereverzev;

public class Link extends Element {
    private String url;
    private String text;

    public static class Builder implements ru.nsu.pereverzev.Builder {
        private String url;
        private String text;
        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }
        public Builder setText(String text) {
            this.text = text;
            return this;
        }
        public Link build() {
            return new Link(text, url);
        }
    }

    Link() {
        this.url = "";
        this.text = "";
    }

    Link(String text, String url) {
        this.url = url;
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public String getText() {    
        return text;    
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        String result = "[" + text + "](" + url + ")\n";
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
        Link other = (Link) obj;
        if (!text.equals(other.text)) {
            return false;
        }
        if (!url.equals(other.url)) {
            return false;
        }
        return true;
    }
}