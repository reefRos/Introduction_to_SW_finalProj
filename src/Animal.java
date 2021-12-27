import javax.swing.*;
import java.io.Serializable;
import java.util.Random;


//Animal class hold the information about a certain animal
public class Animal extends Thread implements Serializable, Runnable
{
    private String name; //string which hold's the name of the animal
    private int animalNum; //int which hold the animal's number
    private String animalType, gender; //strings for the type of animal and gender of the animal
    int age; //animals age
    Department department; //Which department is the animal a part of


    //Default constructor for animal class. It initializes the inner field to null/zero values
    public Animal()
    {
        setAnimalNum(0);
        setName(null);
        setAnimalType(null);
        setGender(null);
        age = 0;
    }

    //Constructor for animal class. Receives arguments and implements them in the inner fields
    public Animal(String newName, int newAnimalNum, String newAnimalType, String newGender, int newAge, Department newDep)
    {
        setAnimalName(newName);
        setAnimalNum(newAnimalNum);
        setAnimalType(newAnimalType);
        setGender(newGender);
        age = newAge;
        department = newDep;
    }


    //Get method for animal name
    public String getAnimalName()
    {
        return name;
    }

    //Set method for animal name
    public void setAnimalName(String name)
    {
        this.name = name;
    }

    //set method for animal number
    public void setAnimalNum(int animalNum)
    {
        this.animalNum = animalNum;
    }

    //Get method for animal number
    public int getAnimalNum()
    {
        return animalNum;
    }

    //Set method for the gender of the animal
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    //Set method for the type of animal
    public void setAnimalType(String animalType)
    {
        this.animalType = animalType;
    }

    //Get method for the type of animal
    public String getAnimalType()
    {
        return animalType;
    }

    //get method for the gender of animal
    public String getGender()
    {
        return gender;
    }


    //Mate method takes 3 arguments: another animal of the same type,offsprings wanted name and the wanted animal number for the offspring.
    //It randomizes the gender of the offspring and implements the wanted fields through the animal class constructor.
    Animal mate(Animal animalToMate,String offspringName,int offspringNum)
    {
        String[] gender = {"Male","Female"}; //Initialize an array to hold gender options
        Random random = new Random(); //Initialize a randomizer
        int select = random.nextInt(gender.length); //choose a random number (0 or 1)

        if (animalType.equals(animalToMate.animalType)) //Check's if both animals are from the same type (I.E. cant mate a pigeon and parrot)
        {
            if (!(getGender().equals(animalToMate.getGender()))) //Makes sure that the animals aren't from the same gender
            {
                Animal baby=new Animal(offspringName,offspringNum,this.getAnimalType(),gender[select],0,this.department); //Calls animal constructor
                baby.setAnimalType(animalToMate.animalType); //Set the type as the parents'
                JOptionPane.showMessageDialog(null,"Congratulation! A new animal was born!","NEW ANIMAL",JOptionPane.PLAIN_MESSAGE);
                return baby; //Return the new animal created
            }
        }
        JOptionPane.showMessageDialog(null,"No new animal was born","Mate",JOptionPane.PLAIN_MESSAGE);
        return null; //Return null if the conditions wern't met
    }


    //Uses the run method from thread's class to start the feeding. Depending on the animal type, a different string will be printed
    public void run()
    {
        System.out.println(this.getAnimalName() +" is currently eating");
        if(department.departmentName.equals("Birds"))
            System.out.println("CHIRP CHIRP CHIRP");

        if(department.departmentName.equals("Reptiles"))
            System.out.println("CHOMP CHOMP CHOMP");

        if(department.departmentName.equals("Predators"))
            System.out.println("RAWWR NUM NUM NUM");

        if(department.departmentName.equals("Monkeys"))
            System.out.println("WHOO WHOO WHOO");

        if(department.departmentName.equals("Sea Animals"))
            System.out.println("BLOOP BLOOP BLOOP");

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
        }
        System.out.println("An animal has finished eating"); //Once an animal has finished eating, print a message

    }


    //Animal class toString method
    public String toString()
    {
        return (getAnimalName() + " ,animal number: " + getAnimalNum() + " ,is a " + getGender() + " " + getAnimalType() +" and is " + age + " years old");
    }

}