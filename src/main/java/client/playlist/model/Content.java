package client.playlist.model;

import java.util.List;

public class Content {
    private String name;
    private List<Preroll> preroll;
    private List<Video> videos;

    public String getName() {
        return name;
    }

    public List<Preroll> getPreroll() {
        return preroll;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreroll(List<Preroll> preroll) {
        this.preroll = preroll;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
