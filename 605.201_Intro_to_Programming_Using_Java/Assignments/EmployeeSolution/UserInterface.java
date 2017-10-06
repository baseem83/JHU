/**
    * This class creates the UserInterface for our EmployeeSolution.
    * Users select how many employees they want to add to their 'company'.
    * A new Employee array is created to hold the appropriate number. Users 
    * enter all information, otherwise they are left with defaults.
    *
    * Once finished, the user interface asks the user if they would like to exit,
    * at which point it prints out all Employee information.
    * 
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/

import java.util.Scanner;

public class UserInterface
{
    public static void main(String [] args)
    {
        int empCount;   //number of employees
        Employee[] employeeList; //array to hold all employees
        String firstName; //variable to store the first name
        String lastName;  //variable to store the last name
        String street;    //variable to store the street name
        String city;      //variable to store city
        String state;     //variable to store state
        String zip;       //variable to store zip code
        int month;        //variable to store hire month
        int day;          //variable to store hire day
        int year;         //variable to store hire year
        
        //Instantiate new scanner for user inputs
        Scanner input = new Scanner(System.in);
        
        //Print instructions to screen
        System.out.print("How many employees would you like to create: ");
        
        empCount = input.nextInt();  //Get the number of employees
        input.nextLine();     //flush the scanner of \n
        
        //Instantiate the array to the correct number of employees
        employeeList = new Employee[empCount];
        
        //Loop through each employee, creating employee and contained objects
        for (int i = 0; i < empCount; i++)
        {
            employeeList[i] = new Employee();  //create new employee at index
            
            //Print heading 
            System.out.println("\nEmployee " + employeeList[i].getEmployeeNumber()
                    + " Name Information");
            System.out.println("--------------------------------------------");
           
            System.out.print("Enter employee's first name: ");  //output msg
            firstName = input.nextLine();  //get user input for emp first name
            
            System.out.print("Enter employee's last name: "); //output msg
            lastName = input.nextLine();  //get user input for emp first name
            
            //Update employee at index, setting new full name
            employeeList[i].setOrUpdateName(firstName, lastName);
            
            System.out.println();  //formatting
            
            //Print heading
            System.out.println("Employee " + employeeList[i].getEmployeeNumber() 
                    + " Address Information");
            System.out.println("--------------------------------------------");
            
            System.out.print("Enter employee's street address: "); //output msg
            street = input.nextLine();  //get user input for street
            System.out.print("Enter employee's city: "); //output msg
            city = input.nextLine(); //get user input for city
            System.out.print("Enter employee's state: "); //output msg
            state = input.nextLine(); //get user input for state
            System.out.print("Enter employee's zip code: "); //output msg
            zip = input.nextLine(); //get user input for zip
            
            //Update employee at index, setting new full address
            employeeList[i].setOrUpdateAddress(street, city, state, zip);
            
            System.out.println();  //formatting
            
            //Print heading
            System.out.println("Employee " + employeeList[i].getEmployeeNumber() 
                    + " Hire Date Information");
            System.out.println("--------------------------------------------");
            
            System.out.print("Enter employee's hire month: "); //output msg
            month = input.nextInt();  //get user input for hire month
            System.out.print("Enter employee's hire day: "); //output msg
            day = input.nextInt();  //get user input for hire day
            System.out.print("Enter employee's hire year: "); //output msg
            year = input.nextInt();  //get user input for hire year
            
            //Update employee at index, setting new hire date
            employeeList[i].setOrUpdateHireDate(month, day, year);
            
            input.nextLine();  //flush the scanner of \n
            
            System.out.println(); //formatting
        }
        
        System.out.print("Press any key to print employee information then exit: ");
        input.next();
        
        System.out.println();
        
        for(Employee emp : employeeList)
        {
            System.out.println(emp.toString());
            System.out.println("--------------------------------------------");
        }
        
    }
    
    private static void updateName(Employee emp, Scanner input)
    {
        
    }
}