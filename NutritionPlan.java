//Made by Ryan

/**
 * Calculates and holds the user's daily calorie goal and macronutrient targets
 * based on their weight goal and desired rate of change. Uses data from the User object.
 */
public class NutritionPlan {
    //fixed set of options
    public enum WeightGoal { LOSE, MAINTAIN, GAIN }
    
    //main fields
    private User user;
    private WeightGoal weightGoal;
    private double ratePerWeekLbs; // e.g. 0.5, 1.0
    //Calculated output variables
    private double maintenanceCalories;
    private double dailyCalorieGoal;
    private double fatGoalG;
    private double carbGoalG;
 
    public NutritionPlan(User user, WeightGoal weightGoal, double ratePerWeekLbs) {
        this.user = user;
        this.weightGoal = weightGoal;
        this.ratePerWeekLbs = ratePerWeekLbs;
        calculate();
    }
 
    /**
     * Calculates maintenance calories using the Mifflin-St Jeor equation (moderate activity).
     * Then adjusts for the personal goal. Macros split: protein 30%, fat 25%, carbs 45%.
     */
    private void calculate() {
        double weightKg = user.getBodyWeightLbs() * 0.453592;
        double heightCm = user.getHeightInches() * 2.54;
        double age = user.getAge();
 
        // Mifflin-St Jeor BMR (Calories at rest, using male formula as default; can be extended)
        double bmr = (10 * weightKg) + (6.25 * heightCm) - (5 * age) + 5;
 
        // Moderate activity multiplier to calculate maintenance calories
        maintenanceCalories = bmr * 1.55;
 
        // 3500 calories ≈ 1 lb of fat
        double weeklyCalAdjustment = ratePerWeekLbs * 3500;
        double dailyAdjustment     = weeklyCalAdjustment / 7.0;
 
        switch (weightGoal) {
            case LOSE:     
                 dailyCalorieGoal = maintenanceCalories - dailyAdjustment; 
                 break;
            case GAIN:    
                 dailyCalorieGoal = maintenanceCalories + dailyAdjustment; 
                 break;
            case MAINTAIN: dailyCalorieGoal = maintenanceCalories;                   
                 break;
        }
 
        // Macro breakdown from total calories (25% of calories from fat, 45% from carbs )
        //1 gram of fat = 9 calories, 1 gram of carbs = 4 calories
        fatGoalG  = (dailyCalorieGoal * 0.25) / 9.0;
        carbGoalG = (dailyCalorieGoal * 0.45) / 4.0;
    }
 
    // Getters for maintenance and personalized goals
    public double getMaintenanceCalories() {
        return maintenanceCalories; 
    }
    public double getDailyCalorieGoal() { 
        return dailyCalorieGoal; 
    }
    public double getFatGoalG() { 
        return fatGoalG; 
    }
    public double getCarbGoalG() { 
        return carbGoalG; 
    }
    public WeightGoal getWeightGoal() { 
        return weightGoal; 
    }
    public double getRatePerWeekLbs() { 
        return ratePerWeekLbs; 
    }
    // used to display the Nutrition Plan's contents (WeightGoal, Maintenance, Daily Goal,
    // Fat Goal, and Carb Goal)
    @Override
    public String toString() {
        return String.format(
            "\n--- Nutrition Plan ---\n" +
            "Goal          : %s (%.1f lbs/week)\n" +
            "Maintenance   : %.0f cal/day\n" +
            "Daily Goal    : %.0f cal/day\n" +
            "Fat Goal      : %.1f g\n" +
            "Carb Goal     : %.1f g\n",
            weightGoal, ratePerWeekLbs,
            maintenanceCalories, dailyCalorieGoal,
            fatGoalG, carbGoalG);
    }
}
