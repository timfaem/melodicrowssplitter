package com.example.app.models.filters;

import com.example.app.models.Song;

import java.util.ArrayList;
import java.util.List;

public class CompositeExtractor extends Component{

    List<MusicalFeatureExtractor> component = new ArrayList<>();

    @Override
    public Object extract(Song song) {
        return null;
    }
}
