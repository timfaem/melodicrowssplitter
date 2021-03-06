package com.example.app.histograms;

import com.example.app.models.Song;
import com.example.app.models.filters.MusicalFeatureExtractor;
import com.example.app.models.filters.YearExtractor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class YearHistogram {
    private final List<Song> samePatternSongs;
    private final String title;
    private Map<Integer, AtomicInteger> data;

    public YearHistogram(List<Song> samePatternSongs, String title) {
        this.samePatternSongs = samePatternSongs;
        this.title = title;
        this.data = createYearHistogram(samePatternSongs, new YearExtractor());
    }

    public void print() {
        drawHistogram(createSongYearDataset(data), title);
    }

    public void toCSVFile() {
        File csvFile = new File(title + "histo.csv");
        try {
            FileWriter writer = new FileWriter(csvFile);
            writer.write("an, numar,\n");
            for (Map.Entry<Integer, AtomicInteger> entry : data.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue() + ",\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private CategoryDataset createSongYearDataset(Map<Integer, AtomicInteger> histogramData) {
        Map<Integer, AtomicInteger> histogram = new TreeMap<>(histogramData);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry entry : histogram.entrySet()) {
            dataset.addValue(
                    ((AtomicInteger) entry.getValue()).doubleValue(),
                    ((Integer) entry.getKey()),
                    ((Integer) entry.getKey()));
        }
        return dataset;
    }

    private Map<Integer, AtomicInteger> createYearHistogram(List<Song> songs, MusicalFeatureExtractor<Integer> ext) {
        Map<Integer, AtomicInteger> histogram = new HashMap<>();
        songs.forEach(s -> {
            int year = ext.extract(s);

            if (histogram.get(year) == null) {
                histogram.put(year, new AtomicInteger(1));
            } else {
                histogram.get(year).incrementAndGet();
            }
        });

        return useYearBuckets(histogram);
    }

    private Map<Integer, AtomicInteger> useYearBuckets(Map<Integer, AtomicInteger> histogram) {
        Map<Integer, AtomicInteger> bucketted = new HashMap<>();
        int startYear = 1890;
        int endYear = 1990;
        int incrementStep = 5;
        for (int i = startYear; i <= endYear; i += incrementStep) {
            bucketted.put(i, new AtomicInteger(0));
        }
        int bucket = 0;
        for (Map.Entry<Integer, AtomicInteger> entry : histogram.entrySet()) {
            bucket = startYear + ((entry.getKey() - startYear) / incrementStep) * incrementStep;
            AtomicInteger atomicInteger = bucketted.get(bucket);
            if (atomicInteger != null) {
                atomicInteger.addAndGet(entry.getValue().intValue());
            } else {
                System.out.println("NPE at bucket " + bucket);
            }
        }
        System.out.println("Bucketed: " + bucketted);

        this.data = bucketted;
        return bucketted;
    }

    private void drawHistogram(CategoryDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createBarChart("", "", "", dataset, PlotOrientation.VERTICAL, false, true, false);

//        JFreeChart chart = ChartFactory.createLineChart("", "Years", "Number of Songs", dataset, PlotOrientation.VERTICAL, false, true, false);

//        chart.setAntiAlias(true);

        ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new Dimension(200, 150));
        ApplicationFrame frame = new ApplicationFrame(title);
        frame.setContentPane(chartPanel);
        frame.setSize(new Dimension(400, 400));
        frame.setVisible(true);

        saveChart(dataset, title);
    }

    private void saveChart(CategoryDataset dataset, String title) {
        JFreeChart lineChartObject = ChartFactory.createBarChart(
                title, "Year",
                "Songs Count",
                dataset, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 640; /* Width of the image */
        int height = 480; /* Height of the image */
        File lineChart = new File("LineChart.jpeg");
        try {
            ChartUtilities.saveChartAsJPEG(lineChart, lineChartObject, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
