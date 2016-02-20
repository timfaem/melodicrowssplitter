package com.example.app.filehelpers;


import org.junit.Test;

import java.io.IOException;

public class SongFileReaderTest {

    @Test
    public void testFileReader() throws IOException {
        String path = System.getProperty("user.dir") + "\\input\\test";
        FileReader sfReader = new FileReader(path);
        while (sfReader.hasNext())
        {
            sfReader.advance();
            System.out.println(sfReader.getFileText());
        }

    }

}
