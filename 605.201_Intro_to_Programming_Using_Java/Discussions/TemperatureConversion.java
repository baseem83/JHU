/**
    * This application converts a temperature value from fahrenheit/celsius
	* to its counterpart.
    * @author:Baseem Astiphan
    * @version:1.0.0.0
*/

import java.util.Scanner;

public class TemperatureConversion
{
    public static void main( String [] args )
    {
        int userChoice = 0;       // User selection: 1, 2, 3

        while( userChoice != 3 )
        {
            userChoice = mainMenu();  //Present user with options and record selection
            ProcessEntry(userChoice); //Select the appropriate conversion based on selection
        }
    }
    
    //*******************************************************************************************
    //REGION: INPUT METHODS. Methods that control user input
     
    /**
        * This method outputs the main menu to the screen
        * and accepts the user's input
        *
        * return int. The user's menu selection
        *
        * precondition: the user's selection is within range 1-3
        * postcondition: the user's selection is returned
        *
        * @author: Baseem Astiphan
    */        
    static int mainMenu()
    {
        Scanner input = new Scanner(System.in);
        System.out.print( "Enter 1 to convert F->C, 2 to convert C->F, 3 to quit: " );
        return input.nextInt();
    }
    
    /**
        * This method outputs a requset for fahrenheit value
        * and accepts the user's input
        *
        * return float: The fahrenheit value
        *
        * precondition: the user input a float compatible value
        * postcondition: the fahrenheit value is returned
        *
        * @author: Baseem Astiphan
    */        
    static float GetFahreinheit()
    {
        Scanner input = new Scanner(System.in);
        System.out.print( "Enter a Fahrenheit temperature: " );
        return input.nextFloat();
    }

    /**
        * This method outputs a requset for celsius value
        * and accepts the user's input
        *
        * return float: The celsius value
        *
        * precondition: the user input a float compatible value
        * postcondition: the celsius value is returned
        *
        * @author: Baseem Astiphan
    */            
    static float GetCelsius()
    {
        Scanner input = new Scanner(System.in);
        System.out.print( "Enter a Celsius temperature: " );
        return input.nextFloat();
    }
    
    //END REGION INPUT METHODS
        
    //*******************************************************************************************
    //REGION: OUTPUT METHODS. Methods that output control output of information
    
    
     /**
        * This method handles workflow based on user selection
        * 
        * return none. Outputs information to screen
        *
        * postcondition: Information is printed to screen
        *
        * @author: Baseem Astiphan
    */
    static void ProcessEntry(int userSelection)
    {
        float temperatureFahrenheit = 0;              // Fahrenheit temperature
        float temperatureCelsius = 0;                 // Celsius temperature
        
        switch( userSelection )
        {
            case 1:     // Convert Fahrenheit to Celsius
                temperatureFahrenheit = GetFahreinheit(); // User input Fahrenheit val
                
                //Convert Fahrenheit value to Celsius
                temperatureCelsius = convertFToC(temperatureFahrenheit); 
                
                //Display result to user
                outputFToC(temperatureFahrenheit, temperatureCelsius);
                break;
            case 2:     // Convert Celsius to Fahrenheit
                temperatureCelsius = GetCelsius(); //user input Celsius value
                
                
                /*
                    Below converts the celsius temperature and outputs to screen
                    in a single line
                */
                outputCToF(temperatureCelsius, convertCToF(temperatureCelsius));
                
                // //Convert Ceslius value to Fahrenheit
                // temperatureFahrenheit = convertCToF(temperatureCelsius);
                
                // //Display result to user
                // outputCToF(temperatureCelsius, temperatureFahrenheit);
                break;
            case 3:     // End Program
                adios();  //Display quit message to user
                break;
            default:    // Invalid Data Entered
                invalidEntry();  //Display invalid entry message to user
        }
    }
    
    
    /**
        * This method converts a fahrenheit value into celsius.
        * 
        * param fahrenheit is the fahrenheit value
        * param celsius is the celsius value
        * return none. Outputs a message to screen
        *
        * precondition: fahrenheit and celsius are both float compatible arguments
        * postcondition: the converted celsius value is printed to screen
        *
        * @author: Baseem Astiphan
    */
    static void outputFToC(float fahrenheit, float celsius)
    {
        //Print out message to the screen
        System.out.println(fahrenheit + " degrees Fahrenheit is " + celsius + " degrees Celsius" );
    }
    
    
    /**
        * This method converts a celsius value into fahrenheit.
        * 
        * param celsius is the celsius value
        * param fahrenheit is the fahrenheit value
        * return none. Outputs a message to screen
        *
        * precondition: fahrenheit and celsius are both float compatible arguments
        * postcondition: the converted fahrenheit value is printed to screen
        *
        * @author: Baseem Astiphan
    */
    static void outputCToF(float celsius, float fahrenheit)
    {
        //Print message to the screen
        System.out.println(celsius + " degrees Celsius is " + fahrenheit + " degrees Fahrenheit" );
    }

    /**
        * This method outputs a message to the user if an entry is out of range
        * 
        * return none. Outputs a message to screen
        *
        * postcondition: the error message is printed to screen
        *
        * @author: Baseem Astiphan
    */
    static void invalidEntry()
    {
        System.out.println("Invalid Data: You must enter 1, 2, or 3");
    }
    
    /**
        * This method outputs a quit message to the screen
        * 
        * return none. Outputs a message to screen
        *
        * postcondition: the quit message is printed to screen
        *
        * @author: Baseem Astiphan
    */    
    static void adios()
    {
        System.out.println("Bye Bye");
    }
    //END OUTPUT METHODS
    
    //*******************************************************************************************
    /*
        REGION: CONVERSION METHODS. This code section contains all methods that 
        convert data from one format to another
    */
    
    /**
        * This method converts a fahrenheit value into celsius.
        * 
        * param fahrenheit is the fahrenheit value
        
        * return float. The converted celsius value
        *
        * precondition: fahrenheit is float compatible 
        * postcondition: the converted celsius value is returned
        *
        * @author: Baseem Astiphan
    */
    static float convertFToC(float fahrenheit)
    {
        return 5F/9F * ( fahrenheit - 32F );
    }

    /**
        * This method converts a celsius value into fahrenheit.
        * 
        * param celsius is the celsius value
        
        * return float. The converted fahrenheit value
        *
        * precondition: celsius is float compatible 
        * postcondition: the converted fahrenheit value is returned
        *
        * @author: Baseem Astiphan
    */
    static float convertCToF(float celsius)
    {
        return 9F/5F * celsius + 32F;
    }
    
    /*
        END CONVERSION METHODS
    */
    
}


    