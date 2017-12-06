/**
    * Interface definining methods needed for persistence to a text file database
    *
    * @author Baseem Astiphan
    * @version 1.0.0.1
*/
public interface Persistable<T, O>
{
    T getKeyField();
    String writeToDatabase(String delimeter);
    O readFromDBToObject(String line, String delimeter);
}