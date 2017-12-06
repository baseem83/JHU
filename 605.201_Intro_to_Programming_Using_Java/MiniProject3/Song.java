import java.util.regex.Pattern;


/**
    * Class to model a general song and the usual attributes.
    *
    * @author Baseem Astiphan
    * @version 1.0.0.1
*/
public class Song 
{
    private String title;       //song title
    private String itemCode;    //song item code
    private String description; //song description
    private String artist;      //song artist
    private String album;       //song album
    private double price;       //song price
    
    /**
        *Constructor that takes parameters to set attributes during creation.
        *
        * @author Baseem Astiphan
        * @param title The song title
        * @param itemCode The song itemCode
        * @param description The song description
        * @param artist The song artist
        * @param album The song album
        * @param price The price of the song
        
    */
    public Song(String title, String itemCode, String description, String artist, String album, double price)
    {
        this.title = title;             //set title to argument
        this.itemCode = itemCode;       //set itemCode to argument
        this.description = description; //set description to argument
        this.artist = artist;           //set artist to argument
        this.album = album;             //set album to argument
        this.price = price;             //set price to argument
    }
    
    /**
        * Constructor with no parameters to replace default. Creates a new Song
        * object with default values for attributes
        *
        * @author Baseem Astiphan
    */
    public Song() {}
    
    /**
        * Getter method for the song title
        *
        * @author Baseem Astiphan
        * @return song title -- String
    */
    public String getTitle()
    {
        return title;  //return song title
    }
    
    /**
        * Setter method for the song title
        *
        * @author Baseem Astiphan
        * @param title -- String
    */
    public void setTitle(String title)
    {
        this.title = title;  //set song title
    }
    
    /**
        * Getter method for the song item code
        *
        * @author Baseem Astiphan
        * @return song itemCode -- String
    */
    public String getItemCode()
    {
        return itemCode; //return song itemCode
    }
    
    /**
        * Setter method for the song item code
        *
        * @author Baseem Astiphan
        * @param itemCode -- String
    */
    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode; //set song itemCode
    }
    
    /**
        * Getter method for the song description
        *
        * @author Baseem Astiphan
        * @return song description -- String
    */
    public String getDescription()
    {
        return description;  //return song description
    }
    
    /**
        * Setter method for the song description
        *
        * @author Baseem Astiphan
        * @param description -- String
    */
    public void setDescription(String description)
    {
        this.description = description;  //set song description
    }
    
    /**
        * Getter method for the song artist
        *
        * @author Baseem Astiphan
        * @return song artist -- String
    */
    public String getArtist()
    {
        return artist;  //return song artist
    }
    
    /**
        * Setter method for the song artist
        *
        * @author Baseem Astiphan
        * @param artist -- String
    */
    public void setArtist(String artist)
    {
        this.artist = artist;  //set song artist
    }
    
    /**
        * Getter method for the song album
        *
        * @author Baseem Astiphan
        * @return song album -- String
    */
    public String getAlbum()
    {
        return album;   //return song album
    }
    
    /**
        * Setter method for the song album
        *
        * @author Baseem Astiphan
        * @param album -- String
    */
    public void setAlbum(String album)
    {
        this.album = album;  //set song album
    }
    
    /**
        * Getter method for the song price
        *
        * @author Baseem Astiphan
        * @return song price -- Double
    */
    public double getPrice()
    {
        return price;  //return song price
    }
    
    /**
        * Setter method for the song price
        *
        * @author Baseem Astiphan
        * @param price -- Double
    */
    public void setPrice(double price)
    {
        this.price = price; //set song price
    }
    
    /**
        * Static method to parse a line of text into a Song object.
        * Method takes a delimeter for the split process
        *
        * @author Baseem Astiphan
        * @param line String to be parsed into a a song
        * @param delimeter String used to split the line into components
        * @return Song the newly created song
    */
    public static Song parseSong(String line, String delimeter)
    {
        Song song = new Song();  //instantiate a new song, default values for fields
        
        //Split line argument based on delimeter (note use of Pattern.quote to handle
        //delimters that also serve as Regex metacharacter)
        String[] elements = line.split(Pattern.quote(delimeter));
        
        //Maybe this can be an exception of something. Now it just returns
        //empty new song (maybe this is actually OK.) 
        //Check to see if the supplied line, when split, has an appropriate number
        //of elements
        if (elements.length != 6)
        {
            //Output error message to the console
            System.out.println("Incorrect number of elements!");
            return song;  //return a song with default values
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
        *Overridden toString method for user friendly output
        *
        * @author Baseem Astiphan
        * @return String user friendly representation of instance
    */
    public String toString()
    {
        //Build and return user-friendly string
        return getTitle() + " (" + getArtist() + ") " +
               getDescription() + ". Album: " + getAlbum() +
               " Price: " + getPrice();
    }
}