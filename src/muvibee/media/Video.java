package muvibee.media;



import java.awt.image.BufferedImage;

public class Video extends Media{
	private String format;
	private String director;
	private String actors;

        public Video(){ }

        public Video(String title, String actors){
            super(title);
            this.actors = actors;
        }


	public Video(String format, 
		    String director,
		    String actors,
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
		this.director = director;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}