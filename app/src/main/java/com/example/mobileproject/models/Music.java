package com.example.mobileproject.models;

public class Music {

    String name, artist, resourceId;
    public Music(String name, String artist, String resourceId)
    {
        this.name = name;
        this.artist = artist;
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
