import java.util.Scanner;
import java.io.*;
import javafx.application.Application;

/**
    *Entry class for the application
    *
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/
public class SongDatabase
{
    /**
        *Method to open the application and assign a datafile.
        *
        * @author Baseem Astiphan   
        * @param args Command line args
    */
    public static void main(String [] args)
    {
        //Create a temp file
        File tempFile;

        //Scanner for input
        Scanner input = new Scanner(System.in);

        //Confirm command line arguments were included
        if (args.length == 0)
        {
            System.out.println("Please furnish a database file name");
            return;
        }

        //Set temp file
        tempFile = new File(args[0]);
        
        //Ask user if they want to create new file
        if (!tempFile.exists())
        {
            System.out.print(args[0] + " does not exist. " +
                    "Would you like to create it? [y / n] ");
            if(!input.nextLine().toLowerCase().trim().equals("y"))
            {
                System.out.println("Goodbye!");
                return;
            }
        }

        //Launch the GUI application
        SongDatabaseUserForm songDb = new SongDatabaseUserForm();
        Application.launch(SongDatabaseUserForm.class, args);
    }
}