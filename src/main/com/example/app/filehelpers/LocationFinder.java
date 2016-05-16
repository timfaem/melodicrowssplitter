package com.example.app.filehelpers;

import com.example.app.models.Location;

public interface LocationFinder {
    Location getLocationForName(String name);
}
