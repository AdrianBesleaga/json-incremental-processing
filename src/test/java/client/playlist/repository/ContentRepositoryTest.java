package client.playlist.repository;

import client.playlist.exceptions.NoValidContentFoundException;
import client.playlist.ingestion.PreprocessAndSave;
import client.playlist.model.OutputPlaylist;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContentRepositoryTest {
    String inputFile = "client/client-sample-data.json";

    @Test
    public void getOutputPlaylists() throws NoValidContentFoundException {
        String contentId = "MI3";
        String countryCode = "UK";
        IContentRepository contentRepository = new PreprocessAndSave(inputFile).extractAndSaveData();
        Set<OutputPlaylist> playlists = contentRepository.getOutputPlaylists(contentId, countryCode);
        Set<OutputPlaylist> expectedOutput = new HashSet<>();
        expectedOutput.add(new OutputPlaylist("Playlist1", new HashSet<>(Arrays.asList("V6", "V2"))));
        expectedOutput.add(new OutputPlaylist("Playlist2", new HashSet<>(Arrays.asList("V7", "V3"))));
        Assert.assertEquals(playlists, expectedOutput);
    }
}