public class Customer
{
    //Customer objects
    int orderNumber;
    String customerName;
    String contactNumber;
    String customerLocation;
    String customerEmail;


    //Creating constructor method
    public Customer(int orderNumber, String customerName, String contactNumber, String customerLocation, String customerEmail)
    {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.contactNumber = contactNumber;
        this.customerLocation = customerLocation;
        this.customerEmail = customerEmail;
    }

    //Creating toString method to print in main method
    public String toString()
    {
        String output = "Order number: " + orderNumber;
        output += "\nCustomer: " + customerName;
        output += "\nEmail: " + customerEmail;
        output += "\nPhone number: " + contactNumber;
        output += "\nLocation: " + customerLocation + "\n";

        return output;
    }

}
