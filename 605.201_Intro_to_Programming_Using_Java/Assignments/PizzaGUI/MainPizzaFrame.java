//In GUI app, is it best practice to call methods on a form state variable,
//or should the state be passed in as argument (ie, recalculate price: I simply
//reference the forms 'pizzSize' attribute in a method that takes no args).

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
    *This class creates the main panel to be used as the content
    *of the pizza GUI main frame. This sets up radio buttons for the
    *size of the pizza, as well as checkboxes related to toppings.
    *
    * @author Baseem Astiphan
    * @varsion 1.0.0.0
*/
class MainPizzaPanel extends JPanel
{
    //Set up Swing radio buttons for each pizza size
    private JRadioButton smallPizzaRadio;  //small
    private JRadioButton mediumPizzaRadio; //medium
    private JRadioButton largePizzaRadio;  //large

    //Set up checkboxes for various toppings. This is a 
    //fully vegetarian pizza, so sorry, no meat toppings.
    private JCheckBox plainCheckBox;          //plain
    private JCheckBox broccoliCheckBox;       //broccoli
    private JCheckBox peppersCheckBox;        //peppers
    private JCheckBox oliveCheckBox;          //olive
    private JCheckBox sunDriedTomatoCheckBox; //sun-dried tomatoes
    private JCheckBox basilCheckBox;          //basil

    //Instance variable for the label to show the price of the pizza
    private JLabel priceLabel;

    //Variable to hold enum, related to size of the pizza
    private PizzaSize pizzaSize;

    //Instance variable to hold total price of pizza
    private double pizzaPrice;

    /**
        *This is a constructor for the main pizza panel.
        *It instantiates controls for the, and adds panels
        *
        * @author Baseem Asstiphan
    */
    public MainPizzaPanel()
    {
        //Create new radio button controls for pizza sizes
        //Set the small size as the initially selected value
        smallPizzaRadio = new JRadioButton("Small", true);
        mediumPizzaRadio = new JRadioButton("Medium");
        largePizzaRadio = new JRadioButton("Large");

        //Craete a button group to house the size radio buttons
        ButtonGroup sizeGroup = new ButtonGroup();
        
        //Add radio buttons to the button group
        sizeGroup.add(smallPizzaRadio);
        sizeGroup.add(mediumPizzaRadio);
        sizeGroup.add(largePizzaRadio);

        //Create a titled to surround the pizza size radio buttons
        Border sizeBorder = BorderFactory.createEtchedBorder();
        sizeBorder = BorderFactory.createTitledBorder(sizeBorder, "Select a size");

        //Add inline action listeners for pizza size radio clicks. This is handled
        //inline using lambda expressions to call the change size method.
        smallPizzaRadio.addActionListener( (ae) -> changeSize(PizzaSize.SMALL));
        mediumPizzaRadio.addActionListener( (ae) -> changeSize(PizzaSize.MEDIUM));
        largePizzaRadio.addActionListener( (ae) -> changeSize(PizzaSize.LARGE));

        //Create a panel house the sizing controls
        JPanel sizePanel = new JPanel();
        //Use a flow layout for control rendering
        sizePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        //Add respective radio buttons for sizes
        sizePanel.add(smallPizzaRadio);
        sizePanel.add(mediumPizzaRadio);
        sizePanel.add(largePizzaRadio);

        //Set the border for the group
        sizePanel.setBorder(sizeBorder);

        //Create checkboxes for the various topping options. This is a fully 
        //vegetarian pizza, so no meat toppings. Plain is the default.
        plainCheckBox = new JCheckBox("Plain", true);
        broccoliCheckBox = new JCheckBox("Broccoli");
        peppersCheckBox = new JCheckBox("Peppers");
        oliveCheckBox = new JCheckBox("Olives");
        sunDriedTomatoCheckBox = new JCheckBox("Sun Dried Tomatoes");
        basilCheckBox = new JCheckBox("Basil");

        //Create an instance of the internal class used to handle topping events
        PizzaCheckboxListener listener = new PizzaCheckboxListener();

        //Add new class instance as event handler for the topping checkboxes
        plainCheckBox.addActionListener(listener);
        broccoliCheckBox.addActionListener(listener);
        peppersCheckBox.addActionListener(listener);
        oliveCheckBox.addActionListener(listener);
        sunDriedTomatoCheckBox.addActionListener(listener);
        basilCheckBox.addActionListener(listener);

        //Create a border with title for the toppings checkboxes
        Border toppingsBorder = BorderFactory.createEtchedBorder();
        toppingsBorder = BorderFactory.createTitledBorder(toppingsBorder, "Toppings");

        //Createa a panel to hold the toppings
        JPanel toppingsPanel = new JPanel();

        //Set a layout manager
        toppingsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        //Add respective topping checkboxes to the appropriate panel
        toppingsPanel.add(plainCheckBox);
        toppingsPanel.add(broccoliCheckBox);
        toppingsPanel.add(peppersCheckBox);
        toppingsPanel.add(oliveCheckBox);
        toppingsPanel.add(sunDriedTomatoCheckBox);
        toppingsPanel.add(basilCheckBox);

        //Set border to the toppings checkbox panel
        toppingsPanel.setBorder(toppingsBorder);

        //Create a label to hold the total price
        priceLabel = new JLabel("Total cost is: ");
        
        //Add a panel to hold the price label
        JPanel pricePanel = new JPanel();
        
        //Set layout manager for the pricing panel
        pricePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Add label to the price panel
        pricePanel.add(priceLabel);

        //Use a border layout for the entirety of the panel
        this.setLayout(new BorderLayout());

        //Add sub panels to the top level panel
        this.add(sizePanel, BorderLayout.NORTH);
        this.add(toppingsPanel, BorderLayout.CENTER);
        this.add(pricePanel, BorderLayout.SOUTH);

        //Call changeSize method to set state to the appropriate size
        changeSize(PizzaSize.SMALL);
    }

    /**
        *This method is used to change the variable that managers
        *state of pizza size. It first sets the pizzaSize variable to the 
        *appropriate size, then it recalculates the total price.
        *
        * @author Baseem Astiphan
        * @size The new pizza size
    */
    private void changeSize(PizzaSize size)
    {
        //Set the variable holding pizza size
        pizzaSize = size;
        
        //Recalculate the pizza price
        recalculatePizzaPrice();
    }

    /**
        *This method is used to recalculate the price of the pizza based on size
        *and number of toppings. This required weighing a few options, and making
        *a compromise. This method runs through each of the possible checkboxes
        *and increments the value based on whether or not they are selected. This may
        *be a less efficient solution because it has to run through each of the check
        *boxes at each run. The benefit, of course, is that the code is much cleaner
        *and requires less conditionals and/or new inner classes.
        *
        * @author Baseem Astiphan
    */
    private void recalculatePizzaPrice()
    {
        //Create a variable to hold the price per topping (this can be refactored
        //to some type of a factory class in the future).
        double perToppingPrice = 0; //holds the price of a topping, based on size
        
        //Determine pizza size to determine starting cost and per topping price
        switch(pizzaSize)
        {
            case SMALL:  //Small pizza
                pizzaPrice = 5.50;  //starts at $5.50
                perToppingPrice = 0.50;  //Each topping is $0.50
                break;
            case MEDIUM: //Medium pizza
                pizzaPrice = 8.50;  //starts at $8.50
                perToppingPrice = 1.50; //Each topping is $1.50
                break;
            case LARGE:  //Large pizza
                pizzaPrice = 12.50;  //starts at $12.50
                perToppingPrice = 2.00; //Each topping is $2.00
                break;
        }

        //Run through checkboxes, and increment total cost if checked
        pizzaPrice += (broccoliCheckBox.isSelected()) ? perToppingPrice : 0;
        pizzaPrice += (peppersCheckBox.isSelected()) ? perToppingPrice : 0;
        pizzaPrice += (oliveCheckBox.isSelected()) ? perToppingPrice : 0;
        pizzaPrice += (sunDriedTomatoCheckBox.isSelected()) ? perToppingPrice : 0;
        pizzaPrice += (basilCheckBox.isSelected()) ? perToppingPrice : 0;

        //Write the total cost to the label
        priceLabel.setText("Total cost with toppings: " + String.format("$%.2f",pizzaPrice));
    }

    /**
        *This method is used when the the Plain checkbox is selected.
        *It deselects all of the other checkboxes because a pizza cannot 
        *be plain and also have toppings.
        *
        * @author Baseem Astiphan
    */
    private void makePizzaPlain()
    {
        //Deselect all topping checkboxes
        broccoliCheckBox.setSelected(false);
        peppersCheckBox.setSelected(false);
        oliveCheckBox.setSelected(false);
        sunDriedTomatoCheckBox.setSelected(false);
        basilCheckBox.setSelected(false);
    }

    /**
        *This inner class is used to implement an event handler
        *for the toppings checkboxes. It implements the ActionListener
        *interface, and implements the actionPerformed method.
        *
        * @author Baseem Astiphan
        * @implements ActionListener
    */
    public class PizzaCheckboxListener implements ActionListener
    {
        /**
            *This method responds to toppings checkbox clicks.
            *If the plain checkbox is selected, it calls the makePlain
            *method. Otherwise, it deselects the plain checkbox since
            *a pizza can't be both plain and have toppings.
            *
            * @author Baseem Astiophan
            * @param ae Action Event
        */
        public void actionPerformed(ActionEvent ae)
        {
            //Determine caller
            Object source = ae.getSource();

            //If plain checkbox, make the pizza plain
            if (source == plainCheckBox)
            {
                if (plainCheckBox.isSelected())
                {
                    makePizzaPlain(); //call plain method
                }           
            }
            else //not plain
            {
                //deselect the plain checkbox
                plainCheckBox.setSelected(false);
            }
            
            //Call the recalculation method
            recalculatePizzaPrice();
        }
    }
}

/**
    *This is the class that declares the top level user interface for the 
    *pizza ordering application.
    *
    * @author Baseem Astiphan
    * @version 1.0.0.0
*/
public class MainPizzaFrame extends JFrame
{
    /**
        *Constructor for the main pizza frame.
        *
        * @author Baseem Astiphan
    */
    public MainPizzaFrame()
    {
        //Create a new frame
        JFrame frame = new JFrame();
        
        //Add a title to the frame
        frame.setTitle("Pizza Order");
        
        //Set the size of the frame
        frame.setSize(300, 200);
        
        //Center the frame in the screen
        frame.setLocationRelativeTo(null);
        
        //Exit the form when closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add a new instance of the main pizza panel
        frame.add(new MainPizzaPanel());
        
        //Make the frame visible
        frame.setVisible(true);
    }

    /**
        *Generate a new pizza interface on the event dispatching thread
        *
        * @author Baseem Astiphan
    */
    public static void main (String [] args)
    {
        //Create a new interface on the event dispatching thread
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new MainPizzaFrame();
            }
        });
    }
}

/**
    *Enum to manage the size of the pizza
*/
enum PizzaSize
{
    SMALL, MEDIUM, LARGE;
}