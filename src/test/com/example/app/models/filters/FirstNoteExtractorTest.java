package com.example.app.models.filters;

import com.example.app.models.Measure;
import com.example.app.models.Note;
import com.example.app.models.Song;
import org.junit.Assert;
import org.junit.Test;

public class FirstNoteExtractorTest {

    private static final int NOTE_DURATION = 100;

    private Song song = new Song.Builder()
            .addMeasure(new Measure.Builder()
                    .addNote(new Note.Builder().duration(NOTE_DURATION).build())
                    .addNote(new Note.Builder().duration(NOTE_DURATION + 2).build()).build()).build();

    private FirstNoteExtractor underTest = new FirstNoteExtractor();

    @Test
    public void testExtractsCorrectly() {
        Assert.assertEquals(NOTE_DURATION, underTest.extract(song).getDuration());
    }
}
