package muvibee.media;

import java.awt.image.BufferedImage;
import java.util.Observable;
import muvibee.db.DBUtil;

/**
 * @author Yassir Klos
 * Abstrakte Klasse Media gibt Methoden und Atribute vor,
 * die von Book/Music/Video geerbt werden bzw. implementiert werden muessen
 */
public abstract class Media extends Observable {

    private int ID = -1;
    private String title = "";
    private String ean = "";
    private String genre = "";
    private int releaseYear = 0;
    private String location = "";
    private String lentTo = "";
    private int lentDay = 0;
    private int lentMonth = 0;
    private int lentYear = 0;
    private int lentUntilDay = 0;
    private int lentUntilMonth = 0;
    private int lentUntilYear = 0;
    private int rating = 0;
    private String description = "";
    private String comment = "";
    private BufferedImage cover;
    private boolean isDeleted;
    private boolean isLent = false;

    public Media() {
    }

    public void updateObservers() {
        setChanged();
        notifyObservers();
    }

    public void updateDB() {
        DBUtil.dbUpdate(this);
    }

    public void deleteDB() {
        DBUtil.dbDelete(this);
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

    public boolean isLent() {
        return isLent;
    }

    public void setIsLent(boolean isLent) {
        this.isLent = isLent;
    }

    public String getLentTo() {
        return lentTo;
    }

    public void setLentTo(String lendTo) {
        this.lentTo = lendTo;
    }

    public int getLentDay() {
        return lentDay;
    }

    public String getLentDate() {
        String day = String.valueOf(this.lentDay);
        String month = String.valueOf(this.lentMonth);
        if (this.lentDay < 10) {
            day = "0" + this.lentDay;
        }
        if (this.lentMonth < 10) {
            month = "0" + this.lentMonth;
        }
        if (this.lentYear == 0) {
            return null;
        }
        return this.lentYear + "-" + month + "-" + day;
    }

    public void setLentDate(String date) {
        if (date == null) {
            this.lentYear = 0;
            this.lentMonth = 0;
            this.lentDay = 0;
        } else {
            String[] zerlegt = new String[3];
            zerlegt = date.split("-");
            this.lentYear = Integer.parseInt(zerlegt[0]);
            this.lentMonth = Integer.parseInt(zerlegt[1]);
            this.lentDay = Integer.parseInt(zerlegt[2]);
        }
    }

    public void setLentDay(int lendDay) {
        this.lentDay = lendDay;
    }

    public int getLentMonth() {
        return lentMonth;
    }

    public void setLentMonth(int lendMonth) {
        this.lentMonth = lendMonth;
    }

    public int getLentUntilDay() {
        return lentUntilDay;
    }

    public String getLentUntilDate() {
        String day = String.valueOf(this.lentUntilDay);
        String month = String.valueOf(this.lentUntilMonth);
        if (this.lentUntilDay < 10) {
            day = "0" + this.lentUntilDay;
        }
        if (this.lentUntilMonth < 10) {
            month = "0" + this.lentUntilMonth;
        }
        if (this.lentUntilYear == 0) {
            return null;
        }
        return this.lentUntilYear + "-" + month + "-" + day;
    }

    public void setLentUntilDate(String date) {
        if (date == null) {
            this.lentYear = 0;
            this.lentMonth = 0;
            this.lentDay = 0;
        } else {
            String[] zerlegt = new String[3];
            zerlegt = date.split("-");
            this.lentUntilYear = Integer.parseInt(zerlegt[0]);
            this.lentUntilMonth = Integer.parseInt(zerlegt[1]);
            this.lentUntilDay = Integer.parseInt(zerlegt[2]);
        }
    }

    public void setLentUntilDay(int lendUntilDay) {
        this.lentUntilDay = lendUntilDay;
    }

    public int getLentUntilMonth() {
        return lentUntilMonth;
    }

    public void setLentUntilMonth(int lendUntilMonth) {
        this.lentUntilMonth = lendUntilMonth;
    }

    public int getLentUntilYear() {
        return lentUntilYear;
    }

    public void setLentUntilYear(int lendUntilYear) {
        this.lentUntilYear = lendUntilYear;
    }

    public int getLentYear() {
        return lentYear;
    }

    public void setLentYear(int lendYear) {
        this.lentYear = lendYear;
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

    /**
     * Vergleicht einen Suchstring mit einem Media Objekt.
     * @param str
     * @return
     */
    public boolean sortMatches(String str) {
        if (description == null) {
            description = "";
        }
        str = str.toLowerCase();
        return title.toLowerCase().contains(str) || genre.toLowerCase().contains(str)
                || location.toLowerCase().contains(str) || lentTo.toLowerCase().contains(str)
                || description.toLowerCase().contains(str) || comment.toLowerCase().contains(str);
    }

    /**
     * Vergleicht ein Media Objekt mit einem such Media Objekt.
     * Und gibt zurück ob ein such Media Objekt zu einem Media Objekt passt.
     * Standard initialisierte Attribute des such Media Objekts werden nicht beruecksichtigt.
     * @param m
     * @return true wenn das such Media Objekt zu diesem hier passt.
     */
    public boolean sortMatches(Media m) {
        if (description == null) {
            description = "";
        }
        return title.toLowerCase().contains(m.title.toLowerCase())
                && ean.toLowerCase().contains(m.ean.toLowerCase())
                && genre.toLowerCase().contains(m.genre.toLowerCase())
                && (releaseYear == m.releaseYear || m.releaseYear == 0)
                && location.toLowerCase().contains(m.location.toLowerCase())
                && (rating == m.rating || m.rating == -1)
                && isLent == m.isLent
                && lentTo.toLowerCase().contains(m.lentTo.toLowerCase())
                && (lentDay == m.lentDay || m.lentDay == 0)
                && (lentMonth == m.lentMonth || m.lentMonth == 0)
                && (lentYear == m.lentYear || m.lentYear == 0)
                && (lentUntilDay == m.lentUntilDay || m.lentUntilDay == 0)
                && (lentUntilMonth == m.lentUntilMonth || m.lentUntilMonth == 0)
                && (lentUntilYear == m.lentUntilYear || m.lentUntilYear == 0)
                && comment.toLowerCase().contains(m.comment.toLowerCase());
    }

    @Override
    public String toString() {
        return "\n ID: " + this.ID
                + "\n Titel: " + this.title
                + "\n EAN: " + this.ean
                + "\n Genre: " + this.genre
                + "\n ReleaseYear: " + this.releaseYear
                + "\n Location: " + this.location
                + "\n isLent: " + this.isLent
                + "\n lentDay: " + this.lentDay
                + "\n lentMonth: " + this.lentMonth
                + "\n lentYear: " + this.lentYear
                + "\n LentDate: " + this.getLentDate()
                + "\n LentTo: " + this.lentTo
                + "\n lentUntilDay: " + this.lentUntilDay
                + "\n lentUntilMonth: " + this.lentUntilMonth
                + "\n lentUntilYear: " + this.lentUntilYear
                + "\n LentUntilDate: " + this.getLentUntilDate()
                + "\n Rating: " + this.rating
                + "\n Description: " + this.description
                + "\n Comment: " + this.comment
                + "\n Cover: " + this.cover.toString()
                + "\n isDeleted: " + this.isDeleted
                + "\n isLent: " + this.isLent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Media) {
            Media m = (Media) obj;
            if (this.ID == m.ID) {
                if (this instanceof Book && m instanceof Book
                        || this instanceof Music && m instanceof Music
                        || this instanceof Video && m instanceof Video) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.ID;
        return hash;
    }
}
