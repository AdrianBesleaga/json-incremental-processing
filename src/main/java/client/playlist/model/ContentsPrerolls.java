package client.playlist.model;

import java.util.ArrayList;
import java.util.List;

public class ContentsPrerolls {
    private List<Content> content = new ArrayList<>();
    private List<Preroll> preroll = new ArrayList<>();


    public List<Preroll> getPreroll() {
        return preroll;
    }

    public void setPreroll(List<Preroll> preroll) {
        this.preroll = preroll;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

}
