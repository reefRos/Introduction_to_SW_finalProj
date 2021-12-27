import javax.swing.*;
import java.io.Serializable;

//Product class
public class Product implements Serializable
{
    private int catalogNum;
    double price;
    int amountInStock;
    String name;
    String description;


    //Empty Constructor
    public Product()
    {

    }

    //Constructor
    public Product(int newCatalogNum, double newPrice, int newAmountInStock, String newName, String newDescription)
    {
        setCatalogNum(newCatalogNum);
        price = newPrice;
        amountInStock = newAmountInStock;
        name = newName;
        description = newDescription;
    }

    //Get method for the catalog number
    public int getCatalogNum()
    {
        return catalogNum;
    }

    //Set method for the catalog number
    public void setCatalogNum(int catalogNum)
    {
        this.catalogNum = catalogNum;
    }

    //Description method for printing the product's description
    void description()
    {
        JOptionPane.showMessageDialog(null, description, "Zoo Store", JOptionPane.PLAIN_MESSAGE);
    }

    //toString method for product class
    public String toString()
    {
        return("Product: "+name+"\nCatalog num: "+getCatalogNum()+"\n");
    }

}
