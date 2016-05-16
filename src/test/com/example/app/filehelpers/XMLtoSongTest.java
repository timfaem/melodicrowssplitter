package com.example.app.filehelpers;

import org.junit.Assert;
import org.junit.Test;

public class XMLtoSongTest {
    @Test
    public void testDegreesToDouble() {
        String location = "44°49'3.38\"N";
        Double latLong = LocationFileReader.degreesToDouble(location);
        Assert.assertTrue(Math.abs(latLong - 44.8176055) < 0.0001);
    }
}
