package edu.psu.se411.lab09_inventory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		logger.info("Application is starting...");

Inventory<Item> inventory = new Inventory<>();

Book book1 = new Book(1, "Concurrency in Java", "John");
Book book2 = new Book(2, "Java Basics", "Ahmed");

ElectronicDevice device1 =
        new ElectronicDevice(3, "Laptop", "Computer");

ElectronicDevice device2 =
        new ElectronicDevice(4, "iPhone", "Smartphone");

inventory.addItem(book1);
inventory.addItem(book2);
inventory.addItem(device1);
inventory.addItem(device2);

System.out.println("All Inventory Items:");

for (Item item : inventory.getAllItems()) {
    System.out.println(item);
}


System.out.println("\nSearch by Name:");
System.out.println(inventory.findItems(
        new SearchByName("Laptop")));

System.out.println("\nSearch by ID:");
System.out.println(inventory.findItems(
        new SearchById(1)));

System.out.println("\nSearch by Author:");
System.out.println(inventory.findItems(
        new SearchByAuthor("John")));

System.out.println("\nSearch by Category:");
System.out.println(inventory.findItems(
        new SearchByCategory("Computer")));

// Search using a lambda expression
System.out.println("\nSearch using Lambda:");
System.out.println(inventory.findItems(
        item -> item.getName().equalsIgnoreCase("iPhone")));

displayInventory(inventory);
		
		logger.info("Application is closing...");
	}
	

public static void displayInventory(Inventory<?> inventory) {

    System.out.println("\nDisplaying Inventory:");

    for (Object item : inventory.getAllItems()) {
        System.out.println(item);
    }
}

}
