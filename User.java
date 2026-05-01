//Made by Ryan

/**
 * Stores personal profile data such as name, age, height, body weight, and body fat percentage.
 */
public class User {
    private String name;
    private int age;
    private double heightInches;
    private double bodyWeightLbs;
    private double bodyFatPercentage;
 
    public User(String name, int age, double heightInches, double bodyWeightLbs, double bodyFatPercentage) {
        this.name = name;
        this.age = age;
        this.heightInches = heightInches;
        this.bodyWeightLbs = bodyWeightLbs;
        this.bodyFatPercentage = bodyFatPercentage;
    }
 
    // Getters
    public String getName()              { return name; }
    public int getAge()                  { return age; }
    public double getHeightInches()      { return heightInches; }
    public double getBodyWeightLbs()     { return bodyWeightLbs; }
    public double getBodyFatPercentage() { return bodyFatPercentage; }
 
    // Setters
    public void setBodyWeightLbs(double bodyWeightLbs) { this.bodyWeightLbs = bodyWeightLbs; }
    public void setBodyFatPercentage(double bfp)       { this.bodyFatPercentage = bfp; }
 
    @Override
    public String toString() {
        return String.format("User: %s | Age: %d | Height: %.1f in | Weight: %.1f lbs | Body Fat: %.1f%%",
                name, age, heightInches, bodyWeightLbs, bodyFatPercentage);
    }
}
 