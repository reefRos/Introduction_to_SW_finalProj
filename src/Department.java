import java.util.Vector;
import java.io.Serializable;

public class Department implements Serializable
{
    String departmentName; //String to hold the department's name
    Vector<Animal> animalsInDep = new Vector<>();//Vector to hold the animals in set department
    Vector<Employee> employees = new Vector<>();//Vector to hold the employees of the department

    //Empty Constructor
    public Department()
    {

    }

    //Constructor for department class. it cal only receive a set string to create a department
    public Department(String depName)
    {
        departmentName = depName;

        if(depName.equals("Store")) //If the department name received is store then initialize a store department
        {
            Store store  = new Store();
            store.setVisible(true);
            Employee storeEmp = new Employee("Steve",3267,5300,this,2);
            employees.add(storeEmp);
            Zoo.zooEmployees.add(storeEmp);
        }

        if(depName.equals("Reptiles")) //If the department name received is Reptiles then initialize a reptiles department with the animals and employees
        {
            Animal reptile1 = new Animal("Bobo",1234,"Crocodile","Male",15,this);
            Animal reptile2 = new Animal("Dundee",1235,"Crocodile","Female",20,this);
            Animal reptile3 = new Animal("Koko",2345,"Lizard","Female",35,this);
            Animal reptile4 = new Animal("Cookie",2346,"Lizard","male",33,this);
            Animal reptile5 = new Animal("Jojo",3456,"Komodo dragon","Male",20,this);
            Animal reptile6 = new Animal("Jojo",3456,"Komodo dragon","Male",25,this);
            animalsInDep.add(reptile1);
            animalsInDep.add(reptile2);
            animalsInDep.add(reptile3);
            animalsInDep.add(reptile4);
            animalsInDep.add(reptile5);
            animalsInDep.add(reptile6);

            Employee emp1 = new Employee("Bob", 12345, 5000, this, 0);
            employees.add(emp1);
            Zoo.zooEmployees.add(emp1);
        }

        if(depName.equals("Birds"))//If the department name received is Birds then initialize a birds department with the animals and employees
        {
            Animal bird1 = new Animal ("Donald",8765,"Pigeon","Male",3,this);
            Animal bird2 = new Animal ("Melania",8764,"Pigeon","Female",2,this);
            Animal bird3 = new Animal("Mike",2345,"Parrot","Female",15,this);
            Animal bird4 = new Animal ("Vanilla",8765,"Parrot","Male",30,this);
            Animal bird5 = new Animal("Jack",45673,"Sparrow","Male",1,this);
            Animal bird6 = new Animal ("Elizabeth",8765,"Sparrow","Female",2,this);
            animalsInDep.add(bird1);
            animalsInDep.add(bird2);
            animalsInDep.add(bird3);
            animalsInDep.add(bird4);
            animalsInDep.add(bird5);
            animalsInDep.add(bird6);

            Employee emp1 = new Employee("John", 12345, 5000, this, 6);
            employees.add(emp1);
            Zoo.zooEmployees.add(emp1);
        }

        if(depName.equals("Predators"))//If the department name received is Predators then initialize a Predators department with the animals and employees
        {
            Animal pred1 = new Animal("Simba", 4678, "Lion", "Male",10,this);
            Animal pred2 = new Animal("Nala",5433,"Lion","Female",9,this);
            Animal pred3 = new Animal("Jerry",6643,"Tiger","Male",13,this);
            Animal pred4 = new Animal("Newton", 8134,"Sand Cat","Male",3,this);
            Animal pred5 = new Animal("Busa",7358,"Sand Cat","Female",5,this);
            Animal pred6 = new Animal("Buddha",1324,"Tiger","Female",30,this);
            Animal pred7 = new Animal("Juniper", 74839, "Fox","Female",12,this);
            Animal pred8 = new Animal("Fig",3231,"Fox","Male",10,this);
            Animal pred9 = new Animal("Elmwood",43279,"Fox","Male",8,this);
            Animal pred10= new Animal("Timon",1154,"Meerkat","Male",33,this);
            Animal pred11 = new Animal("Bleep",55287,"Meerkat","Female",29,this);

            Employee emp = new Employee("Moshe",90123,3000,this,1);
            Employee em = new Employee("Marina",67322,6000,this,6);

            animalsInDep.add(pred1);
            animalsInDep.add(pred2);
            animalsInDep.add(pred3);
            animalsInDep.add(pred4);
            animalsInDep.add(pred5);
            animalsInDep.add(pred6);
            animalsInDep.add(pred7);
            animalsInDep.add(pred8);
            animalsInDep.add(pred9);
            animalsInDep.add(pred10);
            animalsInDep.add(pred11);

            employees.add(emp);
            employees.add(em);
            Zoo.zooEmployees.add(emp);
            Zoo.zooEmployees.add(em);
        }

        if(depName.equals("Monkeys"))//If the department name received is Monkeys then initialize a monkeys department with the animals and employees
        {
            Animal mon1 = new Animal("Choochoo",64392,"Orangutan","Female",50,this);
            Animal mon2 = new Animal("Richard",3271,"Gorilla","Male",27,this);
            Animal mon3= new Animal("Tarzan",4615,"Gorilla","Male",44,this);
            Animal mon4 = new Animal("Gali",3139,"Baboon","Female",60,this);
            Animal mon5 = new Animal("Kofiko",66189,"Mandrill","Male",13,this);
            Animal mon6 = new Animal("Kofika",43129,"Mandrill","Female",12,this);

            Employee e = new Employee("Miri",52618,7000,this,37);
            Employee em = new Employee("Lee",32719,5500,this,20);
            Employee emp = new Employee("Nora",42179,4000,this,2);

            animalsInDep.add(mon1);
            animalsInDep.add(mon2);
            animalsInDep.add(mon3);
            animalsInDep.add(mon4);
            animalsInDep.add(mon5);
            animalsInDep.add(mon6);

            employees.add(e);
            employees.add(em);
            employees.add(emp);
            Zoo.zooEmployees.add(e);
            Zoo.zooEmployees.add(em);
            Zoo.zooEmployees.add(emp);
        }

        if(depName.equals("Sea Animals"))//If the department name received is Sea Animals then initialize a sea animals department with the animals and employees
        {
            Animal sea1= new Animal("Nemo",1567,"Clown Fish","Male",90,this);
            Animal sea2 = new Animal("Oscar",32619,"Shark","Male",67,this);
            Animal sea3 = new Animal("Victoria",4320,"Shark","Female",55,this);
            Animal sea4 = new Animal("Nootnoot", 3904,"Penguin","Male",49,this);

            Employee e = new Employee("Yam",32144,6600,this,12);

            animalsInDep.add(sea1);
            animalsInDep.add(sea2);
            animalsInDep.add(sea3);
            animalsInDep.add(sea4);

            employees.add(e);
            Zoo.zooEmployees.add(e);
        }

    }

}