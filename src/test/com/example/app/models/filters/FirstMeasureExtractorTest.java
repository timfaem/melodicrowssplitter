package com.example.app.models.filters;

import com.example.app.models.Measure;
import com.example.app.models.Note;
import com.example.app.models.Song;
import org.junit.Assert;
import org.junit.Test;

public class FirstMeasureExtractorTest {

    private static final int NOTE_DURATION = 100;

    private final Measure first = new Measure.Builder()
            .addNote(new Note.Builder().duration(NOTE_DURATION).build())
            .addNote(new Note.Builder().duration(NOTE_DURATION + 2).build()).build();

    private final Measure second = new Measure.Builder()
            .addNote(new Note.Builder().duration(NOTE_DURATION + 4).build())
            .addNote(new Note.Builder().duration(NOTE_DURATION + 6).build()).build();

    private Song song = new Song.Builder()
            .addMeasure(first)
            .addMeasure(second)
            .build();

    private FirstMeasureExtractor underTest = new FirstMeasureExtractor();

    @Test
    public void testExtractsCorrectly() {
        Assert.assertEquals(first, underTest.extract(song));
    }
}
