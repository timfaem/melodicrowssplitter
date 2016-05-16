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
    private static final String PATH = "C:\\Projects\\repos\\melodicrowssplitter\\input\\Coordonate localitati.csv";
    private Map<String, Location> locations = new HashMap<>();

    public LocationFileReader() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH), Charset.forName("utf-32")));
        String line;
        while ((line = reader.readLine()) != null) {
            String parts[] = line.split("\\s*,\\s*");
            String judetSat = parts[0];
            if (parts.length == 1) {
                continue;
            }
            Double latDouble = degreesToDouble(parts[1]);
            Double longDouble = degreesToDouble(parts[2]);

            locations.put(judetSat, new Location(latDouble, longDouble, judetSat));
        }
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

        if (indexOfDegree != -1) {
            loc = Double.valueOf(location.substring(0, indexOfDegree));
        }

        if (indexOfMinutes != -1) {
            loc += Double.valueOf(location.substring(indexOfDegree + 1, indexOfMinutes)) / 60;
        }

        if (indexOfSeconds != -1) {
            loc += Double.valueOf(location.substring(indexOfMinutes + 1, indexOfSeconds)) / 3600;
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
