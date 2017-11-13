import java.util.Scanner;
import java.util.LinkedList;

/**
    *This class templates a print queue, creates a FIFO list of print
    * jobs.
    *
    *
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/
public class PrintQueue
{
    public static void main (String [] args)
    {
        //Scanner for user input
        Scanner input = new Scanner(System.in);

        //Variable to hold user selection
        int userSelection;

        //Linked list of job to serve as print queue
        LinkedList<Job> ll = new LinkedList<Job>();

        //Get user input
        userSelection = getInteger("\nEnter [1] to add a job, or [0] to quit",
                                    input, 0, 1);
        //Loop through user inputs
        while (userSelection != 0)
        {
            //Create a new job
            Job job = new Job();
            
            //Add job to linked list
            ll.add(job);
            System.out.println("Added job number " + job.getJobNumber() + " to the Print Queue.");
            
            //Get users next step
            userSelection = getInteger("\nEnter [1] to add a job, or [0] to quit",
                                    input, 0, 1);            
        }

        //Print all jobs on exit
        for (Job aJob : ll)
        {
            System.out.println(aJob);   
        }
    }

       /**
        * This helper method returns an integer value based on user input.
        * The method takes a user output message, an instantized Scanner object,
        * a lower bounded value, and an upper bounded value.
        *
        * return int: the validated user input
        *
        * @author Baseem Astiphan
        * @param message        the text to be displayed to user
        * @param input          instantized Scanner object
        * @param lowBound       the minimum accepted user input
        * @param upperBound     the maximum accepted user input   
        * @return integer value
        *
    */
    public static int getInteger(String message, Scanner input, int lowBound, int upperBound)
    {
        boolean inputError = false; // maintain error state
        int output = 0; //output to be retured
        
        do
        {
            System.out.print(message + ": "); //display output message to user
            
            //Check if the user input is NOT an integer compatible type
            inputError = !input.hasNextInt();
            if (inputError)  //error
            {
                input.next();   //flush out the scanner
            }
            else 
            {
                output = input.nextInt(); //get next input
                
                //Confirm entry is between lower and upper bound
                if (output < lowBound || output > upperBound)
                {
                    inputError = true; //error, outside of bounds
                }
            }
        }
        while (inputError); //retry if not an integer
        
        return output; //return the user entry
    }
}