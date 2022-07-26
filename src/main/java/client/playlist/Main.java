package client.playlist;

import client.playlist.ingestion.PreprocessAndSave;
import client.playlist.model.OutputPlaylist;
import client.playlist.repository.IContentRepository;

import java.util.List;
import java.util.Set;

/**
 * A simple test class that allows for the creation of playlists.
 */
public class Main {
    public static void main(String[] args) {
        String inputFile = "client/client-sample-data.json";
        String contentId = "MI3";
        String countryCode = "US";

        if (args.length == 2) {
            contentId = args[0];
            countryCode = args[1];
        }

        try {
            IContentRepository contentRepository = new PreprocessAndSave(inputFile).extractAndSaveData();
            List<OutputPlaylist> playlists = contentRepository.getOutputPlaylists(contentId, countryCode);

            for (OutputPlaylist playlist : playlists) {
                System.out.println(playlist.getName());
                System.out.println(playlist.getVideos().toString().replace("[", "{").replace("]", "}"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
