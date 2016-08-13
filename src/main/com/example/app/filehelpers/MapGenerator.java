package com.example.app.filehelpers;

import com.example.app.models.Location;
import com.example.app.models.Song;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class MapGenerator {

    private static String pre = "<!DOCTYPE html>\n<html>\n<head>\n<script src=\"http://maps.googleapis.com/maps/api/js\"></script>\n<script>\n\tvar locations = ";
    private static String post = ";\n" +
            "\n" +
            "\tfunction initialize() \n" +
            "\t{\n" +
            "\n" +
            "\t\tvar mapProp = {\n" +
            "\t\t\t//45.948420, 25.105847\n" +
            "\t\t\tcenter:new google.maps.LatLng(45.948420,25.105847),\n" +
            "\t\t\tzoom:7,\n" +
            "\t\t\tmapTypeId:google.maps.MapTypeId.ROADMAP\n" +
            "\t\t};\n" +
            "\t\tvar map = new google.maps.Map(document.getElementById(\"googleMap\"), mapProp);\n" +
            "\n" +
            "\t\n" +
            "\t\tvar markers =[];\n" +
            "\t\tfor (i = 0; i < locations.length; i++) \n" +
            "\t\t{  \n" +
            "\t\t\tmarkers[i] = new google.maps.Marker({\n" +
            "\t\t\t     position: new google.maps.LatLng(locations[i][1], locations[i][2]),\n" +
            "\t\t\t     animation: google.maps.Animation.DROP,\n" +
            "\t\t\t     title:locations[i][0],\n" +
            "\t\t\t     map: map});\n" +
            "\t\t}\n" +
            "\n" +
            "\t}\n" +
            "\n" +
            "\tgoogle.maps.event.addDomListener(window, 'load', initialize);\n" +
            "\n" +
            "</script>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<div id=\"googleMap\" style=\"width:1000px;height:760px;\"></div>\n" +
            "</body>\n" +
            "\n" +
            "</html>\n";

    public static void generateMapFile(List<Song> samePatternSongs, String fileName) {
        List<String> locations = generateLocationList(samePatternSongs);
        File mapFile = new File(fileName + ".html");
        try {
            FileWriter fos = new FileWriter(mapFile);
            BufferedWriter buf = new BufferedWriter(fos);
            buf.write(pre + locations + post);
            buf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> generateLocationList(List<Song> samePatternSongs) {
        List<String> locations = new ArrayList<>();
        int i = 1;
        for (Song s : samePatternSongs) {
            Location loc = s.getLocation();
            if (loc != null) {
                locations.add("['" + loc.name + "'," + BigDecimal.valueOf(loc.lat).setScale(6, RoundingMode.HALF_UP) + "," + BigDecimal.valueOf(loc.lon).setScale(6, RoundingMode.HALF_UP) + "," + (i++) + "]");
            }
        }
        return locations;
    }
}

