package muvibee.media;

import java.awt.image.BufferedImage;

public class Book extends Media {

    private String author;
    private String language;
    private String isbn;

    public Book() {
    }

    public Book(String title, String ean, String releaseYear, BufferedImage cover, String author, String isbn, String language) {
        super(title, ean, releaseYear, cover);
        this.author = author;
        this.isbn = isbn;
        this.language = language;
    }

    @Override
    public boolean matches(String str) {
        return super.matches(str) || author.contains(str) || language.contains(str);
    }
    //TODO equals

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
