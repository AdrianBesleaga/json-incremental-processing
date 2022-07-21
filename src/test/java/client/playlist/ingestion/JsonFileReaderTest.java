package client.playlist.ingestion;

import client.playlist.model.ContentsPrerolls;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class JsonFileReaderTest {
    ObjectMapper objectMapper = new ObjectMapper();
    JsonFileReader jsonFileReader = new JsonFileReader();
    ClassLoader classLoader = JsonFileReaderTest.class.getClassLoader();
    File file = new File(classLoader.getResource("client/client-sample-data.json").getFile());

    @Test
    public void readJsonFile() throws IOException {
        ContentsPrerolls expectedObject = objectMapper.readValue(file, ContentsPrerolls.class);
        ContentsPrerolls resultObject = jsonFileReader.readJsonFile(file);
        Assert.assertEquals(
                objectMapper.writeValueAsString(expectedObject),
                objectMapper.writeValueAsString(resultObject)
        );
    }
}