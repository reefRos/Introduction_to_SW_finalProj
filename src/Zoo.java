import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.awt.event.WindowListener;


//Zoo class holds the entire system.
public class Zoo extends WindowAdapter implements WindowListener
{
    static Vector<Employee> zooEmployees = new Vector<>(); //Vector that holds all of the employees in the zoo
    static Vector<Department> departments = new Vector<>(); //Vector that holds all of the departments in the zoo
    JButton storeButton, employeeButton, enterButton, cancelButton; //Buttons for the zoo system
    JTextField empNameField, empNumField;
    final ButtonHandler handler = new ButtonHandler(); //Initialize zoo's button handler
    JFrame empFrame; //Frame for the employee system
    private static final int MAX_THREADS = 3; //Constant for number of max threads


    //Default constructor for zoo class
    public Zoo() throws IOException, ClassNotFoundException
    {
        JFrame zooFrame = new JFrame("Zoo App"); //Initializing JFrame to hold zoo system
        zooFrame.setSize(500, 400);
        zooFrame.setLocationRelativeTo(null);
        zooFrame.setResizable(false);
        zooFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Using the WindowAdapter interface we can read/write files when the system closes/opens
        zooFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e)  //Method which activates when the main window closes and writes the data to a file
            {
                writeToFile("employees.dat",zooEmployees); //Write the employee vector to a file
                writeToFile("departments.dat",departments); //Write departments vector to a file
                writeToFile("productSales.dat",Store.productSales); //Write productSale vector to a file
                writeToFile("products.dat",Store.products); //Write the product vector to a file
                JOptionPane.showMessageDialog(null,"Bye Bye");
                super.windowClosing(e); //Calls the method from WindowListener to close the frame
            }

            @Override
            public void windowOpened(WindowEvent e) //Method which activates when the user opens the system
            {
                try
                {
                    loadFromFile("employees.dat"); //Read the employee file
                    loadFromFile("departments.dat"); //Read the department file
                    loadFromFile("products.dat"); //Read the product file
                    loadFromFile("productSales.dat"); //Read the productSale file
                } catch (IOException ex) //Catch block that catches any exception arising from loadFromFile
                {
                    String exceptionMsg = "java.io.FileNotFoundException: products.dat (The system cannot find the file specified)";
                    JOptionPane.showMessageDialog(null,"An error has occurred while loading up files"); //If the files are empty/none existing use a default template to create the vectors
                    if (departments.isEmpty()) //The department vector depends on employee file and department file. So if either the employee file or department file is empty, we just need to use the default template for departments to create employee vector and department vector respectfully
                        initDefaultDepartments();

                    if (Store.products.isEmpty()) //If the product vector is empty and products file is empty, then use a default template for the products
                        Store.initStore();
                } catch (ClassNotFoundException ex)
                {
                    JOptionPane.showMessageDialog(null,"An error has occurred while loading up files");
                }
            }

        });


        JPanel zooPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8)); //JPanel to hold the zoo gui
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10)); //JPanel to hold to buttons for zoo
        final Icon zooImage = new ImageIcon("rsz_zooimage.jpg"); //Image for main menu
        JLabel zooImageLabel = new JLabel(zooImage);
        JLabel zooLabel = new JLabel("Welcome to the zoo!"); //JLabel for main menu
        storeButton = new JButton("Enter Store"); //Initializing the systems main buttons and adding them to the correct panel
        employeeButton = new JButton("Employee's System");
        storeButton.addActionListener(handler);
        employeeButton.addActionListener(handler);
        buttonPanel.add(storeButton);
        buttonPanel.add(employeeButton);

        zooPanel.add(zooLabel, BorderLayout.NORTH);
        zooPanel.add(zooImageLabel, BorderLayout.CENTER);
        zooPanel.add(buttonPanel, BorderLayout.SOUTH);

        zooFrame.add(zooPanel);
        zooFrame.setVisible(true);
    }


    //Method which uses threads to "feed" the animals of a certain department
    static void feeding(Department dep)
    {
        ExecutorService es = Executors.newFixedThreadPool(MAX_THREADS); //Initialize ExecutorService

        //Loop to announce what animals have entered the feeding station
        for(int i=0;i<dep.animalsInDep.size();i++)
        {
            System.out.println(dep.animalsInDep.elementAt(i).getAnimalName()+" is entering the feeding station");
            es.execute(dep.animalsInDep.elementAt(i));
        }
        System.out.println("All the animals are in the feeding stations");
        es.shutdown(); //Shut down Executor service

        try{
            es.awaitTermination(100, TimeUnit.SECONDS);
            System.out.println("*** After awaitTermination");
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("*** FINISHED ***");
    }


    //Method which initializes the employee login system. It uses the textfields to move information to employeeSystem method in employee class
    void employeeLogIn()
    {
        empFrame = new JFrame("Employee's System"); //Initialize the JFrame that holds the login system
        empFrame.setSize(300, 175);
        empFrame.setLocationRelativeTo(null);
        empFrame.setResizable(false);
        JPanel empPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 7, 10)); //Initialize the JPanel to hold the gui components of the system

        //Initialize GUI components for the system and add them to the corresponding panel
        JLabel empLabel = new JLabel("Enter your name and employee's number:");
        JLabel empNameLabel = new JLabel("Name");
        JLabel empNumLabel = new JLabel("Employee Number");
        empNameField = new JTextField(23);
        empNumField = new JTextField(15);
        enterButton = new JButton("Enter");
        cancelButton = new JButton("Cancel");
        enterButton.addActionListener(handler);
        cancelButton.addActionListener(handler);

        empPanel.add(empLabel);
        empPanel.add(empNameLabel);
        empPanel.add(empNameField);
        empPanel.add(empNumLabel);
        empPanel.add(empNumField);
        empPanel.add(enterButton);
        empPanel.add(cancelButton);

        empFrame.add(empPanel);
        empFrame.setVisible(true);
    }


    //Method that receives 2 arguments: the file's name and the vector that is to be written
    void writeToFile(String fileName, Vector vector) {
        try {
            FileOutputStream file = new FileOutputStream(fileName); //Write to this specific file. if file doesnt exist, then create one.
            ObjectOutputStream output = new ObjectOutputStream(file); //initializing the output stream
            output.writeObject(vector); //This line writes the data to the binary file
            output.flush();
            output.close(); //Close file stream
            file.close(); //closes the file
        } catch (IOException e) {
            e.printStackTrace(); //prints the throwable along with other details like the line number and class name where the exception occurred.
        }
    }


    //Method that receives a file's name and reads that file to a corresponding vector. if the file is not found then it reads a default template
    void loadFromFile(String fileName) throws IOException, ClassNotFoundException
    {
        File file = new File(fileName); //Open a binary file which holds data about certain clubbers
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName)); //initializing the input stream
        if (file.length() != 0) { //If the file isn't empty then use the file name and read the right file


            if (fileName.equals("employees.dat"))
                zooEmployees = (Vector<Employee>) input.readObject();

            if (fileName.equals("departments.dat"))
                departments = (Vector<Department>) input.readObject();

            if (fileName.equals("productSales.dat"))
                Store.productSales = (Vector<ProductSale>) input.readObject();

            if (fileName.equals("products.dat"))
                Store.products = (Vector<Product>) input.readObject();

            input.close(); //Close file stream
        } else {
            JOptionPane.showMessageDialog(new JFrame(), fileName + " is empty!... Initializing default vector!"); //Show a message if the file is empty/not existing
        }
    }


    //Method that initializes a default template for department vector and adds them to the main department vector
    public void initDefaultDepartments()
    {
        Department store = new Department("Store");
        Department reptiles = new Department("Reptiles");
        Department birds = new Department("Birds");
        Department predators = new Department("Predators");
        Department monkeys = new Department("Monkeys");
        Department seaAnimals = new Department("Sea Animals");
        departments.add(store);
        departments.add(birds);
        departments.add(reptiles);
        departments.add(predators);
        departments.add(monkeys);
        departments.add(seaAnimals);
    }


    //Method that searches an employee by name and their employee's number
    Employee employeeSearch()
    {
        String nameRegex = "[A-Za-z]*"; //Regular expressions that make sure the right statement is entered
        String numRegex = "[0-9]+";
        int i;

        if (!Pattern.matches(nameRegex, empNameField.getText()) || empNameField.getText().isEmpty() || !Pattern.matches(numRegex,empNumField.getText())) //If the statement entered doesnt follow the regex, then pop a message and try again
        {
            JOptionPane.showMessageDialog(null, "An invalid input has been detected... Please enter a valid text!", "Null input", JOptionPane.INFORMATION_MESSAGE);
        }

        //If the statement is ok then start searching the employee in the employee vector and return it
        else
        {
            for (i=0; i<zooEmployees.size(); i++)
            {
                if ((empNameField.getText().equals(zooEmployees.elementAt(i).getName())) && (Integer.parseInt(empNumField.getText()) == zooEmployees.elementAt(i).getEmployeeNum())) {
                    return zooEmployees.elementAt(i);
                }
            }
            if (i == zooEmployees.size()) { //If the employee couldnt be found then pop a message
                JOptionPane.showMessageDialog(null, "This employee does not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }


    //Button handler for zoo class
    public class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //if store button is pressed then open the store
            if (e.getSource() == storeButton)
            {
                Department storeDep = new Department("Store");
            }

            //If employee button is pressed then open the employee login system
            if (e.getSource() == employeeButton)
            {
                employeeLogIn();
            }

            //If enter button is pressed then commit the employee system method for the wanted employee
            if (e.getSource() == enterButton)
            {
                if (employeeSearch() != null)
                {
                    employeeSearch().employeeSystem();
                }
            }

            //If cancel button is pressed then hide the employee login system frame
            if (e.getSource() == cancelButton)
            {
                empFrame.setVisible(false);
            }
        }
    }
}