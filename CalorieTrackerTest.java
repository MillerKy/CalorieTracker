//Made by Gavin

import java.util.Scanner;
 
/*
 * Entry point for the Calorie Tracker application.
 * Guides the user through profile setup, nutrition planning, meal logging,
 * and end-of-day summary.
 */
public class CalorieTrackerTest {
 
    private static final Scanner scanner = new Scanner(System.in);
 
    public static void main(String[] args) {
        printBanner();
 
        //Step 1: Build User profile
        User user = buildUserProfile();
        System.out.println("\n" + user);
 
        //Step 2: Build Nutrition Plan
        NutritionPlan plan = buildNutritionPlan(user);
        System.out.println(plan);
 
        //Step 3: Get date for the log
        System.out.print("Enter today's date (e.g. 2025-04-29): ");
        String date = scanner.nextLine().trim();
        MealLog mealLog = new MealLog(date);
 
        //Step 4: Meal logging loop
        mealLoggingLoop(mealLog);
 
        //Step 5: Daily summary
        DailySummary summary = new DailySummary(mealLog, plan);
        summary.display();
    }
    // Profile setup
 
    private static User buildUserProfile() {
        System.out.println("\n=== Step 1: Your Profile ===");
 
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
 
        int age = promptInt("Age: ");
 
        double heightFt  = promptDouble("Height — feet: ");
        double heightIn  = promptDouble("Height — additional inches: ");
        double totalInches = (heightFt * 12) + heightIn;
 
        double weight = promptDouble("Body weight (lbs): ");
        double bf     = promptDouble("Body fat percentage (enter 0 if unknown): ");
 
        return new User(name, age, totalInches, weight, bf);
    }
    // Nutrition plan setup
 
    private static NutritionPlan buildNutritionPlan(User user) {
        System.out.println("\n=== Step 2: Nutrition Plan ===");
        System.out.println("What is your weight goal?");
        System.out.println("  1 = Lose weight");
        System.out.println("  2 = Maintain weight");
        System.out.println("  3 = Gain weight");
 
        int choice = promptInt("Choice (1-3): ");
        NutritionPlan.WeightGoal goal;
        switch (choice) {
            case 1:  goal = NutritionPlan.WeightGoal.LOSE;     break;
            case 3:  goal = NutritionPlan.WeightGoal.GAIN;     break;
            default: goal = NutritionPlan.WeightGoal.MAINTAIN; break;
        }
 
        double rate = 0;
        if (goal != NutritionPlan.WeightGoal.MAINTAIN) {
            System.out.println("At what rate? (e.g. 0.5 = 0.5 lbs/week, 1.0 = 1 lb/week)");
            rate = promptDouble("Rate (lbs/week): ");
        }
 
        return new NutritionPlan(user, goal, rate);
    }
    // Meal logging
 
    private static void mealLoggingLoop(MealLog mealLog) {
        System.out.println("\n=== Step 3: Log Your Meals ===");
        System.out.println("Commands: [a]dd food | [v]iew log | [d]one for the day\n");
 
        while (true) {
            System.out.print("Command: ");
            String cmd = scanner.nextLine().trim().toLowerCase();
 
            switch (cmd) {
                case "a":
                    addFoodItem(mealLog);
                    break;
                case "v":
                    mealLog.printLog();
                    break;
                case "d":
                    System.out.println("Finishing up your log...");
                    return;
                default:
                    System.out.println("Unknown command. Enter 'a', 'v', or 'd'.");
            }
        }
    }
 
    private static void addFoodItem(MealLog mealLog) {
        System.out.print("Food name: ");
        String name = scanner.nextLine().trim();
 
        System.out.println("Meal type: 1=Breakfast  2=Lunch  3=Dinner  4=Snack");
        int mealChoice = promptInt("Choice (1-4): ");
        String mealType;
        switch (mealChoice) {
            case 1:  mealType = "breakfast"; break;
            case 2:  mealType = "lunch";     break;
            case 3:  mealType = "dinner";    break;
            default: mealType = "snack";     break;
        }
 
        int calories = promptInt("Calories: ");
        int protein  = promptInt("Protein (g): ");
        int fat      = promptInt("Fat (g): ");
        int carbs    = promptInt("Carbs (g): ");
 
        FoodItem item = new FoodItem(name, mealType, calories, protein, fat, carbs);
        mealLog.addFoodItem(item);
        System.out.println("Added: " + item);
    }
    // Helpers
 
    private static int promptInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }
 
    private static double promptDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
 
    private static void printBanner() {
        System.out.println("========================================");
        System.out.println("|        CALORIE TRACKER v1.0          |");
        System.out.println("|  Ryan Wildes, Kyle Miller,           |");
        System.out.println("|  Gavin Lawrence                      |");
        System.out.println("========================================");
    }
}
 