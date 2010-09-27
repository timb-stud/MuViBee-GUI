package muvibee.gui.views.coverdetails;

import muvibee.media.Book;

/**
 * Spezialisierte für Bücher. Super Klasse ist CoverDetailsListEntry
 * @author Christian Rech
 */

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
