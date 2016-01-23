package com.example.main.filehelpers;

import org.junit.Test;

import java.io.IOException;

public class SongFileReaderTest {

    @Test
    public void testFileReader() throws IOException {
        String path = System.getProperty("user.dir") + "\\input\\test";
        SongFileReader sfReader = new SongFileReader(path);
        while (sfReader.hasNext())
        {
            sfReader.advance();
            System.out.println(sfReader.getFileText());
        }

    }

}
