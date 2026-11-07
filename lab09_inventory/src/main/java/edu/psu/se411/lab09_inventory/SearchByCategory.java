
package edu.psu.se411.lab09_inventory;

public class SearchByCategory implements SearchStrategy<Item> {

    private String category;

    public SearchByCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean matches(Item item) {
        if (item instanceof ElectronicDevice) {
            ElectronicDevice device = (ElectronicDevice) item;
            return device.getCategory().equalsIgnoreCase(category);
        }
        return false;
    }
}
