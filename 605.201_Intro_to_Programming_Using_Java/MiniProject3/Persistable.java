public interface Persistable<T>
{
    T getKeyField();
    String writeToDatabase();
}