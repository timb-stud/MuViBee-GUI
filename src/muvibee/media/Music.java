package muvibee.media;

public class Music extends Media {

    private String format = "";
    private String interpreter = "";
    private String type = "";

    public Music() {
        super();
    }

    @Override
    public boolean matches(String str) {
        str = str.toLowerCase();
        return super.matches(str) || format.toLowerCase().contains(str)
                || interpreter.toLowerCase().contains(str)
                || type.toLowerCase().contains(str);
    }

    public boolean matches(Music m){
        return super.matches(m) && format.toLowerCase().contains(m.format.toLowerCase())
                && interpreter.toLowerCase().contains(m.interpreter.toLowerCase())
                && type.toLowerCase().contains(m.type.toLowerCase());
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

    @Override
    public String toString() {
        return super.toString()
         + "\n Format: " + format
         + "\n Interpreter: " + interpreter
         + "\n Type: " + type;
    }
}