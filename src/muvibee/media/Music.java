package muvibee.media;

import java.awt.image.BufferedImage;

public class Music extends Media {

    private String format = "";
    private String interpreter = "";
    private String type = "";

    public Music() {
    }

    public Music(String title, String ean, String releaseYear, BufferedImage cover, String interpreter) {
        super(title, ean, releaseYear, cover);
        this.interpreter = interpreter;
    }

    @Override
    public boolean matches(String str) {
        return super.matches(str) || format.contains(str) || interpreter.contains(str) || type.contains(str);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(String interpreter) {
        this.interpreter = interpreter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
