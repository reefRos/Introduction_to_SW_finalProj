import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Vector;
import java.util.regex.*;
import javax.swing.*;

public class Store extends JFrame implements Serializable
{
    static Vector<Product> products = new Vector<>();
    static Vector<PurchaseFromSupplier> purchaseFromSuppliers = new Vector<>();
    static Vector<ProductSale> productSales = new Vector<>();
    static JComboBox<String> productsComboBox;
    static String[] productsBox = {"Animals Stickers", "Lion Doll", "Penguin Doll", "Animals Book", "Coloring Book","Giraffe Bracelet Stand","Whale Bedclothes","Meerkat Snowball"};
    JButton buyButton, cancelBuyButton, descriptionButton;
    JTextField quantityField;
    JPanel storePanel;
    static Supplier s = new Supplier("Studio Oh", "USA", 911234); //Initialize the only supplier of the store

    //Default constructor
    public Store()
    {
        //Initialize the frame
        setTitle("Zoo Store");
        setSize(420,180);
        setLocationRelativeTo(null);
        setResizable(false);
        final ButtonHandler handler = new ButtonHandler(); //Initialize a button handler

        storePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 7, 15)); //Initialize the store panel

        JLabel sl = new JLabel("Which product would you like to buy?"); //Initialize label
        productsComboBox = new JComboBox(productsBox); //Initialize combobox with the store's products

        JLabel bl = new JLabel("Quantity: "); //Initialize label
        quantityField = new JTextField(30); //Initialize text fields to get the quantity for the buy

        //Initialize buttons and adding action listeners
        buyButton = new JButton("Buy");
        cancelBuyButton = new JButton("Cancel");
        descriptionButton = new JButton("Description");
        buyButton.addActionListener(handler);
        cancelBuyButton.addActionListener(handler);
        descriptionButton.addActionListener(handler);

        //Adding the components to the panel and the panel to the frame
        storePanel.add(sl);
        storePanel.add(productsComboBox);
        storePanel.add(bl);
        storePanel.add(quantityField);
        storePanel.add(buyButton);
        storePanel.add(cancelBuyButton);
        storePanel.add(descriptionButton);
        add(storePanel);
    }

    //Initialize the products in the store in case the products' file was not found
    static void initStore()
    {
        //Initialize products
        Product p1 = new Product(123, 7.5, 2, "Animals Stickers","Fun and colorful animals stickers");
        Product p2 = new Product(234, 50.0, 0, "Lion Doll","A fluffy stuffed lion doll");
        Product p3 = new Product(345, 60.0, 15, "Penguin Doll","A fluffy stuffed penguin doll");
        Product p4 = new Product(920,40.0,20,"Animals Book","A book with information and fun facts about animals");
        Product p5 = new Product(829,35.90,12,"Coloring Book","A book with black and white drawings of animals for kids to color");
        Product p6 = new Product(420,88.0,30,"Giraffe Bracelet Stand","A stand that looks like a giraffe's neck to put bracelets on it");
        Product p7 = new Product(932,150.90,5,"Whale Bedclothes","Bedclothes with whales prints on it for fun and peaceful sleep");
        Product p8 = new Product(330,36.90,50,"Meerkat Snowball","A little glass snowball with meerkats inside. Flip it to see the magic!");

        //Adding the products to the vector
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);
        products.add(p6);
        products.add(p7);
        products.add(p8);
    }

    //A method for selling products
    void sellProducts(ProductSale ps)
    {
        int productID = ps.product.getCatalogNum(); //Getting the product's catalog number
        int i;

        //Searching for the product
        for (i = 0; i < products.size(); i++) {
            if (products.elementAt(i).getCatalogNum() == productID)
            {
                //If the stock is empty
                if (products.elementAt(i).amountInStock == 0)
                {
                    //Checking if the user wants the store to order a new stock from the supplier
                    int reply = JOptionPane.showConfirmDialog(null, "No existing stock of " + products.elementAt(i).name + " \nWould you like us to order new stock?", "Zoo Store", JOptionPane.YES_NO_OPTION);

                    //If the user reply yes
                    if (reply == JOptionPane.YES_OPTION)
                    {
                        //Activate the update stock method from Sale class. If the supplier doesn't work at the time, cancel the sale
                        if(!(ps.sale.updateStock(ps, products.elementAt(i),s)))
                        {
                            ps.sale.setInvoiceNum(0); //Setting the invoice number to 0
                            break;
                        }
                    }

                    //If the user reply no
                    else
                    {
                        ps.sale.setInvoiceNum(0); //Setting the invoice to 0 and cancel sale
                        break;
                    }
                }

                //If there's enough quantity in stock
                if (ps.amount <= products.elementAt(i).amountInStock)
                {
                    productSales.add(ps); //Adding the sale to the vector
                    products.elementAt(i).amountInStock -= ps.amount; //Update the product's stock
                    JOptionPane.showMessageDialog(null, ps.sale.toString() + "\n"+ps.product.toString()+"Quantity: "+ps.amount, "Zoo Store", JOptionPane.PLAIN_MESSAGE); //Showing the user the invoice
                    break;
                }

                //If there's stock but not enough for the sale
                if (ps.amount > products.elementAt(i).amountInStock)
                {
                    //Checking if the user still wants to buy the existing stock
                    int reply = JOptionPane.showConfirmDialog(null, "Only " + products.elementAt(i).amountInStock + " units in stock.\nWould you like to purchase the existing stock?", "Zoo Store", JOptionPane.YES_NO_OPTION);

                    //If the user reply yes
                    if (reply == JOptionPane.YES_OPTION)
                    {
                        //Checking if the user wants the store to order a new stock from the supplier
                        int reply1 = JOptionPane.showConfirmDialog(null, "Would you like us to order the rest of the amount you wanted?", "Zoo Store", JOptionPane.YES_NO_OPTION);

                        //If the user reply yes
                        if (reply1 == JOptionPane.YES_OPTION)
                        {
                            //Activate the update stock method from Sale class. If the supplier doesn't work at the time, only sell the existing stock
                            if(ps.sale.updateStock(ps, products.elementAt(i),s))
                            {
                                products.elementAt(i).amountInStock -= ps.amount; //Update the product's stock
                                JOptionPane.showMessageDialog(null, ps.sale.toString() + "\n"+ps.product.toString()+"Quantity: "+ps.amount, "Zoo Store", JOptionPane.PLAIN_MESSAGE);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, ps.sale.toString() + "\n"+ps.product.toString()+"Quantity: "+products.elementAt(i).amountInStock, "Zoo Store", JOptionPane.PLAIN_MESSAGE);
                                ps.amount = products.elementAt(i).amountInStock; //Update the product's stock
                            }
                        }

                        //If the user reply no
                        else
                        {
                            JOptionPane.showMessageDialog(null, ps.sale.toString() + "\n"+ps.product.toString()+"Quantity: "+products.elementAt(i).amountInStock, "Zoo Store", JOptionPane.PLAIN_MESSAGE);
                            ps.amount = products.elementAt(i).amountInStock; //Update the product's stock
                        }

                        productSales.add(ps); //Adding the sale to the vector
                        products.elementAt(i).amountInStock = 0; //Setting the stock to 0
                        break;
                    }

                    //If the user reply no
                    else
                    {
                        ps.sale.setInvoiceNum(0); //Setting the invoice number to 0 and cancel the sale
                        break;
                    }
                }

                //Cancel the sale
                else
                    break;
            }
        }
    }


    //A method for purchasing stock from the supplier
    static void purchaseProductFromSupplier(PurchaseFromSupplier ppfs)
    {
        int productID = ppfs.productToOrder.getCatalogNum(); //Getting the product's catalog number
        int i;

        //Searching the product and updating it's stock
        for(i=0;i<products.size();i++)
        {
            if(products.elementAt(i).getCatalogNum()==productID)
            {
                products.elementAt(i).amountInStock+=ppfs.amountToOrder;
                break;
            }
        }
        purchaseFromSuppliers.add(ppfs); //Adding the purchase to the vector
    }


    //Sub class for button handler
    public class ButtonHandler implements ActionListener, Serializable
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //If the buy button was pressed start the selling process
            if(e.getSource() == buyButton)
            {
                String input = productsComboBox.getSelectedItem().toString(); //Getting the name of the product
                String numRegex = "[0-9]+"; //Setting a regex for the quantity field

                //If an invalid input was entered, show a matching message
                if(!Pattern.matches(numRegex,quantityField.getText()))
                {
                    JOptionPane.showMessageDialog(null,"An invalid input has been detected... please enter valid text!","Null input",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                Sale newSale = new Sale(); //Initialize a new sale
                int i;

                //Searching for the product and activate the sell products method
                for(i=0;i<products.size();i++)
                {
                    if(products.elementAt(i).name.equals(input))
                    {
                        ProductSale ps = new ProductSale(newSale, products.elementAt(i), Integer.parseInt(quantityField.getText())); //Initialize a new product sale
                        sellProducts(ps); //Activate the sell products method
                    }
                }
            }

            //If the cancel buy button was pressed, close the frame
            if(e.getSource() == cancelBuyButton)
            {
                setVisible(false);
            }

            //If the description button was pressed activate the description method
            if(e.getSource() == descriptionButton)
            {
                String input = productsComboBox.getSelectedItem().toString(); //Getting the product from the combobox

                //Searching for the product
                for(int i=0;i<products.size();i++)
                {
                    if(products.elementAt(i).name.equals(input))
                    {
                        products.elementAt(i).description(); //Activate the description method
                    }
                }
            }
        }
    }
}
