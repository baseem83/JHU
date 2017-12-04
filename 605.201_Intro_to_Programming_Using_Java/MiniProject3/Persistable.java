public interface Persistable<T, O>
{
    T getKeyField();
    String writeToDatabase(String delimeter);
    O readFromDBToObject(String line, String delimeter);
}