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

	public Music(	String format,
			String interpreter,
			String type,
			String title,
			String ean, 
			String genre,
			int year,
			String location,
			String lendTo,
			int lendDay,
			int lendMonth,
			int lendYear,
			int lendUntilDay,
			int lendUntilMonth,
			int lendUntilYear,
			int rating,
			String description,
			String comment,
			BufferedImage cover,
			boolean isDeleted) {

		super(title, 
			ean,
			genre,
			year,
			location,
			lendTo,
			lendDay,
			lendMonth,
			lendYear,
			lendUntilDay,
			lendUntilMonth,
			lendUntilYear,
			rating,
			description,
			comment,
			cover,
			isDeleted);
		this.format = format;
		this.interpreter = interpreter;
		this.type = type;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}