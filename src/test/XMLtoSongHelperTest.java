package test;

import main.files.SongFileReader;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

public class XMLtoSongHelperTest {

    @Test
    public void test() throws IOException, SAXException {
        String path = System.getProperty("user.dir") + "\\input\\test";
        SongFileReader sfReader = new SongFileReader(path);
        sfReader.readSongs(path);
    }
}
