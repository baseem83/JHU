/**
    * This class templates a general Address. It includes street, city
    * state, and zip code fields. This class also establishes default 
    * values to be used in case any of the field values is not furnished,
    * or in cases where a field value fails validation.
    *
    * While not in scope now, default values try to meet constraints that
    * should reasonably exist on the underlying field. For example, state
    * will always be a 2 character abbreviation. While we could initialize 
    * with a default message, if this gets passed into a database (for example)
    * it may overflow the field and return an error. Hence, this returns the
    * two character 'ZZ'. Same concept applies to the zip code.
    * 
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/
public class Address
{
    //Default street name, used when none provided or failed validation
    private static final String DEFAULT_STREET = "Street-Name";
    
    //Default city name, used when none provided or failed validation
    private static final String DEFAULT_CITY = "City-Name";
    
    //Default state name, used when none provided or failed validation
    private static final String DEFAULT_STATE = "ZZ";
    
    //Default zip code, used when none provided or failed validation
    private static final String DEFAULT_ZIP_CODE = "00000";
    
    private String street;  //member variable to hold street
    private String city;    //member variable to hold city
    private String state;   //member variable to hold state
    private String zipCode; //member variable to hold zip code
    
    /**
        * Constructor when no arguments are passed. It chains constructors
        * with the parameterized constructor, passing in default values.
        *
        * @author Baseem Astiphan
    */
    public Address()
    {
        //Call parameterized constructor with default values
        this(DEFAULT_STREET, DEFAULT_CITY, DEFAULT_STATE, DEFAULT_ZIP_CODE);
    }
    
    /**
        * Constructor when arguments are passed. The constructor calls the
        * setStreet, setCity, setState, and setZip methods respectively to set 
        * corresponding values. Methods are used rather than direct assignment  
        * so that error checking can be applied in only one place throughout 
        * the code (encapsulation).
        * 
        * @author Baseem Astiphan
        * @param String street The street name property of the Address class
        * @param String city  The city name property of the Address class
        * @param String city  The state name property of the Address class
        * @param String zip  The zip code property of the Address class
        * 
        * @return Constructor that returns a new Name instance
    */    
    public Address(String street, String city, String state, String zip)
    {
        setStreet(street); //set street
        setCity(city);     //set city
        setState(state);   //set state
        setZipCode(zip);   //set zip code
    }

    /**
        * This method sets the street name to the 'street' argument. Prior
        * to setting, the class confirms that street does not contain any
        * disallowed characters. For the sake of this assignment, street names
        * may only contain upper/lower case letters, apostrophes, periods,
        * hyphens, or spaces.
        * 
        * The class instance prints to screen when a disallowed character 
        * is inlcuded in the street, then it sets the value to the defined default.
        * Normally, an exception should be thrown, passing the responsibility of
        * handling the error to the programmer. But, we haven't yet worked with 
        * exceptions.
        * 
        * precondition The street does not include disallowed characters
        * 
        * postcondition Street has no disallowed characters
        * 
        * @author Baseem Astiphan
        * @param String street  The street name for the class instance
    */    
    public void setStreet(String street)
    {
        //Check for disallowed characters
        if (street.matches(".*[^A-Za-z0-9 '.-]+.*"))
        {
            //Generate an error message (refactor to exception in the future)
            System.out.println("Street should only contain letters, numbers, or [- ' .].");
            System.out.println("Default street set --> " + DEFAULT_STREET);
            
            //Set street to prefined default
            setStreet(DEFAULT_STREET);
            return;
        }
        else
        {
            this.street = street; //set street member variable to street argument
        }
    }
    
    /**
        * This method returns the street name property
        *
        * postcondition Street name is returned
        * 
        * @author Baseem Astiphan
        * @return String street property
    */       
    public String getStreet()
    {
        return street; //return the street
    }
    
    /**
        * This method sets the city name to the 'city' argument. Prior
        * to setting, the class confirms that city does not contain any
        * disallowed characters. For the sake of this assignment, city names
        * may only contain upper/lower case letters, apostrophes, periods,
        * hyphens, or spaces.
        * 
        * The class instance prints to screen when a disallowed character 
        * is inlcuded in the city, then it sets the value to the defined default.
        * Normally, an exception should be thrown, passing the responsibility of
        * handling the error to the programmer. But, we haven't yet worked with 
        * exceptions.
        * 
        * precondition The city does not include disallowed characters
        * 
        * postcondition city has no disallowed characters
        * 
        * @author Baseem Astiphan
        * @param String city  The city name for the class instance
    */    
    public void setCity(String city)
    {   //Check for invalid entry
        if (city.matches(".*[^A-Za-z '.-]+.*"))
        {
            //Generate an error message (refactor to exception in the future)
            System.out.println("City should only contain letters or [- ' .].");
            System.out.println("Default city set --> " + DEFAULT_CITY);
            
            setStreet(DEFAULT_STREET); //set street to the default
            return;
        }
        else
        {
            this.city = city; //set city member variable to city argument
        }
    }
    
    /**
        * This method returns the city property
        *
        * postcondition City is returned
        * 
        * @author Baseem Astiphan
        * @return String city property
    */     
    public String getCity()
    {
        return city; //return city property
    }
    
    /**
        * This method sets the state name to the 'state' argument. Prior
        * to setting, the class confirms that state does not contain any
        * disallowed characters. For the sake of this assignment, state names
        * may only contain upper/lower case letters and must be 2 characters
        * long.
        * 
        * The class instance prints to screen when a disallowed character 
        * is inlcuded in the state, then it sets the value to the defined default.
        * Normally, an exception should be thrown, passing the responsibility of
        * handling the error to the programmer. But, we haven't yet worked with 
        * exceptions.
        * 
        * precondition The state does not include disallowed characters
        * precondition The state is two characters long
        * 
        * postcondition state has no disallowed characters and is 2 characters
        * 
        * @author Baseem Astiphan
        * @param String state  The state name for the class instance
    */       
    public void setState(String state)
    {
        //Check for invalid entry
        if (!state.matches("[A-Za-z][A-Za-z]"))
        {
            //Generate an error message (refactor to exception in the future)            
            System.out.println("State should be 2 characters long and only contain [A-Z].");
            System.out.println("Default state set --> " + DEFAULT_STATE);
            
            setState(DEFAULT_STATE); //set state member variable to default
            return;
        }
        else
        {
            this.state = state; //set state member variable to state argument
        }
    }
  
    /**
        * This method returns the state property
        *
        * postcondition state has returned
        * 
        * @author Baseem Astiphan
        * @return String state property
    */  
    public String getState()
    {
        return state; //return state member variable
    }

    /**
        * This method sets the zip code name to the 'zip code' argument. Prior
        * to setting, the class confirms that zip code does not contain any
        * disallowed characters. For the sake of this assignment, zip codes
        * may only contain numbers and they must be 5 digits long.
        * 
        * The class instance prints to screen when a disallowed character 
        * is inlcuded in the zip code, then it sets the value to the defined default.
        * Normally, an exception should be thrown, passing the responsibility of
        * handling the error to the programmer. But, we haven't yet worked with 
        * exceptions.
        * 
        * precondition The zip code does not include disallowed characters
        * precondition The zip code is 5 digits long
        * 
        * postcondition zip code has no disallowed characters and is 5 digits
        * 
        * @author Baseem Astiphan
        * @param String zipCode  The zip code for the class instance
    */       
    public void setZipCode(String zipCode)
    {
        //Check for invalid entry
        if (!zipCode.matches("^[0-9]{5}$"))
        {
            //Generate an error message (refactor to exception in the future)             
            System.out.println("Zip code should be 5 digits and contain [0-9].");
            System.out.println("Default state set --> " + DEFAULT_ZIP_CODE);
            
            setZipCode(DEFAULT_ZIP_CODE); //set zip code to default
            return;
        }
        else
        {
            this.zipCode = zipCode; //set zip code member variable to zipCode argument
        }
    }
    
    /**
        * This method returns the zip code property
        *
        * postcondition zip code is returned
        * 
        * @author Baseem Astiphan
        * @return String state property
    */  
    public String getZipCode()
    {
        return zipCode; //return zipCode member variable
    }
    
    /**
        * This method allows the user to change an entire address with one method call.
        * It calls the setStreet, setCity, setState, and setZip methods so that it can 
        * encapsulate their error checking.
        * 
        * precondition The street does not include disallowed characters
        * precondition The city does not include disallowed characters        
        * precondition The state does not include disallowed characters
        * precondition The state is two characters long
        * precondition The zip code does not include disallowed characters
        * precondition The zip code is 5 digits long
        * 
        * postcondition A fully compliant, updated address
        * 
        * @author Baseem Astiphan
        * @param String street    The street for the class instance        
        * @param String city      The city for the class instance        
        * @param String state     The state for the class instance        
        * @param String zipCode  The zip code for the class instance
    */  
    public void setFullAddress(String street, String city, String state, String zip)
    {
        setStreet(street); //set street
        setCity(city);     //set city
        setState(state);   //set state
        setZipCode(zip);   //set zip code        
    }
    
    /**
        * This method returns the full address, formatted with spaces and commas.
        * 
        * postcondition Full address, fully formatted.
        * 
        * @author Baseem Astiphan
        * @return String street, city, state zipCode
    */
    public String getFullAddress()
    {
        //return full formatted address in one line
        return getFullAddress(false);
    }

    /**
        * This method overload returns the full address, formatted with spaces 
        * and commas, and optionally prints to two lines if the 'twoLines' param
        * is set to tru.
        * 
        * postcondition Full address, fully formatted.
        * 
        * @author Baseem Astiphan
        * @param boolean twoLines if true, prints to two lines
        * @return String street, city, state zipCode
    */
    public String getFullAddress(boolean twoLines)
    {
        //return full formatted address. If twoLines is true, split into two lines
        return twoLines ? getStreet() + "\n\t\t" + getCity() + ", " 
            + getState() + " " + getZipCode() : getStreet() + ", " 
            + getCity() + ", " + getState() + " " + getZipCode();
    }
    
    /**
        * This method overrides the toString, and returns the full address, 
        * formatted with spaces and commas, prefaced with the string literal:
        * "Full Address: "
        * 
        * postcondition Full address, fully formatted, with a prefix
        * 
        * @author Baseem Astiphan
        * @return String "Full Address: " street, city, state zipCode
    */
    public String toString()
    {
        //Fully formatted address with a prefix
        return "Full Address:\t" + getFullAddress(true);
    }
    

    //POSSIBLY REMOVE UNIT TESTS
    public static void main(String[] args)
    {
        Address ad = new Address();
        System.out.println(ad.getFullAddress());
        System.out.println(ad.toString());
        System.out.println(ad);
        
        Address a = new Address("123 Fake Street", "SuperFake", "NY", "10028");
        System.out.println(a);
        
        System.out.println(a.getFullAddress());
        System.out.println(a.getFullAddress(true));
        System.out.println();
        System.out.println(a.toString());
    }
}