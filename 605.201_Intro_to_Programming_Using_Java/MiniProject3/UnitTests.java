import java.io.File;

public class UnitTests
{
    public static void main (String [] args)
    {
        createNewSongOverloadedConstructor();
        createNewSongDefaultConstructor();
        parseSongFromLineOfTextWithDelimeter();
        writeToDatabaseOutputsCorrectResult();
        // createNewFileIfItDoesNotExist();
        
        System.out.println("Finished without error");
    }
    
    private static void createNewSongDefaultConstructor()
    {
        Song song = new Song();
        
        assert (song instanceof Song) : "Song is not of class Song";
    }
    
    private static void createNewSongOverloadedConstructor()
    {
        Song song = new Song("Song 1", "S01", "Just a good first song", "Baseem Astiphan", "Songs", 9.99);
        
        assert (song.getTitle().equals("Song 1")) : "Title Doesn't Match";
        assert (song.getItemCode().equals("S01")) : "ItemCode Doesn't Match";
        assert (song.getDescription().equals("Just a good first song")) : "Desc Doesn't Match";
        assert (song.getArtist().equals("Baseem Astiphan")) : "Artist Doesn't Match";
        assert (song.getAlbum().equals("Songs")) : "Album Doesn't Match";
        assert (song.getPrice() == 9.99) : "Price Doesn't Match";
    }
    
    
    private static void parseSongFromLineOfTextWithDelimeter()
    {
        String line = "Song 1, S01, Just a good first song, Baseem Astiphan, Songs, 9.99";
        
        Song song = Song.parseSong(line, ",");
        
        assert (song.getTitle().equals("Song 1")) : "Title Doesn't Match";
        assert (song.getItemCode().equals("S01")) : "ItemCode Doesn't Match";
        assert (song.getDescription().equals("Just a good first song")) : "Desc Doesn't Match";
        assert (song.getArtist().equals("Baseem Astiphan")) : "Artist Doesn't Match";
        assert (song.getAlbum().equals("Songs")) : "Album Doesn't Match";
        assert (song.getPrice() == 9.99) : "Price Doesn't Match";
    }
    
    private static void writeToDatabaseOutputsCorrectResult()
    {
        String line = "Song 1, S01, Just a good first song, Baseem Astiphan, Songs, 9.99";
        
        SongForDB song = SongForDB.parseSong(line, ",");
        
        assert (song.writeToDatabase(",").equals("Song 1,S01,Just a good first song,Baseem Astiphan,Songs,9.99")) : "DB Output is incorrect" ;
    }
    
    // private static void createNewFileIfItDoesNotExist()
    // {
        // File file = new File("unitTests.txt");
        
        // if (file.exists())
        // {
            // file.delete();
        // }
        
        // assert (!file.exists()) : "File already exists";
        
        // file = MainMethods.getOrCreateDatabase("unitTests.txt");
        
        // assert (file.exists()) : "File has not been created successfuly";
    // }
    
}