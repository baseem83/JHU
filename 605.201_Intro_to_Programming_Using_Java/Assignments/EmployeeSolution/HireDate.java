/**
    * This class templates a general Hire Date. It includes month, day,
    * and year fields. This class also establishes default 
    * values to be used in case any of the field values is not furnished,
    * or in cases where a field value fails validation.
    *
    * While not in scope now, default values should be more reasonably fit 
    * the business case. In many instances with new hires, they are added into
    * a HR system the day they are hired. In the future, consider creating a 
    * static{ } code block to automatically set the default values to today's
    * date for that given day. This requires using built in Date() methods, 
    * which are disallowed for this assignment.
    * 
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/
public class HireDate
{
    //Default month, used when none provided or failed validation
    private static final int DEFAULT_MONTH = 1;
    
    //Default year, used when none provided or failed validation
    private static final int DEFAULT_DAY = 1;
    
    //Default year, used when none provided or failed validation
    private static final int DEFAULT_YEAR = 2017;
    
    private int hireMonth;  //month of hire
    private int hireDay;    //day of hire
    private int hireYear;   //year of hire
    
    /**
        * Constructor when no arguments are passed. It chains constructors
        * with the parameterized constructor, passing in default values.
        *
        * @author Baseem Astiphan
    */        
    public HireDate()
    {
        //Call parameterized constructor with default values
        this(DEFAULT_MONTH, DEFAULT_DAY, DEFAULT_YEAR);
    }
    
    /**
        * Convenience onstructor when 1 arguments is passed. It chains 
        * constructors with the parameterized constructor, passing in 
        * default values for all but the year. The user can create a new
        * date with only a year input (and defaults for month and day).
        *
        * @author Baseem Astiphan
        * @param int year  year of hire
    */    
    public HireDate(int year)
    {
        //Call parameterized constructor with default values for all but year
        this(DEFAULT_MONTH, DEFAULT_DAY, year);
    }
    
    /**
        * Convenience onstructor when 2 arguments are passed. It chains 
        * constructors with the parameterized constructor, passing in 
        * default values for the day. The user can create a new
        * date with only a month and year input (and defaults for day).
        *
        * @author Baseem Astiphan
        * @param int month 
        * @param int year  year of hire
    */       
    public HireDate(int month, int year)
    {
        //Call parameterized constructor with default values for day
        this(month, DEFAULT_DAY, year);
    }
    
    /**
        * Constructor when arguments are passed. The constructor calls the
        * setMonth, setDay, and setYear methods respectively to set 
        * corresponding values. Methods are used rather than direct assignment  
        * so that error checking can be applied in only one place throughout 
        * the code (encapsulation).
        * 
        * @author Baseem Astiphan
        * @param int month The month of hire
        * @param int day   The day of hire
        * @param int year  The year of hire
        * 
        * @return Constructor that returns a new HireDate instance
    */        
    public HireDate(int month, int day, int year)
    {
        setMonth(month); //set month member variable to month argument
        setDay(day);     //set day member variable to day argument
        setYear(year);   //set year member variable to year argument
    }
    
    /**
        * This method sets the hire month to the 'month' argument. Prior
        * to setting, the class confirms that month does not contain any
        * disallowd values (range is between 1-12 inclusive).
        * 
        * The class instance prints to screen when a disallowed value 
        * is inlcuded. Normally, an exception should be thrown, passing the 
        * responsibility of handling the error to the programmer. But, we 
        * haven't yet worked with exceptions.
        * 
        * precondition The month argument contains an integer in range
        * 
        * postcondition Month is a permissible month
        * 
        * @author Baseem Astiphan
        * @param int month  The month of hire
    */        
    public void setMonth(int month)
    {
        //Check for validation errors
        if (month < 1 || month > 12)
        {
            //Generate output message if error (refactor to exception in future)
            System.out.println("Month must be an integer between 1 and 12.");
            System.out.println("Default month set --> " + DEFAULT_MONTH);
            
            setMonth(DEFAULT_MONTH);  //Set month to default
            return;
        }
        else
        {
            hireMonth = month; //set hireMonth member variable = month argument
        }
    }
    
    /**
        * This method returns the hireMonth property
        *
        * postcondition hireMonth is returned
        * 
        * @author Baseem Astiphan
        * @return int month of hire
    */      
    public int getMonth()
    {
        return hireMonth; //return month of hire
    }

    /**
        * This method sets the hire day to the 'day' argument. Prior
        * to setting, the class confirms that day does not contain any
        * disallowd values (range is between 1-31 inclusive).
        * 
        * The class instance prints to screen when a disallowed value 
        * is inlcuded. Normally, an exception should be thrown, passing the 
        * responsibility of handling the error to the programmer. But, we 
        * haven't yet worked with exceptions.
        * 
        * precondition The day argument contains an integer in range
        * 
        * postcondition day is a permissible day
        * 
        * @author Baseem Astiphan
        * @param int day  The day of hire
    */            
    public void setDay(int day)
    {
        //Check for validation errors
        if (day < 1 || day > 31)
        {
            //Generate output message if error (refactor to exception in future)
            System.out.println("Day must be an integer between 1 and 31.");
            System.out.println("Default month set --> " + DEFAULT_DAY);
            
            setDay(DEFAULT_DAY); //set day to default
            return;
        }
        else
        {
            hireDay = day; //set hireDay to day argument
        }
    }
    
    /**
        * This method returns the hireDay property
        *
        * postcondition hireDay is returned
        * 
        * @author Baseem Astiphan
        * @return int day of hire
    */      
    public int getDay()
    {
        return hireDay; //return day of hire
    }
    
    /**
        * This method sets the hire year to the 'year' argument. Prior
        * to setting, the class confirms that year does not contain any
        * disallowd values (range is between 1900-2020 inclusive).
        * 
        * The class instance prints to screen when a disallowed value 
        * is inlcuded. Normally, an exception should be thrown, passing the 
        * responsibility of handling the error to the programmer. But, we 
        * haven't yet worked with exceptions.
        * 
        * precondition The year argument contains an integer in range
        * 
        * postcondition year is a permissible year
        * 
        * @author Baseem Astiphan
        * @param int year  The year of hire
    */   
    public void setYear(int year)
    {
        //Check for validation errors
        if (year < 1900 || year > 2020)
        {
            //Generate output message if error (refactor to exception in the future)
            System.out.println("Year must be an integer between 1900 and 2020.");
            System.out.println("Default month set --> " + DEFAULT_YEAR);
            
            setYear(DEFAULT_YEAR); //set year to default
            return;
        }
        else
        {
            hireYear = year; //set hireYear to year argument
        }
    }
    
    
    /**
        * This method returns the hireYear property
        *
        * postcondition hireYear is returned
        * 
        * @author Baseem Astiphan
        * @return int year of hire
    */          
    public int getYear()
    {
        return hireYear; // return year of hire
    }

    /**
        * This method takes arguments for month, day, and year then calls
        * setMonth, setDay, and setYear methods respectively to set 
        * corresponding values. Methods are used rather than direct assignment  
        * so that error checking can be applied in only one place throughout 
        * the code (encapsulation).
        * 
        * @author Baseem Astiphan
        * @param int month The month of hire
        * @param int day   The day of hire
        * @param int year  The year of hire
        * 
        * @return none 
    */     
    public void setFullHireDate(int month, int day, int year)
    {
        setMonth(month); //set month member variable to month argument
        setDay(day);     //set day member variable to day argument
        setYear(year);   //set year member variable to year argument        
    }
    
    /**
        * This method returns the full hire date by calling the overloaded
        * getFullHireDate() method with a value of false. This returns the 
        * date in the format M/d/yyyy.
        *
        * postcondition Full date is returned
        * 
        * @author Baseem Astiphan
        * @return String Full date of hire, formatted M/d/yyyy
    */     
    public String getFullHireDate()
    {
        //Call getFullHireDate with argument false (not long date version)
        return getFullHireDate(false); 
    }

/**
        * This method returns the full hire date, and based on the passed in 
        * boolean argument, returns (True) a long String representation, or a
        * (False) short representation formatted M/d/yyyy.
        *
        * postcondition Full date is returned in appropriate format
        * 
        * @author Baseem Astiphan
        * param boolean longDate true for the long date format, false for short format
        * @return String Full date of hire, formatted M/d/yyyy or MMMM d, yyyy
    */         
    public String getFullHireDate(boolean longDate)
    {
        String output; //output of method call
        
        if (longDate) //argument is true
        {
             //determine the instance month and get long month format
            switch(getMonth())
            {                 
                case 1: 
                    output = "January";
                    break;
                case 2: 
                    output =  "February";
                    break;
                case 3: 
                    output =  "March";
                    break;
                case 4: 
                    output =  "April";
                    break;
                case 5: 
                    output =  "May";
                    break;
                case 6: 
                    output =  "June";
                    break;
                case 7: 
                    output =  "July";
                    break;
                case 8: 
                    output =  "August";
                    break;
                case 9: 
                    output =  "September";
                    break;
                case 10: 
                    output =  "October";
                    break;
                case 11: 
                    output =  "November";
                    break;
                case 12: 
                    output =  "December";
                    break;
                default: 
                    output =  "None";
                    break;
            }
            // Add the day and year to the long format of the month
            output = output + " " + getDay() + ", " + getYear();
        }
        else
        {
            //Return the short date format (M/d/yyyy)
            output = getMonth() + "/" + getDay() + "/" + getYear();
        }
        
        return output; //return the appropriate format
    }
    
    /**
        * This method overrides the toString, and returns the full hire date, 
        * formatted with forward slashes, prefaced with the string literal:
        * "Hire Date: "
        * 
        * postcondition Full hire date, fully formatted, with a prefix
        * 
        * @author Baseem Astiphan
        * @return String "Hire Date: " hireMonth/hireDay/hireYear
    */    
    public String toString()
    {
        //return formatted date
        return "Hire Date:\t" + getFullHireDate(true);
    }
    
    
    //REMOVE UNIT TESTS
    public static void main (String [] args)
    {   
        System.out.println((new HireDate()).getFullHireDate(true));
        System.out.println(new HireDate(10, 4, 2017));
    }
}