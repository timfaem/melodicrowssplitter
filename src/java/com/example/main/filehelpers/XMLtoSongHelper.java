package com.example.main.filehelpers;

import com.example.main.models.Song;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLtoSongHelper {

    private static final String CREATOR = "creator";


    public static Song readSong(Document document) {
        NodeList nodeList = document.getElementsByTagName(CREATOR);
        for (int i = 0; i < nodeList.getLength(); i++) {
            String creatorData = nodeList.item(i).getFirstChild().getNodeValue();
            String[] split = creatorData.split("\n");
            String y = split[1].substring(0, 4);
            int year = 0;
            String location;
            if (y.matches("\\b\\d{4}\\b")) {
                year = Integer.parseInt(y);
                location = split[1].substring(6);
            } else {
                location = split[1];
            }
//            System.out.println("Melodic rows: " + split[2].substring(0, split[2].length() - 1) + "\nyear: " + year + "\nlocation: " + location);
        }

        nodeList = document.getElementsByTagName("note");
        System.out.println(nodeList);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println("Measure["+i+"]: " + nodeList.item(i));
        }

        return null;
    }


//    public String getCreatorFromDocument(String tagName, Document document) {
//        NodeList nodeList = document.getElementsByTagName(tagName);
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            String creatorData = nodeList.item(i).getFirstChild().getNodeValue();
//            String[] split = creatorData.split("\n");
//            if (split.length > 2) {
//                return split[2].substring(0, split[2].length() - 1);
//            }
//        }
//        return null;
//    }
}
