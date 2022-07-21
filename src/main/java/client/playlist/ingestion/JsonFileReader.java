package client.playlist.ingestion;

import client.playlist.model.Content;
import client.playlist.model.ContentsPrerolls;
import client.playlist.model.Preroll;
import client.playlist.model.Video;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Read big json file with JacksonStreamingApi (incremental processing)
 */
public class JsonFileReader implements IJsonFileReader {
    private final JsonFactory jfactory;
    private final ObjectMapper mapper;

    public JsonFileReader() {
        jfactory = new JsonFactory();
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    public ContentsPrerolls readJsonFile(File file) {
        ContentsPrerolls contentsPrerolls = new ContentsPrerolls();
        try {
            JsonParser jParser = jfactory.createParser(file);
            while (jParser.nextToken() != JsonToken.END_OBJECT) {
                jParser.nextToken();
                String fieldname = jParser.getCurrentName();
                switch (fieldname) {
                    case "content":
                        contentsPrerolls.setContent(readContents(jParser));
                        break;
                    case "preroll":
                        contentsPrerolls.setPreroll(readPrerolls(jParser));
                        break;
                }
            }
            jParser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentsPrerolls;
    }

    private List<Preroll> readPrerolls(JsonParser jParser) throws IOException {
        if (jParser.currentToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("Expected content to be an array");
        }
        List<Preroll> prerolls = new ArrayList<>();
        while (jParser.nextToken() != JsonToken.END_ARRAY) {
            Preroll preroll = new Preroll();
            while (jParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jParser.getCurrentName();
                jParser.nextToken();
                switch (fieldname) {
                    case "name":
                        preroll.setName(jParser.getText());
                        break;
                    case "videos":
                        preroll.setVideos(readVideos(jParser));
                        break;
                }
            }
            prerolls.add(preroll);
        }
        return prerolls;
    }

    private List<Content> readContents(JsonParser jParser) throws IOException {
        jParser.nextToken();
        if (jParser.currentToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("Expected content to be an array");
        }
        List<Content> contents = new ArrayList<>();

        while (jParser.nextToken() != JsonToken.END_ARRAY) {
            Content content = new Content();
            while (jParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldname = jParser.getCurrentName();
                jParser.nextToken();
                switch (fieldname) {
                    case "name":
                        content.setName(jParser.getText());
                        break;
                    case "preroll":
                        content.setPreroll(readPrerolls(jParser));
                        break;
                    case "videos":
                        content.setVideos(readVideos(jParser));
                        break;
                }
            }
            contents.add(content);
        }
        return contents;
    }

    private List<Video> readVideos(JsonParser jParser) throws IOException {
        if (jParser.currentToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("Expected content to be an array");
        }
        List<Video> videos = new ArrayList<>();
        while (jParser.nextToken() != JsonToken.END_ARRAY) {
            Video video = mapper.readValue(jParser, Video.class);
            videos.add(video);
        }
        return videos;
    }
}