import java.io.File;
import java.io.IOException;
import java.util.*;
import java.io.*;

/**
    *Abstract class modeling generic interaction with a Text File persistence
    *mechanism. This class creates functionality for adding, editing, and deleting
    *entities from a Text File using generics for general, runtime support.
    *
    *This specific implementation uses a TreeMap to store items in memory that back
    *the corresponding persisted elements. 
    *
    *This class extends a single abstrat method that must be implemented, 
    *refreshEntitiesFromDB(). This was not implemented as a generic to allow
    *for custom treatment of objects coming into scope.
    *
    * @author Baseem Astiphan
    * @version 1.0.0.1
*/  
public abstract class TextFileDataContext<K, T extends Comparable<T> & Persistable<K, T>>
{   
    protected File dataFile;  //File where data is persisted
    protected TreeMap<K, T> entities = null;  //In memory collection of objects
    protected String delimiter; //Delimiter for text file storage

    /**
        *Constructor taking a file path and a database delimiter. NOTE, a constructor
        *does not create a file, but just creates a File object at the 
        *specified path.
        *
        * @author Baseem Astiphan
        * @param filePath Path where data is stored
        * @delimiter How elements are separated in file storage
    */
    public TextFileDataContext(String filePath, String delimiter)
    {
        //Set datafile to file path
        dataFile = new File(filePath);
        
        //set delimiter
        this.delimiter = delimiter;
    }
    
    /**
        *Method specifying whether the dataFile exists, and is a legal file.
        *
        * @author Baseem Astiphan 
        * @return boolean reflecting whether datafile already exists
    */
    public boolean exists()
    {
        //True if datafile exists and is a file
        return (dataFile.exists() || dataFile.isFile());
    }
    
    /**
        *Convenience method to check if the dataFile is empty
        *
        * @author Baseem Astiphan
        * @return boolean reflecting whether file has data
    */
    public boolean isEmpty()
    {
        //If size is greater than 0, it's true
        return getEntities().size() == 0 ? true : false;
    }

    /**
        * Method to create a new datafile at the specified path. If one 
        * already exists, new file is not created and method returns false.
        *
        * @author Baseem Astiphan
        * @return Whether or not file was created
        * @throws IOException if file erred during creation
    */
    public boolean createNewDataFile() throws IOException
    {
        //Create new file
        return dataFile.createNewFile();
    }

    /**
        *Overloaded createNewDataFile method that will overwrite
        *an existing file if it exists, and a 'true' argument is passed.
        *
        * @author Baseem Astiphan
        * @param overwrite whether or not to overwrite an existing file
        * @return true if new file was created
        * @throws IOException if error during file creation
    */
    public boolean createNewDataFile(boolean overwrite) throws IOException
    {
        //If the file exists, and it was flagged for overwriting, delete it
        if (overwrite && exists())
        {
            dataFile.delete(); //delete
        }
        
        //Call method without overload
        return createNewDataFile();
    }
    /**
        *Return the TreeMap containing the in-memory list of objects and their keys
        *
        * @author Baseem Astiphan
        * @return treemap with respective entities
    */
    public TreeMap<K, T> getEntities()
    {
        //Use a singleton type pattern. If entities is not set, create a new 
        //treemap
        if (entities == null)
        {
            //create a new TreeMap
            entities = new TreeMap<K, T>();
        }
        
        //return entities
        return entities;
    }

    /**
        * Abstract method, for retrieving elements from storage.
        *
        * @author Baseem Astiphan
        * @return TreeMap<K,T> elements from DB
    */
    public abstract TreeMap<K, T> refreshEntitiesFromDB();
    
    /**
        *Method to add a new entity to both in-memory collection and 
        *filesystem storage.
        *
        * @author Baseem Astiphan
        * @param entity Object of type T to be added
        * @throws Exception if an error occurs during adding
    */
    public void addEntity(T entity)
        throws Exception
    {
        //If entity does not successfully add to in-memory collection
        if (!addEntityToCollection(entity))
        {
            //throw an exception if failure
            throw new Exception("Error adding new item");
        }

        //If failed to add to filesystem storage (but it did add to in-memory)
        if (!addEntityToTextFile(entity))
        {
            //Maintain atomicity-- remove item from collection
            //if it failed to add to database
            getEntities().remove(entity.getKeyField());
            
            //throw exception on error
            throw new Exception("Error persisting item");
        }
    }

    /**
        *Helper method to add entity to in-memory collection
        *
        * @author Baseem Astiphan
        * @param entity Element to be added
        * @return boolean true if successfully added
    */
    private boolean addEntityToCollection(T entity)
    {
        //to hold output
        boolean output = true;
        
        try
        {
            //add element to treeview collection
            getEntities().put(entity.getKeyField(), entity);
        }
        catch (Exception ex)
        {
            //on error, set output to false
            output = false;
        }
        
        //return result of operation
        return output;
    }
    
    /**
        *Helper method to add entity to filesystem storage
        *
        * @author Baseem Astiphan
        * @param entity Element to be added
        * @return boolean true if successfully added
    */
    private boolean addEntityToTextFile(T entity)
    {
        //to hold output
        boolean output = true;
        
        //Try with resources to created a writer
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile, true)))
        {
            //If not an empty file, add a newline character
            if (dataFile.length() != 0)
            {
                bw.newLine();
            }
            
            //write serialized version of object to datafile
            bw.write(entity.writeToDatabase(delimiter));
            
        }
        catch (Exception ex)  //Error occurs
        {
            System.out.println(ex);
            output = false;  //set output to false
        }
        
        //return result of operation
        return output;
    }

    /**
        *Method to edit an entity in both in-memory collection and 
        *filesystem storage.
        *
        * @author Baseem Astiphan
        * @param entity Object of type T to be edited
        * @throws Exception if an error occurs during edit
    */
    public void editEntity(T entity)
        throws Exception
    {
        //Keep a copy of the entity being changed
        T oldEntity = null;

        if (getEntities().containsKey(entity.getKeyField()))
        {
            //Set old version of the entity
            oldEntity = getEntities().get(entity.getKeyField());
        }

        //Throw exception if it doesn't add to in-memory
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
                //replace in-memory with original version
                getEntities().put(entity.getKeyField(), oldEntity);
            }
            
            //throw exception
            throw new Exception("Error persisting item");
        }
    }

    /**
        *Helper method to edit entity to in-memory collection.
        *Because this implementation's addEntityToCollection method 
        *overwrites entities with the same key, we can leverage the 
        *addEntity() method for the same behavior.
        *
        * @author Baseem Astiphan
        * @param entity Element to be edited
        * @return boolean true if successfully edited
    */    
    private boolean editEntityInCollection(T entity)
    {
        //Call the already defined add method
        return addEntityToCollection(entity);
    }

    /**
        *Helper method to edit entity to filesystem storage.
        *Because this implementation's edit functionality 
        *recreates the textFile on edit (just like the delete),
        *we can leverage the deleteEntityFromTextFile()'s behavior.
        *
        * @author Baseem Astiphan
        * @param entity Element to be edited
        * @return boolean true if successfully edited
    */    
    private boolean editEntityInTextFile(T entity)
    {
        //call the deleteEntityFromTextFile method
        return deleteEntityFromTextFile(entity);
    }

    /**
        *Method to delete an entity in both in-memory collection and 
        *filesystem storage.
        *
        * @author Baseem Astiphan
        * @param entity Object of type T to be delete
        * @throws Exception if an error occurs during delete
    */    
    public void deleteEntity(T entity)
        throws Exception
    {
        //Throw error if unsuccessfully deleted from collection
        if (!deleteEntityFromCollection(entity))
        {
            //error
            throw new Exception("Error adding new item");
        }

        //If it fails to delete from text file after successfully
        //deleting from in-memory collection
        if (!deleteEntityFromTextFile(entity))
        {
            //Maintain atomicity-- remove item from collection
            //if it failed to add to database
            getEntities().put(entity.getKeyField(), entity);
            
            //throw error
            throw new Exception("Error persisting item");
        }
    }
    
    /**
        *Helper method to delete entity to in-memory collection.
        *
        * @author Baseem Astiphan
        * @param entity Element to be edited
        * @return boolean true if successfully deleted
    */     
    private boolean deleteEntityFromCollection(T entity)
    {
        //method output
        boolean output = true;

        try
        {
            //Try to remove from in-memory collection
            getEntities().remove(entity.getKeyField());
        }
        catch (Exception ex)
        {
            //Set output to false on error
            output = false;
        }

        //return result of operation
        return output;
    }

    /**
        *Helper method to delete entity from filesystem storage.
        *
        * @author Baseem Astiphan
        * @param entity Element to be edited
        * @return boolean true if successfully edited
    */        
    private boolean deleteEntityFromTextFile(T entity)
    {
        //Hold result of operation
        boolean output = true;

        try
        {
            //Create a new dataFile, overwriting the old one
            createNewDataFile(true);

            //Loop over entities treemap
            for (Map.Entry<K, T> ent : getEntities().entrySet())
            {
                //Add entities to text file
                addEntityToTextFile(ent.getValue());
            }
        }
        catch (Exception ex)
        {
            //On error, set output to false
            output = false;
        }

        //return result of operation
        return output;
    }
}