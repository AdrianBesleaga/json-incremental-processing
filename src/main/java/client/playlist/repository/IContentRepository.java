package client.playlist.repository;

import client.playlist.exceptions.NoValidContentFoundException;
import client.playlist.model.OutputPlaylist;
import client.playlist.model.PlayList;

import java.util.Set;

public interface IContentRepository {
    Set<PlayList> getPlaylists(String contentId, String countryCode) throws NoValidContentFoundException;

    Set<OutputPlaylist> getOutputPlaylists(String contentId, String countryCode) throws NoValidContentFoundException;

    void addPlayList(String contentId, String country, PlayList playList);
}
