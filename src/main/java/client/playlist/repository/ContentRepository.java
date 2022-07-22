package client.playlist.repository;

import client.playlist.exceptions.NoValidContentFoundException;
import client.playlist.model.OutputPlaylist;
import client.playlist.model.PlayList;
import client.playlist.model.Video;

import java.util.*;
import java.util.stream.Collectors;

public class ContentRepository implements IContentRepository {

    //todo ConcurrentHashMap?
    private final Map<String, Set<PlayList>> inMemoryDb = new HashMap<>();

    @Override
    public Set<PlayList> getPlaylists(String contentId, String countryCode) throws NoValidContentFoundException {
        String key = contentId + "_" + countryCode;
        if (inMemoryDb.containsKey(key)) {
            return inMemoryDb.get(key);
        }
        throw new NoValidContentFoundException(String.format("No valid playlist found for " +
                "contentId: %s and countryCode: %s", contentId, countryCode));
    }

    public void addPlayList(String contentId, String country, PlayList playList) {
        String key = contentId + "_" + country;
        if (inMemoryDb.containsKey(key)) {
            Set<PlayList> playLists = inMemoryDb.get(key);
            playList.setName("Playlist" + (playLists.size()));
            playLists.add(playList);
        } else {
            inMemoryDb.put(key, new HashSet<>(Arrays.asList(playList)));
        }
    }

    @Override
    public Set<OutputPlaylist> getOutputPlaylists(String contentId, String countryCode) throws NoValidContentFoundException {
        return getPlaylists(contentId, countryCode)
                .stream()
                .map(playList -> {
                    Set<String> videoIds = playList.getVideos().stream().map(Video::getName).collect(Collectors.toSet());
                    return new OutputPlaylist(playList.getName(), videoIds);
                })
                .collect(Collectors.toSet());
    }
}
