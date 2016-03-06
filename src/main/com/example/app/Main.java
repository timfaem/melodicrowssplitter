package com.example.app;

import com.example.app.filehelpers.FileReader;
import com.example.app.filehelpers.TextToSongHelper;
import com.example.app.models.Note;
import com.example.app.models.Pitch;
import com.example.app.models.Song;
import com.example.app.models.Step;
import com.example.app.models.filters.FirstNoteExtractor;
import com.example.app.models.filters.LastNoteExtractor;
import com.example.app.models.filters.LastNoteFirstMelodicRowExtractor;
import com.example.app.models.filters.MusicalFeatureExtractor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    private static final String PATH_ALL = System.getProperty("user.dir") + "\\input\\all";
    private static final String PATH_TEST = System.getProperty("user.dir") + "\\input\\test";
    private static final String PATH_EVERYONE = "C:\\Users\\Tm\\Desktop\\judi doctorat\\RFSDB xml";

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        List<Song> songs = TextToSongHelper.getSongs(new FileReader(PATH_EVERYONE));
        System.out.println("Total songs processed: " + songs.size());

//        SongHelper.filterSongsByGenre(songs, Genre.BOCET);

        drawHistogram(createFirstNoteDataset(createPitchHistogram(songs, new FirstNoteExtractor())), "First Note");
        drawHistogram(createFirstNoteDataset(createPitchHistogram(songs, new LastNoteExtractor())), "Last song note");

//        drawHistogram(createFirstNoteDataset(createHistogram(songs, new FirstNoteExtractor())), "First Note");
        drawHistogram(createFirstNoteDataset(createPitchHistogram(songs, new LastNoteFirstMelodicRowExtractor())), "Last note of First Melodic row");


    }

    private void drawHistogram(CategoryDataset dataset, String title) {
        JFreeChart barChart = ChartFactory.createBarChart("", "", "", dataset, PlotOrientation.VERTICAL, true, true, false);
        barChart.setAntiAlias(true);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(200, 150));
        ApplicationFrame frame = new ApplicationFrame(title);
        frame.setContentPane(chartPanel);
        frame.setSize(new Dimension(400, 400));
        frame.setVisible(true);
    }

    private void drawHistogram(CategoryDataset dataset) {
        drawHistogram(dataset, "");
    }

    private CategoryDataset createFirstNoteDataset(Map<Pitch, AtomicInteger> histogramData) {
        Map<Pitch, AtomicInteger> histogram = new TreeMap<>(histogramData);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        histogram.entrySet().removeIf(pitchAtomicIntegerEntry -> {
//            Pitch pi = pitchAtomicIntegerEntry.getKey();
//            return isIgnored(pi);
//        });
        for (Map.Entry entry : histogram.entrySet()) {
            if (!isIgnored((Pitch) entry.getKey())) {
                dataset.addValue(((AtomicInteger) entry.getValue()).doubleValue(), ((Pitch) entry.getKey()).getStep().toString() + ((Pitch) entry.getKey()).getOctave(), ((Pitch) entry.getKey()).getStep().toString() + ((Pitch) entry.getKey()).getOctave());
            }
            System.out.println(((Pitch) entry.getKey()).getOctave() + " " + ((Pitch) entry.getKey()).getStep() + " = " + entry.getValue());
        }
        return dataset;
    }

    private boolean isIgnored(Pitch pi) {
        return (pi.getOctave() < 3) ||
                (pi.getOctave() == 3 && (pi.getStep().compareTo(Step.D) < 0)) ||
                (pi.getOctave() == 4 && (pi.getStep().compareTo(Step.E) > 0)) ||
                (pi.getOctave() > 4);
    }

    private Map<Pitch, AtomicInteger> createPitchHistogram(List<Song> songs, MusicalFeatureExtractor<Note> ext) {
        Map<Pitch, AtomicInteger> histogram = new HashMap<>();
        songs.forEach(s -> {
            Note n = ext.extract(s);

            if (histogram.get(n.getPitch()) == null) {
                histogram.put(n.getPitch(), new AtomicInteger(1
                ));
            } else {
                histogram.get(n.getPitch()).incrementAndGet();
            }
        });
        return histogram;
    }

//    private Map<Step, AtomicInteger> createHistogram(List<Song> songs, MusicalFeatureExtractor<Note> extractor) {
//        Map<Step, AtomicInteger> histogram = new HashMap<>();
//        for (Step s : Step.values()) {
//            histogram.put(s, new AtomicInteger(0));
//        }
//
//        songs.forEach(s -> {
//            Note n = extractor.extract(s);
//            histogram.get(n.getPitch().getStep()).incrementAndGet();
//        });
//        return histogram;
//    }
}
