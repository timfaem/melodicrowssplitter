package com.example.app;

import com.example.app.filehelpers.FileReader;
import com.example.app.filehelpers.TextToSongHelper;
import com.example.app.models.Genre;
import com.example.app.models.Note;
import com.example.app.models.Song;
import com.example.app.models.Step;
import com.example.app.models.filters.FirstNoteExtractor;
import com.example.app.models.helpers.SongHelper;
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

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        List<Song> songs = TextToSongHelper.getSongs(new FileReader(PATH_ALL));

//        SongHelper.filterSongsByGenre(songs, Genre.BOCET);

        drawFirstNoteHistogram(songs);

    }

    private void drawFirstNoteHistogram(List<Song> songs)
    {
        drawHistogram(createDataset(songs));
    }

    private void drawHistogram(CategoryDataset dataset, String title)
    {
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

    private CategoryDataset createDataset(List<Song> songs) {
        Map<Step, AtomicInteger> histogram = new TreeMap<>(createHistogram(songs));
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry entry : histogram.entrySet()) {
            dataset.addValue(((AtomicInteger) entry.getValue()).doubleValue(), entry.getKey().toString(), entry.getKey().toString());
        }
        return dataset;
    }

    private Map<Step, AtomicInteger> createHistogram(List<Song> songs) {
        Map<Step, AtomicInteger> histogram = new HashMap<>();
        for (Step s : Step.values()) {
            histogram.put(s, new AtomicInteger(0));
        }

        songs.forEach(s -> {
            Note n = new FirstNoteExtractor().extract(s);
            histogram.get(n.getPitch().getStep()).incrementAndGet();
        });
        return histogram;
    }
}
