package muvibee.media;



import java.awt.image.BufferedImage;
import java.util.Observable;



public abstract class Media extends Observable {
	String	title = new String();
	private String	ean = new String();
	private String	genre = new String();
	private int	releaseYear;
	private String	location = new String();
	private String	lendTo = new String();
	private int	lendDay;
	private int	lendMonth;
	private int	lendYear;
	private int	lendUntilDay;
	private int	lendUntilMonth;
	private int	lendUntilYear;
	private int	rating;
	private String	description = new String();
	private String	comment = new String();
	private BufferedImage cover;
	private boolean isDeleted;

        public Media(){ }

        public Media(String title){
            this.title = title;
        }


	public Media(	String title,
			String ean,
			String genre,
			int releaseYear,
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
	    
		this.title = title;
		this.ean = ean;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.location = location;
		this.lendTo = lendTo;
		this.lendDay = lendDay;
		this.lendMonth = lendMonth;
		this.lendYear = lendYear;
		this.lendUntilDay = lendUntilDay;
		this.lendUntilMonth = lendUntilMonth;
		this.lendUntilYear = lendUntilYear;
		this.rating = rating;
		this.description = description;
		this.comment = comment;
		this.cover = cover;
		this.isDeleted = isDeleted;
	}

        //TODO equals

	public abstract String toString();

	public void insertIntoDB() {
		//DBInsertor.insertIntoDB(this);
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
                setChanged();
                notifyObservers();
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
                setChanged();
                notifyObservers();
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
                setChanged();
                notifyObservers();
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLentTo() {
		return lendTo;
	}

	public void setLendTo(String lendTo) {
		this.lendTo = lendTo;
	}

	public int getLendDay() {
	    return lendDay;
	}

	public void setLendDay(int lendDay) {
	    this.lendDay = lendDay;
	}

	public int getLendMonth() {
	    return lendMonth;
	}

	public void setLendMonth(int lendMonth) {
	    this.lendMonth = lendMonth;
	}

	public int getLendUntilDay() {
	    return lendUntilDay;
	}

	public void setLendUntilDay(int lendUntilDay) {
	    this.lendUntilDay = lendUntilDay;
	}

	public int getLendUntilMonth() {
	    return lendUntilMonth;
	}

	public void setLendUntilMonth(int lendUntilMonth) {
	    this.lendUntilMonth = lendUntilMonth;
	}

	public int getLendUntilYear() {
	    return lendUntilYear;
	}

	public void setLendUntilYear(int lendUntilYear) {
	    this.lendUntilYear = lendUntilYear;
	}

	public int getLendYear() {
	    return lendYear;
	}

	public void setLendYear(int lendYear) {
	    this.lendYear = lendYear;
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

        //TODO monate/tage vergleich
        public boolean matches(String str) {
            return title.contains(str) || genre.contains(str) || location.contains(str) || lendTo.contains(str) || description.contains(str) || comment.contains(str);
        }

}