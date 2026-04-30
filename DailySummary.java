/**
 * Takes a MealLog and a NutritionPlan and generates a complete end-of-day report,
 * showing total intake versus goals and remaining macros.
 */
public class DailySummary {
 
    private MealLog mealLog;
    private NutritionPlan plan;
 
    public DailySummary(MealLog mealLog, NutritionPlan plan) {
        this.mealLog = mealLog;
        this.plan    = plan;
    }
 
    /** Prints the full daily summary to the console. */
    public void display() {
        double totalCal     = mealLog.getTotalCalories();
        double totalProtein = mealLog.getTotalProteinG();
        double totalFat     = mealLog.getTotalFatG();
        double totalCarbs   = mealLog.getTotalCarbsG();
 
        double calGoal     = plan.getDailyCalorieGoal();
        double proteinGoal = plan.getProteinGoalG();
        double fatGoal     = plan.getFatGoalG();
        double carbGoal    = plan.getCarbGoalG();
 
        double remCal     = calGoal     - totalCal;
        double remProtein = proteinGoal - totalProtein;
        double remFat     = fatGoal     - totalFat;
        double remCarbs   = carbGoal    - totalCarbs;
 
        System.out.println("\n========================================");
        System.out.println("   DAILY SUMMARY — " + mealLog.getDate());
        System.out.println("========================================");
 
        // Meal log
        mealLog.printLog();
 
        // Summary table
        System.out.println("\n----------------------------------------");
        System.out.printf("%-18s %8s %8s %8s%n", "", "CALORIES", "PROTEIN", "FAT", "CARBS");
        System.out.printf("%-18s %8s %8s %8s %8s%n", "", "CALORIES", "PROTEIN(g)", "FAT(g)", "CARBS(g)");
        System.out.println("----------------------------------------");
        System.out.printf("%-18s %8.0f %10.1f %8.1f %8.1f%n",
                "Goal:",     calGoal,    proteinGoal, fatGoal,    carbGoal);
        System.out.printf("%-18s %8.0f %10.1f %8.1f %8.1f%n",
                "Consumed:", totalCal,   totalProtein, totalFat,  totalCarbs);
        System.out.println("----------------------------------------");
        System.out.printf("%-18s %8.0f %10.1f %8.1f %8.1f%n",
                "Remaining:", remCal, remProtein, remFat, remCarbs);
        System.out.println("========================================");
 
        // Status message
        if (remCal < 0) {
            System.out.printf("⚠  You exceeded your calorie goal by %.0f calories.%n", Math.abs(remCal));
        } else if (remCal == 0) {
            System.out.println("✓  You hit your calorie goal exactly!");
        } else {
            System.out.printf("✓  You have %.0f calories remaining for the day.%n", remCal);
        }
        System.out.println("========================================\n");
    }
}