/**
    * This program displays plus signs in a console window.
    * The user indicates the maximum number of plus signs when prompted.
    * The user indicates whether the output increases or decreases based on 
    * response to a prompt( 1 = descreasing, 2 = increasing)
    * @author:Baseem Astiphan
    * @version:1.0.0.0
*/

import java.util.Scanner;

public class PlusSignGenerator 
{
    //Declare constant printed character. Using const increases program flexibility
    public static final char CHARACTER_TO_SCREEN = '+';
    
    public static void main (String [] args)
    {
        int maximumPlusSigns; //maximum plus signs
        byte sortOrder; //sort order (1 = decreasing, 2 = increasing)
        int i;          //iterator variable
        
        //Instantiate scanner to read in user inputs from console
        Scanner input = new Scanner(System.in);
        
        //Prompt user for maximum plus signs
        System.out.print("\nPlease enter the maximum number of plus signs: ");
        maximumPlusSigns = input.nextInt(); //read in max plus signs
        
        //Prompt user for the sort order
        System.out.print("Select sort order (1 = decreasing 2 = increasing): ");
        sortOrder = input.nextByte(); //read in sort order
        
        /*
            Below handles errors when the sort order is out of range. Currently
            the program only accepts discrete values 1 or 2 (stored as byte).
            Originally, I considered storing this in a do-while loop, 
            and encapsulating all of the logic, but I wanted to output a 
            different message if a mistake was made (for clarity). 
            There is also the option of controlling the output message using a 
            state variable (first time through loop?), but that just increases 
            complexity with no gain.
        */
        while ((sortOrder != 1) && (sortOrder != 2))
        {
            System.out.println("\n****** Error ******"); //Error heading
            System.out.print("Sort order must be [1] or [2]."); //Err message
            System.out.println();
            System.out.print("[Type 1 for decreasing order. "); //Instructions
            System.out.print("Type 2 for increasing order.] : "); //Instructions
            sortOrder = input.nextByte(); //Store new user input over original
        }
        
        System.out.println();
        
        //Outer for loop to iterate through the number of expected rows
        for (i = 1; i <= maximumPlusSigns; i++)
        {
            //Locally scoped variable -- number of symbols in this rows
            int rowPlus;    // This will vary based on sort order
            
            /* Below calculates number of plus symbols for the row. Since there
               are only two options, I processed using a ternary operator. If 
               more options should be introduced, consider a switch statement. 
			   This calc leverages that a relationship always exists between 
			   the iterator and the current row. In descreasing order, the calc 
			   is the max number of plus signs minus the iteration, plus 1 to 
			   account for boundary values. In increasing order, it is simply 
			   the value of the iterator variable.
            */
            rowPlus = (sortOrder == 1) ? maximumPlusSigns - i + 1 : i;
            
            //Inner loop to write the correct number of symbols
            for (int j = 1; j <= rowPlus; j++)
            {
                System.out.print(CHARACTER_TO_SCREEN);
            }
            
            //Move to a new line for the next row
            System.out.println();
        }
    }
}
