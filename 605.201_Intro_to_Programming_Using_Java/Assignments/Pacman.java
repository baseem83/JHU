import java.util.Scanner;

/**
    TALK ABOUT THE PROGRAM HERE
    
    @author Baseem Astiphan
    @version 1.0.0.0
*/
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
    
    private static final double COOKIE_THRESHOLD = 0.15;
    
    
    private static int cookieCount = 0;
    
    
    
    public static void main(String [] args)
    {
        //Declare and instantiate scanner for user input
        Scanner input = new Scanner (System.in);
        
        int userChoice; //User selection for next steps
        
        
        welcomeMessage();
        
        System.out.println("Enter the grid size per dimension.");
        
        //X-dim of grid. Limit width of grid to 80 cells to prevent wrapping
        gridDimensions[0] = getInteger("X-axis: ", input, 0, 80, "Must be int between 1 and 80");
        
        //Y-dim of grid. Limit height of grid to 100 cells for the sake of this
        //assignment. Otherwise, no restriction needs to be placed on height.
        gridDimensions[1] = getInteger("Y-axis: ", input, 0, 80, "Must be int between 1 and 100");
        
        cookieCount = (int)(gridDimensions[0] * gridDimensions[1] * COOKIE_THRESHOLD);
        
        //Instantiate the 2D char grid to contain the pacman playing board
        grid = new char[gridDimensions[0]][gridDimensions[1]]; 
        
        //MOVE???
        generateGrid('.');
        
        //MOVE??
        paintBoard();
        
        
        while (true)
        {
            userChoice = getInteger("Please make a selection", input, 0, 10, "Only select int between 0, 10");
            switchBoard(userChoice);
        }
    }
    
    public static void switchBoard(int input)
    {
        switch (input)
        {
            case 1:
                break;
            case 2:
                turnPacman(2);
                break;
            case 3:
                turnPacman(3);
                break;
            case 4:
                movePacman();
                break;
            
        }
    }
    
    public static void welcomeMessage()
    {
        String message = "\nWelcome to the exciting world of Pacman. Pick\n"   +
            "your board size, then roam around collecting randomly placed\n" +
            "cookies. But don't meander too much. The less moves you use,\n" +
            "the better your statistics.\nMost importantly, have fun!\n";
            
        System.out.println(message);
        System.out.println("--------------------------------------------------------------");
    }
    
    public static void turnPacman(int turnDirection)
    {
        int divisor = PAC_SYMBOLS.length;
        
        if (turnDirection == 2)
        {
            --facingDirection;
            // facingDirection %= divisor;
        }
        else if (turnDirection == 3)
        {
            ++facingDirection; 
            // facingDirection %= divisor;
        }
        else
        {
            // Do nothing for now
        }
        
        facingDirection = modulus(facingDirection, divisor);
        paintBoard();
    }
    
    public static int modulus(int dividend, int divisor)
    {
        int output;
        
        output = dividend % divisor;
        
        return output >= 0 ? output : output + divisor;
    }
    
    public static void paintBoard()
    {
        paintGrid();
    }
    
    public static void paintGrid()
    {
        generatePacman();
        for (int i = 0; i < gridDimensions[1]; i++)
        {
            for (int j  = 0; j < gridDimensions[0]; j++)
            {
                System.out.print(grid[j][i]);
            }
            System.out.println();
        }
    }
    
    public static void movePacman()
    {
        grid[location[0]][location[1]] = ' ';
        switch (facingDirection)
        {
            case 0:
                location[0] = modulus(--location[0], gridDimensions[0]);
                break;
            case 1:
                location[1] = modulus(--location[1], gridDimensions[1]);
                break;
            case 2:
                location[0] = modulus(++location[0], gridDimensions[0]);
                break;
            case 3:
                location[1] = modulus(++location[1], gridDimensions[1]);
                break;
        }
        
        System.out.println("0: " + location[0] + " 1: " + location[1]);
        
        generatePacman();
        paintBoard();
    }
    
    public static void generatePacman()
    {
        grid[location[0]][location[1]] = PAC_SYMBOLS[facingDirection];
    }
    /*
        NOTE: inner vs. outer seemingly inverted in order to paint the 
        grid correctly. 
    */
    public static void generateGrid(char fillChar)
    {
        char fill;
        int cookieCounter = 0;
        
        for (int i = 0; i < gridDimensions[1]; i++)
        {
            for (int j  = 0; j < gridDimensions[0]; j++)
            {
                if (i == 0 && j == 0)
                {
                    grid[j][i] = PAC_SYMBOLS[0];
                }
                else
                {
                    //grid[j][i] = fillCharacter('.', 'O', COOKIE_THRESHOLD);
                    
                    fill = fillCharacter('.', 'O', (gridDimensions[0] * gridDimensions[1] - (i * gridDimensions[0] + j)), cookieCount - cookieCounter);
                    // System.out.print(" " + (gridDimensions[0] * gridDimensions[1] - (i * gridDimensions[0] + j)));
                    if (fill == 'O')
                    {
                        cookieCounter++;
                    }
                    
                    grid[j][i] = fill;
                }
            }
        }
    }

    private static char fillCharacter(char standard, char random, int remSpaces, int remCookies)
    {
        System.out.println("RemCookies: " + remCookies + " RemSpaces: " + remSpaces);
        double threshold = (double)remCookies / (double)remSpaces;
        System.out.println("Thresh: " + threshold);
        return Math.random() <= threshold ? random : standard;
    }
    
    // private static char fillCharacter(char standard, char random, double threshold)
    // {
        // return Math.random() <= threshold ? random : standard;
    // }
    
    /**
        * This helper method returns an integer value based on user input.
        * The method takes a user output message, an instantized Scanner object,
        * a lower bounded value, and an upper bounded value.
        *
        * return int: the validated user input
        *
        * @author Baseem Astiphan
        * @version 1.0.0.0
        * @param message        the text to be displayed to user
        * @param input          instantized Scanner object
        * @param lowBound       the minimum accepted user input
        * @param upperBound     the maximum accepted user input
        
        *
    */
    public static int getInteger(String message, Scanner input, int lowBound, int upperBound, String errorMsg)
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
                System.out.println(errorMsg);
                input.next();   //flush out the scanner
            }
            else 
            {
                output = input.nextInt(); //get next input
                
                //Confirm entry is between lower and upper bound
                if (output < lowBound || output > upperBound)
                {
                    System.out.print(errorMsg + ": ");
                    inputError = true; //error, outside of bounds
                }
            }
        }
        while (inputError); //retry if not an integer
        
        return output; //return the user entry
    }
}
