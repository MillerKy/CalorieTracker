//made by Gavin

import java.util.ArrayList;
import java.util.List;

/*
 * Manages a collection of FoodItem objects for a given day.
 * Responsible for adding entries and calculating running totals.
 */
public class MealLog {

    private List<FoodItem> entries;
    private String date;

    public MealLog(String date) {
        this.date    = date;
        this.entries = new ArrayList<>();
    }

    // Adds a food item to the log
    public void addFoodItem(FoodItem item) {
        entries.add(item);
    }

    // Returns all logged food items.
     
    public List<FoodItem> getEntries() {
        return entries;
    }

    public String getDate() { 
    	return date; 
    }

    // Running totals
    public int getTotalCalories() {
        return entries.stream().mapToInt(FoodItem::getCalories).sum();
    }

    public int getTotalProtein() {
        return entries.stream().mapToInt(FoodItem::getProtein).sum();
    }

    public int getTotalFat() {
        return entries.stream().mapToInt(FoodItem::getFat).sum();
    }

    public int getTotalCarbs() {
        return entries.stream().mapToInt(FoodItem::getCarbs).sum();
    }

    // Prints a running breakdown of all meals logged so far.
    public void printLog() {
        System.out.println("\n--- Meal Log for " + date + " ---");
        if (entries.isEmpty()) {
            System.out.println("  No entries logged yet.");
            return;
        }
        for (FoodItem item : entries) {
            System.out.println(item);
        }
        System.out.printf("\nRunning Totals: %d cal | Protein: %dg  Fat: %dg  Carbs: %dg%n",
                getTotalCalories(), getTotalProtein(), getTotalFat(), getTotalCarbs());
    }
}
