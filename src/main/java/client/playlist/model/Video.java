package client.playlist.model;

import java.util.List;

public class Video {
    private String name;
    private Attribute attributes;

    public static class Attribute {
        private String language;
        private String aspect;
        private List<String> countries;

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getAspect() {
            return aspect;
        }

        public void setAspect(String aspect) {
            this.aspect = aspect;
        }

        public List<String> getCountries() {
            return countries;
        }

        public void setCountries(List<String> countries) {
            this.countries = countries;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attribute getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute attributes) {
        this.attributes = attributes;
    }
}
