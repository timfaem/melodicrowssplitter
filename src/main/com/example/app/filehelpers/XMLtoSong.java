package com.example.app.filehelpers;

import com.example.app.models.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class XMLtoSong {

    public static Set<String> exceptions = new HashSet<>();

    public static Song toSong(String xmlString, String filePath, LocationFinder locFinder) throws FileNotFoundException {

        JSONObject jsonObj = XML.toJSONObject(xmlString);

        JSONObject scorePartwiseJSON = new JSONObject();
        if (jsonObj.has("score-partwise")) {
            scorePartwiseJSON = jsonObj.getJSONObject("score-partwise");
        } else {
            System.out.println("Song at: " + filePath + " has no score-partwise");
        }
        String creator = scorePartwiseJSON.getJSONObject("identification").getJSONObject("creator").getString("content");
        String[] split = creator.split("\r\n");
        String title = split[0];
        String[] authorGenreNo = split[0].split(" ");
        String author = authorGenreNo[0].substring(0, authorGenreNo[0].length() - 1);
        Genre genre = Genre.fromString(authorGenreNo[1].substring(0, authorGenreNo[1].length() - 1));


        String y = split[1].startsWith(" ") ? split[1].substring(1, 5) : split[1].substring(0, 4);
        int year = 0;
        String location;
        if (y.matches("\\b\\d{4}\\b")) {
            year = Integer.parseInt(y);
            location = split[1].substring(6).replace(". \r", "");
        } else {
            location = split[1].trim().replace(". \r", "");
        }

        JSONArray measuresJSON = new JSONArray();
        try {
            //TODO to be uncommented
//            measuresJSON = scorePartwiseJSON.getJSONObject("part").getJSONArray("measure");
        } catch (JSONException ex) {
            System.out.println("Exception at: " + filePath);
        }

        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);

        String[] judetSat = location.trim().split("\\.");
        String sat = judetSat[judetSat.length - 1];
        if (sat.startsWith(" ")) {
            sat = sat.substring(1);
        }
        Location songLocation = locFinder.getLocationForName(sat);

        Song.Builder song = new Song.Builder()
                .author(author)
                .fileName(fileName)
                .year(year)
                .melodicRows(split[2].substring(0, split[2].length() - 1))
                .location(songLocation)
                .title(title)
                .genre(genre);

        if (songLocation == null) {
            exceptions.add(sat + "(" + judetSat[judetSat.length - 2] + ")");
        }

        for (int i = 0; i < measuresJSON.length(); i++) {
            Measure.Builder measure = new Measure.Builder();
            JSONArray notes = ((JSONObject) measuresJSON.get(i)).getJSONArray("note");
            for (int j = 0; j < notes.length(); j++) {
                JSONObject jsonNote = (JSONObject) notes.get(j);

                int duration = jsonNote.getInt("duration");

                JSONObject pitchJson = jsonNote.getJSONObject("pitch");
                Pitch pitch = new Pitch.Builder()
                        .octave(pitchJson.getInt("octave"))
                        .step(Step.fromString(pitchJson.getString("step")))
                        .alter(Alter.fromInt(jsonObj.opt("alter") != null ? jsonObj.getInt("alter") : 0))
                        .build();

                Note note = new Note.Builder()
                        .duration(duration)
                        .pitch(pitch)
                        .build();

                measure.addNote(note);
            }

            song.addMeasure(measure.build());
        }

        return song.build();
    }

}
