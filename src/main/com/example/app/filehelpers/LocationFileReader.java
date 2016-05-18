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

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH), Charset.forName("Windows-1252")));
        String line = reader.readLine(); // TODO ignore the column names
        while ((line = reader.readLine()) != null) {
            String parts[] = line.split("\\s*,\\s*");
            String locName = parts[0];
            if (locName.startsWith("\""))
            {
                locName.substring(1);
            }
            Double latDouble = 0.0, longDouble = 0.0;
            if (parts.length == 4) {
                latDouble = degreesToDouble(parts[2]);
                longDouble = degreesToDouble(parts[3]);
            } else if (parts.length == 3) {
                latDouble = degreesToDouble(parts[1]);
                longDouble = degreesToDouble(parts[2]);
            } else {

            }


            locations.put(locName, new Location(latDouble, longDouble, locName));
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
