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
        return super.matches(str) || author.contains(str) || language.contains(str);
    }

    public boolean matches(Book b){
        return super.matches(b) && author.contains(b.author)
                && language.contains(b.language) && isbn.contains(b.isbn);
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
