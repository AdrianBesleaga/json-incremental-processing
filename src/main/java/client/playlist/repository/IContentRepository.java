package client.playlist.repository;

import client.playlist.exceptions.NoValidContentFoundException;
import client.playlist.model.OutputPlaylist;
import client.playlist.model.PlayList;

import java.util.LinkedHashSet;
import java.util.List;

public interface IContentRepository {
    LinkedHashSet<PlayList> getPlaylists(String contentId, String countryCode) throws NoValidContentFoundException;

    List<OutputPlaylist> getOutputPlaylists(String contentId, String countryCode) throws NoValidContentFoundException;

    void addPlayList(String contentId, String country, PlayList playList);
}
