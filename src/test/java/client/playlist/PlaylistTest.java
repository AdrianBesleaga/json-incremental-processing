package client.playlist;

import client.playlist.exceptions.NoValidContentFoundException;
import client.playlist.ingestion.PreprocessAndSave;
import client.playlist.model.OutputPlaylist;
import client.playlist.repository.IContentRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PlaylistTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    String inputFile = "client/client-sample-data.json";

    @Test
    public void should_throw_exception_if_not_found() throws NoValidContentFoundException {
        String contentId = "abc";
        String countryCode = "xyz";
        thrown.expect(NoValidContentFoundException.class);
        thrown.expectMessage("No valid playlist found for contentId: " + contentId + " and countryCode: " + countryCode);
        IContentRepository contentRepository = new PreprocessAndSave(inputFile).extractAndSaveData();
        contentRepository.getOutputPlaylists(contentId, countryCode);
    }

    @Test
    public void should_match_output_for_MI3_UK() throws NoValidContentFoundException {
        String contentId = "MI3";
        String countryCode = "UK";
        IContentRepository contentRepository = new PreprocessAndSave(inputFile).extractAndSaveData();
        Set<OutputPlaylist> playlists = contentRepository.getOutputPlaylists(contentId, countryCode);
        Set<OutputPlaylist> expectedOutput = new HashSet<>();
        expectedOutput.add(new OutputPlaylist("Playlist1", new HashSet<>(Arrays.asList("V6", "V2"))));
        expectedOutput.add(new OutputPlaylist("Playlist2", new HashSet<>(Arrays.asList("V7", "V3"))));
        Assert.assertEquals(expectedOutput, playlists);
    }

    @Test
    public void should_match_output_for_MI3_US() throws NoValidContentFoundException {
        String contentId = "MI3";
        String countryCode = "US";
        thrown.expect(NoValidContentFoundException.class);
        thrown.expectMessage("No valid playlist found for contentId: " + contentId + " and countryCode: " + countryCode);
        IContentRepository contentRepository = new PreprocessAndSave(inputFile).extractAndSaveData();
        contentRepository.getOutputPlaylists(contentId, countryCode);
    }

    @Test
    public void should_match_output_for_MI3_CA() throws NoValidContentFoundException {
        String contentId = "MI3";
        String countryCode = "CA";
        IContentRepository contentRepository = new PreprocessAndSave(inputFile).extractAndSaveData();
        Set<OutputPlaylist> playlists = contentRepository.getOutputPlaylists(contentId, countryCode);
        Set<OutputPlaylist> expectedOutput = new HashSet<>();
        expectedOutput.add(new OutputPlaylist("Playlist1", new HashSet<>(Arrays.asList("V5", "V1"))));
        Assert.assertEquals(expectedOutput, playlists);
    }
}