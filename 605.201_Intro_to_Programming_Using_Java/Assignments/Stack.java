public class Stack 
{
    public static void main (String [] args)
    {
        Stacker myStack1 = new Stacker();
        Stacker myStack2 = new Stacker();
        
        for (int i = 0; i < 9; i++)
        {
            myStack1.push(i);
        }
        
        
        for (int i = 0; i < 20; i++)
        {
            myStack2.push(i);
        }
        
        System.out.println("Stack1 Stuff");
        for (int i = 0; i < 10; i++)
        {
            System.out.println(myStack1.pop());
        }
        
        System.out.println("Stack2 Stuff");
        for (int i = 0; i < 10; i++)
        {
            System.out.println(myStack2.pop());
        }
    }
}

class Stacker 
{
    int stck[] = new int[10];
    int tos;
    
    Stacker()
    {
        tos = -1;
    }
    
    void push(int item)
    {
        if (tos == 9)
            System.out.println("Stack is full");
        else
            stck[++tos] = item;
        
    }
    
    int pop ()
    {
        if (tos < 0)
        {
            System.out.println("Stack underflow");
            return 0;
        }
        else
        {
            return stck[tos--];
        }   
    }
}