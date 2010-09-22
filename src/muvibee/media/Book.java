package muvibee.media;

public class Book extends Media {

    private String author;
    private String language;
    private String isbn;

    public Book() {
        super();
    }

    @Override
    public boolean matches(String str) {
        str = str.toLowerCase();
        return super.matches(str) || author.toLowerCase().contains(str)
                || language.toLowerCase().contains(str)
                || isbn.toLowerCase().contains(str);
    }

    public boolean matches(Book b){
        return super.matches(b) && author.toLowerCase().contains(b.author.toLowerCase())
                && language.toLowerCase().contains(b.language.toLowerCase())
                && isbn.toLowerCase().contains(b.isbn.toLowerCase());
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

    @Override
    public String toString() {
        return super.toString()
         + "\n Author: " + author
         + "\n Language: " + language
         + "\n ISBN: " + isbn;
    }
}
