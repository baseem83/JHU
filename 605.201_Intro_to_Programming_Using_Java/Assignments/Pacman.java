/**
    * Play Pacman. The Pacman game allows a user to move around a 
    * (command prompt) board collecting cookies. The numeric keypad 
    * as a controller for Pacman's movement. The game measures statistics,
    * specifically how many cookies acquired per move (user should aim)
    * to keep that number as high as possible.
    *
    * Cookies are generated and randomly placed around the board. Approximately
    * 15% of all board spaces are cookies.
    *   
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/


import java.util.Scanner;

public class Pacman
{
    //Character array containing the 4 possible pacman 'faces'
    private static final char[] PAC_SYMBOLS = {'>', 'V', '<', '^'};
    
    //Integer (serving as an enum) dictating the current direction pacman faces
    private static int facingDirection = 0;
    
    //2D char array incl. the grid, pacman, eaten/uneaten spaces, and cookies
    //1st dimension is X axis, 2nd dimension is Y axis
    public static char[][] grid;
    
    //Int array containing pacmans current location (x, y)
    public static int[] location = {0,0};
    
    //Int array containing the grid's dimensions (x, y). This can also be 
    //found using grid[x].length, but this makes it more accessible
    private static int[] gridDimensions = new int[2];
    
    //Percentage of board that should include includes
    private static final double COOKIE_THRESHOLD = 0.15;
    
    //Total number of cookies on board. Grid size * COOKIE_THRESHOLD
    private static int cookieCount = 0;
    
    //Total number of cookies that pacman has already eaten
    private static int cookiesEaten = 0;
    
    //Total number of moves pacman has made
    private static int totalMoves = 0;
    
    //Character to be used for cookies
    private static final char cookieCharacter = 'O';
    
    //Character to be used for standard, nonvisited cells
    private static final char standardCharacter = '.';
    
    public static void main(String [] args)
    {
        //Declare and instantiate scanner for user input
        Scanner input = new Scanner (System.in);
        
        int userChoice = 0; //User selection for next steps
        
        //Print a message to screen welcoming the player to the game
        welcomeMessage();
        
        //Print to screen the instructions for the game
        instructionMenu();
        
        System.out.println("Enter the grid size per dimension.");
        
        //X-dim of grid. Limit width of grid to 80 cells to prevent wrapping
        gridDimensions[0] = getInteger("X-axis: ", input, 0, 80, "Must be int between 1 and 80", false);
        
        //Y-dim of grid. Limit height of grid to 100 cells for the sake of this
        //assignment. Otherwise, no restriction needs to be placed on height.
        gridDimensions[1] = getInteger("Y-axis: ", input, 0, 100, "Must be int between 1 and 100", false);
        
        //Round down the number of cells in grid multiplied by cookie percentage
        cookieCount = (int)(gridDimensions[0] * gridDimensions[1] * COOKIE_THRESHOLD);
        
        //Instantiate the 2D char grid to contain the pacman playing board
        grid = new char[gridDimensions[0]][gridDimensions[1]]; 
        
        System.out.println();  //formatting
        
        //MOVE???
        generateGrid(standardCharacter, cookieCharacter);
        
        //MOVE??
        paintBoard();
        
        //Loop to get user inputs. A user input number 5 exits the game
        while (userChoice != 5)
        {
            //Get and validate user input
            userChoice = getInteger("\nPlease make a selection", input, 
                    1, 5, "Select a number between 1 and 5: ", true);
            
            //Pass control flow to switchBoard method based on user input 
            switchBoard(userChoice);
        }
    }
   
    /**
        * This method consolidates control flow based on user inputs.
        * The method takes an integer value then calls the appropriate
        * method(s) based on the input.
        *
        * This method helps keep much of the program logic out of main method
        *
        *
        * @author Baseem Astiphan
        * @param input  The user selection 
        *
    */   
    public static void switchBoard(int input)
    {
        switch (input)  //Selected command
        {
            case 1:
                instructionMenu();  //Print instructions
                paintBoard();       //Paint the board
                break;
            case 2:
                turnPacman(2);      //Turn Pacman counterclockwise
                break;
            case 3:
                turnPacman(3);      //Turn Pacman clockwise
                break;
            case 4:
                movePacman();       //Move pacman
                break;
            case 5:
                exitGameAndDisplayStatistics(); //Exit and stats
                break;
            
        }
    }
    
    /**
        * This prints a message welcoming the user to the game and setting up basic
        * instructions.
        *
        * This method helps keep the welcome text out of main, making it easier to change
        * and reuse if needed.
        *
        *
        * @author Baseem Astiphan
        *
    */       
    public static void welcomeMessage()
    {
        //Message to be displayed to user
        String message = "\nWelcome to the exciting world of Pacman. Pick\n"   +
            "your board size, then roam around collecting randomly placed\n" +
            "cookies. But don't meander too much. The less moves you use,\n" +
            "the better your statistics.\nMost importantly, have fun!";
            
        //Output message    
        System.out.println(message);
    }
    
    /**
        * This method prints the available commands to the user.
        *
        * This method helps keep instructions out of the main method,
        * making it easier to reuse and add instructions as needed.
        *
        * @author Baseem Astiphan
        *
    */       
    public static void instructionMenu()
    {
        //Below lines contain the commands available to the user, and screen outputs.
        System.out.println("\n--------------------------------------------------------------");
        System.out.println("Command List");
        System.out.println("--------------------------------------------------------------");
        System.out.println("1.\t Display this list of commands");
        System.out.println("2.\t Turn Pacman counterclockwise, but don't move.");
        System.out.println("3.\t Turn Pacman clockwise, but don't move.");
        System.out.println("4.\t Move Pacman in the direction he is facing.");
        System.out.println("5.\t Exit the game and show statistics.");
        System.out.println();
    }
    
    /**
        * This method allows the user to exit the game. Prior to exiting, the 
        * user's statistics will be printed to the screen. These include
        * the number of cookies eaten, total number of moves, and the number
        * of cookies per move. These values are stored in static member 
        * variables available to the class.
        *
        * @author Baseem Astiphan
        *
    */       
    public static void exitGameAndDisplayStatistics()
    {
        double stats;
        //Print messages to the screen
        System.out.println("\nThanks for playing. Your stats are as follows: ");
        
        //Number of cookies eaten
        System.out.println("\tTotal Cookies Eaten:\t" + cookiesEaten);
        
        //Total moves taken
        System.out.println("\tTotal Moves Taken:\t" + totalMoves);
        
        //Calculate and print cookies per move
        //If no moves have been made, return zero (rather than NaN)
        stats = totalMoves == 0 ? 0.00 : ((double)cookiesEaten / (double)totalMoves);
        System.out.println("\tCookies per move:\t" + stats);
        System.out.println("Good bye!");
    }
    
    /**
        * This method is used to turn Pacman in the appropriate direction.
        * turnDirection matches up with its corresponding command from the
        * instructionMenu(). [2] corresponds to a counterclockwise turn, and 
        * [3] corresponds to a clockwise turn.
        *
        * Symbols corresponding with the appropriate state are stored in 
        * static constant array PAC_SYMBOLS.
        *
        * When a clockwise turn is requested, the indexer of the PAC_SYMBOLS 
        * array is incremented, allowing for movement to the right within the
        * array. Conversely, a counterclockwise turn decrements the state 
        * variable, and allows for movement to the left. A modified 
        * modulus() method allows for array wraparound when the boundaries are 
        * passed.
        *
        * precondition turnDirection is either the number 2 or 3
        *
        * @author Baseem Astiphan
        * @param int turnDirection [2] counterclockwise [3] clockwise
        *
    */        
    public static void turnPacman(int turnDirection)
    {
        //Used for wraparound, the number of PAC_SYMBOLS
        int divisor = PAC_SYMBOLS.length;
        
        if (turnDirection == 2) //counterclockwise
        {
            --facingDirection;  //decrement PAC_SYMBOLS indexer
        }
        else if (turnDirection == 3) //clockwise
        {
            ++facingDirection;  //increment PAC_SYMBOLS indexer
        }
        else
        {
            // Leave this in place in case of future needs, 
            // Does nothing at this point in development
        }
        
        //Modulus method to be used for wrapping around the array
        //E.g., facingDirection = 4, and divisor = 4, returns 0th element
        facingDirection = modulus(facingDirection, divisor);
        System.out.println();  //Formatting
        paintBoard();  //Redraw the board with new states
    }
    
    /**
        * This helper method returns the modulus of two numbers in a manner
        * that supports wrapping at the boundaries. In some languages, modulus
        * always returns a positive number that serves as the remainer. In Java,
        * a negative dividend returns a negative modulus value. This disallows
        * use in wrapping. For example: -1 % 4 returns -1. In some languages, it 
        * would return 3. We want to use the latter implementation.
        *
        * return int: the remainder of two numbers
        *
        * @author Baseem Astiphan
        * @param divident       the numerator value
        * @param divisor        the divisor value
        *
        * @return int           the remainer of the two values
        *
    */    
    public static int modulus(int dividend, int divisor)
    {
        int output;  //value to be returned
        
        output = dividend % divisor;  //take the standard Java modulus
        
        //If output is negative, add back the divisor
        return output >= 0 ? output : output + divisor;
    }
    
    /**
        * This method prints the game board based on the current state
        * of the game. It first updates Pacman's symbol in the underlying grid 
        * to reflect the direction that pacman is facing. Then it prints each
        * grid element to the screen.
        *
        * postcondition Grid is printed to the screen with Pacman facing the correctly
        *               direction
        *
        * @author Baseem Astiphan
        *
    */     
    public static void paintBoard()
    {
        updatePacSymbol();  //update Pacman symbol based on direction facing
        paintGrid();  //Paint grid elements to the board
    }

    /**
        * This method prints the grid to the screen based on elements in the underlying
        * 2D character array.
        *
        * postcondition Grid is printed to screen
        * postcondition Cell elements should only be either {. ' ' O PAC_SYMBOL}
        *
        * @author Baseem Astiphan
        *
    */         
    public static void paintGrid()
    {
        //Use nested for loop to move through all elements of 2D array
        //and print element at the location to the screen
        for (int i = 0; i < gridDimensions[1]; i++)
        {
            for (int j  = 0; j < gridDimensions[0]; j++)
            {
                System.out.print(grid[j][i]);  //print grid element
            }
            System.out.println(); //Jump to the next line
        }
    }
    
    /**
        * This method is used to move Pacman in the appropriate direction 
        * based on Pacmans orientation. If Pacman is facing left or right,
        * the first element of the location[] array (the x element) is 
        * decremented/incremented, respectively. Similarly, if Pacman is 
        * facing up or down, the second element of location[] array (the y
        * element) is decremented/incremented, respectively.
        *
        * The cell that Pacman just exited is replaced with a blank spaces
        * to indicate that it has been visited. The new cell at which 
        * Pacman arrives is updated with the new PAC_SYMBOLS. If the new cell
        * had a cookie, the eatCookie() method is run. 
        *
        * Every move increments the totalMoves counter.
        *
        * IMPORTANT NOTE: in this implementation, Pacman can wrap around the 
        * board when he hits a boundary. If he hits a left/right boundary, he 
        * stays on the same row, but paints at the opposite side of the board.
        * Similarly, if he hits a top/bottom boundary, he stays in the same 
        * column, but repaints on the opposite side of the board. This feature
        * uses the modulus() modified method to allow for wrapping at the 
        * boundaries.
        *
        * postcondition board is repainted with a moved pacman
        * postcondition move counter is incremented
        * postcondition eatCookie() may fire if a cookie was available
        *
        * @author Baseem Astiphan
        *
    */          
    public static void movePacman()
    {
        //Replace current location with a space to indicate it was visited
        grid[location[0]][location[1]] = ' ';
        
        switch (facingDirection) //which direction is Pacman facing
        {
            case 0: //facing left
                //Decrement x-axis value
                location[0] = modulus(--location[0], gridDimensions[0]);
                break;
            case 1: //facing up
                //Decrement y-axis value
                location[1] = modulus(--location[1], gridDimensions[1]);
                break;
            case 2: //facing right
                //increment x-axis value
                location[0] = modulus(++location[0], gridDimensions[0]);
                break;
            case 3: //facing down
                //increment y-axis value
                location[1] = modulus(++location[1], gridDimensions[1]);
                break;
        }
        
        //Check if there is a cookie at the new location
        if (grid[location[0]][location[1]] == cookieCharacter)
        {
            eatCookie(); //fire off eatCookie method.
        }
        
        //increment the total number of moves counter
        totalMoves++;
        
        paintBoard();  //repaint the board based on new states
    }
    
    /**
        * This method encapsulates the logic for eating a cookie. Currently, it
        * increments the static cookiesEaten variable, and prints a message
        * to screen indicating the total number of cookies eaten.
        *
        * postcondition Prints to screen when a cookie is eaten
        *
        * @author Baseem Astiphan
        *
    */     
    public static void eatCookie()
    {
        //Increment cookiesEaten counter and print to screen
        System.out.println("You ate a delicious cookie. That was number " + ++cookiesEaten);
        
    }
 
    /**
        * This method encapsulates the logic for updating the Pacman symbol 
        * based on the facingDire static variable. Currently, it takes the
        * x-axis and y-axis values from the location[] static array, and 
        * updates that location with the PAC_SYMBOLS based on facingDirection.
        *
        * postcondition Updates grid with new pac symbol
        *
        * @author Baseem Astiphan
        *
    */      
    public static void updatePacSymbol()
    {
        //Grid at appropriate location is updated with the PAC_SYMBOLS
        grid[location[0]][location[1]] = PAC_SYMBOLS[facingDirection];
    }

    /**
        * This method populates the 2D grid array with the appropriate 
        * characters at startup. It takes two parameters: fillChar is the normal char
        * to be used in the grid, and specialChar is to be used for the 
        * cookies. This layout creates for a more flexible game, where 
        * different types of characters can be used to replace cookies 
        * if needed.
        *
        * This method calls a helper method to be used for determining when
        * a cell should be standard and when it should be a cookie.
        *
        * @author Baseem Astiphan
        * @param fillChar    String standard for non-visited cells
        * @param specialChar String special for cookie cells
        *
    */
    public static void generateGrid(char fillChar, char specialChar)
    {
        char fill;  //will hold result of helper method for standard vs special
        int cookieCounter = 0; //number of cookies added to grid
        
        //Nested loop to move through each cell of the 2D grid
        for (int i = 0; i < gridDimensions[1]; i++)
        {
            for (int j  = 0; j < gridDimensions[0]; j++) //inner loop
            {
                if (i == 0 && j == 0)  //if cell location is (0,0)
                {
                    grid[j][i] = PAC_SYMBOLS[0];  //paint pacman, starting loc
                }
                else
                {
                    //Below returns the appropriate character for the grid loc,
                    //based on helper method. Takes the fillChar, specialChar,
                    //number of cells in grid, and cookies remaining to be placed
                    fill = fillCharacter(fillChar, specialChar, 
                           (gridDimensions[0] * gridDimensions[1] - (i * gridDimensions[0] + j)),
                           cookieCount - cookieCounter);
                    
                    //Placing a cookie on the board
                    if (fill == cookieCharacter)
                    {
                        cookieCounter++;  //increment variable storing num of cookies
                    }
                    
                    //cell at location[j][i] should equal fill charater returned
                    grid[j][i] = fill;
                }
            }
        }
    }

    /**
        * This helper method returns the character that should be in a given
        * cell. The game spec requires that approximately 15% of all grid 
        * cells should be cookies. This method encapsulates the calculation.
        *
        * It takes both the standard and the special character, the remaining 
        * number of spaces, and the remaining number of cookies. Next, it 
        * uses randomness to determine if the cell should be a cookie. This layout
        * allows for reasonable spacing between cookies as the threshold increases 
        * and decreases based on past action.
        * (Details included in design document)
        *
        * precondition Must be more remaining spaces than remaining cookies
        *
        * @author Baseem Astiphan
        * @param standard    String standard for non-visited cells
        * @param specialChar String special for cookie cells
        * @param remSpaces  int number of spaces left on the board
        * @param remCookies int number of cookies left to be placed
        * @return char either a standard character or the special character
        *
    */    
    private static char fillCharacter(char standard, char specialChar, int remSpaces, int remCookies)
    {
        double threshold = (double)remCookies / (double)remSpaces;
        return Math.random() <= threshold ? specialChar : standard;
    }
    
    
    /**
        * This helper method returns an integer value based on user input.
        * The method takes a user output message, an instantized Scanner object,
        * a lower bounded value, and an upper bounded value.
        *
        * return int: the validated user input
        *
        * @author Baseem Astiphan
        * @param message          String  the text to be displayed to user
        * @param input            Scanner instantized Scanner object
        * @param lowBound         int     the minimum accepted user input
        * @param upperBound       int     the maximum accepted user input
        * @param dispInstructions boolean display instructions on error
        * @return                 int     value that passes validation
        *
    */
    public static int getInteger(String message, Scanner input, int lowBound, int upperBound, String errorMsg, boolean dispInstructions)
    {
        boolean inputError = false; // maintain error state
        int output = 0; //output to be retured
        
        System.out.print(message + ": "); //display output message to user
        
        do
        {
            
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
            
            //Display instructions if error and user requested.
            if (inputError && dispInstructions)
            {
                instructionMenu(); //Display instructionMenu
                paintBoard();  //reprint board, per specification
                System.out.println();  //formatting
                System.out.print(errorMsg + ": ");  //Display error msg
            }
            else if (inputError)
            {
                paintBoard(); //reprint board, per specification
                System.out.println();  //formatting
                System.out.print(errorMsg + ": "); //Display error msg
            }
        }
        while (inputError); //retry if not an integer
        
        return output; //return the user entry
    }
}
