package muvibee.media;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import muvibee.db.DBUpdater;

public abstract class Media extends Observable {

    private final String COVER_PATH = "data/images/";
    private int ID = -1;
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
        try {
            this.cover = ImageIO.read(new File(COVER_PATH + "default_cover.jpg"));
        } catch (IOException ex) {
            throw new RuntimeException("Das default Cover wird nicht auf der Festplatte gefunden");
        }
    }

    public void updateObservers() {
        setChanged();
        notifyObservers();
    }


    public void updateDB() {
        DBUpdater.dbUpdate(this);
    }

    public void deleteDB() {
        DBUpdater.dbDelete(this);
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public boolean isIsLent() {
        return isLent;
    }

    public void setIsLent(boolean isLent) {
        this.isLent = isLent;
    }

    public String getLentTo() {
        return lentTo;
    }

    public void setLendTo(String lendTo) {
        this.lentTo = lendTo;
    }

    public int getLendDay() {
        return lendDay;
    }

    public String getLendDate() {
        String day = String.valueOf(this.lendDay);
        String month = String.valueOf(this.lendMonth);
        if (this.lendDay < 10) {
            day = "0" + this.lendDay;
        } else if  (this.lendMonth < 10) {
            month = "0" + this.lendMonth;
        }
        return this.lendYear + "-" + month + "-" + day;
    }

    public void setLendDate(String date) {
        String[] zerlegt = new String[3];
        zerlegt = date.split("-");
        this.lendYear = Integer.parseInt(zerlegt[0]);
        this.lendMonth = Integer.parseInt(zerlegt[1]);
        this.lendDay = Integer.parseInt(zerlegt[2]);
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

    public String getLendUntilDate() {
        String day = String.valueOf(this.lendUntilDay);
        String month = String.valueOf(this.lendUntilMonth);
        if (this.lendUntilDay < 10) {
            day = "0" + this.lendUntilDay;
        } else if  (this.lendUntilMonth < 10) {
            month = "0" + this.lendUntilMonth;
        }
        return this.lendUntilYear + "-" + month + "-" + day;
    }

    public void setLendUntilDate(String date) {
        String[] zerlegt = new String[3];
        zerlegt = date.split("-");
        this.lendUntilYear = Integer.parseInt(zerlegt[0]);
        this.lendUntilMonth = Integer.parseInt(zerlegt[1]);
        this.lendUntilDay = Integer.parseInt(zerlegt[2]);
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
        if (cover != null)
            this.cover = cover;
        else
            try {
                this.cover = ImageIO.read(new File("resources/Biene.png"));
            } catch (IOException ex) {
                Logger.getLogger(Media.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    //TODO monate/tage vergleich
    public boolean matches(String str) {
        return title.contains(str) || genre.contains(str) 
                || location.contains(str) || lentTo.contains(str)
                || description.contains(str) || comment.contains(str);
    }

    public boolean matches(Media m){
        System.out.println("Title: " +title.contains(m.title));
        System.out.println("ean: " + ean.contains(m.ean));
        System.out.println("genre:" + genre.contains(m.genre));
        System.out.println("releaseYear: " + (releaseYear == m.releaseYear));
        System.out.println("location: " + location.contains(m.location));
        System.out.println("islent:"  + (isLent == m.isLent));
        System.out.println("lentto: " + lentTo.contains(m.lentTo));
        if(rating == -1 || m.rating == -1){
            return title.contains(m.title) && ean.contains(m.ean)
                && genre.contains(m.genre) && releaseYear == m.releaseYear
                && location.contains(m.location) && isLent == m.isLent
                && lentTo.contains(m.lentTo)
                && lendDay == m.lendDay && lendMonth == m.getLendMonth()
                && lendYear == m.lendYear && lendUntilDay == m.lendUntilDay
                && lendUntilMonth == m.lendUntilMonth && lendUntilYear == m.lendUntilYear
                && comment.contains(m.comment);
        }else{
            return title.contains(m.title) && ean.contains(m.ean)
                && genre.contains(m.genre) && releaseYear == m.releaseYear
                && location.contains(m.location) && isLent == m.isLent
                && lentTo.contains(m.lentTo)
                && lendDay == m.lendDay && lendMonth == m.getLendMonth()
                && lendYear == m.lendYear && lendUntilDay == m.lendUntilDay
                && lendUntilMonth == m.lendUntilMonth && lendUntilYear == m.lendUntilYear
                && rating == m.rating && comment.contains(m.comment);
        }
        
    }

    @Override
    public String toString() {
        return "ID " + ID + " : " + title;
    }
}
