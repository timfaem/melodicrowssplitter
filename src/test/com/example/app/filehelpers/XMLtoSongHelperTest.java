package com.example.app.filehelpers;

import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

public class XMLtoSongHelperTest {

    @Test
    public void test() throws IOException, SAXException {
        String path = System.getProperty("user.dir") + "\\input\\test";
        FileReader sfReader = new FileReader(path);
        while (sfReader.hasNext())
        {
            sfReader.advance();
            String fileText = sfReader.getFileText();
            System.out.println(fileText);
        }
    }
}
