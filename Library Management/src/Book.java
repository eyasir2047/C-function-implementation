public class Book extends LibraryItem{
    private int isbn;

    public Book(int id, String title, String author, int publicationYear, int isbn) {
        super(id, title, author, publicationYear);
        this.isbn = isbn;
    }

    public int getIsbn() {
        return isbn;
    }
}
