/**
    * This class templates a general Employee. It includes a name Object,
    * an address object, and a hireDate object. In addition, each Employee
    * has an employeeNumber. The system autoincrements and generates this 
    * employeeNumber. This can be overriden, but it's generally frowned upon
    * because it can lead to issues if inserted into a database. Use cautiously.
    *
    * On instantiation, the Employee constructor creates new contained objects
    * set to their default values.
    * 
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/
public class Employee
{
    //Static variable holding the current employee number for incrementation
    private static int employeeNumberIncrementer = 0;
    
    private int employeeNumber;        //employee's number
    private Name employeeName;         //name object
    private Address employeeAddress;   // employee object
    private HireDate employeeHireDate; //employee hire date

    
/* BELOW SECTIONS ALL COMMENTED OUT FOR POSSIBLE FUTURE REMOVAL */    
 
    // /*
        // * Overloaded constructor when no arguments are passed. It chains 
        // * constructors with the parameterized constructor, passing in 
        // * an incremented employee number, and default values for contained
        // * objects.
        // *
        // * @author Baseem Astiphan
    // */    
    // public Employee()
    // {
        // this(++Employee.employeeNumberIncrementer, new Name(), new Address(), new HireDate());
    // }


        // POSSIBLEY REMOVING FOR THE FUTURE
    // /*
        // * Chained constructor with passed arguments. The constructor calls the
        // * autoincrements the employeeNumber, then uses the passed in objects
        // * as contained member objects of the class.
        // * 
        // * @author Baseem Astiphan
        // * @param String first The first name property of the name class
        // * @param String last  The last name property of the name class
        // * 
        // * @return Constructor that returns a new Name instance
    // */
    // public Employee(Name name, Address address, HireDate hireDate)
    // {
        // this(++Employee.employeeNumberIncrementer, name, address, hireDate);
    // }
    
    // public Employee(int empNumber, Name name, Address address, HireDate hireDate)
    // {
        // employeeNumber = empNumber;
        // employeeName = name;
        // employeeAddress = address;
        // employeeHireDate = hireDate;
    // }
    
/* END OF REMOVAL FOR FUTURE ---- REFORMAT THOUGH   */

    /**
        * Constructor when no arguments are passed. It chains constructors
        * with the parameterized constructor, passing in default values.
        *
        * @author Baseem Astiphan
    */        
    public Employee()
    {
        //Chain overloaded constructor, passing in next emp number
        //Chaining methods allows all logic to be encapsulated in one place
        this(++Employee.employeeNumberIncrementer);
    }
    
    /**
        * Constructor when arguments are passed. The constructor calls the
        * setOrUpdateName, setOrUpdateAddress, and setOrUpdateHireDate methods
        * to create new, contained instance of their corresponding objects set 
        * to their default values. It also assigns the empNumber to the 
        * employeeNumber variable.
        * Methods are used rather than direct assignment  
        * so that error checking can be applied in only one place throughout 
        * the code (encapsulation).
        * 
        * @author Baseem Astiphan
        * @param int empNumber Employee Number
        * 
        * @return Constructor that returns a new Employee instance
    */        
    
    public Employee(int empNumber)
    {
        //Set employeeNumber
        employeeNumber = empNumber;
        setOrUpdateName();     //create a default Name object
        setOrUpdateAddress();  //create a default Address object
        setOrUpdateHireDate(); //create a default HireDate object
    }
    
    /**
        * This method sets the employeeNumbers variable. Because employeeNumbers
        * are autoincremented and may be stored/used elsewhere, this should be 
        * used carefully and sparingly. It may cause duplicate entries.
        *  
        * postcondition new employee number
        * 
        * @author Baseem Astiphan
        * @param int num  The new employeeNumber
    */     
    public void setEmployeeNumber(int num)
    {
        // Assigns a new employee number
        employeeNumber = num;
    }    
    
    /**
        * This method returns the employeeNumber
        *
        * postcondition Employee number is returned
        * 
        * @author Baseem Astiphan
        * @return int return employeeNumber
    */        
    public int getEmployeeNumber()
    {
        //get the employeeNumber
        return employeeNumber;
    }
    
    /**
        * This method sets the employeeName variable to a new Name object
        * if one is not assigned. It initializes the name with default values.
        * 
        * postcondition employeeName is a new object
        * 
        * @author Baseem Astiphan
        * @param int month  The month of hire
    */     
    public void setOrUpdateName()
    {
        //Check if employee name is not assigned
        if (employeeName == null)
        {
            employeeName = new Name(); //assign a new object
        }
    }
    
    /**
        * This overloaded method assigns a new object to the employeeName
        * variable if one doesn't exist. Then it updates the name with
        * the arguments passed into the method.
        * 
        * postcondition Employee name with passed in values
        * 
        * @author Baseem Astiphan
        * @param String first  The employee first name
        * @param String last  The employee last name
    */    
    public void setOrUpdateName(String first, String last)
    {
        if (employeeName == null)
        {
            employeeName = new Name(first, last);
        }
        else
        {
            employeeName.setFullName(first, last);
        }
    }
    
    /**
        * This method returns the full name property
        *
        * postcondition Full name has is returned
        * 
        * @author Baseem Astiphan
        * @return String firstName + ' ' + lastName property
    */        
    public String getEmployeeName()
    {
        //get the employees full, formatted name
        return employeeName.getFullName();
    }

    /**
        * This method sets the employeeAddress variable to a new Address object
        * if one is not assigned. It initializes the address with default values.
        * 
        * postcondition employeeAddress is a new object
        * 
        * @author Baseem Astiphan
    */       
    public void setOrUpdateAddress()
    {
        //Chcek if employeeAddress has been assigned
        if (employeeAddress == null)
        {
            //Assign with a new object with default values
            employeeAddress = new Address();
        }
    }
    
    /**
        * This overloaded method assigns a new object to the employeeAddress
        * variable if one doesn't exist. Then it updates the address with
        * the arguments passed into the method.
        * 
        * postcondition Employee address with passed in values
        * 
        * @author Baseem Astiphan
        * @param String street  The employee street
        * @param String city    The employee city
        * @param String state   The employee state
        * @zip   String zip     The employee zip code  
    */    
    public void setOrUpdateAddress(String street, String city, String state, String zip)
    {
        //Check if employeeAddress is still null
        if (employeeAddress == null)
        {
            //If Null, assign a new address object with argument values
            employeeAddress = new Address(street, city, state, zip);
        }
        else //not null
        {
            //Update the employee address information
            employeeAddress.setFullAddress(street, city, state, zip);
        }
    }
 
    /**
        * This method returns the full address property
        *
        * postcondition Address is returned
        * 
        * @author Baseem Astiphan
        * @return String street + ' ' + city + ', ' + state + ' ' + zipCode
    */   
    public String getEmployeeAddress()
    {
        //Get the employee full address (method defined in Address class)
        return employeeAddress.getFullAddress();
    }

    /**
        * This method sets the employeeHireDate variable to a new HireDate object
        * if one is not assigned. It initializes the address with default values.
        * 
        * postcondition employeeHireDate is a new object
        * 
        * @author Baseem Astiphan
    */        
    public void setOrUpdateHireDate()
    {
        //Check if employeeHireDate has been assigned
        if (employeeHireDate == null)
        {
            //Initialize a new HireDate object with default values
            employeeHireDate = new HireDate();
        }
    }
    
    /**
        * This overloaded method assigns a new object to the employeeHireDate
        * variable if one doesn't exist. Then it updates the HireDate with
        * the arguments passed into the method.
        * 
        * postcondition Employee HireDate with passed in values
        * 
        * @author Baseem Astiphan
        * @param int month  The employee hire month
        * @param int day    The employee hire day
        * @param int year   The employee hire year
    */    
    public void setOrUpdateHireDate(int month, int day, int year)
    {
        //Check if employeeHireDate is unassigned
        if (employeeHireDate == null)
        {
            //Create new HireDate instance if unassigned; default values
            employeeHireDate = new HireDate(month, day, year);
        }
        else
        {
            //Update the hire date info (method defined in HireDate class)
            employeeHireDate.setFullHireDate(month, day, year);
        }
    }

    /**
        * This method returns the hire date property
        *
        * postcondition Hire Date is returned
        * 
        * @author Baseem Astiphan
        * @return String M/dd/yyyy
    */       
    public String getEmployeeHireDate()
    {
        //Get the employee hire date (method defined in HireDate class)
        return employeeHireDate.getFullHireDate();
    }
   
    /**
        * This method overrides the toString, and returns full employee 
        * information, including full name, full address, and hire date,
        * all neatly formatted.
        * 
        * postcondition Full employee information, formatted
        * 
        * @author Baseem Astiphan
        * @return String Employee Information
    */       
    public String toString()
    {
        //Fully formatted employee information
        return "Employee Num:\t" + employeeNumber + 
            "\n" + employeeName.toString() + 
            "\n" + employeeAddress.toString() + 
            "\n" + employeeHireDate.toString();
    }
    
    /**
        * This method overrides the toString, and returns full employee 
        * information, including full name, full address, and hire date,
        * all neatly formatted.
        * 
        * postcondition Full employee information, formatted
        * 
        * @author Baseem Astiphan
        * @return String Employee Information
    */           
    public static void resetEmployeeNumberIncrememnter(int currentNumber)
    {
        //set employeeNum to assigned value
        employeeNumberIncrementer = currentNumber;
    }
    
    /**
        * This method returns the current employeeNumberIncrementer.
        * 
        * 
        * @author Baseem Astiphan
        * @return int employeeNumberIncrementer
    */           
    public static int getCurrentEmployeeNumberIncrementer()
    {
        return employeeNumberIncrementer;
    }
    
    
    //UNIT TESTS CONSIDER REMOVING.
    public static void main (String [] args)
    {
        Employee emp1 = new Employee();
        
        System.out.println(emp1.toString());
        
        emp1.setOrUpdateAddress("123 Fake Street", "Boston", "MA", "11112");
        System.out.println();
        System.out.println(emp1.toString());
        
        emp1.setOrUpdateName("Fletcher", "Dukakis");
        System.out.println();
        System.out.println(emp1.toString());
        
        emp1.setOrUpdateHireDate(10, 5, 2017);
        System.out.println();
        System.out.println(emp1.toString());
        
        System.out.println();
        System.out.println(emp1.getEmployeeNumber());
        
        System.out.println();
        emp1.setEmployeeNumber(66);
        System.out.println(emp1.getEmployeeNumber());
        
        // Employee emp = new Employee();
        // System.out.println(emp.toString());
        
        // Employee emp1 = new Employee();
        // System.out.println(emp1.toString());
        
        // emp1.setOrUpdateName();
        // System.out.println();
        // System.out.println(emp1.toString());
        
        // emp1.setOrUpdateName("Bill", "Poophead");
        // System.out.println();
        // System.out.println(emp1.getEmployeeName());
        
        // emp1.setOrUpdateAddress();
        // System.out.println();
        // System.out.println(emp1.toString());
        
        // emp1.setOrUpdateAddress("123 Fake Street", "New York", "NY", "10027");
        // System.out.println();
        // System.out.println(emp1.toString());
        // System.out.println(emp1.getEmployeeAddress());
        
        // emp1.setOrUpdateHireDate();
        // System.out.println();
        // System.out.println(emp1.toString());
        // emp1.setOrUpdateHireDate(10,3,2017);
        // System.out.println(emp1.toString());
        // System.out.println(emp1.getEmployeeHireDate());
        
        // Employee emp2 = new Employee(new Name("Fred", "Flintstone"), 
                                     // new Address("222 Fun St", "Englewood", "NJ", "07631"),
                                     // new HireDate(9,30,2017));
                                     
        // System.out.println(emp2.toString());
        
        // Employee.resetEmployeeNumberIncrememnter(6);
        
        // Employee emp3 = new Employee(new Name("FrAAed", "Flintstone"), 
                                     // new Address("22222 Fun St", "Englewood", "NJ", "07631"),
                                     // new HireDate(9,15,2017));
                                     
        // System.out.println(emp3.toString());
        
        // Name n = new Name("Fletcher", "Dukakis");
        // Address a = new Address("100 Cool Street", "Boston", "MA", "91812");
        
        // emp3.setOrUpdateName(n);
        // emp3.setOrUpdateAddress(a);
        // System.out.println(emp3.toString());
        // System.out.println("\n");
        
        // a.setStreet("444 Anallys Road");
        // System.out.println(emp3.toString());
        // System.out.println("\n");
    }
    
    
    
}