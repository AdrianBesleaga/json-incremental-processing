package client.playlist.model;

import java.util.List;
import java.util.Objects;

public class OutputPlaylist {
    private final String name;
    private final List<String> videos;

    public OutputPlaylist(String name, List<String> videos) {
        this.name = name;
        this.videos = videos;
    }

    public String getName() {
        return name;
    }

    public List<String> getVideos() {
        return videos;
    }

    @Override
    public String toString() {
        return "OutputPlaylist{" +
                "name='" + name + '\'' +
                ", videos=" + videos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputPlaylist that = (OutputPlaylist) o;
        return name.equals(that.name) && videos.equals(that.videos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, videos);
    }
}
