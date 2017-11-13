import java.io.*;
import java.util.Scanner;

/**
    *This class templates an interface for end-user engagement.
    *
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/
public class ContactListInterface
{
    /**
        *Entry point of appliation
        *
        * @author Baseem Astiphan
    */
    public static void main(String [] args)
    {
        //variable to hold file name
        String fileName = null; 
        int userSelection;  //user input
        
        //Scanner for user input
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a filename containing your contacts: ");
        
        //Read in filename
        fileName = input.nextLine();

        //Confirm file exists
        if (!(new File(fileName).exists()))
        {
            System.out.println("File does not exist");
            return;
        }

        //Generate new contact list instance
        ContactList cl = new ContactList(fileName);

        //Show commands
        showCommands();

        //Use switchboard to control flow
        switchboard(input, cl);


    }

    /**
        * This methodc controls processing flow based on user input
        *
        * @author Baseem Astiphan
        * @param input a Scanner used to gather user input
    */ 
    public static void switchboard(Scanner input, ContactList cl)
    {
        //Accept user input and constrain values to appropriate range
        int userSelection = getInteger("Selection",
                            input, 0, 4);
        
        //Exit at 4
        while (userSelection != 4)
        {
            input.nextLine(); //flush scanner newline

            System.out.println();
            Contact contact = null;
            //Pick method based on user input
            switch(userSelection)
            {
                case 0:
                    showCommands();
                    break;
                case 1:
                    System.out.println("Enter a new contact, formatted as <last>,<first>,<company>,<phone>,<email>");
                    contact = Contact.parseContact(input.nextLine());
                    cl.addContact(cl.getContacts(), contact);
                    break;
                case 2:
                    System.out.println("Enter a contact to delete, formatted as <last> <first>");
                    String[] key = input.nextLine().split(" ");
                    cl.deleteContact(cl.getContacts(), key[0] + key[1], cl.getContactFile());
                    break;
                case 3:
                    cl.printContactList(cl.getContacts());
                    break;
                case 4:
                    break;
            }

            //Allow user to perform another command until quit
            userSelection = getInteger("Selection",
                            input, 0, 4);
        }
    }

    /**
        *Method to print to screen a list of user commands
        *
        * @author Baseem Astiphan
    */
    public static  void showCommands()
    {
        System.out.println("Welcome to the Contact List. Select an option");
        System.out.println("[0] List commands.");
        System.out.println("[1] Add a contact.");
        System.out.println("[2] Delete a contact.");
        System.out.println("[3] Print all contacts.");
        System.out.println("[4] Quit.");
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