package client.playlist.model;

import java.util.List;

public class Video {
    private String name;
    private Attribute attributes;

    public String getName() {
        return name;
    }

    public Attribute getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "Video{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                '}';
    }

    public static class Attribute {
        private String language;
        private String aspect;
        private List<String> countries;

        public String getLanguage() {
            return language;
        }


        public String getAspect() {
            return aspect;
        }


        public List<String> getCountries() {
            return countries;
        }


        @Override
        public String toString() {
            return "Attribute{" +
                    "language='" + language + '\'' +
                    ", aspect='" + aspect + '\'' +
                    ", countries=" + countries +
                    '}';
        }
    }
}
