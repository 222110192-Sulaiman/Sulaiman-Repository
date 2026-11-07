
package edu.psu.se411.lab09_inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventory<T> {

    private List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public void removeItem(T item) {
        items.remove(item);
    }

    public List<T> getAllItems() {
        return items;
    }

public List<T> findItems(SearchStrategy<T> strategy) {
    List<T> results = new ArrayList<>();

    for (T item : items) {
        if (strategy.matches(item)) {
            results.add(item);
        }
    }

    return results;
}

}