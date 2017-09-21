public class CalendarGenerator
{
    public static void main (String [] args)
    {
        printMonthHeader(9, 2017);
        printMonthBody(9, 2017);
    
    }
    
    // public static void printMonthCalendar(int month, int year)
    // {
        
        
    // }

    public static void printMonthHeader(int month, int year)
    {
        System.out.print(addLeadingSpaces(getMonthName(month), 20));
        System.out.print(addLeadingSpaces(Integer.toString(year), 7));
        System.out.println();
        
        System.out.println(new String(new char[35]).replace('\0', '-'));
        
        for (int i = 0; i < 7; i++)
        {
            System.out.print(addLeadingSpaces(getDayName(i % 7), 4));
        }
        System.out.println();
    }

    public static void printMonthBody(int month, int year)
    {
        
    }

    public static String getMonthName(int month)
    {
        switch(month)
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

    public static String getDayName(int day)
    {
        switch(day)
        {       
            case 1: return "Mon";
            case 2: return "Tue";
            case 3: return "Wed";
            case 4: return "Thu";
            case 5: return "Fri";
            case 6: return "Sat";
            case 0: return "Sun";
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
  
    public static int getNumDaysInMonth(int month, int year)
    {
        if (month == 2)
        {
            if (isLeapYear(year))
            {
                return 29;
            }
            else
            {
                return 28;
            }
        }
        else if ((month < 8 && month % 2 == 1) || (month >= 8 && month % 2 ==0))
        {
            return 31;
        }
        else 
        {
            return 30;
        }
    }

    public static boolean isLeapYear(int year)
    {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static String addLeadingSpaces(String text, int desiredLength)
    {
        if (text.length() >= desiredLength)
        {
            return text;
        }
        else
        {
            return new String(new char[desiredLength - text.length()]).replace('\0',' ') + text;
        }
    }
}

