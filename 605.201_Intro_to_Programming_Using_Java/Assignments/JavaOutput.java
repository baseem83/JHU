/**
    * This program outputs text to a command window using a 
    * moasic-like structue. Individual letters are combined to 
    * create the appearance of larger letter.
	* @author Baseem Astiphan
	* @version 1.0.0.0
*/

public class JavaOutput
{
    public static void main(String [] args)
    {
        //Print 3 lines of whitespace for legibility
        System.out.println();
        System.out.println();
        System.out.println();
        
        //Below is the layout for the first row of text. Read: MY JAVA
        System.out.println("MM   MM  Y   Y       J     A     V     V    A   ");
        System.out.println("M MMM M   Y Y        J    A A     V   V    A A  ");
        System.out.println("M  M  M    Y     J   J   AAAAA     V V    AAAAA ");
        System.out.println("M     M    Y      J J   A     A     V    A     A");
        
        //Print 3 lines of whitespace for legibility
        System.out.print("\n\n\n");
        
        //Below is the layout for the second row of text. Read: USING
        System.out.println("U     U  SSSSS  IIIIIII N    N  GGGG  ");
        System.out.println("U     U  S         I    NN   N  G     ");
        System.out.println("U     U   SS       I    N N  N  G GGGG");
        System.out.println(" U   U       S     I    N  N N  G    G");
        System.out.println("  UUU    SSSSS  IIIIIII N    N  GGGGGG");
        
        //Print 3 lines of whitespace for legibility
        System.out.print("\n\n\n");
        
        //Below is the layout for the third row of text. Read: COMMAND
        System.out.println(" CCC    OOO   MM   MM  MM   MM     A     N    N  DDD  ");
        System.out.println("C      O   O  M MMM M  M MMM M    A A    NN   N  D  D ");
        System.out.println("C      O   O  M  M  M  M  M  M   AAAAA   N N  N  D  D ");
        System.out.println("C      O   O  M     M  M     M  A     A  N   NN  D  D ");
        System.out.println(" CCC    OOO   M     M  M     M  A     A  N    N  DDD  ");
        
        //Print 3 lines of whitespace for legibility
        System.out.print("\n\n\n");
        
        //Below is the layout for the fourth row of text. Read: PROMPT
        System.out.println("PPPP   RRRR    OOO   MM   MM  PPPP   TTTTTTT");
        System.out.println("P   P  R   R  O   O  M MMM M  P   P     T   ");
        System.out.println("PPPP   RRRR   O   O  M  M  M  PPPP      T   ");
        System.out.println("P      R R    O   O  M     M  P         T   ");
        System.out.println("P      R  R    OOO   M     M  P         T   ");
    }
}