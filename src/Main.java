import main.files.SongFileReader;
import org.xml.sax.SAXException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, SAXException {
        String path = System.getProperty("user.dir") + "\\input";
        SongFileReader sfReader = new SongFileReader(path);

        for (String song : sfReader.getFilePaths(path)) {
            System.out.println(sfReader.getCreatorFromDocument("creator", sfReader.getDocumentForFileAtPath(song)));
        }
    }


}
