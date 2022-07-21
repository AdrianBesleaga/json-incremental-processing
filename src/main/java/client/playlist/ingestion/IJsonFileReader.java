package client.playlist.ingestion;

import client.playlist.model.ContentsPrerolls;

import java.io.File;

public interface IJsonFileReader {

    ContentsPrerolls readJsonFile(File file);
}
