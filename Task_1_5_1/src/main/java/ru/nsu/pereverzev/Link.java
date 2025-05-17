package ru.nsu.pereverzev;

/**
 * Represents a link element in a document.
 */
public class Link extends Element {
    private String url;
    private String text;

    /**
     * Constructs an empty link.
     */
    Link() {
        this.url = "";
        this.text = "";
    }

    /**
     * Constructs a link with text and URL.
     */
    Link(String text, String url) {
        this.url = url;
        this.text = text;
    }

    /**
     * Builder for creating Link instances.
     */
    public static class Builder implements ru.nsu.pereverzev.Builder {
        private String url;
        private String text;

        /**
         * Sets the link URL.
         */
        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * Sets the link text.
         */
        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        /**
         * Builds the Link instance.
         */
        public Link build() {
            return new Link(text, url);
        }
    }

    /**
     * Returns the link URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the link text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the link text.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Sets the link URL.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Returns the link as a formatted string.
     */
    @Override
    public String toString() {
        String result = "[" + text + "](" + url + ")\n";
        return result;
    }

    /**
     * Checks equality with another object.
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