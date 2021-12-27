import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.regex.*;

public class Employee implements Serializable
{
    private String name;
    int seniority;
    private int employeeNum;
    int salary;
    Department worksInDep;
    private JButton depAniButton, feedButton, cancelButton, bonusButton, mateButton, okMateButton, watchSalesButton;
    private ButtonHandler handler1;
    public JTextField nameField1,nameField2,offspringField,animalNumField;
    JFrame mateFrame;

    //Empty Constructor
    public Employee()
    {
        setName(null);
        setEmployeeNum(0);
        salary = 0;
        seniority = 0;
    }

    //Constructor
    public Employee(String newName, int newEmployeeNum, int newSalary, Department newWorksInDep, int newSeniority)
    {
        setName(newName);
        setEmployeeNum(newEmployeeNum);
        salary = newSalary;
        worksInDep = newWorksInDep;
        seniority = newSeniority;
    }

    //Set method for setting an employee number
    public void setEmployeeNum(int employeeNum){
        this.employeeNum = employeeNum;
    }

    //Get method for getting an employee number
    public int getEmployeeNum(){
        return employeeNum;
    }

    //Set method for setting the employee's name
    public void setName(String name) {
        this.name = name;
    }

    //Get method for getting the employee's name
    public String getName()
    {
        return name;
    }


    //Method that builds the employee's system
    void employeeSystem()
    {
        handler1 = new ButtonHandler(); //Initialize a button handler
        JFrame empFrame = new JFrame("Employee's System"); //Initialize the frame
        empFrame.setSize(400, 200); //Setting the frame's size
        empFrame.setLocationRelativeTo(null); //Setting the frame's location
        empFrame.setResizable(false); //Disable the option to resize the frame (for keeping the order of the components)

        GridLayout grid1 = new GridLayout(5,1,5,10); //Initialize a grid layout
        JPanel empPanel = new JPanel(grid1); //Initialize the panel that will have the employee's information
        JPanel buttPanel = new JPanel(); //Initialize the panel that will have the buttons

        //Labels for the employee's information
        JLabel labelName = new JLabel("Name: "+getName());
        JLabel labelNum = new JLabel("Employee's number: "+getEmployeeNum());
        JLabel labelSen = new JLabel("Employee's seniority: "+seniority);
        JLabel labelSal = new JLabel("Salary: "+salary);
        JLabel labelDep = new JLabel("Department: "+worksInDep.departmentName);

        //Adding the labels to the panel
        empPanel.add(labelName);
        empPanel.add(labelNum);
        empPanel.add(labelDep);
        empPanel.add(labelSen);
        empPanel.add(labelSal);

        //If the employee works in the "Store" department, he will have the option to see the invoices
        if(worksInDep.departmentName.equals("Store"))
        {
            watchSalesButton = new JButton("See Invoices"); //Initialize the button
            watchSalesButton.addActionListener(handler1); //Adding an action listener
            buttPanel.add(watchSalesButton); //Adding the button to the panel
        }

        //If the employee works in any other department, he will have other options
        else
        {
            //Initialize the buttons
            depAniButton = new JButton("Animals");
            feedButton = new JButton("Feed");
            bonusButton = new JButton("Bonus");
            mateButton = new JButton("Mate");

            //Adding action listeners
            depAniButton.addActionListener(handler1);
            feedButton.addActionListener(handler1);
            bonusButton.addActionListener(handler1);
            mateButton.addActionListener(handler1);

            //Adding the buttons to the panel
            buttPanel.add(depAniButton);
            buttPanel.add(feedButton);
            buttPanel.add(bonusButton);
            buttPanel.add(mateButton);
        }

        //Adding the panels to the frame and setting it to be visible
        empFrame.add(empPanel,BorderLayout.CENTER);
        empFrame.add(buttPanel,BorderLayout.SOUTH);
        empFrame.setVisible(true);
    }


    //A method that calculates the employee's bonus to his salary, based on the number of animal in his department and his seniority
    void calculateBonus(int numOfAnimals)
    {
        int bonus=0; //Initialize the bonus
        int salaryWithBonus=salary; //Initialize the salary with bonus with the employee's salary

        //Bonus if the department has 5 to 9 animals
        if((numOfAnimals>=5) && (numOfAnimals<10))
        {
            bonus = 30; //Setting the bonus to 30
            salaryWithBonus += bonus + (numOfAnimals * seniority); //Calculate the bonus
        }

        //Bonus if the department has 10 or more animals
        if(numOfAnimals>=10)
        {
            bonus = 50; //Setting the bonus to 50
            salaryWithBonus += bonus + (numOfAnimals * seniority); //Calculate the bonus
        }

        JOptionPane.showMessageDialog(null, "Salary with bonus: "+salaryWithBonus, "Calculate Bonus", JOptionPane.PLAIN_MESSAGE); //Showing the salary with the bonus on the screen
    }


    //A method that checks if an animal is in the employee's department
    Animal searchInDep(int key)
    {
        //For loop to search in the animals' vector
        for(int i = 0;i<worksInDep.animalsInDep.size();i++)
        {
            if(worksInDep.animalsInDep.elementAt(i).getAnimalNum() == key)
                return worksInDep.animalsInDep.elementAt(i); //If the animals was found, return it
        }
        return null; //If the animal was not found, return null
    }


    //A method that builds the mate's system
    void mateSystem()
    {
        //Initialize the frame and panel
        mateFrame = new JFrame("Mating");
        mateFrame.setSize(430,210);
        mateFrame.setLocationRelativeTo(null);
        mateFrame.setResizable(false);
        JPanel matePanel = new JPanel();

        //Printing the animals in the console for easy access
        for(int i=0;i<worksInDep.animalsInDep.size();i++)
        {
            System.out.println(worksInDep.animalsInDep.elementAt(i).toString());
        }

        //Initialize grids layouts
        GridLayout grid1 = new GridLayout(4, 1, 5, 18);
        GridLayout grid2 = new GridLayout(4, 1, 5, 13);

        //Initialize the panels
        JPanel labelPanel = new JPanel(grid1);
        JPanel fieldPanel = new JPanel(grid2);
        JPanel buttonPanel = new JPanel();

        //Initialize the labels
        JLabel parentLabel1 = new JLabel("First Parent's Number:",SwingConstants.RIGHT);
        JLabel parentLabel2 = new JLabel("Second Parent's Number:",SwingConstants.RIGHT);
        JLabel offSpringLabel = new JLabel("Offspring's Name:",SwingConstants.RIGHT);
        JLabel numLabel = new JLabel("Offspring's Number:",SwingConstants.RIGHT);

        //Adding the labels to the panel
        labelPanel.add(parentLabel1);
        labelPanel.add(parentLabel2);
        labelPanel.add(offSpringLabel);
        labelPanel.add(numLabel);

        //Initialize the text fields and adding them to the panel
        nameField1 = new JTextField(19);
        nameField2 = new JTextField(19);
        offspringField = new JTextField(19);
        animalNumField = new JTextField(19);
        fieldPanel.add(nameField1);
        fieldPanel.add(nameField2);
        fieldPanel.add(offspringField);
        fieldPanel.add(animalNumField);

        //Adding the labels and text fields panels to the main panel
        matePanel.add(labelPanel);
        matePanel.add(fieldPanel);

        //Initialize the buttons, setting action listeners and adding them to the panel
        okMateButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        okMateButton.addActionListener(handler1);
        cancelButton.addActionListener(handler1);
        buttonPanel.add(okMateButton);
        buttonPanel.add(cancelButton);

        //Adding the main panel and the button panel to the frame
        mateFrame.add(matePanel,BorderLayout.CENTER);
        mateFrame.add(buttonPanel,BorderLayout.SOUTH);

        //Setting the frame to be visible
        mateFrame.setVisible(true);
    }


    //A method for choosing an animal in the department and see it's info. When you choose the animal from the combobox, it's info will immediately appear on the screen
    void printAnimals()
    {
        //Initialize the frame
        JFrame anFrame = new JFrame("Department's Animals");
        anFrame.setSize(300, 80);
        anFrame.setLocationRelativeTo(null);

        //Initialize the panel and the combobox
        JPanel anPanel = new JPanel(new FlowLayout());
        JComboBox <String> anComboBox = new JComboBox<>();

        //For loop for adding the animals to the combobox
        for(int i=0;i<worksInDep.animalsInDep.size();i++)
        {
            anComboBox.addItem(worksInDep.animalsInDep.elementAt(i).getAnimalName());
        }

        //Item listener for choosing an animal from the combobox
        anComboBox.addItemListener(
                new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange()== ItemEvent.SELECTED) //gets the index that is selected
                            JOptionPane.showMessageDialog(null, worksInDep.animalsInDep.elementAt(anComboBox.getSelectedIndex()).toString(), "Zoo Animals", JOptionPane.PLAIN_MESSAGE); //Shows the animal's info on the screen
                    }
                }
        );

        JLabel label = new JLabel("Select Animal:"); //Initialize label

        //Adding the label and combobox to the panel
        anPanel.add(label);
        anPanel.add(anComboBox);

        //Adding the panel to the frame and making it visible
        anFrame.add(anPanel);
        anFrame.setVisible(true);
    }


    //toString method for printing the employee's info
    public String toString()
    {
        return ("Name: "+getName()+"\nEmployee's number: "+getEmployeeNum()+"\nEmployee's seniority: "+seniority+"\nSalary: "+salary+"\nDepartment: "+worksInDep.departmentName);
    }


    //Sub-class for the button handler
    public class ButtonHandler implements ActionListener, Serializable
    {
        String nameRegex = "[A-Za-z]*"; //Initialize a regex for name fields
        String numRegex = "[0-9]+"; //Initialize a regex for number fields

        @Override
        public void actionPerformed(ActionEvent e)
        {
            //If the button for printing the animals was pressed, activate the print animals method
            if(e.getSource() == depAniButton)
            {
                printAnimals();
            }

            //If the mate button was pressed, activate the mate system
            if(e.getSource() == mateButton)
            {
                mateSystem();
            }

            //If the ok button in the mate system was pressed, start the mating process
            if(e.getSource() == okMateButton)
            {
                //Checks if the input in the text fields is valid
                if(!Pattern.matches(numRegex,nameField1.getText()) || !Pattern.matches(numRegex,nameField2.getText()) || !Pattern.matches(nameRegex,offspringField.getText()) || offspringField.getText().isEmpty() || !Pattern.matches(numRegex,animalNumField.getText()))
                {
                    JOptionPane.showMessageDialog(null,"An invalid input has been detected... please enter valid text!","Null input",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                //If the animal number that was entered in the text field already exists, show a matching message on the screen
                if(searchInDep(Integer.parseInt(animalNumField.getText()))!=null)
                {
                    JOptionPane.showMessageDialog(null,"An animal with the same animal number already exists. Enter a new one","Key found",JOptionPane.INFORMATION_MESSAGE);
                }

                //If the animal's number doesn't exist, checking if the parents exists
                else
                {
                    if (searchInDep(Integer.parseInt(nameField1.getText())) != null && searchInDep(Integer.parseInt(nameField2.getText())) != null && offspringField.getText() != null) {
                        Animal baby = searchInDep(Integer.parseInt(nameField1.getText())).mate(searchInDep(Integer.parseInt(nameField2.getText())), offspringField.getText(), Integer.parseInt(animalNumField.getText())); //Initialize the new animal
                        if (baby != null)
                            worksInDep.animalsInDep.add(baby); //Adding the new animal to the department's vector

                        mateFrame.setVisible(false); //Closing the mate frame
                    } else {
                        JOptionPane.showMessageDialog(null, "Couldn't find one of the parent's key", "Key not found", JOptionPane.INFORMATION_MESSAGE); //If the parent's key was not found, show a matching message
                        return;
                    }
                }
            }

            //If the cancel button in the mate frame was pressed, closes the frame
            if(e.getSource() == cancelButton)
            {
                mateFrame.setVisible(false);
            }

            //If the feed button was pressed, start the feeding process
            if(e.getSource() == feedButton)
            {
                Zoo.feeding(worksInDep); //Activate the zoo's feeding method
            }

            //If the bonus button was pressed, activate the calculate bonus method
            if(e.getSource() == bonusButton)
            {
                calculateBonus(worksInDep.animalsInDep.size()); //Activate the calculate bonus method
            }

            //If the watch invoices button was pressed, start the searching process
            if(e.getSource() == watchSalesButton)
            {
                String input = JOptionPane.showInputDialog(null,"Enter an invoice number: ","Search Invoice",JOptionPane.QUESTION_MESSAGE); //Getting the input for the invoice
                Pattern p = Pattern.compile("[0-9]+"); //Setting a pattern for only numbers
                Matcher m = p.matcher(input);
                //If an invalid input was entered, show a matching message
                if(!m.find())
                {
                    JOptionPane.showMessageDialog(null,"An invalid input has been detected... please enter valid text!","Null input",JOptionPane.INFORMATION_MESSAGE);
                }

                //Searching the invoice
                else
                {
                    int i=0;
                    for(i=0;i<Store.productSales.size();i++)
                    {
                        //If the invoice was found, show it on the screen
                        if(Store.productSales.elementAt(i).sale.getInvoiceNum() == Integer.parseInt(input))
                        {
                            JOptionPane.showMessageDialog(null,Store.productSales.elementAt(i).sale.toString()+"\n"+Store.productSales.elementAt(i).product.toString()+"Quantity: "+Store.productSales.elementAt(i).amount,"Invoice",JOptionPane.PLAIN_MESSAGE);
                            break;
                        }
                    }
                    //If the index has reached the end of the vector, the invoice doesn't exist
                    if(i==Store.productSales.size())
                        JOptionPane.showMessageDialog(null,"The invoice was not found","Search Invoice",JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }
}
