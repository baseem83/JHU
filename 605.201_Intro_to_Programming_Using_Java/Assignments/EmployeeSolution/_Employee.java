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

    public Employee()
    {
        this(++Employee.employeeNumberIncrementer);
    }
    
    public Employee(int empNumber)
    {
        employeeNumber = empNumber;
        setOrUpdateName();
        setOrUpdateAddress();
        setOrUpdateHireDate();
    }
    
    public void setOrUpdateName()
    {
        if (employeeName == null)
        {
            employeeName = new Name();
        }
    }
    
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
    
    public String getEmployeeName()
    {
        return employeeName.getFullName();
    }

    public void setOrUpdateAddress()
    {
        if (employeeAddress == null)
        {
            employeeAddress = new Address();
        }
    }
    
    public void setOrUpdateAddress(String street, String city, String state, String zip)
    {
        if (employeeAddress == null)
        {
            employeeAddress = new Address(street, city, state, zip);
        }
        else
        {
            employeeAddress.setFullAddress(street, city, state, zip);
        }
    }
    
    public String getEmployeeAddress()
    {
        return employeeAddress.getFullAddress();
    }

    public void setOrUpdateHireDate()
    {
        if (employeeHireDate == null)
        {
            employeeHireDate = new HireDate();
        }
    }
    
    public void setOrUpdateHireDate(int month, int day, int year)
    {
        if (employeeHireDate == null)
        {
            employeeHireDate = new HireDate(month, day, year);
        }
        else
        {
            employeeHireDate.setFullHireDate(month, day, year);
        }
    }
    
    public String getEmployeeHireDate()
    {
        return employeeHireDate.getFullHireDate();
    }
    
    public String toString()
    {
        return "Employee Num:\t" + employeeNumber + 
            "\n" + employeeName.toString() + 
            "\n" + employeeAddress.toString() + 
            "\n" + employeeHireDate.toString();
    }
    
    public static void resetEmployeeNumberIncrememnter(int currentNumber)
    {
        employeeNumberIncrementer = currentNumber;
    }
    
    public static int getCurrentEmployeeNumberIncrementer()
    {
        return employeeNumberIncrementer;
    }
    
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