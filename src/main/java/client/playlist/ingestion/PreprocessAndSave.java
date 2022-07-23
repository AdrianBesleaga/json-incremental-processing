package client.playlist.ingestion;

import client.playlist.model.*;
import client.playlist.repository.ContentRepository;
import client.playlist.repository.IContentRepository;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Process input from json file and improve storage access by key <contentId>_<countryCode>
 */
public class PreprocessAndSave {
    private final IJsonFileReader jsonFileReader = new JsonFileReader();
    private final ClassLoader classLoader = PreprocessAndSave.class.getClassLoader();
    private final String inputFile;

    public PreprocessAndSave(String inputFile) {
        this.inputFile = inputFile;
    }

    public IContentRepository extractAndSaveData() {
        File file = new File(classLoader.getResource(inputFile).getFile());
        ContentsPrerolls contentsPrerolls = jsonFileReader.readJsonFile(file);
        IContentRepository contentRepository = new ContentRepository();
        Map<String, List<PlayList>> playlistMap = getPlaylists(contentsPrerolls);
        addToDatabase(contentRepository, playlistMap);
        return contentRepository;
    }

    private void addToDatabase(IContentRepository contentRepository, Map<String, List<PlayList>> playlistMap) {
        playlistMap.forEach((contentId, playLists) -> {
            playLists.forEach(playList -> {
                playList.getVideos().forEach(video -> {
                    video.getAttributes().getCountries().forEach(country -> {
                        if (allMatchCountry(playList, country))
                            contentRepository.addPlayList(contentId, country, playList);
                    });
                });
            });
        });
    }

    private boolean allMatchCountry(PlayList playList, String country) {
        return playList.getVideos().stream().allMatch(vid -> vid.getAttributes().getCountries().contains(country));
    }

    private Map<String, List<PlayList>> getPlaylists(ContentsPrerolls contentsPrerolls) {
        Map<String, List<PlayList>> playListsMap = new HashMap<>();
        contentsPrerolls.getContent().forEach(content -> {
            List<PlayList> contentPlaylist = new ArrayList<>();
            content.getVideos().forEach(video -> {
                Video.Attribute videoAttributes = video.getAttributes();
                List<String> videoCountries = videoAttributes.getCountries();
                String videoAspect = videoAttributes.getAspect();
                String videoLanguage = videoAttributes.getLanguage();
                PlayList playList = createPlaylist(contentsPrerolls, content, videoCountries,
                        videoAspect, videoLanguage);
                playList.getVideos().add(video);
                contentPlaylist.add(playList);
            });
            playListsMap.put(content.getName(), contentPlaylist);
        });
        return playListsMap;
    }

    private PlayList createPlaylist(ContentsPrerolls contentsPrerolls, Content content,
                                    List<String> videoCountries, String videoAspect, String videoLanguage) {
        PlayList playList = new PlayList();
        content.getPreroll().forEach(preroll -> {
            String prerollName = preroll.getName();
            Optional<Preroll> optionalPreroll = contentsPrerolls.getPreroll()
                    .stream()
                    .filter(prerollFilter -> prerollFilter.getName().equals(prerollName))
                    .findFirst();
            optionalPreroll.ifPresent(prerollValue -> {
                Set<Video> matchingPrerolls = findMatchingPrerollVideos(videoCountries, videoAspect,
                        videoLanguage, prerollValue);
                matchingPrerolls.forEach(matchingPrerollVideo -> {
                    playList.getVideos().add(matchingPrerollVideo);
                });
            });
        });
        return playList;
    }

    private Set<Video> findMatchingPrerollVideos(List<String> videoCountries, String videoAspect,
                                                 String videoLanguage, Preroll prerollValue) {
        return prerollValue.getVideos()
                .stream()
                .filter(prerollVideo -> {
                    Video.Attribute attributes = prerollVideo.getAttributes();
                    List<String> countries = attributes.getCountries();
                    String aspect = attributes.getAspect();
                    String language = attributes.getLanguage();
                    return aspectIsEqual(aspect, videoAspect)
                            && videoLanguage.equals(language)
                            && countries.stream().anyMatch(videoCountries::contains);
                })
                .collect(Collectors.toSet());
    }

    private boolean aspectIsEqual(String aspect, String videoAspect) {
        if (aspect == null || videoAspect == null) {
            return true;
        }
        return videoAspect.equals(aspect);
    }
}
