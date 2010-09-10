package muvibee.media;



import java.awt.image.BufferedImage;

public class Video extends Media{
	private String format;
	private String director;
	private String actor;

        public Video(){ }

        public Video(String title, String actor){
            super(title);
            this.actor = actor;
        }


	public Video(String format, String director, String actor, String title,
			String ean, String genre, int year, String location, String lendTo,
			String lendDate, String backDate, int rating, String description,
			String comment, BufferedImage cover, boolean isDeleted) {
		super(title, ean, genre, year, location, lendTo, lendDate, backDate, rating, description, comment, cover, isDeleted);
		this.format = format;
		this.director = director;
		this.actor = actor;
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

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}