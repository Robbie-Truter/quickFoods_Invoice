public class Restaurant
{

    //Restaurant objects
    String nameOfRestaurant;
    String restaurantLocation;
    String restaurantContactNumber;
    int numberOfEachMeal;
    String listOfOrderedMeals;
    int mealPrices;
    String specialInstructions;
    int totalAmountPaid;
    String driverName;
    String driverLocation;
    String customerAddress;
    int driverLoads;

    //Creating constructor method
    public Restaurant(String nameOfRestaurant, String restaurantLocation, String restaurantContactNumber, int numberOfEachMeal, String listOfOrderedMeals, int mealPrices, String specialInstructions, int totalAmountPaid, String driverName, String driverLocation, String customerAddress, int driverLoads) {
        this.nameOfRestaurant = nameOfRestaurant;
        this.restaurantLocation = restaurantLocation;
        this.restaurantContactNumber = restaurantContactNumber;
        this.numberOfEachMeal = numberOfEachMeal;
        this.listOfOrderedMeals = listOfOrderedMeals;
        this.mealPrices = mealPrices;
        this.specialInstructions = specialInstructions;
        this.totalAmountPaid = totalAmountPaid;
        this.driverName = driverName;
        this.driverLocation = driverLocation;
        this.customerAddress = customerAddress;
        this.driverLoads = driverLoads;
    }

    //Creating toString method to print in main method
    public String toString()
    {
        String output = "\nYou have ordered the following from " + nameOfRestaurant + " in " + restaurantLocation + ":\n";
        output += "\n" + numberOfEachMeal + " x " + listOfOrderedMeals + "(" + "R" + mealPrices + ")\n";
        output += "\nSpecial instructions: " + specialInstructions + "\n";
        output += "\nTotal: " + "R" + totalAmountPaid + "\n";

        output += "\n" + driverName + " is nearest to the restaurant and so he will be delivering your \norder to you at:\n " ;
        output += "\n" + customerAddress + "\n";
        output += "\nIf you need to contact the restaurant, their number is " + restaurantContactNumber;
        //output += "\n" + driverLoads;

        return output;
    }

}
