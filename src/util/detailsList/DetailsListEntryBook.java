package util.detailsList;

import muvibee.media.Book;

@SuppressWarnings("serial")
public class DetailsListEntryBook extends DetailsListEntry{

        private Book book;

	public DetailsListEntryBook(Book book) {
		super(book, book.getAuthor());
                this.book = book;
	}

        public Book getBook() {
            return book;
        }
}
