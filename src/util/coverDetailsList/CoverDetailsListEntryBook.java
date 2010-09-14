package util.coverDetailsList;

import muvibee.media.Book;

@SuppressWarnings("serial")
public class CoverDetailsListEntryBook extends CoverDetailsListEntry{

        private Book book;

	public CoverDetailsListEntryBook(Book book) {
		super(book, book.getAuthor());
                this.book = book;
	}

        public Book getBook() {
            return book;
        }
}
