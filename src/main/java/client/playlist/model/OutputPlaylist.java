package client.playlist.model;

import java.util.Objects;
import java.util.Set;

public class OutputPlaylist {
    private final String name;
    private final Set<String> videos;

    public OutputPlaylist(String name, Set<String> videos) {
        this.name = name;
        this.videos = videos;
    }

    public String getName() {
        return name;
    }

    public Set<String> getVideos() {
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
