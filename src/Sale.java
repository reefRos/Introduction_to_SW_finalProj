import java.util.*;
import javax.swing.*;
import java.io.Serializable;

//Sale Class
public class Sale implements Serializable
{
    GregorianCalendar date; //A field that saves the date of the sale
    private int invoiceNum; //A field that saves the invoice number


    //Empty Constructor
    public Sale()
    {
        date = (GregorianCalendar) GregorianCalendar.getInstance(); //Getting the time of the user's computer
        Random rand = new Random(); //Initialize a random
        int randInvoice = rand.nextInt(1000); //Invoke a random number for the invoice number

        //Searching the sales of the store to see if the number that was invoked already exists, if so it invokes a new number
        for(int i=0;i<Store.productSales.size();i++)
        {
            if(Store.productSales.elementAt(i).sale.getInvoiceNum() == randInvoice)
            {
                System.out.println("Invoice number "+randInvoice+" is taken, generating new one.");
                randInvoice = rand.nextInt(1000);
                i=0;
            }
        }
        setInvoiceNum(randInvoice); //Setting the invoice number
    }


    //Get method for the invoice number
    public int getInvoiceNum()
    {
        return invoiceNum;
    }

    //Set method for the invoice number
    public void setInvoiceNum(int invoiceNum)
    {
        this.invoiceNum = invoiceNum;
    }

    //A method that checks if the supplier works at the time
    boolean updateStock(ProductSale ps,Product prod,Supplier s)
    {
        //If the supplier is working, ordering a new stock and return true
        if(s.checkWorkingHours())
        {
            PurchaseFromSupplier newPurchase = new PurchaseFromSupplier(ps.amount-ps.product.amountInStock, prod, s);
            Store.purchaseProductFromSupplier(newPurchase);
            return true;
        }

        //If the supplier is not working, shows a matching message and return false
        else
        {
            JOptionPane.showMessageDialog(null, "Supplier does not work at this time. Please try again at a different time!");
            return false;
        }
    }

    //toString for sale class
    public String toString()
    {
        return ("Date: "+date.getTime()+"\nInvoice Number: "+getInvoiceNum());
    }

}
