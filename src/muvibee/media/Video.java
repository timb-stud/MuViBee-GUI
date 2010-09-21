package muvibee.media;

public class Video extends Media {

    private String format = "";
    private String director = "";
    private String actors = "";

    public Video() {
        super();
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

    @Override
    public String toString() {
        return super.toString()
         + " Format: " + format
         + " Director: " + director
         + " Actors: " + actors;
    }
}