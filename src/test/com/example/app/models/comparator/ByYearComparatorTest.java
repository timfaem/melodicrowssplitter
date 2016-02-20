package com.example.app.models.comparator;

import com.example.app.models.Song;
import org.junit.Assert;
import org.junit.Test;

public class ByYearComparatorTest {

    private Song song1 = new Song.Builder().year(1992).build();

    private Song song2 = new Song.Builder().year(2000).build();

    private ByYearComparator underTest = new ByYearComparator();

    @Test
    public void testNonEmptyYearSongs() {
        Assert.assertTrue(underTest.compare(song1, song2) < 0);
    }
}
