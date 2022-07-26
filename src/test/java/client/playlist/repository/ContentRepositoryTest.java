package client.playlist.repository;

import client.playlist.exceptions.NoValidContentFoundException;
import client.playlist.ingestion.PreprocessAndSave;
import client.playlist.model.OutputPlaylist;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContentRepositoryTest {
    String inputFile = "client/client-sample-data.json";

    @Test
    public void getOutputPlaylists() throws NoValidContentFoundException {
        String contentId = "MI3";
        String countryCode = "UK";
        IContentRepository contentRepository = new PreprocessAndSave(inputFile).extractAndSaveData();
        List<OutputPlaylist> playlists = contentRepository.getOutputPlaylists(contentId, countryCode);
        List<OutputPlaylist> expectedOutput = new ArrayList<>();
        expectedOutput.add(new OutputPlaylist("Playlist1", new ArrayList<>(Arrays.asList("V6", "V2"))));
        expectedOutput.add(new OutputPlaylist("Playlist2", new ArrayList<>(Arrays.asList("V7", "V3"))));
        Assert.assertEquals(playlists, expectedOutput);
    }
}