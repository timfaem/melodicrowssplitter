import main.files.SongFileReader;
import main.models.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException, SAXException {
        String path = System.getProperty("user.dir") + "\\input\\test";
        SongFileReader sfReader = new SongFileReader(path);

        String message = "<?xml version='1.0' encoding='UTF-16' standalone='no'?>\n" +
                "<!DOCTYPE score-partwise PUBLIC '-//Recordare//DTD MusicXML 3.0 Partwise//EN' 'http://www.musicxml.org/dtds/partwise.dtd'>\n" +
                "<score-partwise version='3.0'>\n" +
                "\t<movement-title>model</movement-title>\n" +
                "\t<identification>\n" +
                "\t\t<creator type='composer'>EM. Mio. 57.\n" +
                "1947. HD. Bo?orod.\n" +
                "6, 6, 6r, 6, 6</creator>\n" +
                "\t\t<rights>Copyright © </rights>\n" +
                "\t\t<encoding>\n" +
                "\t\t\t<software>Sibelius 6.0</software>\n" +
                "\t\t\t<software>Dolet 6.2 for Sibelius</software>\n" +
                "\t\t\t<encoding-date>2015-11-09</encoding-date>\n" +
                "\t\t\t<supports element='accidental' type='no'/>\n" +
                "\t\t\t<supports element='transpose' type='no'/>\n" +
                "\t\t\t<supports attribute='new-page' element='print' type='yes' value='yes'/>\n" +
                "\t\t\t<supports attribute='new-system' element='print' type='yes' value='yes'/>\n" +
                "\t\t</encoding>\n" +
                "\t</identification>\n" +
                "\t<defaults>\n" +
                "\t\t<scaling>\n" +
                "\t\t\t<millimeters>7</millimeters>\n" +
                "\t\t\t<tenths>40</tenths>\n" +
                "\t\t</scaling>\n" +
                "\t\t<page-layout>\n" +
                "\t\t\t<page-height>1597</page-height>\n" +
                "\t\t\t<page-width>1234</page-width>\n" +
                "\t\t</page-layout>\n" +
                "\t</defaults>\n" +
                "\t<part-list>\n" +
                "\t\t<score-part id='P1'>\n" +
                "\t\t\t<part-name>Acoustic Guitar</part-name>\n" +
                "\t\t\t<part-abbreviation>A. Gtr.</part-abbreviation>\n" +
                "\t\t\t<score-instrument id='P1-I1'>\n" +
                "\t\t\t\t<instrument-name>Acoustic Guitar [notation]</instrument-name>\n" +
                "\t\t\t\t<instrument-abbreviation>A. Gtr.</instrument-abbreviation>\n" +
                "\t\t\t\t<instrument-sound>pluck.guitar.steel-string</instrument-sound>\n" +
                "\t\t\t</score-instrument>\n" +
                "\t\t\t<midi-instrument id='P1-I1'>\n" +
                "\t\t\t\t<volume>79</volume>\n" +
                "\t\t\t\t<pan>0</pan>\n" +
                "\t\t\t</midi-instrument>\n" +
                "\t\t</score-part>\n" +
                "\t</part-list>\n" +
                "<!--=========================================================-->\n" +
                "\t<part id='P1'>\n" +
                "\t\t<measure number='1'>\n" +
                "\t\t\t<attributes>\n" +
                "\t\t\t\t<divisions>768</divisions>\n" +
                "\t\t\t\t<key>\n" +
                "\t\t\t\t\t<fifths>1</fifths>\n" +
                "\t\t\t\t\t<mode>major</mode>\n" +
                "\t\t\t\t</key>\n" +
                "\t\t\t\t<time>\n" +
                "\t\t\t\t\t<beats>4</beats>\n" +
                "\t\t\t\t\t<beat-type>4</beat-type>\n" +
                "\t\t\t\t</time>\n" +
                "\t\t\t\t<clef>\n" +
                "\t\t\t\t\t<sign>G</sign>\n" +
                "\t\t\t\t\t<line>2</line>\n" +
                "\t\t\t\t</clef>\n" +
                "\t\t\t</attributes>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>G</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>single</syllabic>\n" +
                "\t\t\t\t\t<text>P&apos;un</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>B</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>begin</syllabic>\n" +
                "\t\t\t\t\t<text>pi</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>B</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>end</syllabic>\n" +
                "\t\t\t\t\t<text>cior</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>C</step>\n" +
                "\t\t\t\t\t<alter>1</alter>\n" +
                "\t\t\t\t\t<octave>4</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>single</syllabic>\n" +
                "\t\t\t\t\t<text>de</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>A</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>begin</syllabic>\n" +
                "\t\t\t\t\t<text>pla</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>G</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>end</syllabic>\n" +
                "\t\t\t\t\t<text>i,</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t</measure>\n" +
                "<!--=========================================================-->\n" +
                "\t\t<measure number='2'>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>B</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>single</syllabic>\n" +
                "\t\t\t\t\t<text>P&apos;o</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>B</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>begin</syllabic>\n" +
                "\t\t\t\t\t<text>gu</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>E</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>end</syllabic>\n" +
                "\t\t\t\t\t<text>r?</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>F</step>\n" +
                "\t\t\t\t\t<alter>1</alter>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>single</syllabic>\n" +
                "\t\t\t\t\t<text>de</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>B</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>begin</syllabic>\n" +
                "\t\t\t\t\t<text>ra</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>A</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>end</syllabic>\n" +
                "\t\t\t\t\t<text>i,</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t</measure>\n" +
                "<!--=========================================================-->\n" +
                "\t\t<measure number='3'>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>G</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>single</syllabic>\n" +
                "\t\t\t\t\t<text>Dai</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>G</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>begin</syllabic>\n" +
                "\t\t\t\t\t<text>Dom</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>E</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>middle</syllabic>\n" +
                "\t\t\t\t\t<text>nu</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>F</step>\n" +
                "\t\t\t\t\t<alter>1</alter>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>end</syllabic>\n" +
                "\t\t\t\t\t<text>lui</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>G</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>begin</syllabic>\n" +
                "\t\t\t\t\t<text>Doam</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>G</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>end</syllabic>\n" +
                "\t\t\t\t\t<text>ne,</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t</measure>\n" +
                "<!--=========================================================-->\n" +
                "\t\t<measure number='4'>\n" +
                "\t\t\t<print new-system='yes'/>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>G</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>begin</syllabic>\n" +
                "\t\t\t\t\t<text>Ia</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>B</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>end</syllabic>\n" +
                "\t\t\t\t\t<text>c?</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>B</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>single</syllabic>\n" +
                "\t\t\t\t\t<text>vin</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>C</step>\n" +
                "\t\t\t\t\t<alter>1</alter>\n" +
                "\t\t\t\t\t<octave>4</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>single</syllabic>\n" +
                "\t\t\t\t\t<text>în</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>A</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>begin</syllabic>\n" +
                "\t\t\t\t\t<text>ca</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>A</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>end</syllabic>\n" +
                "\t\t\t\t\t<text>le,</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t</measure>\n" +
                "<!--=========================================================-->\n" +
                "\t\t<measure number='5'>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>B</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>single</syllabic>\n" +
                "\t\t\t\t\t<text>?i</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>B</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>begin</syllabic>\n" +
                "\t\t\t\t\t<text>co</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>E</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>middle</syllabic>\n" +
                "\t\t\t\t\t<text>boa</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>F</step>\n" +
                "\t\t\t\t\t<alter>1</alter>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>end</syllabic>\n" +
                "\t\t\t\t\t<text>r?&apos;n</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>A</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>384</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>eighth</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>begin</syllabic>\n" +
                "\t\t\t\t\t<text>va</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<note>\n" +
                "\t\t\t\t<pitch>\n" +
                "\t\t\t\t\t<step>G</step>\n" +
                "\t\t\t\t\t<octave>3</octave>\n" +
                "\t\t\t\t</pitch>\n" +
                "\t\t\t\t<duration>768</duration>\n" +
                "\t\t\t\t<voice>1</voice>\n" +
                "\t\t\t\t<type>quarter</type>\n" +
                "\t\t\t\t<lyric number='1' name='verse' default-y='-80'>\n" +
                "\t\t\t\t\t<syllabic>end</syllabic>\n" +
                "\t\t\t\t\t<text>le.</text>\n" +
                "\t\t\t\t</lyric>\n" +
                "\t\t\t</note>\n" +
                "\t\t\t<barline location='right'>\n" +
                "\t\t\t\t<bar-style>light-heavy</bar-style>\n" +
                "\t\t\t</barline>\n" +
                "\t\t</measure>\n" +
                "\t</part>\n" +
                "<!--=========================================================-->\n" +
                "</score-partwise>\n";

        JSONObject jsonObj = XML.toJSONObject(message);
        String jsonString = jsonObj.toString(4);
        System.out.println(jsonString);

        JSONObject scorePartwiseJSON = jsonObj.getJSONObject("score-partwise");

        String creator = scorePartwiseJSON.getJSONObject("identification").getJSONObject("creator").getString("content");
        String[] split = creator.split("\n");
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
                .year(year)
                .melodicRows(split[2].substring(0, split[2].length() - 1))
                .location(new Location(location))
                .title(split[0])
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
            System.out.println(song.build());
    }
}
