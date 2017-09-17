/**
    * This game gives a player the opportunity to guess a random number.
	* The user specifies the range of the random number and the number
	* of guesses he/she would like to use. 
    * @author:Baseem Astiphan
    * @version:1.0.0.0
*/

import java.util.Scanner;

public class RandomNumberGame
{
    public static void main(String [] args)
    {
		/*  
			The below block of code introduces the game and the 
			instructions of the game.
		*/
        System.out.println("\n\nWelcome to the Random Number Game!");
        //System.out.println("************************************************");
        System.out.println("----------------------------------");
        System.out.println("INSTRUCTIONS");
        System.out.println("--------------------------------------------------------");
        System.out.println("\tEnter an upper bound for the random number.");
        System.out.println("\tThe program will generate a number between 1 and");
        System.out.println("\tthe designated upper bound. ");
        System.out.println("\tSelect the maximum number of guesses ");
        System.out.println("\tyou'd like to use.");
        System.out.println("--------------------------------------------------------");
        System.out.println("\nGood luck!\n");
        
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
            //user for the upper bound of the random range. Number must be 
			//positive intger
            do
            {
                System.out.print("Upper bound for the random number: ");
                
                //Check if the user input is NOT an integer compatible type
                inputError = !input.hasNextInt();
                if (inputError)
                {
                    input.next();   //flush out the scanner
					
					//Display error message to user
                    System.out.println("\nPlease enter a positive whole number!");
                }
                else 
                {
                    maxNumber = input.nextInt(); //set upper boundary
					
					//Confirm entry is positive number greater than 1
					if (maxNumber <= 1)
					{
						inputError = true;
						System.out.println("\nPlease enter a positive whole number!");
					}
                }
            }
            while (inputError); //retry if not an integer
            
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
					
					//Display error message to user
                    System.out.println("\nPlease enter a whole number!");
                }
                else 
                {
                    maxGuesses = input.nextInt(); //set upper boundary
					
					//Confirm entry is positive number greater than 1
					if (maxGuesses <= 1)
					{
						inputError = true;
						System.out.println("\nPlease enter a positive whole number!");
					}
                }
            }
            while (inputError); //retry if not an integer
            
            System.out.println(); //Blank lines for formatting
            
            //Generate and store the random number
            numberToGuess = (int)(maxNumber * Math.random()) + 1;
			
            int i;  //iterator variable            
			
			/*  The below block encapsulates the game's guessing logic. 
				The success label allows an inner loop to break out of
				the code encapsulated in the label. The loop controlling 
				individual user guesses is inside of this 'success' block.
				If the loop fully processes, it follows that the user never
				correctly guessed the number. He/she will come to the lines
				indicating a lack of success. If the user does guess correctly,
				he will break out of the entire block and never come to these 
				lines of code.
			*/
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
                    while (inputError);  //retry if incorrect type
                    
					//Check if the user guessed the number correctly
                    if (currentGuess == numberToGuess)
                    {
						//Display a success message
                        System.out.print("\nCongratualtions. You guessed the ");
                        System.out.println("random number correctly.");
                        System.out.println("You used " + i + " guess(es)");
						
						// Break out of the game logic, since the user was successful
                        break success;
                    }
                    else  //incorrect guess
                    {
                        System.out.println("\nSorry, that was incorrect.");
                        if (currentGuess > numberToGuess)
                        {
                            System.out.println("Too high."); //Guess too high
                        }
                        else
                        {
                            System.out.println("Too low.");  //Guess too low
                        }
						
						//Output number of remaining guesses
                        System.out.println((maxGuesses - i) + " guesses reamining.");
                        System.out.println();
                    }
                }
                
				/*  User only gets to these lines if the for loop completely terminated
					without a break out of the 'success' block of code. User loses
				*/
                System.out.println("Sorry, you are out of guesses.");
				
				//Print the correct answer
                System.out.println("The correct answer was " + numberToGuess + ".\n");   
            }
			
			//Offer the user another attempt, if they press 'y'
            System.out.print("\n\nType 'Y' to play again: ");
			
			//Permit both a capital or a lower case 'y', to limit confusion
            if (!input.next().toLowerCase().equals("y"))
            {
                break; //User does not want to play again. QUIT
            }
            
            System.out.println("\n"); //Formatting
        }
        
    }
}        