public class Song implements Comparable<Song>, Persistable<String, Song>
{
    private String title;
    private String itemCode;
    private String description;
    private String artist;
    private String album;
    private double price;
    
    public Song(String title, String itemCode, String description, String artist, String album, double price)
    {
        this.title = title;
        this.itemCode = itemCode;
        this.description = description;
        this.artist = artist;
        this.album = album;
        this.price = price;
    }
    
    public Song() {}
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getItemCode()
    {
        return itemCode;
    }
    
    public void setItemCode(String itemCode)
    {
        this.itemCode = itemCode;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getArtist()
    {
        return artist;
    }
    
    public void setArtist(String artist)
    {
        this.artist = artist;
    }
    
    public String getAlbum()
    {
        return album;
    }
    
    public void setAlbum(String album)
    {
        this.album = album;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    @Override
    public int compareTo(Song s2)
    {
        return this.getTitle().compareTo(s2.getTitle());
    }
    
    @Override
    public String getKeyField()
    {
        return getTitle();
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
    public Song readFromDBToObject(String dbLine, String delimeter)
    {
        Song temp = Song.parseSong(dbLine, delimeter);
        song = temp;
    }
    
    public static Song parseSong(String line, String delimeter)
    {
        Song song = new Song();
        String[] elements = line.split(delimeter);
        
        //Maybe this can be an exception of something. Now it just returns empty new song
        //maybe this is actually OK
        if (elements.length != 6)
        {
            System.out.println("Incorrect number of arguments!");
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
    
    @Override
    public String toString()
    {
        return getTitle() + " (" + getArtist() + ") " +
               getDescription() + ". Album: " + getAlbum() +
               " Price: " + getPrice();
    }
}