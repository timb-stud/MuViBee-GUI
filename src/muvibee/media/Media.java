package muvibee.media;



import java.awt.image.BufferedImage;



public abstract class Media {
	private String title;
	private String ean;
	private String genre;
	private int year;
	private String location;
	private String lendTo;
	private String lendDate;
	private String backDate;
	private int rating;
	private String description;
	private String comment;
	private BufferedImage cover;
	private boolean isDeleted;

        public Media(){ }

        public Media(String title){
            this.title = title;
        }


	public Media(String title, String ean, String genre, int year, String location, String lendTo,
			String lendDate, String backDate, int rating, String description,
			String comment, BufferedImage cover, boolean isDeleted) {
		this.title = title;
		this.ean = ean;
		this.genre = genre;
		this.year = year;
		this.location = location;
		this.lendTo = lendTo;
		this.lendDate = lendDate;
		this.backDate = backDate;
		this.rating = rating;
		this.description = description;
		this.comment = comment;
		this.cover = cover;
		this.isDeleted = isDeleted;
	}

	public abstract String toString();

	public void insertIntoDB() {
		//DBInsertor.insertIntoDB(this);
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLendTo() {
		return lendTo;
	}

	public void setLendTo(String lendTo) {
		this.lendTo = lendTo;
	}

	public String getLendDate() {
		return lendDate;
	}

	public void setLendDate(String lendDate) {
		this.lendDate = lendDate;
	}

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BufferedImage getCover() {
		return cover;
	}

	public void setCover(BufferedImage cover) {
		this.cover = cover;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}