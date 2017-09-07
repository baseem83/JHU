/**
 * This program takes a user's height (in inches)
 * and weight (in pounds) and calculates the Body Mass Index.
 * @author: Baseem Astiphan
 * @version: 1.0.0.0
**/

import java.util.Scanner;

public class BodyMassIndexCalculator 
{   
    //Declare constants
    static final float LBS_TO_KILO = 0.45359237F; //conversion ratio 
    static final float INCHES_TO_METERS = 0.0254F; //conversion ratio
    
    public static void main (String [] args)
    {
        float weight;             //User weight in pounds
        float height;             //User heigh in inches
        float bodyMassIndex;    //BMI Calculation
        
        //Use a scanner to read user inputs
        Scanner input = new Scanner(System.in);
        System.out.println( "\n" );   //Blank lines for formatting
        
        System.out.print("Enter height in inches: ");
        height = input.nextInt();   //read in the height
        
        System.out.print("Enter weight in pounds: ");
        weight = input.nextInt();    //read in the weight
        
        //Convert height and weight to metric counterparts 
        height *= INCHES_TO_METERS;      //use shorthand notation
        weight *= LBS_TO_KILO; //use shorthand notation
        
        //Calculate bodyMassIndex. NOTE: Math.pow() returns type double
        //so explicitly cast it to a float
        bodyMassIndex = weight / (float)Math.pow(height, 2);
        System.out.println("Body Mass Index: " + bodyMassIndex);
        
        //Display BMI info from Dept of Health and Human Services
        System.out.println();   //Blank line for formatting
        System.out.println("\tUnderweight: less than 18.5");
        System.out.println("\tNormal: 18.5 - 24.9");
        System.out.println("\tOverweight: 25.0 - 29.9");
        System.out.println("\tObese: over 30");
    }
}


/*
public class BodyMassIndexCalculator 
{   
    //Declare constants
    static final float LBS_TO_KILO = 0.45359237F; //conversion ratio 
    static final float INCHES_TO_METERS = 0.0254F; //conversion ratio
    
    public static void main (String [] args)
    {
        int weight;             //User weight in pounds
        int height;             //User heigh in inches
        float weightInKg;       //Convert weight to KG
        float heightInMeters;   //Convert height to meters  
        float bodyMassIndex;    //BMI Calculation
        
        //Use a scanner to read user inputs
        Scanner input = new Scanner(System.in);
        System.out.println( "\n" );   //Blank lines for formatting
        
        System.out.print("Enter height in inches: ");
        height = input.nextInt();   //read in the height
        
        System.out.print("Enter weight in pounds: ");
        weight = input.nextInt();    //read in the weight
        
        //Convert height and weight to metric counterparts
        heightInMeters = height * INCHES_TO_METERS;
        weightInKg = weight * LBS_TO_KILO;
        
        //Calculate bodyMassIndex. NOTE: Math.pow() returns type double
        //so explicitly cast it to a float
        bodyMassIndex = weightInKg / (float)Math.pow(heightInMeters, 2);
        System.out.println("Body Mass Index: " + bodyMassIndex);
        
        //Display BMI info from Dept of Health and Human Services
        System.out.println();   //Blank line for formatting
        System.out.println("\tUnderweight: less than 18.5");
        System.out.println("\tNormal: 18.5 - 24.9");
        System.out.println("\tOverweight: 25.0 - 29.9");
        System.out.println("\tObese: over 30");
    }
}

*/