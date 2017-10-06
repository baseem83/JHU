
/**
 This application translates between English and Morse code strings.
 The user selects the translation direction, then inputs a phrase
 in the selected language. The system encodes the String from the
 original language into the new one. Morse Code letters are separated
 with a single space, and morse code words are separated with a
 bar character. English characters are print consecutively, no
 spacing, and English words are separated with a space.

 @author Baseem Astiphan
 @version 1.0.0.0
 */
import java.util.Scanner;

public class MorseCodeConverter
{
    public static void main (String [] args)
    {
        //Instantiate Scanner object for user input
        Scanner input = new Scanner(System.in);
        int userSelection;  //user selected translation direction
        String inputString; //original string to be translated

        //Determine direction from the user
        userSelection = getInteger("\nEnter 1 to convert English -> Morse, " +
                "2 for Morse -> English", input, 1, 2);

        input.nextLine();  // flush out residual newline character

        //Get the string to be translated. Accept any string. The methods
        //deal with non-encoded letters
        System.out.print("Enter the text to be converted: ");
        inputString = input.nextLine();  //string to be translated

        //Display to screen the result of the translation, 1: toMorse
        //2: toEnglish.
        //If character is not encoded, use a '^' for that character
        System.out.println(translatePhraseMorseCode(inputString,
                userSelection == 1 ? "toMorse" : "toEnglish", "^"));
    }

    /**
     * This method takes an entire phrase to be translated, and uses the
     * 'translateUnitMorseCode' helper method to translate the phrase.
     * The method takes a user input string, a String direction as arguments,
     * and an error String. For efficiency purposes, this method builds upon
     * a StringBuilder rather than instantiate multiple String objects.
     *
     * precondition: the text contains English characters that are encoded
     * precondition: the direction is either "toMorse" or "toEnglish"
     *
     * postcondition: any characters that are not encoded return 'notFound'
     *
     *
     * @author Baseem Astiphan
     * @param text           the text to be translated
     * @param direction      translation direction. "toEnglish", "toMorse"
     * @param notFound       placeholder for not encoded characters
     * @return String: the translated phrase
     *
     */
    public static String translatePhraseMorseCode(String text, String direction, String notFound)
    {
        String[] splitText;  //array to hold individual letters
        StringBuilder outputString = new StringBuilder(); //output
        String translatedLetter = new String(); //translated letter

        switch(direction) //determine direction of translation
        {
            case "toMorse":  // from English to Morse code
                //split phrase by the letter, excluding the beginning of string
                //if text.split("") alone, it would return an extra empty element
                splitText = text.split("(?!^)");
                break;

            case "toEnglish":   //English to Morse code
                splitText = text.split(" "); //split phrase by spaces
                break;
            default:
                splitText = new String[0]; //in case of error
                break;
        }

        //Loop through individual letters
        for (String element : splitText)
        {
            //Translate letter from origin to destination direction
            translatedLetter = translateUnitMorseCode(element, direction);

            if (translatedLetter.equals("")) //failed translation
            {
                outputString.append(notFound); //append placeholder letter
            }
            else
            {
                outputString.append(translatedLetter); //append the translation
            }

            //Add a string between letters if it is Morse code
            if (direction == "toMorse")
            {
                outputString.append(" ");
            }
        }

        return outputString.toString(); //output the result of translation

    }

    /**
     * This helper method takes a letter to be translated, and returns the
     * translated letter.
     * The method takes a user string, and a String direction as arguments.
     *
     * precondition: the letter contains English characters that are encoded
     * precondition: the direction is either "toMorse" or "toEnglish"
     *
     * postcondition: any characters that are not encoded return empty string ""
     *
     * return String: the translated letter
     *
     * @author Baseem Astiphan
     * @param letter         the letter to be translated
     * @param direction      translation direction. "toEnglish", "toMorse"
     *
     */
    public static String translateUnitMorseCode(String letter, String direction)
    {
        String output = new String(); //hold the translated character for output
        int columnIndex = 0; //column to be returned. 0: English 1: Morse Code

        //Translation dictionary. The below two dimensional array maps English
        //letters to their Morse code counterparts. Includes a space to handle
        //translating the bar character back and forth.
        String[][] morseSymbols = {{"A", ".-"}, {"B", "-..."}, {"C", "-.-."},
                {"D", "-.."}, {"E", "."}, {"F",  "..-."}, {"G", "--."}, {"H", "...."},
                {"I", ".."}, {"J", ".---"}, {"K", "-.-"}, {"L", ".-.."},
                {"M", "--"}, {"N", "-."}, {"O", "---"}, {"P", ".--."},
                {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
                {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"},
                {"Y", "-.--"}, {"Z", "--.."}, {"1", ".----"}, {"2", "..---"},
                {"3", "...--"}, {"4", "....-"}, {"5", "....."}, {"6", "-...."},
                {"7", "--..."}, {"8", "---.."}, {"9", "----."}, {"0", "-----"},
                {" ", "|"}};

        switch(direction)  //translation direction
        {
            case "toMorse": //from English to Morse
                columnIndex = 0; //Searching English letters
                break;
            case "toEnglish": //from Morse to English
                columnIndex = 1; //Searching Morse letters
                break;
        }

        //Shorthand for loop to iterate through each String array in the outer array
        // and find a match to the 'letter' argument.
        for (String[] morsePair : morseSymbols)
        {
            //Found letter in the specified column (ignore case)
            if (letter.equalsIgnoreCase(morsePair[columnIndex]))
            {
                //Below is a shorthand way to toggle between column 0 and 1
                //without having to use if-else statements or a switch statement.
                output = morsePair[(columnIndex + 1) % 2];
                break;
            }
        }
        return output; //return the translated string
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

     *
     */
    public static int getInteger(String message, Scanner input, int lowBound, int upperBound)
    {
        boolean inputError = false; // maintain error state
        int output = 0; //output to be returned

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

