// ADD AN EDIT OPTION (OR INCLUDE IT IN THE ADDENTITY CODE, SINCE THAT MANAGES CHANGES AS WELL)
//Make ITEM CODE Final
//Need to cleanse persistence or 'sql injection'. Don't allow delimeter within fields, if not imple
//include in design document as a 'should have'

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.*;

public abstract class TextFileDataContext<K, T extends Comparable<T> & Persistable<K, T>>
{   
    protected File dataFile;
    protected TreeMap<K, T> entities = null;
    protected String delimiter;

    public TextFileDataContext(String filePath, String delimiter)
    {
        dataFile = new File(filePath);
        this.delimiter = delimiter;
    }
    
    public boolean exists()
    {
        return (dataFile.exists() || dataFile.isFile());
    }
    
    public boolean isEmpty()
    {
        return getEntities().size() == 0 ? true : false;
    }

    public boolean createNewDataFile() throws IOException
    {
        return dataFile.createNewFile();
    }

    public boolean createNewDataFile(boolean overwrite) throws IOException
    {
        if (overwrite && exists())
        {
            dataFile.delete();
        }
        return createNewDataFile();
    }
    
    public TreeMap<K, T> getEntities()
    {
        if (entities == null)
        {
            entities = new TreeMap<K, T>();
        }
        
        return entities;
    }

    public abstract TreeMap<K, T> refreshEntitiesFromDB();
    
    public void addEntity(T entity)
        throws Exception
    {

        if (!addEntityToCollection(entity))
        {
            throw new Exception("Error adding new item");
        }

        if (!addEntityToTextFile(entity))
        {
            //Maintain atomicity-- remove item from collection
            //if it failed to add to database
            getEntities().remove(entity.getKeyField());
            throw new Exception("Error persisting item");
        }
    }

    private boolean addEntityToCollection(T entity)
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
    
    private boolean addEntityToTextFile(T entity)
    {
        boolean output = true;
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile, true)))
        {
            if (dataFile.length() != 0)
            {
                bw.newLine();
            }
            bw.write(entity.writeToDatabase(delimiter));
            
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            output = false;
        }
        
        return output;
    }


    public void editEntity(T entity)
        throws Exception
    {
        T oldEntity = null;

        if (getEntities().containsKey(entity.getKeyField()))
        {
            oldEntity = getEntities().get(entity.getKeyField());
        }

        if (!editEntityInCollection(entity))
        {
            throw new Exception("Error adding new item");
        }

        if (!editEntityInTextFile(entity))
        {
            //Maintain atomicity-- remove item from collection
            //if it failed to add to database
            if (oldEntity != null)
            {
                getEntities().put(entity.getKeyField(), oldEntity);
            }
            throw new Exception("Error persisting item");
        }
    }

    private boolean editEntityInCollection(T entity)
    {
        return addEntityToCollection(entity);
    }

    private boolean editEntityInTextFile(T entity)
    {
        return deleteEntityFromTextFile(entity);
    }

    public void deleteEntity(T entity)
        throws Exception
    {
        if (!deleteEntityFromCollection(entity))
        {
            throw new Exception("Error adding new item");
        }

        if (!deleteEntityFromTextFile(entity))
        {
            //Maintain atomicity-- remove item from collection
            //if it failed to add to database
            getEntities().put(entity.getKeyField(), entity);
            throw new Exception("Error persisting item");
        }
    }
    
    private boolean deleteEntityFromCollection(T entity)
    {
        boolean output = true;

        try
        {
            getEntities().remove(entity.getKeyField());
        }
        catch (Exception ex)
        {
            output = false;
        }

        return output;
    }

    private boolean deleteEntityFromTextFile(T entity)
    {
        boolean output = true;

        try
        {
            createNewDataFile(true);

            for (Map.Entry<K, T> ent : getEntities().entrySet())
            {
                addEntityToTextFile(ent.getValue());
            }
        }
        catch (Exception ex)
        {
            output = false;
        }

        return output;
    }

    private void Test()
    {
        for (Map.Entry s : getEntities().entrySet())
            System.out.println("Type: " + s.getValue().getClass().getSimpleName());
    }
    
    public static void main(String[] args)
    {
        // TextFileDataContext<String, Song> d = new TextFileDataContext<String, Song>("testFile2.data", "|");
        
        // // System.out.println("File is: " + d.getDataFile().getAbsolutePath());
        // System.out.println("\nExists: " + d.exists());
        // try
        // {
            // System.out.println("\nNew File: " + d.createNewDataFile());
        // }
        // catch (Exception ex)
        // {
            // System.out.println(ex);
        // }
        
        // System.out.println("\nExists: " + d.exists());
        
        // Song song = new Song("Song 1", "S01", "Just a good first song", "Baseem Astiphan", "Songs", 9.99);
        
        // d.addEntityToCollection(song);
        
        // for (Map.Entry s : d.getEntities().entrySet())
        // {
            // System.out.print("KEY: " + s.getKey());
            // System.out.println("VAL: " + s.getValue());
        // }
        
        // d.addEntityToTextFile(song);

        // d.deleteEntityFromCollection(song);
        
        // d.deleteEntityFromTextFile(song);

        // System.out.println("After del");
        // for (Map.Entry s : d.getEntities().entrySet())
        // {
            // System.out.print("KEY: " + s.getKey());
            // System.out.println("VAL: " + s.getValue());
        // }

        // System.out.println(d.isEmpty());

        // // TextFileDataContext<Song> s = new TextFileDataContext<Song>();
        // // s.entity = song;
        
        // // System.out.println(s.entity.toString());
    }
}