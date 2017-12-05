import java.util.*;
import java.io.*;

public class SongDataContext extends TextFileDataContext<String, Song>
{   
    public SongDataContext(String filePath, String delimiter)
    {
        super(filePath, delimiter);
        refreshEntitiesFromDB();
    }
    
    public TreeMap<String, Song> refreshEntitiesFromDB()
    {
        String str;
        Song song;
        
        try(BufferedReader br = new BufferedReader(new FileReader(dataFile)))
        {
            while((str = br.readLine()) != null)
            {
                song = new Song();
                song = song.readFromDBToObject(str, delimiter);
                getEntities().put(song.getKeyField(), song);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        return getEntities();
    }
    
    public static void main(String[] args)
    {
        SongDataContext d = new SongDataContext("testFile3.data", "|");
        
        
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
            
            Song song  = new Song("Song 5", "S01", "Just a good first song", "Baseem Astiphan", "Songs", 9.99);            
            Song song2 = new Song("Song 3", "S10", "Just a good second song", "Baseem Astiphan", "Songs2", 6.99);
            Song song3 = new Song("Song 7", "S22", "Just a good second song", "Baseem Astiphan", "Songs2", 6.99);
            Song song4 = new Song("Song 9", "S17", "Just a good second song", "Baseem Astiphan", "Songs2", 6.99);
            Song song5 = new Song("Song 1", "S11", "Just a good second song", "Baseem Astiphan", "Songs2", 6.99);
            Song song6 = new Song("Song 6", "S04", "Just a good second song", "Baseem Astiphan", "Songs2", 6.99);
            Song song7 = new Song("Song 3", "S16", "Just a good second song", "Baseem Astiphan", "Songs2", 6.99);

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
        
        
        
        System.out.println(d.isEmpty());

        // TextFileDataContext<Song> s = new TextFileDataContext<Song>();
        // s.entity = song;
        
        // System.out.println(s.entity.toString());
    }
}