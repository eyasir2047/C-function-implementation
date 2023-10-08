public interface LibraryUser {
    void borrowItem(LibraryItem item);
    void returnItem(LibraryItem item);

    void viewCheckedOutItems();

}
