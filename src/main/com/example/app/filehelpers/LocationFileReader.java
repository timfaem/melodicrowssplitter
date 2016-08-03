package com.example.app.filehelpers;

import com.example.app.models.Location;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class LocationFileReader implements LocationFinder {

    private static final Double UNKNOWN = 0.0;
    private static final String PATH = "C:\\Projects\\repos\\melodicrowssplitter\\input\\Coordonate localitati corectat.csv";
    private Map<String, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException {
        new LocationFileReader();
    }

    public LocationFileReader() throws IOException {
//        Charset charSet = Charset.forName("Windows-1252");
        Charset charSet = Charset.forName("UTF-8");
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH), charSet));
        String line = reader.readLine(); // TODO ignore the column names
        while ((line = reader.readLine()) != null) {
            String parts[] = line.split("\\s*,\\s*");
            String satJudName = parts[0];
            Double latDouble = 0.0, longDouble = 0.0;
            if (parts.length >= 3) {
                latDouble = degreesToDouble(parts[1]);
                longDouble = degreesToDouble(parts[2]);
            }
            String satName = extractSatName(satJudName);
            locations.put(satName, new Location(latDouble, longDouble, satJudName));
        }
        System.out.println(locations);
    }

    private String extractSatName(String satJudName) {
        int separatorIndex = satJudName.lastIndexOf("-");
        return separatorIndex != -1 ?
                satJudName.substring(0, separatorIndex) : satJudName;
    }

    public static Double degreesToDouble(String location) {
        if (location == null || location.isEmpty()) {
            return UNKNOWN;
        }
        location = location.trim();
        Double loc = 0.0;

        int indexOfDegree = location.indexOf("°");
        int indexOfMinutes = location.indexOf("'");
        int indexOfSeconds = location.indexOf("\"");

        try {
            if (indexOfDegree != -1) {
                loc = Double.valueOf(location.substring(0, indexOfDegree).trim());
            }

            if (indexOfMinutes != -1) {
                String min = location.substring(indexOfDegree + 1, indexOfMinutes).trim();
                if (min.startsWith(" ")) {
                    min = min.substring(1);
                }
                loc += Double.valueOf(min) / 60;
            }

            if (indexOfSeconds != -1) {
                String sec = location.substring(indexOfMinutes + 1, indexOfSeconds).trim();
                if (sec.startsWith(" ")) {
                    sec = sec.substring(1);
                }
                loc += Double.valueOf(sec) / 3600;
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        return loc;
    }

    @Override
    public Location getLocationForName(String name) {
        for (Map.Entry<String, Location> entry : locations.entrySet()) {
            if (entry.getKey().contains(name)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
