
import java.io.Serializable;
import java.util.GregorianCalendar;

public class PurchaseFromSupplier implements Serializable //Class for the purchasing of a product from a supplier
{
    GregorianCalendar date; //A date field that holds the date of the purchase
    int amountToOrder; //How much of the product has been purchased
    Product productToOrder; //What product is purchased
    Supplier supplierToOrderFrom; //Who is the supplier

    //Empty Constructor
    public PurchaseFromSupplier()
    {
        date = (GregorianCalendar) GregorianCalendar.getInstance();
        amountToOrder =0;
        productToOrder = null;
        supplierToOrderFrom = null;
    }

    //Default constructor for this class. It Initializes the inner fields with the arguments received
    public PurchaseFromSupplier(int amount, Product product, Supplier supplier)
    {
        date = (GregorianCalendar) GregorianCalendar.getInstance();
        amountToOrder=amount;
        productToOrder=product;
        supplierToOrderFrom=supplier;
    }
}
