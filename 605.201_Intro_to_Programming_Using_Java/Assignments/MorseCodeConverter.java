// NOTES / QUESTIONS
// 1. Should we use char or String
// 2. Is there a memory/efficiency implication declaring 
//    String [26][((2))] vs String[2][((26))]
// 3. Appropriate manner to get string input
// 4. Should there be spaces around the pipe | operator
// 5. When translating Morse code to english, will words be separated 
//    by a pipe?

public class MorseCodeConverter
{
    public static void main (String [] args)
    {
        // String[][] morseSymbols = {{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                                    // "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                                    // "U", "V", "W", "X", "Y", "Z"}, {".-", "-...", "-.-.",
                                    // "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
                                    // ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
                                    // "...", "-", "..-", "...-", ".--", "-..-", "-.--",
                                    // "--.."}};
        
        String[][] morseSymbols = {{"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."}, 
                                   {"E", "-."}, {"F",  "..-."}, {"G", "--."}, {"H", "...."}, 
                                   {"I", ".."}, {"J", ".---"}, {"K", "-.-"}, {"L", ".-.."}, 
                                   {"M", "--"}, {"N", "-."}, {"O", "---"}, {"P", ".--."},  
                                   {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
                                   {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"},
                                   {"Y", "-.--"}, {"Z", "--.."}};
        
            // System.out.println(morseSymbols.length);
            // for (int i = 0; i < morseSymbols.length; i++)
            // {
                // System.out.println(morseSymbols[i][0] + " : " + morseSymbols[i][1]);
            // }
            
             System.out.println(translatePhraseToMorseCode("to be", "toMorse"));
             System.out.println(translatePhraseToMorseCode("- --- | -... .", "toEnglish"));
             System.out.println(translatePhraseToMorseCode("123456789 Welcome", "toMorse"));
             System.out.println(translatePhraseToMorseCode(".-- . .-.. -.-. --- -- .", "toEnglish"));
    }
    
    public static String translatePhraseToMorseCode(String text, String direction)
    {
        String[] splitText;
        StringBuilder outputString = new StringBuilder();
        
        switch(direction)
        {
            case "toMorse":
                splitText = text.split("");
                break;
                
            case "toEnglish":
                splitText = text.split(" ");
                break;
            default:
                splitText = new String[0];
                break;
        }
        
        for (String element : splitText)
        {
              outputString.append(translateUnitOfMorseCode(element, direction));
              if (direction == "toMorse")
              {
                  outputString.append(" ");
              }
        }
        
        return outputString.toString();
        
    }
    
    public static String translateUnitOfMorseCode(String letter, String direction)
    {
        String output = new String();
        int columnIndex = 0;
        String[][] morseSymbols = {{"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."}, 
                                   {"E", "."}, {"F",  "..-."}, {"G", "--."}, {"H", "...."}, 
                                   {"I", ".."}, {"J", ".---"}, {"K", "-.-"}, {"L", ".-.."}, 
                                   {"M", "--"}, {"N", "-."}, {"O", "---"}, {"P", ".--."},  
                                   {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
                                   {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"},
                                   {"Y", "-.--"}, {"Z", "--.."}, {"1", ".----"}, {"2", "..---"}, 
                                   {"3", "...--"}, {"4", "....-"}, {"5", "....."}, {"6", "-...."}, 
                                   {"7", "--..."}, {"8", "---.."}, {"9", "----."}, {"0", "-----"}, 
                                   {" ", "|"}};
        switch(direction)
        {
            case "toMorse":
                columnIndex = 0;
                break;
            case "toEnglish":
                columnIndex = 1;
                break;
        }
        
        // for (int i = 0; i < text.length(); i++)
        // {
            // for (String[] morsePair : morseSymbols)
            // {
                // if (text.substring(i, i + 1).equalsIgnoreCase(morsePair[columnIndex]))
                // {
                    // outputString.append(morsePair[(columnIndex + 1) % 2]);
                    // outputString.append(" ");
                    // break;
                // }
            // }
        // }
        
        for (String[] morsePair : morseSymbols)
        {
            if (letter.equalsIgnoreCase(morsePair[columnIndex]))
            {
                output = morsePair[(columnIndex + 1) % 2];
                break;
            }
        }
        
        
        return output;
   
    }    
    
    public static String translateMorseCode(String text, String direction)
    {
        String output = new String();
        int columnIndex = 0;
        String[][] morseSymbols = {{"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."}, 
                                   {"E", "."}, {"F",  "..-."}, {"G", "--."}, {"H", "...."}, 
                                   {"I", ".."}, {"J", ".---"}, {"K", "-.-"}, {"L", ".-.."}, 
                                   {"M", "--"}, {"N", "-."}, {"O", "---"}, {"P", ".--."},  
                                   {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
                                   {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"},
                                   {"Y", "-.--"}, {"Z", "--.."}};
        switch(direction)
        {
            case "toMorse":
                columnIndex = 0;
                break;
            case "toEnglish":
                columnIndex = 1;
                break;
        }
        
        for (int i = 0; i < text.length(); i++)
        {
            for (String[] morsePair : morseSymbols)
            {
                if (text.substring(i, i + 1).equalsIgnoreCase(morsePair[columnIndex]))
                {
                    output += morsePair[(columnIndex + 1) % 2];
                    output += " ";
                    break;
                }
            }
        }
        
        return output;
   
    }
    
    
}
