package com.example.app.models.comparator;

import com.example.app.models.Song;
import org.junit.Assert;
import org.junit.Test;

public class ByTitleComparatorTest {

    private Song song1 = new Song.Builder().title("ABC").build();

    private Song song2 = new Song.Builder().title("DEF").build();

    private ByTitleComparator underTest = new ByTitleComparator();

    @Test
    public void testNonEmptyTitleSongs() {
        Assert.assertTrue(underTest.compare(song1, song2) < 0);
    }


}
