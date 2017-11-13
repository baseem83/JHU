import java.util.Random;

/**
    *This class templates a print job, which has both a 
    *job number and a estimated print time.
    *
    * The job number should be auto incremented, and therefore
    * stored as a static variable for the class
    *
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/
public class Job
{
    //Static variable to keep incrememnting job number
    private static int lastJobNumber = 0; 
    private int jobNumber; //instance job number
    private int estimatedPrintTime; //estimated print time

    /**
        * Get the assigned job number. Note, this is 
        * a read only field; no setter
        *
        * @author Baseem Astiphan
    */
    public int getJobNumber()
    {
        return jobNumber;
    }

    /**
        * Get the randomly assigned est print time. Note, this is 
        * a read only field; no setter
        *
        * @author Baseem Astiphan
    */
    public int getEstimatedPrintTime()
    {
        return estimatedPrintTime;
    }


    /**
        * Default constructor. Increments and assigns 
        * static job number variable
        * Randomly ssigns a print time.
    */
    public Job()
    {
        //increment the static job number
        jobNumber = ++lastJobNumber;

        //Create a random number generator instance
        Random rnGenerator = new Random();

        //set the estimated print time to a random between 10 - 1000
        estimatedPrintTime = rnGenerator.nextInt(991) + 10;
    }

    /**
        * Overridden toString method to return well formatted information.
        *
        * @author Baseem Astiphan
    */
    public String toString()
    {
        return "\nJob Number:      " + getJobNumber() + "\n" +
               "Est. Print Time: " + getEstimatedPrintTime();
    }

    /*
        Test methods below. Ignore
    */

    /*
    public static void main(String [] args)
    {
        Job j = new Job();
        Job j1 = new Job();

        System.out.println(j);
        System.out.println(j1);
    }
    */
}
