
package edu.psu.se411.lab09_inventory;

public class SearchByAuthor implements SearchStrategy<Item> {

    private String author;

    public SearchByAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean matches(Item item) {
        if (item instanceof Book) {
            Book book = (Book) item;
            return book.getAuthor().equalsIgnoreCase(author);
        }
        return false;
    }
}
