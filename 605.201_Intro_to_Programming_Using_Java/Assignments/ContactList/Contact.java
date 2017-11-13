import java.util.*;

/**
    * This class templates a general contact class, with 
    * its respective attributes
    *
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/
public class Contact 
{
    private String firstName;
    private String lastName;
    private String company;
    private String phone;
    private String email;

    /**
        * Parameterized constructor for a new contact instance
        *
        * @author Baseem Astiphan
        * @param first First name
        * @param last last name
        * @param company company name
        * @param phone Phone number
        * @param email Email address
    */
    public Contact(String first, String last, String company, String phone, String email)
    {
        //Call respective setter methods
        setFirstName(first);
        setLastName(last);
        setCompany(company);
        setPhoneNumber(phone);
        setEmail(email);
    }

    public Contact() {}

    /**
        *Getter for firstName
        *
        * @author Baseem Astiphan
    */
    public String getFirstName()
    {
        return firstName;
    }

    /**
        *Setter for firstName
        *
        * @author Baseem Astiphan
        * @param firstName The first name to set
    */
    public void setFirstName(String firstName)
    {
        this.firstName =  firstName;
    }

    /**
        *Getter for lastName
        *
        * @author Baseem Astiphan
    */
    public String getLastName()
    {
        return lastName;
    }

    /**
        *Setter for lastName
        *
        * @author Baseem Astiphan
        * @param lastName The last name to set
    */
    public void setLastName(String lastName)
    {
        this.lastName =  lastName;
    }


    /**
        *Getter for company
        *
        * @author Baseem Astiphan
    */
    public String getCompany()
    {
        return company;
    }

    /**
        *Setter for company
        *
        * @author Baseem Astiphan
        * @param company The company to set
    */
    public void setCompany(String company)
    {
        this.company =  company;
    }


    /**
        *Getter for phone
        *
        * @author Baseem Astiphan
    */
    public String getPhoneNumber()
    {
        return phone;
    }

    /**
        *Setter for Phone Number
        *
        * @author Baseem Astiphan
        * @param phone The phone to set
    */
    public void setPhoneNumber(String phone)
    {
        this.phone =  phone;
    }

    /**
        *Getter for email
        *
        * @author Baseem Astiphan
    */
    public String getEmail()
    {
        return email;
    }

    /**
        *Setter for email
        *
        * @author Baseem Astiphan
        * @param email The email to set
    */
    public void setEmail(String email)
    {
        this.email =  email;
    }

    /**
        *Override the compare method from 
        *to indicate sort order on natural ordering of last names
        *
        * @author Baseem Astiphan
        * @param cont2 compared contact
    */
    public int compareTo(Contact cont2)
    {
        //Leverage the built in Sring.compareTo method
        return this.getLastName().compareTo(cont2.getLastName());
    }

    /**
        * toString to return well formatted version of instance
        *
        * @return formatted string describing object
    */
    public String toString()
    {
        return "Last Name:  " + getLastName() + "\n" +
               "First Name: " + getFirstName() + "\n" +
               "Company:    " + getCompany() + "\n" +
               "Phone Num:  " + getPhoneNumber() + "\n" +
               "Email Addy: " + getEmail() + "\n";
    }

    /**
        * Method to create a new instance of a contact from a comma
        * delimeted line of information. (Useful for reading from
        * a file)
        *
        * @author Baseem Astiphan
        * @param contactLine a line to be parsed
    */
    static Contact parseContact(String contactLine)
    {
        //Split string containing contact
        String[] cont = contactLine.split(",");
        //Set up a new contact
        Contact contact = new Contact(cont[1], cont[0], cont[2], cont[3], cont[4]);
        return contact;
    }

    /*
        Below is used to call unit tests
    */
    public static void main(String [] args)
    {
        UnitTests.createNewContactInstance();
        UnitTests.newContactInstanceHasCorrectProperties();
        UnitTests.compareWorksCorrectly();

        System.out.println("No errors reported");
    }
}

class UnitTests
{
    static void createNewContactInstance()
    {
        Contact ct = new Contact("Bill", "Smith", "Google", "212-555-1000", "abc@abc.com");
        assert (ct instanceof Contact) : "Contact Not Created";
    }

    static void newContactInstanceHasCorrectProperties()
    {
        Contact ct = new Contact("Bill", "Smith", "Google", "212-555-1000", "abc@abc.com");
        assert (ct.getFirstName() == "Bill") : "Wrong first";   
        assert (ct.getLastName() == "Smith") : "Wrong last";   
        assert (ct.getCompany() == "Google") : "Wrong comp";   
        assert (ct.getPhoneNumber() == "212-555-1000") : "Wrong phone";   
        assert (ct.getEmail() == "abc@abc.com") : "Wrong email";   
    }

    static void compareWorksCorrectly()
    {
        Contact ct = new Contact("Bill", "Smith", "Google", "212-555-1000", "abc@abc.com");
        Contact ct2 = new Contact("Bill", "Thoith", "Google", "212-555-1000", "abc@abc.com");

        assert(ct.compareTo(ct2) == -1) : "Compare broke";
    }
}