//Made by Gavin

/*
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
 
    // Prints the full daily summary to the console
    public void display() {
        int totalCal = mealLog.getTotalCalories();
        int totalProtein = mealLog.getTotalProtein();
        int totalFat = mealLog.getTotalFat();
        int totalCarbs = mealLog.getTotalCarbs();
 
        double calGoal = plan.getDailyCalorieGoal();
        double proteinGoal = plan.getProteinGoalG();
        double fatGoal = plan.getFatGoalG();
        double carbGoal = plan.getCarbGoalG();
 
        System.out.println("\n========================================");
        System.out.println("   DAILY SUMMARY — " + mealLog.getDate());
        System.out.println("========================================");
 
        mealLog.printLog();

        System.out.println("\n----------------------------------------");
        System.out.printf("%-18s %8s %10s %8s %8s%n", "", "CALORIES", "PROTEIN(g)", "FAT(g)", "CARBS(g)");
        System.out.println("----------------------------------------");
        System.out.printf("%-18s %8.0f %10.1f %8.1f %8.1f%n", "Goal:",      calGoal,  proteinGoal, fatGoal,  carbGoal);
        System.out.printf("%-18s %8d %10d %8d %8d%n",          "Consumed:",  totalCal, totalProtein, totalFat, totalCarbs);
        System.out.println("----------------------------------------");
        System.out.printf("%-18s %8.0f %10.1f %8.1f %8.1f%n", "Remaining:", calGoal - totalCal, proteinGoal - totalProtein, fatGoal - totalFat, carbGoal - totalCarbs);
        System.out.println("========================================");
 
        double remCal = calGoal - totalCal;
        if (remCal < 0) {
            System.out.printf("You exceeded your calorie goal by %.0f calories.%n", Math.abs(remCal));
        } else if (remCal == 0) {
            System.out.println("You hit your calorie goal exactly!");
        } else {
            System.out.printf("You have %.0f calories remaining for the day.%n", remCal);
        }
        System.out.println("========================================\n");
    }
}
    }
}
