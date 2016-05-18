package com.example.app;

import com.example.app.filehelpers.FileReader;
import com.example.app.filehelpers.TextToSongHelper;
import com.example.app.models.*;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    private static final String PATH_ALL = System.getProperty("user.dir") + "\\input\\all";
    private static final String PATH_TEST = System.getProperty("user.dir") + "\\input\\test";
    private static final String PATH_EVERYONE = "C:\\Users\\Tm\\Desktop\\judi doctorat\\RFSDB xml";
    private static final String ARRANGED_RESULT_COMPARE_6_NOTE = System.getProperty("user.dir") + "\\input\\patternuri\\arranged_result_compare_6_Note_.csv";
    private static final String ARRANGED_RESULT_COMPARE_6_TIMP = System.getProperty("user.dir") + "\\input\\patternuri\\arranged_result_compare_6_Timp_.csv";
    private static final String ARRANGED_RESULT_COMPARE_7_NOTE = System.getProperty("user.dir") + "\\input\\patternuri\\arranged_result_compare_7_Note_.csv";
    private static final String ARRANGED_RESULT_COMPARE_7_TIMP = System.getProperty("user.dir") + "\\input\\patternuri\\arranged_result_compare_7_Timp_.csv";

    private static final String ARRANGED_RESULT_COMPARE_8_NOTE = System.getProperty("user.dir") + "\\input\\patternuri\\arranged_result_compare_8_Note_.csv";
    private static final String ARRANGED_RESULT_COMPARE_8_NOTE_TIMP = System.getProperty("user.dir") + "\\input\\patternuri\\arranged_result_compare_8_Note_Timp_.csv";

    public static void main(String[] args) throws IOException {
        new Main();
    }

    public Main() throws IOException {
        FileReader songFileReader = new FileReader(PATH_EVERYONE);
        List<Song> songs = TextToSongHelper.getSongs(songFileReader);
        System.out.println("Total songs processed: " + songs.size());

        //drawHistograms(songs);

        Map<String, Song> songTitleMap = new HashMap<>();
        Map<String, Song> songFileNameMap = new HashMap<>();
        for (Song s : songs) {
            songTitleMap.put(s.getTitle(), s);
            songFileNameMap.put(s.getFileName(), s);
        }

        File file = new File(ARRANGED_RESULT_COMPARE_8_NOTE_TIMP);
        try {
            BufferedReader bRead = new BufferedReader(new java.io.FileReader(file));
            bRead.readLine();
            String line = bRead.readLine(); //ignore column titles
//            while ((line = bRead.readLine()) != null) {
            List<Song> samePatternSongs = extractSongInfo(songTitleMap, songFileNameMap, line);
            List<String> locations = new ArrayList<>();
            int i = 1;
            for (Song s : samePatternSongs) {
                Location loc = s.getLocation();
                if (loc != null) {
                    locations.add("['" + loc.name + "'," + BigDecimal.valueOf(loc.lat).setScale(6, RoundingMode.HALF_UP) + "," + BigDecimal.valueOf(loc.lon).setScale(6, RoundingMode.HALF_UP) + "," + (i++) + "]");
                }
            }
            System.out.println(locations);
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Song> extractSongInfo(Map<String, Song> songTitleMap, Map<String, Song> songFileNameMap, String line) {
        String[] parts = line.split(",");
        Integer count = Integer.parseInt(parts[0]);
        String pattern = parts[1];

        List<Song> selectedSongs = new ArrayList<>();
        for (int i = 2; i < count + 2; i++) {
            String songFileName = trimFileName(parts[i]);
            Song song = songFileNameMap.get(songFileName);
            if (song == null) {
                songFileName = songFileName.replace(".xml", "");
                song = songTitleMap.get(songFileName);
                if (song != null) { // if found by song name
                    selectedSongs.add(song);
                }
            } else {  // if found by file name
                selectedSongs.add(song);
            }
        }

        computeStdDev(count, pattern, selectedSongs);
        return selectedSongs;
    }

    private Double computeStdDev(Integer count, String pattern, List<Song> selectedSongs) {
        int sum = 0;
        int totalNoNullYearSongs = 0;
        for (Song s : selectedSongs) {
            if (s.getYear() != 0) {
                totalNoNullYearSongs++;
                sum += s.getYear();
            }
        }
        Double avg = 0.0;
        if (totalNoNullYearSongs != 0) {
            avg = Double.valueOf(sum / totalNoNullYearSongs);
        }

        Double varianceNumerator = 0.0;
        for (Song s : selectedSongs) {
            varianceNumerator += Math.pow(s.getYear() - avg, 2);
        }

        Double stdDev = totalNoNullYearSongs != 0 ? Math.sqrt(varianceNumerator / totalNoNullYearSongs) : 9999;

        System.out.println(
                "Count: " + count +
                        " Nr: " + totalNoNullYearSongs +
                        " Pattern: " + pattern +
                        " StdDev: " + stdDev);

        return stdDev;
    }

    private String trimFileName(String songFileName) {
        if (songFileName.startsWith("[")) {
            songFileName = songFileName.substring(1);
        }
        if (songFileName.endsWith("]")) {
            songFileName = songFileName.substring(0, songFileName.length() - 1);
        }
        if (songFileName.startsWith(" ")) {
            songFileName = songFileName.substring(1);
        }
        songFileName = songFileName.replace(".txt", ".xml");
        return songFileName;
    }

    private void drawHistograms(List<Song> songs) {
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
