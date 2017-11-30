import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class TextFileDataContext<K, T extends Comparable<T> & Persistable<K>>
{   
    private File dataFile;
    private TreeMap<K, T> entities = null;

 
    public TextFileDataContext(String filePath)
    {
        dataFile = new File(filePath);
    }
    
    public boolean exists()
    {
        return (dataFile.exists() || dataFile.isFile());
    }
    
    public boolean createNewDataFile() throws IOException
    {
        return dataFile.createNewFile();
    }
    
    public TreeMap<K, T> getEntities()
    {
        if (entities == null)
        {
            entities = new TreeMap<K, T>();
        }
        
        return entities;
    }
    
    public boolean addEntityToCollection(T entity)
    {
        boolean output = true;
        
        try
        {
            getEntities().put(entity.getKeyField(), entity);
        }
        catch (Exception ex)
        {
            output = false;
        }
        
        return output;
    }
    
    public boolean addEntityToTextFile(T entity)
    {
        boolean output = true;
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile, true)))
        {
            if (dataFile.length() != 0)
            {
                bw.newLine();
            }
            bw.write(entity.writeToDatabase());
            
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            output = false;
        }
        
        return output;
    }
    
    
    
    //Below should be removed when live. We don't want to return a reference to the actual file
    // public File getDataFile()
    // {
        // return dataFile;
    // }
    
    
    public static void main(String[] args)
    {
        TextFileDataContext<String, Song> d = new TextFileDataContext<String, Song>("testFile2.data");
        
        // System.out.println("File is: " + d.getDataFile().getAbsolutePath());
        System.out.println("\nExists: " + d.exists());
        try
        {
            System.out.println("\nNew File: " + d.createNewDataFile());
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
        
        System.out.println("\nExists: " + d.exists());
        
        Song song = new Song("Song 1", "S01", "Just a good first song", "Baseem Astiphan", "Songs", 9.99);
        
        d.addEntityToCollection(song);
        
        for (Map.Entry s : d.getEntities().entrySet())
        {
            System.out.print("KEY: " + s.getKey());
            System.out.println("VAL: " + s.getValue());
        }
        
        d.addEntityToTextFile(song);
        
        // TextFileDataContext<Song> s = new TextFileDataContext<Song>();
        // s.entity = song;
        
        // System.out.println(s.entity.toString());
    }
}