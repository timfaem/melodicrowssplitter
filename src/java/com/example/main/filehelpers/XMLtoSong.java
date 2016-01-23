package com.example.main.filehelpers;

import com.example.main.models.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class XMLtoSong {

    public static Song toSong(String xmlString) {

        JSONObject jsonObj = XML.toJSONObject(xmlString);

//        String jsonString = jsonObj.toString(4);
//        System.out.println(jsonString);

        JSONObject scorePartwiseJSON = jsonObj.getJSONObject("score-partwise");

        String creator = scorePartwiseJSON.getJSONObject("identification").getJSONObject("creator").getString("content");
        String[] split = creator.split("\n");
        String title = split[0];
        String[] authorGenreNo = split[0].split(" ");
        String author = authorGenreNo[0].substring(0, authorGenreNo[0].length() - 1);
        Genre genre = Genre.fromString(authorGenreNo[1].substring(0, authorGenreNo[1].length() - 1));



        String y = split[1].substring(0, 4);
        int year = 0;
        String location;
        if (y.matches("\\b\\d{4}\\b")) {
            year = Integer.parseInt(y);
            location = split[1].substring(6);
        } else {
            location = split[1];
        }

        JSONArray measuresJSON = scorePartwiseJSON.getJSONObject("part").getJSONArray("measure");

        Song.Builder song = new Song.Builder()
                .author(author)
                .year(year)
                .melodicRows(split[2].substring(0, split[2].length() - 1))
                .location(new Location(location))
                .title(title)
                .genre(genre);

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
