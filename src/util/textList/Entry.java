package util.textList;

public class Entry {

	
	private String title;
	private String author;
	
	
	public Entry(String title, String author) {
		this.title = title;
		this.author = author;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}
	
}
