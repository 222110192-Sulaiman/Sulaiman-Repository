
package edu.psu.se411.lab09_inventory;

public class Book extends Item {

    private String author;

    public Book(int id, String name, String author) {
        super(id, name);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return super.toString() + ", Author: " + author;
    }
}
