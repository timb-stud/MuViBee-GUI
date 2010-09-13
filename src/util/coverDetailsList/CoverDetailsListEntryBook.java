package util.coverDetailsList;

import muvibee.media.Book;

@SuppressWarnings("serial")
public class CoverDetailsListEntryBook extends CoverDetailsListEntry{

	public CoverDetailsListEntryBook(Book book) {
		super(book, book.getAuthor());
	}
}
