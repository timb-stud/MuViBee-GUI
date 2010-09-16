package muvibee.media;

public class Video extends Media {

    private String format = "";
    private String director = "";
    private String actors = "";

    public Video() {
    }

    public Video(String title, String actors) {
        super(title);
        this.actors = actors;
    }

    @Override
    public boolean matches(String str) {
        return super.matches(str) || format.contains(str) || director.contains(str) || actors.contains(str);
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
