import java.util.*;

public class SongDataContext extends TextFileDataContext<String, Song>
{
    public SongDataContext(String filePath, String delimeter)
    {
        super(filePath, delimeter);
    }
    
    public TreeMap<String, Song> refreshEntitiesFromDB()
    {
        System.out.println("Yet to be implemented");
        return getEntities();
    }
    
    public static void main(String[] args)
    {
        SongDataContext d = new SongDataContext("testFile3.data", "|");
        System.out.println(d.exists());
        System.out.println(d.isEmpty());
        
        try
        {
            System.out.println(d.createNewDataFile());
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}