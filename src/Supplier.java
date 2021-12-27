import java.time.*;
import java.util.Date;
import java.io.Serializable;

//Supplier Class
public class Supplier implements Serializable
{
    private String brandName, address;
    private int phoneNum;
    private int[] workingHours = new int[2];


    //Empty Constructor
    public Supplier()
    {
        setPhoneNum(0);
        setBrandName(null);
        setAddress(null);
    }


    //Constructor
    public Supplier(String newBrandName, String newAddress, int newPhoneNum)
    {
        setWorkingHours(9,17);
        setBrandName(newBrandName);
        setAddress(newAddress);
        setPhoneNum(newPhoneNum);
    }

    //Set method for setting the working hours
    public void setWorkingHours(int opening,int closing)
    {
        this.workingHours[0] = opening;
        this.workingHours[1] = closing;
    }

    //Set method to set the address
    public void setAddress(String address)
    {
        this.address = address;
    }

    //Set method to set the brand name
    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }

    //Set method for the phone number
    public void setPhoneNum(int phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    //Get methof for the phone number
    public int getPhoneNum()
    {
        return phoneNum;
    }

    //Get method for the address
    public String getAddress()
    {
        return address;
    }

    //Get method for the brand name
    public String getBrandName()
    {
        return brandName;
    }

    //A method for getting the local time and checking if the supplier works at the time
    boolean checkWorkingHours()
    {
        if(workingHours[0]<=LocalTime.now().getHour() && workingHours[1]>=LocalTime.now().getHour())
        {
            return true;
        }
        return false;
    }

    //toString for supplier class
    public String toString()
    {
        return ("Brand name: " + getBrandName() + "\nAddress: " + getAddress() + "\nPhone num: " + getPhoneNum());
    }

}
