/**
    * This application generates calendars and outputs them to the screen.
    * The user specifies the month and the year of the calendar month 
    * to be displayed. 
    * 
    * @author:Baseem Astiphan
    * @version:1.0.0.0
*/

import java.util.Scanner;

public class CalendarGenerator
{
    //Standard column width; store as constant for easier updates
    static final int COLUMN_WIDTH = 4;
    
    public static void main (String [] args)
    {
        int year;   //calendar year (4 digits)
        int month;  //calendar month (1-12)
        
        Scanner input = new Scanner (System.in);
        
        System.out.print("Please enter a calendar month (1-12): ");
        month = input.nextInt(); //get user input for month

        System.out.print("\nPlease enter a calendar month (4 digits, e.g. 2017): ");
        year = input.nextInt(); //get user input for year
        
        System.out.println();  //blank line for formatting
        
        printMonthCalendar(month, year); //print specified calendar
    
    }

    
    /**
        * This method prints to the screen each month's calendar, which
        * includes the month header and the month body, for the specified
        * entire year (12 total calendars).
        *
        * Preconditions: The year is the full four digit year (e.g. 2017)
        *
        * Postcondition: Screen shows the the month calendar, 7 columns
        *                Screen prints 12 of these calendars
        *
        * return none
        *
        * @author Baseem Astiphan
        * @version 1.0.0.0
        * @param year     the integer four digit year (e.g. 2017)
        *
    */    
    public static void printAllCalendars(int year)
    {
        //Print calendar for corresponding month and year
        for (int i = 1; i <=12; i++)
        {
            printMonthCalendar(i, year);
        }
    }

    /**
        * This method prints to the screen the month's calendar, which
        * includes the month header and the month body, for the specified
        * month and year.
        *
        * Preconditions: The month value is 1-12
        *                The year is the full four digit year (e.g. 2017)
        *
        * Postcondition: Screen shows the the month calendar, 7 columns
        *
        * return none
        *
        * @author Baseem Astiphan
        * @version 1.0.0.0
        * @param month    the integer for the month of year (Jan = 1.. Dec = 12)
        * @param year     the integer four digit year (e.g. 2017)
        *
    */
    public static void printMonthCalendar(int month, int year)
    {
        printMonthHeader(month, year); //print month header
        printMonthBody(month, year); //print month body
    }

    /**
        * This method prints to the screen the month's header, which
        * is the month, year, border, and the three letter days of the 
        * week starting with Sun and ending with Sat.
        *
        * Preconditions: The month value is 1-12
        *                The year is the full four digit year (e.g. 2017)
        *
        * Postcondition: Screen shows the the header of the calendar, 7 columns
        *
        * return none
        *
        * @author Baseem Astiphan
        * @version 1.0.0.0
        * @param month    the integer for the month of year (Jan = 1.. Dec = 12)
        * @param year     the integer four digit year (e.g. 2017)
        *
    */            
    public static void printMonthHeader(int month, int year)
    {
        //Print the name of the month, with leading spaces for formatting
        System.out.print(addLeadingSpaces(getMonthName(month), COLUMN_WIDTH * 4));
        
        //Print the calendar year with leading spaces for formatting
        System.out.print(addLeadingSpaces(Integer.toString(year), (int)(COLUMN_WIDTH * 1.5)));
        System.out.println(); //blank line for formatting
        
        //Print a series of hyphens to serves as a border
        System.out.println(new String(new char[COLUMN_WIDTH * 7]).replace('\0', '-'));
        
        //Print 3 letter name of day. Start at 0 since Sun should come 1st
        for (int i = 0; i < 7; i++)
        {
            if (i == 0) //Sunday should be first, force calculation
            {
                System.out.print(addLeadingSpaces(getDayName(7), COLUMN_WIDTH));
            }
            else //correspond i to the day of the week using helper method
            {
                System.out.print(addLeadingSpaces(getDayName(i % 7), COLUMN_WIDTH));   
            }
        }
        System.out.println(); //blank line for formatting
    }

    /**
        * This method prints to the screen the month's body, which
        * is the number of each day of the month in chronological
        * order. Each number should correspond to the appropriate day
        * of the week, and there should only be 7 numbers (max) per row.
        * There can be less in cases where the month does not contain
        * the full week.
        *
        * Preconditions: The month value is 1-12
        *                The year is the full four digit year (e.g. 2017)
        *
        * Postcondition: Screen shows the the body of the calendar, maximum 7 days
        *                per row
        *
        * return none
        *
        * @author Baseem Astiphan
        * @version 1.0.0.0
        * @param month    the integer for the month of year (Jan = 1.. Dec = 12)
        * @param year     the integer four digit year (e.g. 2017)
    */        
    public static void printMonthBody(int month, int year)
    {
        /*
            Determine on which day the month started. NOTE: take a modulus
            of 7 of the first day to account for Mon = 1, but calendar displays
            Sunday as the first item (Sun = 7, though). By calculating start 
            mod 7, a Sunday first day corresponds to 0, and outputs first.
        */
        int start = getStartDay(month, year) % 7;
        
        //Start loop at 0 though days of month start at 1 to account for Mon=1
        //but Sun is first displayed day. Also, we should add the start day of
        //the month because the loop continues over values below the start day.
        for (int i = 0; i < getNumDaysInMonth(month, year) + start; i++)
        {   
            if (i < start) //iterator is less than startDayOfMonth
            {
                //Print nothing, padded with lead spaces for formatting
                System.out.print(addLeadingSpaces("", COLUMN_WIDTH));
                continue; //jump to the top of the loop
            }
            
            //Because i incremented though days haven't printed to calendar, we 
            //must deduct the value of the start day from the current iterator
            //then add 1 for boundaries. Add lead spaces for formatting
            System.out.print(addLeadingSpaces(Integer.toString(i - start + 1), COLUMN_WIDTH));
            
            //Since started index at 0, if i + 1 mod 7 == 0, end row, println
            if ((i + 1) % 7 == 0)
            {
                System.out.println();
            }
        }   

        System.out.println("\n"); //two blank rows to end calendar
    }

    /**
        * This method returns a String representing the full month
        * name corresponding to an integer (1 - 12). In this case,
        * Jan = 1, Feb = 2, ... Dec = 12
        *
        * Preconditions: The month value is 1-12 
        *
        * Postcondition: Returns full name of month, or "None" if number
        *                is out of bounds.
        *
        * return String: Name of Month
        *
        * @author Baseem Astiphan
        * @version 1.0.0.0
        * @param month    the integer for the month of year (Jan = 1.. Dec = 12)
        *
    */    
    public static String getMonthName(int month)
    {
        switch(month)  //month of the year
        {       
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "None";
        }
    }

    /**
        * This method returns a String representing the three letter
        * day of the week corresponding to an integer (1 - 7). In this case,
        * Mon = 1, Tue = 2, Wed = 3, Thu = 4, Fri = 5, Sat = 6, and Sun = 7.
        *
        *
        * Preconditions: The day value is 1-7 
        *
        * Postcondition: Returns three letter day of week, or "None" if number
        *                is out of bounds.
        *
        * return String: three letter day of week
        *
        * @author Baseem Astiphan
        * @version 1.0.0.0
        * @param day    the integer for the day of the week (Mon = 1.. Sun = 7)
        *
    */    
    public static String getDayName(int day)
    {
        switch(day)  //day of week
        {       
            case 1: return "Mon";
            case 2: return "Tue";
            case 3: return "Wed";
            case 4: return "Thu";
            case 5: return "Fri";
            case 6: return "Sat";
            case 7: return "Sun";
            default: return "None";
        }
    }
    
     /****
        The method getStartDay() implements Zeller's Algorithm for determining the
        day of the week the first day of a month is. The method adjusts Zeller's
        numbering scheme for day of week ( 0=Saturday, ..., 6=Friday ) to conform
        to a day of week number that corresponds to ISO conventions (i.e.,
        1=Monday, ..., 7=Sunday)
      
      Pre-Conditions: The month value, m,  is 1-12
                      The year value, y, is the full year (e.g., 2012)
                      
      Post-Conditions: A value of 1-7 is returned, representing the first day of
      the month: 1 = Monday, ..., 7 = Sunday
    ****/
    public static int getStartDay( int month,  int year )
    {
        final int day = 1; // Must be set to day 1 for this to work.  JRD.

        // Adjust month number & year to fit Zeller's numbering system
        if ( month < 3 ) 
        {
            month = month + 12;
            year = year - 1;
        }

        int yearInCent = year % 100;      // k Calculate year within century
        int century = year / 100;      // j Calculate century term
        int firstDay  = 0;            // h Day number of first day in month 'm'

        firstDay = (day + (13 * (month + 1) / 5) + yearInCent +
            (yearInCent / 4) + (century / 4) + (5 * century)) % 7;

        // Convert Zeller's value to ISO value (1 = Mon, ... , 7 = Sun )
        int dayNum = ((firstDay + 5) % 7) + 1;     

        return dayNum;
    }

    /**
        * This method returns an integer detailing the number of days
        * in the month and year input as arguments. This method appropriately
        * calculates that Februaries have 29 days in leap years
        *
        * Preconditions: The month value is 1-12
        *                The year value is the full year (e.g. 2017)
        *
        * Postcondition: Returns discrete values: 28, 29, 30, or 31
        *
        * return integer: total number of days in a month
        *
        * @author Baseem Astiphan
        * @version 1.0.0.0
        * @param month          the integer for the desired month
        * @param year           the integer for the year being questioned
        *
    */    
    public static int getNumDaysInMonth(int month, int year)
    {
        if (month == 2)  // If month is February, check for leap year
        {
            if (isLeapYear(year))  //isLeapYear = true
            {
                return 29; // 29 days in a leap year
            }
            else // not a leap year
            {
                return 28; //28 days in a non-leap year
            }
        }
        //Odd number months before Aug / Even number months >= Aug have 31 days
        else if ((month < 8 && month % 2 == 1) || (month >= 8 && month % 2 ==0))
        {
            return 31; //month has 31 days
        }
        else //Month != 2, Month < Aug and even or Month >= Aug and odd
        {
            return 30;  //30 days in the month
        }
    }

    /**
        * This method returns a boolean dictating whether the year
        * furnished as an argument represent a leap year. The 
        * leap year calculation applies on years that are both evenly 
        * divisible by 4 but NOT evenly divisible by 100, unless the year
        * is evenly divisible by 400.
        *
        * return boolean: true if the year questioned is a leap year
        *
        * @author Baseem Astiphan
        * @version 1.0.0.0
        * @param year            the integer for the year being questioned
        *
    */
    public static boolean isLeapYear(int year)
    {
        //Check if year meets leap year rules. (Evenly divisible by 4 AND not
        //evenly divisible by 100, OR evenly divisible for 400).
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
        {
            return true;  //leap year
        }
        else
        {
            return false;  //not a leap year
        }
    }
    
    /**
        * This helper method returns a String value with leading spaces
        * to total a desired length. This feature is useful for formatting
        * when text values may be of different lengths, but there is a need
        * for uniform total columnar spacing.
        *
        * return String: the original text with leading spaces if 
        *                 necessary (see precondition)
        *
        * @author Baseem Astiphan
        * @version 1.0.0.0
        * @param text            the text to be returned with leading spaces
        * @param desiredLength  the desired total length or text + spaces
        *
    */
    public static String addLeadingSpaces(String text, int desiredLength)
    {
        //Confirm that text length is less than desiredLength, or return original text
        if (text.length() >= desiredLength)
        {
            return text; //original text
        }
        else
        {
            //Pad the text argument with the appropriate number of leading spaces
            return new String(new char[desiredLength - text.length()]).replace('\0',' ') + text;
        }
    }
}

