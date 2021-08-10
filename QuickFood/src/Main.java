import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Main
{
    //main method
    public static void main(String[] args)
    {
        try
        {
            //Connecting to my database QuickFoodMS
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost;database=QuickFoodMS",
                    "SA",
                    "MyPass@word"
            );

            //Values of the objects
            //values here will be inserted into the table
            Customer buyer = new Customer(1233, "Jack", "023456346", "Cape Town", "jilljack@yahoo.com");
            Restaurant seller = new Restaurant("sellersPizza", "Cape Town", "0987654321", 1, "Pepperoni", 60, "cheese", 243, "John", "Cape Town", "12 Cherry Road", 2);


            // Create a direct line to the database for running our queries
            Statement statement = connection.createStatement();
            int rowsAffected;


            try
            {
                //add at least 2 rows of data to Customer table
                statement.executeUpdate("INSERT INTO QuickFoodMS.quickFoodDetails.Customer VALUES (1365, 'Robert Truter', '0798921456', 'Cape Town', 'rob@gmail.com'); ");
                printAllFromTable(statement);

                statement.executeUpdate("INSERT INTO QuickFoodMS.quickFoodDetails.Customer VALUES (1366, 'Jill Jack', '0890256787', 'Cape Town', 'Jilly@gmail.com'); ");
                printAllFromTable(statement);
            }
            catch (SQLException e)
            {
                System.out.println("error");
            }


            try
            {
                //add at least 2 rows of data to Restaurant table
                statement.executeUpdate("INSERT INTO QuickFoodMS.quickFoodDetails.Restaurant VALUES ('Romans', 'Durban', '0896785432', 2, 'Pineapple Pizza', 100, 'none', 100, 'Stephanus, Durban', 'Pine road', 1 );");
                printAllFromTable(statement);

                statement.executeUpdate("INSERT INTO QuickFoodMS.quickFoodDetails.Restaurant VALUES ('Romans', 'Durban', '0896785432', 2, 'Pineapple Pizza', 100, 'none', 100, 'Stephanus, Durban', 'Pine road', 1 );");
                printAllFromTable(statement);
            }
            catch (SQLException e)
            {
                System.out.println("error");
            }


            //Adding a driver load to table if matched with customer
            try
            {
                if(seller.driverLocation == seller.restaurantLocation)
                {
                   statement.executeUpdate("UPDATE QuickFoodMS.quickFoodDetails.Restaurant SET driverLoads + 1 ");
                   printAllFromTable(statement);
                }
            }
            catch (SQLException e)
            {
                System.out.println("Could not update drivers table");
            }


            try {
                //generating an invoice based on values of objects above
                String sql = "insert into QuickFoodMS.quickFoodDetails.invoice "
                        + " (contactNumber, totalAmountPaid)" + " values (?,?)";
                PreparedStatement slct = connection.prepareStatement(sql);

                slct.setInt(1, Integer.parseInt(buyer.contactNumber));
                slct.setString(2, String.valueOf(seller.totalAmountPaid));

                rowsAffected = slct.executeUpdate();
                System.out.println("Finalised: " + rowsAffected + " rows added, ");
                printAllFromTable(statement);

            }
            catch (SQLException e)
            {
                System.out.println("error");
            }


            try {
                //uploading buyer values above to Customer table
                String sql = "insert into QuickFoodMS.quickFoodDetails.Customer"
                        + "(orderNumber, customerName, contactNumber, customerLocation, customerEmail)" + "values(?,?,?,?,?)";
                PreparedStatement slct = connection.prepareStatement(sql);

                slct.setInt(1, buyer.orderNumber);
                slct.setString(2, buyer.customerName);
                slct.setString(3, buyer.contactNumber);
                slct.setString(4, buyer.customerLocation);
                slct.setString(5, buyer.customerEmail);

                rowsAffected = slct.executeUpdate();
                System.out.println(rowsAffected + " rows added, ");
                printAllFromTable(statement);
            }
            catch (SQLException e)
            {
                System.out.println("error");
            }


            try
            {
                //Uploading seller values above to Restaurant table
                String sql = "insert into QuickFoodMS.quickFoodDetails.Restaurant "
                        + " (nameOfRestaurant, restaurantLocation, restaurantContactNumber, numberOfEachMeal, listOfOrderedMeals, mealPrices, specialInstructions, totalAmountPaid, driverName, driverLocation, customerAddress, driverLoads)"
                        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement slct = connection.prepareStatement(sql);

                slct.setString(1, seller.nameOfRestaurant);
                slct.setString(2, seller.restaurantLocation);
                slct.setString(3, seller.restaurantContactNumber);
                slct.setInt(4, seller.numberOfEachMeal);
                slct.setString(5, seller.listOfOrderedMeals);
                slct.setInt(6, seller.mealPrices);
                slct.setString(7, seller.specialInstructions);
                slct.setInt(8, seller.totalAmountPaid);
                slct.setString(9, seller.driverName);
                slct.setString(10, seller.driverLocation);
                slct.setString(11, seller.customerAddress);
                slct.setInt(12, seller.driverLoads);

                rowsAffected = slct.executeUpdate();
                System.out.println(rowsAffected + " rows added, ");
                printAllFromTable(statement);
            }
            catch (SQLException e)
            {
                System.out.println("error");
            }


            //Find and select an order by entering the orderNumber
            System.out.println("Enter the orderNumber you want to select: ");
            Scanner sc = new Scanner(System.in);
            int orderNumber = sc.nextInt();

            try (PreparedStatement psDel3 = connection.prepareStatement("SELECT orderNumber,customerName,contactNumber,customerLocation, customerEmail FROM QuickFoodMS.quickFoodDetails.Customer WHERE orderNumber = ?"))
            {

                psDel3.setInt(1, orderNumber);
                ResultSet res = psDel3.executeQuery();

                while(res.next()){
                    System.out.println(res.getInt( "orderNumber" ) + ", "
                            + res.getString( "customerName" ) + ", "
                            + res.getString( "contactNumber" ) + ", "
                            + res.getString( "customerLocation" )
                            + res.getString( "customerEmail"));
                }
            }

            statement.close();
            connection.close();
        }

        catch(SQLException e)
        {
            System.out.println("Entered invalid orderNumber.");
            e.printStackTrace();
        }


    }


    public static void printAllFromTable (Statement statement) throws
            SQLException {
        ResultSet results = statement.executeQuery( "SELECT contactNumber, totalAmountPaid FROM QuickFoodMS.quickFoodDetails.invoice" );

        while (results.next())
        {
            System.out.println(results.getInt("contactNumber") + ", "
                    + results.getString("totalAmountPaid") + ", "
            );
        }


        ResultSet results2 = statement.executeQuery( "SELECT orderNumber, customerName, contactNumber, customerLocation, customerEmail FROM QuickFoodMS.quickFoodDetails.Customer");
        while (results2.next())
        {
            System.out.println(results2.getString("orderNumber") + ", "
                        + results2.getString("customerName") + ", "
                    + results2.getString("contactNumber") + ", "
            + results2.getString("customerLocation")
            + results2.getString("customerEmail")
            );
        }

        ResultSet results3 = statement.executeQuery( "SELECT nameOfRestaurant, restaurantLocation, restaurantContactNumber, numberOfEachMeal, listOfOrderedMeals, mealPrices, specialInstructions, totalAmountPaid, driverName, driverLocation, customerAddress, driverLoads FROM QuickFoodMS.quickFoodDetails.Restaurant ");
        while(results3.next())
        {
            System.out.println(results3.getString("nameOfRestaurant") + ","
            + results3.getString("restaurantLocation") + ","
            + results3.getString("restaurantContactNumber") + ","
            + results3.getString("numberOfEachMeal") + ","
            + results3.getString("listOfOrderedMeals") + ","
            + results3.getString("mealPrices") + ","
            + results3.getString("specialInstructions"+ ","
            + results3.getString("totalAmountPaid") + ","
            + results3.getString("driverName")+ ","
            + results3.getString("driverLocation") + ","
            + results3.getString("customerAddress")+ ","
            + results3.getString("driverLoads"))
            );

        }
        results.close();

    }


}


