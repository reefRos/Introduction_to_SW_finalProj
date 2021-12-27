import java.io.Serializable;

public class ProductSale implements Serializable //Class to keep important information about a products' sale
{
    public Sale sale; //Sale field
    Product product; //Product field for which product has been sold
    int amount; //How much of the product has been sold

    //Empty Constructor
    public ProductSale()
    {

    }

    //Default constructor for productSale. It Initializes the inner fields with the arguments received
    public ProductSale(Sale newSale, Product newProduct, int newAmount)
    {
        sale = newSale;
        product = newProduct;
        amount = newAmount;
    }

}
