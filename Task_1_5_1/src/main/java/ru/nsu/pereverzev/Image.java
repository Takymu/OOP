package ru.nsu.pereverzev;

/**
 * Class representing image element.
 */
public class Image extends Element {
    private String url;
    private String alt;

    public static class Builder {
        private String url;
        private String alt;
        public Builder() {
            url = "";
            alt = "";
        }
        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }
        public Builder setAlt(String alt) {
            this.alt = alt;
            return this;
        }
        public Image build() {
            return new Image(url, alt);
        }
    }
    
    public Image(String url, String alt) {
        this.url = url;
        this.alt = alt;
    }

    public String getUrl() {
        return url;
    }

    public String getAlt() {    
        return alt;    
    }   

    public void setUrl(String url) {
        this.url = url;
    }

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
