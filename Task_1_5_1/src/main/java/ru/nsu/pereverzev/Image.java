package ru.nsu.pereverzev;

/**
 * Class representing an image element in a document.
 */
public class Image extends Element {
    private String url;
    private String alt;

    /**
     * Builder class for Image.
     */
    public static class Builder {
        private String url;
        private String alt;

        /**
         * Constructs a new Builder with default values.
         */
        public Builder() {
            url = "";
            alt = "";
        }

        /**
         * Sets the URL of the image.
         *
         * @param url the URL of the image
         * @return this Builder
         */
        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * Sets the alt text of the image.
         *
         * @param alt the alt text of the image
         * @return this Builder
         */
        public Builder setAlt(String alt) {
            this.alt = alt;
            return this;
        }

        /**
         * Builds an Image object from the current state of this Builder.
         *
         * @return the built Image object
         */
        public Image build() {
            return new Image(url, alt);
        }
    }
    
    /**
     * Constructs a new Image with the given URL and alt text.
     *
     * @param url the URL of the image
     * @param alt the alt text of the image
     */
    public Image(String url, String alt) {
        this.url = url;
        this.alt = alt;
    }

    /**
     * Gets the URL of the image.
     *
     * @return the URL of the image
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the alt text of the image.
     *
     * @return the alt text of the image
     */
    public String getAlt() {    
        return alt;    
    }   

    /**
     * Sets the URL of the image.
     *
     * @param url the new URL of the image
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Sets the alt text of the image.
     *
     * @param alt the new alt text of the image
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }
    
    @Override
    public String toString() {
        return String.format("![%s](%s)\n", alt, url);
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
        Image other = (Image) obj;
        if (!alt.equals(other.alt)) {
            return false;
        }
        if (!url.equals(other.url)) {
            return false;
        }
        return true;
    }

}
