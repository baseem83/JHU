import java.util.*;
import java.io.*;

/**
    *Class to extend the abstract TextFileDataContext class, implementing
    *the necessary methods. This class also specifies types for the generics
    *available in the parent class.
    *
    * @author Baseem Astiphan
    * @version 1.0.0.1
*/
public class SongDataContext extends TextFileDataContext<String, SongForDB>
{   
    /**
        *Class constructor
        *
        * @author Baseem Astiphan
        * @param filePath path where file is stored
        * @param delimiter The delimiter for the data file
    */
    public SongDataContext(String filePath, String delimiter)
    {
        //call parent class constructor
        super(filePath, delimiter);
        
        //If the file already exists, load entities into memory
        //from the storage file
        if (this.exists())
        {
            //Load entities
            refreshEntitiesFromDB();
        }
    }
    
    /**
        *Method implementation to load entities from filesystem 
        *storage into the in memory collection
        *
        * @author Baseem Astiphan
        * @return TreeMap in-memory collection of entire database
    */
    public TreeMap<String, SongForDB> refreshEntitiesFromDB()
    {
        String str;  //Holds a line of text
        SongForDB song; //Holds the song object
        
        //Try with resources to create a reader for the stoarge file
        try(BufferedReader br = new BufferedReader(new FileReader(dataFile)))
        {
            //Loop through file
            while((str = br.readLine()) != null)
            {
                //Create a default valued song
                song = new SongForDB();
                //Set the song to values from the database
                song = song.readFromDBToObject(str, delimiter);
                //place the song in the in-memory collection
                getEntities().put(song.getKeyField(), song);
            }
        }
        catch (Exception ex)
        {
            //Write error to standard output
            System.out.println(ex);
        }
        
        //return the in-memory collection
        return getEntities();
    }
    
    //Below main method is just used for testing, and is thus commented out. It can be 
    //uncommented for running tests.
    
    /*
    public static void main(String[] args)
    {
        SongDataContext d = new SongDataContext("testFile4.data", "|");
        
        
        System.out.println("\nExists: " + d.exists());
        try
        {
            System.out.println("\nNew File: " + d.createNewDataFile());
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        
        try
        {
            System.out.println("\nExists: " + d.exists());
            
            System.out.println("Im here");
            SongForDB song  = new SongForDB("Song 5", "S01", "Just a good first song", "Baseem Astiphan", "Songs", 9.99);      

            System.out.println("Im here");
            
            SongForDB song2 = new SongForDB("Song 3", "S10", "Just a good second song", "Baseem Astiphan", "Songs2", 6.99);
            SongForDB song3 = new SongForDB("Song 7", "S22", "Just a good second song", "Baseem Astiphan", "Songs2", 6.99);
            SongForDB song4 = new SongForDB("Song 9", "S17", "Just a good second song", "Baseem Astiphan", "Songs2", 6.99);
            SongForDB song5 = new SongForDB("Song 1", "S11", "Just a good second song", "Baseem Astiphan", "Songs2", 6.99);
            SongForDB song6 = new SongForDB("Song 6", "S04", "Just a good second song", "Baseem Astiphan", "Songs2", 6.99);
            SongForDB song7 = new SongForDB("Song 3", "S09", "Just a good second song", "Baseem Astiphan", "Songs2", 6.99);

            d.addEntity(song);
            d.addEntity(song2);
            d.addEntity(song3);
            d.addEntity(song4);
            d.addEntity(song5);
            d.addEntity(song6);
            d.addEntity(song7);
            
            
            System.out.println("Checkpoint");
            d.refreshEntitiesFromDB();
            System.out.println("Checkpoint2");
            
            for (Map.Entry s : d.getEntities().entrySet())
            {
                System.out.print("KEY: " + s.getKey());
                System.out.println("VAL: " + s.getValue());
            }

            d.deleteEntity(song4);

            song2.setTitle("Song 2");
            song2.setDescription("Just a really, really good second song");
            d.editEntity(song2);

            // System.out.println("No force: " + d.createNewDataFile());
            // System.out.println("Force: " + d.createNewDataFile(true));


            // d.deleteEntity(song);
            
            // System.out.println("After del");
            // for (Map.Entry s : d.getEntities().entrySet())
            // {
                // System.out.print("KEY: " + s.getKey());
                // System.out.println("VAL: " + s.getValue());
            // }
            
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }

    }
    */
}