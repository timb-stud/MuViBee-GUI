package muvibee.media;



import java.awt.image.BufferedImage;

public class Music extends Media{
	private String format;
	private String interpreter;
	private String type;

        public Music(){ }

        public Music(String title, String interpreter){
            super(title);
            this.interpreter = interpreter;
        }

	public Music(String format, String interpreter, String type, String title,
			String ean, String genre, int year, String location, String lendTo,
			String lendDate, String backDate, int rating, String description,
			String comment, BufferedImage cover, boolean isDeleted) {
		super(title, ean, genre, year, location, lendTo, lendDate, backDate, rating, description, comment, cover, isDeleted);
		this.format = format;
		this.interpreter = interpreter;
		this.type = type;
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
		// TODO Auto-generated method stub
		return null;
	}
}