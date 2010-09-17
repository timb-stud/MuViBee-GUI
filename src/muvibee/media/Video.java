package muvibee.media;

import java.awt.image.BufferedImage;

public class Video extends Media {

    private String format = "";
    private String director = "";
    private String actors = "";

    public Video() {
    }

    public Video(String title, String ean, String releaseYear, BufferedImage cover, String binding, String actors, String director) {
        super(title, ean, releaseYear, cover);
        if (actors != null) {
            this.actors = actors;
        }
        if (director != null) {
            this.director = director;
        }
        if (binding != null) {
            if (binding.equals("Videokassette")) {
                this.format = "vhs";
            } else {
                if (binding.contains("Blu-ray")) {
                    this.format = "blu-ray";
                } else {
                    this.format = "cd/dvd";
                }
            }
        }
    }

    @Override
    public boolean matches(String str) {
        return super.matches(str) || format.contains(str) || director.contains(str) || actors.contains(str);
    }

    public boolean matches(Video v){
        return super.matches(v) && format.contains(v.format)
                && director.contains(v.director) && actors.contains(v.actors);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actor) {
        this.actors = actor;
    }
}
