package client.playlist.model;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
    private String name;
    private final List<Video> videos = new ArrayList<>();

    public List<Video> getVideos() {
        return videos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "name='" + name + '\'' +
                ", videos=" + videos +
                '}';
    }
}
