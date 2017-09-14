/* Welcome the user and explain the instructions
    "Guess the Random Number"
    **********************************
    Enter a maximum and the computer will generate 
    a random number between 1 and your maximum. Designate
    the number of guesses within which you would like to 
    guess the random number. GOOD LUCK!
    **********************************
    
    Infinite loop (while (true))
        You can exit at any time by entering the letter 'q'
        
        Enter a designated maximum
            Test for quit
            Error --> continue
        How many guesses would you like to use?
            Test for quit
            Error --> continue
            
        OK. I've selected a random number.
        You have NNN guesses to figure it out!
        for loop
            "Guess " + i
            Test for quit
            Error --> continue
            Test for equality
                Congrats, you've won!
                Continue
                --
                Sorry, try again
            
        Sorry, you have not guessed in the correct number.
        The number was: NNN
            
        
*/

import java.util.Scanner;

public class RandomNumberGame
{
    public static void main(String [] args)
    {
        System.out.println("Welcome to the Random Number Game!");
        System.out.println("************************************************");
        System.out.println("INSTRUCTIONS");
        System.out.println("------------------------------------------------");
        System.out.println("\tEnter an upper bound for the random number.");
        System.out.println("\tThe program will generate a number between 1 and");
        System.out.println("\tthe designated upper bound. ");
        System.out.print("\tSelect the maximum number of guesses ");
        System.out.println("you'd like to use.");
        System.out.println("\nGood luck!");
        System.out.println("-------------------------------------------------\n");
        
        Scanner input = new Scanner(System.in);
        int maxNumber = 1;     //the upper bound of the random number range
        int maxGuesses = 0;    //the maximum number of guesses
        int usedGuesses;   //number of used guesses
        int numberToGuess = 0; //randomly generated number to be guessed
        int currentGuess = 0;  //the currently selected guess
        boolean inputError = false;   //incorrect input, requery user
        
        while(true)
        {
            inputError = false; //reset inputError
            
            //Use a do while loop here to test for incorrect input and requery
            //user for the upper bound of the random range
            do
            {
                System.out.print("Upper bound for the random number: ");
                
                //Check if the user input is NOT an integer compatible type
                inputError = !input.hasNextInt();
                if (inputError)
                {
                    input.next();   //flush out the scanner
                    System.out.println("\nPlease enter a whole number!");
                }
                else 
                {
                    maxNumber = input.nextInt(); //set upper boundary
                }
            }
            while (inputError);
            
            System.out.println(); //Blank lines for formatting
            
            //Use a do while loop here to test for incorrect input and requery
            //the user for maximum number of guesses
            do
            {
                System.out.print("Maximum number of guesses: ");
                
                //Check if the user input is NOT an integer compatible type
                inputError = !input.hasNextInt();
                if (inputError)
                {
                    input.next();   //flush out the scanner
                    System.out.println("\nPlease enter a whole number!");
                }
                else 
                {
                    maxGuesses = input.nextInt(); //set upper boundary
                }
            }
            while (inputError);
            
            //Generate and store the random number
            numberToGuess = (int)(maxNumber * Math.random()) + 1;
            
            int i;
            
            success: {
                for (i = 1; i <= maxGuesses; i++)
                {
                    //Use a do while loop here to test for incorrect input and requery
                    //user for the current guess
                    do
                    {
                        System.out.print("Guess " + i + ": ");
                        
                        //Check if the user input is NOT an integer compatible type
                        inputError = !input.hasNextInt();
                        if (inputError)
                        {
                            input.next();   //flush out the scanner
                            System.out.println("\nPlease enter a whole number!");
                        }
                        else 
                        {
                            currentGuess = input.nextInt(); //store current guess
                        }
                    }
                    while (inputError);
                    
                    if (currentGuess == numberToGuess)
                    {
                        System.out.print("Congratualtions. You guessed the ");
                        System.out.println("random number correctly.");
                        System.out.println("You used " + i + " guess(es)");
                        break success;
                    }
                    else
                    {
                        System.out.println("Sorry, that was incorrect.");
                        System.out.println((maxGuesses - i) + " guesses reamining.");
                    }
                }
                
                System.out.println("Sorry, you are out of guesses.");
                System.out.println("The correct answer was " + numberToGuess + ".");
                
            }
        }
        
    }
}        