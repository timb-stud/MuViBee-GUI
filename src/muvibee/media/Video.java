package muvibee.media;

import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import muvibee.gui.StatusBarModel;

public class Video extends Media {

    private String format = "";
    private String director = "";
    private String actors = "";

    public Video() {
         try {
            URL imgURL = getClass().getResource("resources/default_video_cover.jpg");
            this.setCover(ImageIO.read(imgURL));
        } catch (IOException ex) {
            StatusBarModel.getInstance().setFailMessage("default music cover not found");
        }
    }

    @Override
    public boolean matches(String str) {
        str = str.toLowerCase();
        return super.matches(str) || format.toLowerCase().contains(str)
                || director.toLowerCase().contains(str)
                || actors.toLowerCase().contains(str);
    }

    public boolean matches(Video v){
        return super.matches(v) && format.toLowerCase().contains(v.format.toLowerCase())
                && director.toLowerCase().contains(v.director.toLowerCase())
                && actors.toLowerCase().contains(v.actors.toLowerCase());
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

    @Override
    public String toString() {
        return super.toString()
         + "\n Format: " + format
         + "\n Director: " + director
         + "\n Actors: " + actors;
    }
}