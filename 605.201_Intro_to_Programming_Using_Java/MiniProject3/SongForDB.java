import java.util.regex.Pattern;
import java.util.Set;
import java.util.HashSet;
public class SongForDB extends Song implements Comparable<SongForDB>, Persistable<String, SongForDB>
{
    private static Set<String> usedItemCodes = new HashSet<String>();
    public SongForDB() 
    {
        super();
    }
    
    public SongForDB(String title, String itemCode, String description, String artist, String album, double price)
        throws Exception
    {
        setTitle(title);
        setItemCodePreventingDuplicates(itemCode);
        setDescription(description);
        setArtist(artist);
        setAlbum(album);
        setPrice(price);
    }
    
    public void setItemCodePreventingDuplicates(String itemCode)
        throws Exception
    {
        if (!SongForDB.isItemCodeAvailable(itemCode))
        {
            throw new Exception("This item code is used");
        }
        
        SongForDB.usedItemCodes.add(itemCode);
        super.setItemCode(itemCode);
    }
    
    @Override
    public int compareTo(SongForDB s2)
    {
        String thisSong = this.getTitle() + "-" + this.getItemCode();
        String otherSong = s2.getTitle() + "-" + this.getItemCode();
        return thisSong.compareTo(otherSong);
    }
    
    @Override
    public String getKeyField()
    {
        return getTitle() + "-" + getItemCode();
    }
    
    @Override
    public String writeToDatabase(String delimeter)
    {
        return getTitle()       + delimeter +
               getItemCode()    + delimeter +
               getDescription() + delimeter +
               getArtist()      + delimeter +
               getAlbum()       + delimeter +
               Double.toString(getPrice());
    }

    @Override 
    public SongForDB readFromDBToObject(String dbLine, String delimeter)
    {
        SongForDB temp = SongForDB.parseSong(dbLine, delimeter);
        
        setTitle(temp.getTitle());
        setItemCode(temp.getItemCode());
        setDescription(temp.getDescription());
        setArtist(temp.getArtist());
        setAlbum(temp.getAlbum());
        setPrice(temp.getPrice());
        
        return this;
    }

    public static SongForDB parseSong(String line, String delimeter)
    {
        SongForDB song = new SongForDB();
        String[] elements = line.split(Pattern.quote(delimeter));
        
        //Maybe this can be an exception of something. Now it just returns empty new song
        //maybe this is actually OK
        if (elements.length != 6)
        {
            System.out.println("Incorrect number of elements!");
            System.out.println(line + " " + delimeter + " " + elements.length);
            return song;
        }
        
        song.setTitle(elements[0].trim());
        song.setItemCode(elements[1].trim());
        song.setDescription(elements[2].trim());
        song.setArtist(elements[3].trim());
        song.setAlbum(elements[4].trim());
        song.setPrice(Double.parseDouble(elements[5]));
        
        return song;
    }
    
    public static boolean isItemCodeAvailable(String itemCode)
    {
        return !usedItemCodes.contains(itemCode);
    }
}