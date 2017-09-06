/**
 * This program requests 6 integers from the user
 * and displays them in tabular 3 rows by 2 column 
 * format. The program then reports a total by both row and column
 * @author: Baseem Astiphan
 * @version: 1.0.0.0
**/

import java.util.Scanner;

public class NumericEntry
{
    public static void main( String [] args )
    {
        // Define and initialize variables for values to be input
        int userNum1 = 0;      // First value to be input
        int userNum2 = 0;      // Second value to be input
        int userNum3 = 0;      // Third value to be input
        int userNum4 = 0;      // Fourth value to be input
        int userNum5 = 0;      // Fifth value to be input
        int userNum6 = 0;      // Sixth value to be input
        int colOneTotal = 0;   //Subtotal of column 1 
        int colTwoTotal = 0;   //Subtotal of column 2
        int grandTotal = 0;    // Grand total of all inputs
        
        // Use a Scanner to input integer values
        Scanner input = new Scanner( System.in );
        System.out.println( "\n\n" );   //Whitespace for formatting
        System.out.print( "Enter 6 integers separated by a blank space:" );
        userNum1 = input.nextInt();     // Input first value
        userNum2 = input.nextInt();     // Input second value
        userNum3 = input.nextInt();     // Input third value
        userNum4 = input.nextInt();     // Input fourth value
        userNum5 = input.nextInt();     // Input fifth value
        userNum6 = input.nextInt();     // Input sixth value
        
        // Output using System.out.println()
        System.out.println( "\n\n" );
        
        //Create headers. Add each header on a separate line of source code
        //so that it's easier to add new columns in the future, if necessary.
        System.out.print( "\t" + "Value" );
        System.out.print( "\t" + "Value" );
        System.out.print( "\t" + "Total" );
        System.out.println();
        
        //Display row 1 user entries and row total to the screen. 
        System.out.print( "\t" + userNum1 + "\t" + userNum2);
        System.out.println("\t" + (userNum1 + userNum2) );
        
        //Display row 2 user entries and row total to the screen. 
        System.out.print( "\t" + userNum3 + "\t" + userNum4);
        System.out.println("\t" + (userNum3 + userNum4) );
        
        //Display row 3 user entries and row total to the screen. 
        System.out.print( "\t" + userNum5 + "\t" + userNum6);
        System.out.println("\t" + (userNum5 + userNum6) );
        
        System.out.println( "\t---\t---\t---"); //Display subtotal lines
        
        System.out.print("Total");  //Set row footer label
        
        //Subtotal the two individual columns, then display to screen
        colOneTotal = userNum1 + userNum3 + userNum5;
        colTwoTotal = userNum2 + userNum4 + userNum6;
        System.out.print( "\t" + colOneTotal);
        System.out.print( "\t" + colTwoTotal);
        
        //Find the grand total, and display it to screen
        grandTotal += colOneTotal;
        grandTotal += colTwoTotal;
        System.out.print( "\t" + grandTotal);
        
        System.out.println( "\n\n" );   //Whitespace for formatting
    }
}
