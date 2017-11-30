// RE PACMAN
// 1. Can he walk through walls and wrap around? Is that our decision?
// 2. How does the game end? When all cookies are eaten? When all spaces (.) are eaten? Or 
//       only when user hits the exit key?

/**
    * This class templates a general Name. It includes first name
    * and last name member fields. This class also establishes
    * default first and last names to be used in case either of the 
    * two names is not furnished, or in cases where either of the two
    * names fails validation.
    * 
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/
public class Name
{
    //Default first name, used when none provided or failed validation
    private static final String DEFAULT_FIRST = "no-first-name";
    
    //Default last name, used when none provided or failed validation
    private static final String DEFAULT_LAST = "no-last-name";
    
    private String firstName; //first name property
    private String lastName;  //last name property
    
    /*
        * Constructor when no arguments are passed. It chains constructors
        * with the parameterized constructor, passing in default values.
        *
        * @author Baseem Astiphan
    */
    public Name()
    {
        //Call parameterized constructor with default values
        this(DEFAULT_FIRST, DEFAULT_LAST);
    }
    
    /*
        * Constructor when arguments are passed. The constructor calls the
        * setFirstName and setLastName methods respectively to set corresponding
        * values. Methods are used rather than direct assignment so that error 
        * checking can be applied in only one place throughout the code 
        * (encapsulation).
        * 
        * @author Baseem Astiphan
        * @param String first The first name property of the name class
        * @param String last  The last name property of the name class
        * 
        * @return Constructor that returns a new Name instance
    */
    public Name(String first, String last)
    {
        //Set first name to the 'first' argument
        setFirstName(first);
        
        //Set last name to the 'last' argument
        setLastName(last);
    }
    
    /**
        * This method sets the first name to the 'first' argument. Prior
        * to setting, the class confirms that first does not contain any
        * disallowed characters. For the sake of this assignment, first names
        * may only contain upper/lower case letters, apostrophes, periods,
        * hyphens, or spaces.
        * 
        * The class instance prints to screen when a disallowed character 
        * is inlcuded in the name, then it sets the value to the defined default.
        * Normally, an exception should be thrown, passing the responsibility of
        * handling the error to the programmer. But, we haven't yet worked with 
        * exceptions.
        * 
        * precondition The name does not include disallowed characters
        * 
        * postcondition First name has no disallowed characters
        * 
        * @author Baseem Astiphan
        * @param String first  The first name for the class instance
    */
    public void setFirstName(String first)
    {
        //Check for disallowed characters
        if (first.matches(".*[^A-Za-z '.-]+.*")) 
        {
            //Generate an error message (refactor to exception in the future)
            System.out.println("First name should only contain letters or [- ' .].");
            System.out.println("Default first name set --> " + DEFAULT_FIRST);
            
            //Set first name to the pre-defined default
            setFirstName(DEFAULT_FIRST);
            return;
        }
        else
        {
            firstName = first; //firstName property = 'first' argument
        }
    }

    /**
        * This method returns the first name property
        *
        * postcondition First name has is returned
        * 
        * @author Baseem Astiphan
        * @return String firstName property
    */    
    public String getFirstName()
    {
        return firstName; //return firstName
    }
    
    /**
        * This method sets the last name to the 'last' argument. Prior
        * to setting, the class confirms that last does not contain any
        * disallowed characters. For the sake of this assignment, last names
        * may only contain upper/lower case letters, apostrophes, periods,
        * hyphens, or spaces.
        * 
        * The class instance prints to screen when a disallowed character 
        * is inlcuded in the name, then it sets the value to the defined default.
        * Normally, an exception should be thrown, passing the responsibility of
        * handling the error to the programmer. But, we haven't yet worked with 
        * exceptions.
        * 
        * precondition The name does not include disallowed characters
        * 
        * postcondition Last name has no disallowed characters
        * 
        * @author Baseem Astiphan
        * @param String last  The last name for the class instance
    */    
    public void setLastName(String last)
    {
        //Check for disallowed characters
        if (last.matches(".*[^A-Za-z '.-]+.*"))
        {
            //Generate an error message (refactor to exception in the future)
            System.out.println("Last name should only contain letters or [- ' .].");
            System.out.println("Default last name set --> " + DEFAULT_LAST);
            
            //Set last name to the pre-defined default
            setLastName(DEFAULT_LAST);
            return;
        }
        else
        {
            lastName = last; //set lastName property to 'last'
        }
    }
    
    /**
        * This method returns the last name property
        *
        * postcondition Last name has is returned
        * 
        * @author Baseem Astiphan
        * @return String lastName property
    */     
    public String getLastName()
    {
        return lastName; //return last name
    }

    /**
        * This method allows the user to set both the first and last names
        * in a single method. It calls the corresponding setters for first
        * and last names, thereby including the built-in error checking.
        * 
        * precondition The names do not include disallowed characters
        * 
        * postcondition First and last names have no disallowed characters
        * 
        * @author Baseem Astiphan
        * @param String first  The first name for the class instance
        * @param String last  The last name for the class instance
    */     
    public void setFullName(String first, String last)
    {
        //set the firstName to 'first'
        setFirstName(first);
        
        //set the lastName to 'last'
        setLastName(last);
    }    
 
    /**
        * This method returns the first name and last name, separated by a 
        * space.
        * 
        * postcondition First and last names separated by a space
        * 
        * @author Baseem Astiphan
        * @return String firstName <space> lastName
    */    
    public String getFullName()
    {
        return firstName + " " + lastName;
    }
    
    /**
        * This method overrides the toString method. It returns the firstName
        * and lastName separated by a space (calls the getFullName method).
        * This method prefaces the full name with the words "Full Name: " for
        * clarity.
        * 
        * postcondition First and last names separated by space with a preface
        * 
        * @author Baseem Astiphan
        * @return String "Full Name: " firstName <space> lastName
    */
    public String toString()
    {
        //return getFullName with a preface
        return "Full Name:\t" + getFullName();
    }
    
    
    //Some unit tests.... REMOVE THESE
    public static void main (String [] args)
    {
        Name nm = new Name();
        
        nm.setFirstName("Bas eem");
        nm.setLastName("Ast1iphan");
        System.out.println("First name: " + nm.getFirstName());
        System.out.println("Last name: " + nm.getLastName());
        System.out.println("Full name: " + nm.getFullName());
        
        nm.setFullName("Billy", "Bob");
        System.out.println("First name: " + nm.getFirstName());
        System.out.println("Last name: " + nm.getLastName());
        System.out.println("Full name: " + nm.getFullName());
    }
}