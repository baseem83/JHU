import java.util.Scanner;
import java.io.*;

public class MainMethods
{
    private static File dataFile;
    
    public static void main(String [] args)
    {
        getOrCreateDatabase(args[0]);
    }
    
    public static void getOrCreateDatabase(String fileName)
    {
        File data = new File(fileName);
        Scanner input = new Scanner(System.in);
        
        if (data.exists())
        {
            dataFile = data;
        }
        else
        {
            System.out.print(fileName + " does not exist. Would you like to create a new file? [y / n] ");
            if (input.nextLine().trim().toLowerCase().equals("y"))
            {
                System.out.println("Trying...");
                try
                {
                    data.createNewFile();
                }
                catch (IOException ex)
                {
                    System.out.println("File failed to create. Please try again.");
                }
            }
            else
            {
                System.out.println("Exiting application ......");
                return;
            }
        }
    }
}