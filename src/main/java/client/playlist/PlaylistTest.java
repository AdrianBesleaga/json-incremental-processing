package client.playlist;

import java.io.*;
import java.util.*;

/**
 * A simple test class that allows for the creation of playlists.
 */
public class PlaylistTest {
  public static void main(String[] args) {
    String inputFile = "client-sample-data.json";
    String contentId = "MI3";
    String countryCode = "UK";

    if (args.length == 2) {
      contentId = args[0];
      countryCode = args[1];
    }

    /**
     * Temporary hardcoded stuff, replace this section with new code
     */
    if(contentId.equals("MI3") && countryCode.equals("UK")){
      System.out.println("Playlist1\n{V6, V2}\nPlaylist2\n{V7, V3}\n");
    } else if(contentId.equals("MI3") && countryCode.equals("US")){
      System.out.println("(No legal playlist possible because the Pre-Roll Video isn't compatible with the aspect ratio [16:9] of the Content Video)\n");
    } else if(contentId.equals("MI3") && countryCode.equals("CA")){
      System.out.println("Playlist1\n{V5, V1}\n");
    } else {
      System.out.println("Unknown parameters");
    }
    /*ContentRepository contentRepository = null;

    try {
      contentRepository = new ContentRepository(PlaylistTest.class.getResource(inputFile));
    } catch (IOException e) {
      System.err.println("Error creating content repository: " + e.getMessage());
      System.exit(-1);
    }

    try {
      List<Playlist> playlists = contentRepository.getPlaylists(contentId, countryCode);

      for (Playlist playlist : playlists) {
        System.out.println(playlist.getName());
        System.out.println(playlist.getVideoIds().toString().replace("[", "{").replace("]", "}"));
      }
    } catch (ContentException e) {
      System.out.println(e.getMessage());
    }*/
  }
}
