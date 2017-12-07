import java.util.regex.Pattern;
import java.util.Set;
import java.util.HashSet;

/**
    * Class to extend the Song class, furnishing new methods appropriate
    * for working with a persistence framework. These methods are not 
    * appropriate for a general Song class, considering they are helpers
    * to storage system.
    *
    * This class also implementes the Comparable and Persistable intrfaces
    *
    * @author Baseem Astiphan
    * @version 1.0.0.1
*/
public class SongForDB extends Song implements Comparable<SongForDB>, Persistable<String, SongForDB>
{
    //Hash set to store a unique list of currently used itemCodes (useful in
    //implementations where itemCode serves as database key)
    private static Set<String> usedItemCodes = new HashSet<String>();
    
    /**
        *Parameterless constructor for this class. Calls its parents class constructor
        *
        * @author Baseem Astiphan
    */
    public SongForDB() 
    {
        super();
    }
    
    /**
        *Constructor that takes parameters to set field initial values.
        *
        * @author Baseem Astiphan
        * @param title The song title
        * @param itemCode The song itemCode
        * @param description The song description
        * @param artist The song artist
        * @param album The song album
        * @param price The price of the song
        * @throws Exception An in-use itemCode is reused
    */
    public SongForDB(String title, String itemCode, String description, String artist, String album, double price)
        throws Exception
    {
        setTitle(title);  //set title
        setItemCodePreventingDuplicates(itemCode);  //set item code (disallow dupes)
        setDescription(description);        //set description
        setArtist(artist);  //set artist
        setAlbum(album);    //set album
        setPrice(price);    //set price
    }
    
    /**
        * A potential replacement for super class's setItemCode() method,
        * to be used in cases where implementation disallows reusing item Codes
        *
        * @author Baseem Astiphan
        * @param itemCode String item code
        * @throws Exception If an itemCode is duplicated
    */
    public void setItemCodePreventingDuplicates(String itemCode)
        throws Exception
    {
        //Chcek hashset for item code use
        if (!SongForDB.isItemCodeAvailable(itemCode))
        {
            //Exception if it is used
            throw new Exception("This item code is used");
        }
        
        //Add the new itemCode to the hashset
        SongForDB.usedItemCodes.add(itemCode);
        
        //Call parent class's standard setItemCode() method
        super.setItemCode(itemCode);
    }
    
    /**
        *Implement method as definied by Comparable interface. Compares
        *current instance of song to another instance.
        *
        * @author Baseem Astiphan
        * @param s2 Another song against which to compare
    */
    public int compareTo(SongForDB s2)
    {
        //Build comparator stringa, song Title + song ItemCode
        String thisSong = this.getTitle() + "-" + this.getItemCode();
        String otherSong = s2.getTitle() + "-" + this.getItemCode();
        
        //Call String object's compareTo() method, and return result
        return thisSong.compareTo(otherSong);
    }
    
    /**
        *Implement method defined in Persistable interface, definining
        *the database key field.
        *
        * @author Baseem Astiphan
        * @return String representing the key field
    */
    public String getKeyField()
    {
        //Return the title, a hypehn, and the item code
        return getTitle() + "-" + getItemCode();
    }
    
    /**
        *Implement method defined in Persistable interface, definining
        *how the instance gets persisted to a database
        *
        * @author Baseem Astiphan
        * @param delimeter The String to be used to separate elements of object
        * @return String representing database line
    */
    public String writeToDatabase(String delimeter)
    {
        //Build a database line
        return getTitle()       + delimeter +
               getItemCode()    + delimeter +
               getDescription() + delimeter +
               getArtist()      + delimeter +
               getAlbum()       + delimeter +
               Double.toString(getPrice());
    }

    /**
        *Implement method defined in Persistable interface, definining
        *how a new object is created from a database line
        *
        * @author Baseem Astiphan
        * @param dbLine String as read from a text file database
        * @param delimeter The String to be used to separate elements of object
        * @return SongForDB representing new object read from DB
    */ 
    public SongForDB readFromDBToObject(String dbLine, String delimeter)
    {
        //Create a SongForDB placeholder using static method.
        SongForDB temp = SongForDB.parseSong(dbLine, delimeter);
        
        //Set instance variables to match placeholder
        setTitle(temp.getTitle());
        setItemCode(temp.getItemCode());
        setDescription(temp.getDescription());
        setArtist(temp.getArtist());
        setAlbum(temp.getAlbum());
        setPrice(temp.getPrice());
        
        //Return this instance, with newly set fields
        return this;
    }

    /**
        * Static method to create a new SongForDB from a line of text.
        *
        * @author Baseem Astiphan
        * @param line Line containing serialized object info
        * @param delimeter The string used to split the line
        * @return SongForDB as created
    */
    public static SongForDB parseSong(String line, String delimeter)
    {
        //Create a new SongForDB with default values
        SongForDB song = new SongForDB();
        
        //Split using delimeter
        String[] elements = line.split(Pattern.quote(delimeter));
        
        //Maybe this can be an exception of something. Now it just returns
        //empty new song (maybe this is actually OK.) 
        //Check to see if the supplied line, when split, has an appropriate number
        //of elements
        if (elements.length != 6)
        {
            //Write errors to standard output
            System.out.println("Incorrect number of elements!");
            return song; //return song
        }
        
        // Below lines set the attributes for the newly created song to the 
        // appropriate values as passed in
        song.setTitle(elements[0].trim());
        song.setItemCode(elements[1].trim());
        song.setDescription(elements[2].trim());
        song.setArtist(elements[3].trim());
        song.setAlbum(elements[4].trim());
        song.setPrice(Double.parseDouble(elements[5]));
        
        //return newly created song with its set attributes
        return song;
    }
    
    /**
        * Static method detailing whether a product code has been used
        *
        * @author Baseem Astiphan
        * @param itemCode itemCode to be added
        * @return boolean true if itemcode is available
    */
    public static boolean isItemCodeAvailable(String itemCode)
    {
        //Check if itemcode is already in hashset
        return !usedItemCodes.contains(itemCode);
    }

    /**
        * Static method to reset the list of used itemCodes. Useful in 
        * situations where details are being refreshed from a database
        * and the class shouldn't err on all of them.
        *
        * @author Baseem Astiphan
    */
    public static void resetItemCodes()
    {
        //Clear the hashset
        usedItemCodes.clear();
    }
}