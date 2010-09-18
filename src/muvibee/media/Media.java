package muvibee.media;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public abstract class Media extends Observable {

    private String title = "";
    private String ean = "";
    private String genre = "";
    private int releaseYear = -1;
    private String location = "";
    private boolean isLent = false;
    private String lentTo = "";
    private int lendDay = 0;
    private int lendMonth = 0;
    private int lendYear = -1;
    private int lendUntilDay = 0;
    private int lendUntilMonth = 0;
    private int lendUntilYear = -1;
    private int rating = 0;
    private String description = "";
    private String comment = "";
    private BufferedImage cover;
    private boolean isDeleted;

    public Media() {
    }

    public Media(String title, String ean, String releaseYear, BufferedImage cover) {
        this.title = title;
        this.ean = ean;
        if (releaseYear.indexOf("-") != -1) {
            releaseYear = releaseYear.substring(0, releaseYear.indexOf("-"));
        }
        this.releaseYear = Integer.parseInt(releaseYear);
        this.cover = cover;
    }

    //TODO equals
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
        setChanged();
        notifyObservers();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        setChanged();
        notifyObservers();
    }

    public boolean isIsLent() {
        return isLent;
    }

    public void setIsLent(boolean isLent) {
        this.isLent = isLent;
        setChanged();
        notifyObservers();
    }

    public String getLentTo() {
        return lentTo;
    }

    public void setLendTo(String lendTo) {
        this.lentTo = lendTo;
        setChanged();
        notifyObservers();
    }

    public int getLendDay() {
        return lendDay;
    }

    public void setLendDay(int lendDay) {
        this.lendDay = lendDay;
        setChanged();
        notifyObservers();
    }

    public int getLendMonth() {
        return lendMonth;
    }

    public void setLendMonth(int lendMonth) {
        this.lendMonth = lendMonth;
        setChanged();
        notifyObservers();
    }

    public int getLendUntilDay() {
        return lendUntilDay;
    }

    public void setLendUntilDay(int lendUntilDay) {
        this.lendUntilDay = lendUntilDay;
        setChanged();
        notifyObservers();
    }

    public int getLendUntilMonth() {
        return lendUntilMonth;
    }

    public void setLendUntilMonth(int lendUntilMonth) {
        this.lendUntilMonth = lendUntilMonth;
        setChanged();
        notifyObservers();
    }

    public int getLendUntilYear() {
        return lendUntilYear;
    }

    public void setLendUntilYear(int lendUntilYear) {
        this.lendUntilYear = lendUntilYear;
        setChanged();
        notifyObservers();
    }

    public int getLendYear() {
        return lendYear;
    }

    public void setLendYear(int lendYear) {
        this.lendYear = lendYear;
        setChanged();
        notifyObservers();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
        setChanged();
        notifyObservers();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        setChanged();
        notifyObservers();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        setChanged();
        notifyObservers();
    }

    public BufferedImage getCover() {
        return cover;
    }

    public void setCover(BufferedImage cover) {
        if (cover != null)
            this.cover = cover;
        else
            try {
                this.cover = ImageIO.read(new File("resources/Biene.png"));
            } catch (IOException ex) {
                Logger.getLogger(Media.class.getName()).log(Level.SEVERE, null, ex);
            }
        setChanged();
        notifyObservers();
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
        setChanged();
        notifyObservers();
    }

    //TODO monate/tage vergleich
    public boolean matches(String str) {
        return title.contains(str) || genre.contains(str) 
                || location.contains(str) || lentTo.contains(str)
                || description.contains(str) || comment.contains(str);
    }

    public boolean matches(Media m){
        return title.contains(m.title) && ean.contains(m.ean)
                && genre.contains(m.genre) && releaseYear == m.releaseYear
                && location.contains(m.location) && isLent == m.isLent
                && lentTo.contains(m.lentTo)
                && lendDay == m.lendDay && lendMonth == m.getLendMonth()
                && lendYear == m.lendYear && lendUntilDay == m.lendUntilDay
                && lendUntilMonth == m.lendUntilMonth && lendUntilYear == m.lendUntilYear
                && rating == m.rating && comment.contains(m.comment);
    }

    @Override
    public String toString() {
        return title;
    }
}
