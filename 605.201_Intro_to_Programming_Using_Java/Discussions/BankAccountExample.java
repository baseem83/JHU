public class BankAccountExample
{
    public static void main (String [] args)
    {
        // Good transactions
        UnsecureAccount account1 = new UnsecureAccount(1000f);
        UnsecureAccount account2 = new UnsecureAccount(1500f);
        
        account1.TransferMoney(account2, 25f);
        account1.TransferMoney(account2, 175f);
        
        
        //Bad transactions
        //Created money out of thin air
        account1.IncreaseMoney(1000f);
        account1.PrintBalance("This ");
        account2.PrintBalance("Transferring ");
        
        
        //Secure accounts do not allow you to call Increase/Decrease money
        SecureAccount account3 = new SecureAccount(1000f);
        SecureAccount account4 = new SecureAccount(1500f);
        
        account3.TransferMoney(account4, 25f);
        account3.TransferMoney(account4, 175f);
        
        
        //Below would throw a compiler error
        account3.IncreaseMoney(1000f);      
    }
}

class SecureAccount
{
    private float bankBalance;
    public SecureAccount(float startingBalance)
    {
        bankBalance = startingBalance;
    }
    
    public void TransferMoney(SecureAccount account, float amount)
    {
        account.DeductMoney(amount);
        this.IncreaseMoney(amount);
        
        account.PrintBalance("Transferring ");
        this.PrintBalance("This ");
    }
    
    private void DeductMoney(float amount)
    {
        bankBalance -= amount;
    }
    
    private void IncreaseMoney(float amount)
    {
        bankBalance += amount;
    }
    
    public void PrintBalance(String accountName)
    {
        System.out.println(accountName + "account Balance is: " + bankBalance);
    }
}

class UnsecureAccount
{
    private float bankBalance;
    public UnsecureAccount(float startingBalance)
    {
        bankBalance = startingBalance;
    }
    
    public void TransferMoney(UnsecureAccount account, float amount)
    {
        account.DeductMoney(amount);
        this.IncreaseMoney(amount);
        
        account.PrintBalance("Transferring ");
        this.PrintBalance("This ");
    }
    
    public void DeductMoney(float amount)
    {
        bankBalance -= amount;
    }
    
    public void IncreaseMoney(float amount)
    {
        bankBalance += amount;
    }
    
    public void PrintBalance(String accountName)
    {
        System.out.println(accountName + "account Balance is: " + bankBalance);
    }
}