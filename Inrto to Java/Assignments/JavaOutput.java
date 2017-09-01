/*
    This program outputs text to a command window using a 
    moasic-like structue. Individual letters are combined to 
    create the appearance of larger letter.
*/

//NOTES TO SELF: Consider adding a javadoc compliant opening comment
//include some of the attributes (@author, etc) found in textbook
public class JavaOutput
{
    public static void main(String [] args)
    {
        //Add 3 empty lines for layout purposes
        System.out.println();
        System.out.println();
        System.out.println();
        
        //Print out the first row of text
        System.out.println("MM   MM  Y   Y       J     A     V     V    A   ");
        System.out.println("M MMM M   Y Y        J    A A     V   V    A A  ");
        System.out.println("M  M  M    Y     J   J   AAAAA     V V    AAAAA ");
        System.out.println("M     M    Y      J J   A     A     V    A     A");
        
        //Add 3 empty lines between rows of words
        System.out.print("\n\n\n");
        
        //Print out the second row of text
        System.out.println("U     U  SSSSS  IIIIIII N    N  GGGG  ");
        System.out.println("U     U  S         I    NN   N  G     ");
        System.out.println("U     U   SS       I    N N  N  G GGGG");
        System.out.println(" U   U       S     I    N  N N  G    G");
        System.out.println("  UUU    SSSSS  IIIIIII N    N  GGGGGG");
        
        //Add 3 more empty lines between rows of words
        System.out.print("\n\n\n");
        
        //Print out the second row of text
        System.out.println(" CCC    OOO   MM   MM  MM   MM     A     N    N  DDD  ");
        System.out.println("C      O   O  M MMM M  M MMM M    A A    NN   N  D  D ");
        System.out.println("C      O   O  M  M  M  M  M  M   AAAAA   N N  N  D  D ");
        System.out.println("C      O   O  M     M  M     M  A     A  N   NN  D  D ");
        System.out.println(" CCC    OOO   M     M  M     M  A     A  N    N  DDD  ");
        
        //Add 3 more empty lines between rows of words
        System.out.print("\n\n\n");
        
        //Print out the second row of text
        System.out.println("PPPP   RRRR    OOO   MM   MM  PPPP   TTTTTTT");
        System.out.println("P   P  R   R  O   O  M MMM M  P   P     T   ");
        System.out.println("PPPP   RRRR   O   O  M  M  M  PPPP      T   ");
        System.out.println("P      R R    O   O  M     M  P         T   ");
        System.out.println("P      R  R    OOO   M     M  P         T   ");
    }
}