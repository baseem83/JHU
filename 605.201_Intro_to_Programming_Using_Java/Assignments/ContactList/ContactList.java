import java.util.*;
import java.io.*;

/**
    * This class templates a general list of contacts, and 
    * adds methods to view, add, and delete contacts.
    *
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/
public class ContactList
{
    //TreeMap to hold contacts
    private TreeMap<String, Contact> contacts;

    //The filename for the contacts
    private String contactFile;

    /**
        * Getter for the contact treemap
        * @author Baseem Astiphan
    */
    public TreeMap<String, Contact> getContacts()
    {
        return contacts;
    }

    /**
        * Constructor that takes the fileName for data persistence.
        *
        * @author Baseem Astiphan
        * @param fileName path to contact file
    */
    public ContactList(String fileName)
    {
        //Create new instance of treemap
        contacts = new TreeMap<String, Contact>();

        //Set contact file to filename
        contactFile = fileName;

        //load contacts from file
        loadContacts(getContacts(), fileName);
    }


    /**
        * Setter for contact file
        *
        *@author Baseem Astiphan
        *@param file Filepath
    */
    public void setContactFile(String file)
    {
        contactFile = file;
    }

    /**
        * Getter for contact file path
        *
        * @author Baseem Astiphan
    */
    public String getContactFile()
    {
        return contactFile;
    }

    /**
        * Method to load any preexisting contacts from file
        *
        * @author Baseem Astiphan
        * @param cont represents a treemap
        * @param fileName for the file path
    */
    public void loadContacts(TreeMap<String, Contact> cont, String fileName)
    {
        String str = null;
        Contact contact = null;

        //Create a reader to read from input file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            //Is it end of file?
            while((str = br.readLine()) != null)
            {
                //create a new contact
                contact = Contact.parseContact(str);
                //add contact to list
                addContactToTreeMap(cont, contact);
            }
        }
        //Catch exceptions below
        catch (FileNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    /**
        *Method to add contacts to the tree map.
        * 
        * @author Baseem Astiphan
        * @param cont Treemap of contacts
        * @param contact to be added
    */  
    public void addContact(TreeMap<String, Contact> cont, Contact contact)
    {
        //Confirm contact key doesn't exist already
        if (cont.containsKey(contact.getLastName() + contact.getFirstName()))
        {
            System.out.println("Contact already exists!");
            return;
        }
        else
        {
            //Call helper methods to add contact to tree map and to file persistence
            addContactToTreeMap(cont, contact);
            addContactToFile(getContactFile(), contact);
            System.out.println("\nNew Contact Added");
        }
    }

    /**
        * Helper method to add contacts to the tree map.
        * 
        * @author Baseem Astiphan
        * @param cont Treemap of contacts
        * @param contact to be added
    */  
    private void addContactToTreeMap(TreeMap<String, Contact> cont, Contact contact)
    {
        //Add a contact to a treemap
        cont.put(contact.getLastName() + contact.getFirstName(), contact);
    }

    /**
        * Helper method to add contacts to the file.
        * 
        * @author Baseem Astiphan
        * @param filename the filepath
        * @param contact to be added
    */  
    private void addContactToFile(String fileName, Contact contact)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true)))
        {
            //Create a string to be added to file
            String output;
            output = contact.getLastName() + "," +
                     contact.getFirstName() + "," +
                     contact.getCompany() + "," +
                     contact.getPhoneNumber() + "," +
                     contact.getEmail();

            //Check if file is empty, if not, add new line before entering contact info
            File file = new File(fileName);
            if (file.length() != 0)
            {
                bw.newLine(); //add new line
            }
            bw.write(output);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }

    /**
        * Method to delete contacts from the tree map.
        * 
        * @author Baseem Astiphan
        * @param cont Treemap of contacts
        * @param key to be deleted
        * @param fileName path to contact file
    */  
    public void deleteContact(TreeMap<String, Contact> cont, String key, String fileName)
    {
        //Ensure key existss
        if (!(cont.containsKey(key)))
        {
            System.out.println("Contact does not exist");
            return;
        }

        //Call helper methods to delete from treemap and from file
        deleteContactFromTreeMap(cont, key);
        deleteContactFromFile(fileName, key, cont);
    }


    /**
        * Helper method to delete contacts from the tree map.
        * 
        * @author Baseem Astiphan
        * @param cont Treemap of contacts
        * @param key to be deleted
    */  
    private void deleteContactFromTreeMap(TreeMap<String, Contact> cont, String key)
    {
        //remove key from treemap
        cont.remove(key);
    }

    /**
        * Helper method to delete contacts from the file.
        * 
        * @author Baseem Astiphan
        * @param fileName location of contact persistence
        * @param key to be deleted
        * @param cont Treemap of contacts
    */  
    private void deleteContactFromFile(String fileName, String key, TreeMap<String, Contact> cont)
    {
        //Create a temp file to hold a copy of the data, in case of failure.
        File file = new File(fileName);
        if (!(file.renameTo(new File(fileName.split("\\.")[0] + "_backup." + fileName.split("\\.")[1]))))
        {
            System.out.println("Error deleting. Please contact an admin");
            return;
        }
        
        //Get a set from the treemap
        Set<Map.Entry<String, Contact>> contactSet = cont.entrySet();

        //Use a buffered writer to add non-deleted items to file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
        {
            for(Map.Entry<String, Contact> con : contactSet)
            {
                //Add item to file
                addContactToFile(fileName, con.getValue());
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
    }

    /**
        * Method to print items to screen
        *
        * @author Baseem Astiphan
        * @param cont Treemap with contacts
    */
    public void printContactList(TreeMap<String, Contact> cont)
    {
        // Convert treemap to set
        Set<Map.Entry<String, Contact>> contactSet = cont.entrySet();

        //Iterate through set, printing info
        for(Map.Entry<String, Contact> con : contactSet)
        {
            System.out.println(con.getValue());
        }
    }

    /*
        Tests Below
    */
    /*
    public static void main(String [] args)
    {
        // TreeMap<String, Contact> contacts = new TreeMap<String, Contact>(
        //     (cont1, cont2) -> cont1.compareTo(cont2));

        // Contact contact;

        // contact = new Contact("Bill", "Thoith", "Google", "212-555-1000", "abc@abc.com");
        // contacts.put(contact.getLastName() + contact.getFirstName(), contact);

        // contact = new Contact("Bill", "Smith", "Google", "212-555-1000", "abc@abc.com");
        // contacts.put(contact.getLastName() + contact.getFirstName(), contact);

        // contact = Contact.parseContact("Astiphan,Baseem,Johns Hopkins University,555-555-1515,bast1@fakedomain.com");
        // contacts.put(contact.getLastName() + contact.getFirstName(), contact);

        ContactList cl = new ContactList("AddressBook.txt");
        cl.loadContacts(cl.getContacts(), cl.getContactFile());

        Contact contact = new Contact("Bill", "Smith", "Google", "212-555-1000", "abc@abc.com");
        cl.addContact(cl.getContacts(), contact);
        cl.addContact(cl.getContacts(), contact);

        cl.printContactList(cl.getContacts());

        // cl.deleteContact(cl.getContacts(), contact, cl.getContactFile());
        System.out.println("Deleted\n");

        cl.printContactList(cl.getContacts());
        
    }*/

}