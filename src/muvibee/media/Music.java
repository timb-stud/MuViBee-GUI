package muvibee.media;

import java.awt.image.BufferedImage;

public class Music extends Media {

    private String format = "";
    private String interpreter = "";
    private String type = "";

    public Music() {
    }

    public Music(String title, String ean, String releaseYear, BufferedImage cover, String interpreter, String binding) {
        super(title, ean, releaseYear, cover);
        this.interpreter = interpreter;
        if (binding != null) {
            if (binding.contains("LP")) {
                this.format = "LP";
            } else if (binding.contains("Hörkassette") || binding.contains("Musikkassette")) {
                this.format = "Cassette";
            } else {
                this.format = "CD";
            }
        }
    }

    @Override
    public boolean matches(String str) {
        return super.matches(str) || format.contains(str) || interpreter.contains(str) || type.contains(str);
    }

    public boolean matches(Music m){
        return super.matches(m) && format.contains(m.format)
                && interpreter.contains(m.interpreter) && type.contains(m.type);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
        setChanged();
        notifyObservers();
    }

    public String getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(String interpreter) {
        this.interpreter = interpreter;
        setChanged();
        notifyObservers();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        setChanged();
        notifyObservers();
    }
}
