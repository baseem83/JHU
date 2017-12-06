//HAVE NOT YET CONTROLLED FOR PRICE, MAKE SURE IT IS APPROPRIATE

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.collections.*;
import java.io.*;
import java.util.Scanner;
import javafx.event.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
    *This class extends a javafx application to provide
    *a user interface for the song database.
    *Users can add, edit, and delete songs, which get stored
    *into a text file.
    *
    * @author Baseem Astiphan
    * @version 1.0.0.1
*/
public class SongDatabaseUserForm extends Application
{
    //Data persistence manager
    private SongDataContext dataContext;
    
    //Button to add song
    private Button addButton = new Button("Add");
    
    //Button to edit song
    private Button editButton;
    
    //Button to delete song
    private Button deleteButton;
    
    //Button to accept changes
    private Button acceptButton;
    
    //Button to revert changes
    private Button cancelButton;
    
    //Button to exit the application
    private Button exitButton;
    
    //Combobox to hold the songs
    private ComboBox<String> songCombo;
    
    //TextField holding the itemCode
    private TextField itemCodeText;
    
    //TextField holding the description
    private TextField descText;
    
    //TextField holding the artist
    private TextField artistText;
    
    //TextField holding the album
    private TextField albumText;
    
    //TextField holding the price
    private TextField priceText;
    
    //Label holding the form's status
    private Label statusLabel;

    //Status enum detailing the state of the form
    private Status formStatus;

    //An array of the songs supplied by the dataContext
    private SongForDB[] songArray;
    
    //A song instance holding the currently selected song in the combo
    private SongForDB selectedSong;
    
    //A placeholder, to be used for reversion
    private SongForDB placeholderSong;
    
    //The index in the array/combo of the selected song
    private int selectedSongIndex;

    /**
        *Override of the application start method, used to layout
        *the GUI.
        *
        * @author Baseem Astiphan
        * @param primaryStage the main stage
    */
    public void start(Stage primaryStage)
    {
        //Put below into a separate class, that launces the app
        /*
        String fileEntry;
        Scanner input = new Scanner(System.in);

        if (this.getParameters().getUnnamed().size() == 0)
        {
            System.out.println("Please enter a database name");
            return;
        }

        fileEntry = this.getParameters().getUnnamed().get(0).trim();
        dataContext = 
            new SongDataContext(fileEntry, "|");

        if (!dataContext.exists())
        {
            System.out.println(fileEntry + " does not exist. Would you like to " +
                    "create it? [y/n]");

            if (input.nextLine().toLowerCase().equals("y"))
            {
                try
                {
                    dataContext.createNewDataFile();
                }
                catch(Exception ex)
                {
                    System.out.println(ex);
                }
            }       
            else
            {
                return;
            }
        }
        */
        
        //set form title
        primaryStage.setTitle("Songs Libaray");

        //Create a new GridPane to hold controls. (NOTE, we didn't learn
        //this control in class, but reading about it, it seemed like the
        //most capabale layout manager for the type of application
        //we are building. So I spent some time learning it.)
        GridPane entryGrid = new GridPane();
        
        //Create some padding, so things aren't tightly squeezed together
        entryGrid.setPadding(new Insets(20, 20, 20, 20));
        entryGrid.setVgap(10); //Vertical gap inside grid cells
        entryGrid.setHgap(10); //Horizontal gap inside grid cells
        
        //Create a ColumnConstraint to dictate width and alighment
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        column1.setHalignment(HPos.RIGHT);
        
        //Create a ColumnConstraint to dictate width 
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(70);

        //Add the newly created ColumnConstraints to the grid
        entryGrid.getColumnConstraints().addAll(column1, column2);

        //Label for the title
        Label titleLabel = new Label("Title");
        
        //Create a new song combobox, string types
        songCombo = new ComboBox<String>();
       
        //Item code label
        Label itemCodeLabel = new Label("Item Code");
        
        //Create itemCode textField
        itemCodeText = new TextField();
        
        //set prefreed size for the item code textfield
        itemCodeText.setPrefWidth(100);
        
        //Don't fill itemCode textfield into grid cell
        GridPane.setFillWidth(itemCodeText, false);

        //Description label
        Label descLabel = new Label("Description");
        
        //create new description textfield
        descText = new TextField();

        //Artist label
        Label artistLabel = new Label("Artist");
        
        //Create new textfield for artist
        artistText = new TextField();

        //Album label
        Label albumLabel = new Label("Album");
        
        //Create new album textfield
        albumText = new TextField();

        //Price label
        Label priceLabel = new Label("Price");
        
        //Create new textfield for price
        priceText = new TextField();
        
        //Set preferred width for price text
        priceText.setPrefWidth(100);
        
        //Don't fill itemCode textfield into grid cell
        GridPane.setFillWidth(priceText, false);

        //Below lines defines the location of the controls
        //within the grid
        GridPane.setConstraints(titleLabel, 0, 0);
        GridPane.setConstraints(songCombo, 1, 0);
        GridPane.setConstraints(itemCodeLabel, 0, 1);
        GridPane.setConstraints(itemCodeText, 1, 1);
        GridPane.setConstraints(descLabel, 0, 2);
        GridPane.setConstraints(descText, 1, 2);
        GridPane.setConstraints(artistLabel, 0, 3);
        GridPane.setConstraints(artistText, 1, 3);
        GridPane.setConstraints(albumLabel, 0, 4);
        GridPane.setConstraints(albumText, 1, 4);
        GridPane.setConstraints(priceLabel, 0, 5);
        GridPane.setConstraints(priceText, 1, 5);

        //add all of the controls to the grid, in their appropriate
        //places
        entryGrid.getChildren().addAll(titleLabel, songCombo,
                                       itemCodeLabel, itemCodeText,
                                       descLabel, descText,
                                       artistLabel, artistText,
                                       albumLabel, albumText,
                                       priceLabel, priceText);

        //Create a FlowPane to layout the buttons
        FlowPane buttonPane = new FlowPane(20, 10);

        //Create new controls for each of the button objects,
        //and set their text
        addButton = new Button("Add");
        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        acceptButton = new Button("Accept");
        cancelButton = new Button("Cancel");
        exitButton = new Button("Exit");

        //Javafx defaulted to autosizing each button, which resulted
        //in different size buttons, and an inconsistent layout. Not 
        //pretty. So below sets the preferred size for each button.
        addButton.setPrefWidth(70);
        editButton.setPrefWidth(70);
        deleteButton.setPrefWidth(70);
        acceptButton.setPrefWidth(70);
        cancelButton.setPrefWidth(70);
        exitButton.setPrefWidth(70);

        //Event handler for add button, using anonymous inner method
        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent ae)
            {
                setStatus(Status.ADD); //change the status to add
            }
        });

        //Event handler for edit button, using anonymous inner method
        editButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent ae)
            {
                setStatus(Status.EDIT); //change status to edit
            }
        });

        //Event handler for delete button, using anonymous inner method
        deleteButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent ae)
            {
                setStatus(Status.DELETE); //change status to delete
            }
        });

        //Event handler for accept button, using anonymous inner method
        acceptButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent ae)
            {
                try
                {
                    //call acceptChanges() method
                    acceptChanges();
                }
                catch (Exception ex)
                {
                    //Print error info to standard output
                    System.out.println(ex);
                }
            }
        });

        //Event handler for cancel button, using anonymous inner method
        cancelButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent ae)
            {
                if (formStatus == Status.ADD)
                {
                    //Change selected song back to the placeholder
                    selectedSong = placeholderSong;
                    
                    //set placeholder to null
                    placeholderSong = null;
                }
                setStatus(Status.VIEW);  //Change status to VIEW
            }
        });

        //Listener for combobox change, using anonymous inner method
        songCombo.getSelectionModel().selectedIndexProperty().addListener(
            new ChangeListener<Number>()
            {
                //Override changed method
                public void changed(ObservableValue<? extends Number> change, 
                    Number oldVal, Number newVal)
                {
                    //Check if the change was one that cleared the combobox
                    if (newVal.intValue() != -1)
                    {
                        //set the selected song index
                        selectedSongIndex = newVal.intValue();
                        
                        //set the selected song
                        selectedSong = songArray[selectedSongIndex];
                        
                        //refresh the layout
                        refreshLayout();
                    }
                }
            });

        //Add all buttons to the buttonpane
        buttonPane.getChildren().addAll(addButton, editButton,
                                        deleteButton, acceptButton,
                                        cancelButton, exitButton);

        //add the button pane to the entrygrid
        entryGrid.add(buttonPane, 0, 6, 2, 1);

        //Create a new label to hold the status
        statusLabel = new Label();

        //Set location of status label, and add it to the grid
        GridPane.setConstraints(statusLabel, 0, 7);
        entryGrid.getChildren().add(statusLabel);

        //Create a scene to hold the layout
        Scene songScene = new Scene(entryGrid, 500, 350);

        //Load up the combobox
        loadComboBox();
        
        //Set starting status to VIEW
        setStatus(Status.VIEW);

        //Set the stage's scene
        primaryStage.setScene(songScene);
        
        //Show the application
        primaryStage.show();
    }

    /**
        *Method to load the combobox with appropriate values.
        *
        * @author Baseem Astiphan
    */
    private void loadComboBox()
    {
        //Refresh the array of songs
        refreshSongArray();

        //Exit prematurely if the array is empty (no songs)
        if (songArray.length == 0)
        {
            return;
        }

        //Set the selectedSong to the first element of the array if not set
        if (selectedSong == null)
        {
            selectedSong = songArray[0];
        }

        //Clear the combobox
        songCombo.getItems().clear();

        //Add the title of each song in songArray to the combobox
        for(SongForDB s : songArray)
        {
            //add to combobox
            songCombo.getItems().add(s.getTitle());
        }
    }

    /**
        * Method to refresh the layout whenever a change occurs that
        * necessitates a layout chage.
        *
        * @author Baseem Astiphan
    */
    private void refreshLayout()
    {
        //If nothing is selected
        if (selectedSong == null)
        {
            //Set prompt text for combobox
            songCombo.setPromptText("No Selected Item");
            
            //Blank out all other textfields
            itemCodeText.setText("");
            descText.setText("");
            artistText.setText("");
            albumText.setText("");
            priceText.setText("");
        }
        else
        {
            //Set text value of controls to the corresponding
            //values of the selectedSong
            songCombo.setValue(selectedSong.getTitle());
            itemCodeText.setText(selectedSong.getItemCode());
            descText.setText(selectedSong.getDescription());
            artistText.setText(selectedSong.getArtist());
            albumText.setText(selectedSong.getAlbum());
            priceText.setText(Double.toString(selectedSong.getPrice()));
        }
    }

    /**
        *Method to be called when a user accepts changes. It handles actions 
        *appropriately based on the status of the form.
        *
        * @author Baseem Astiphan
        * @throws Exception if there is an issue modify data
    */
    private void acceptChanges()
        throws Exception
    {
        SongForDB song;  //Song placeholder

        //Determine form state
        switch(formStatus)
        {
            case ADD:  //form is in add mode
                //Create a new song based on data in fields
                song = generateSongFromFields();
                
                //Add new song to datacontext
                dataContext.addEntity(song);
                
                //Set selectedSong to the new song
                selectedSong = song;
                
                //Call loadCombo method
                loadComboBox();
                
                //Set status back to view
                setStatus(Status.VIEW);
                break;
            case EDIT:  //edit mode
                //Create a new song based on data in fields
                song = generateSongFromFields();
                
                //Edit song
                dataContext.editEntity(song);
                
                //Reload the comboBox
                loadComboBox();
                
                //set status back to view
                setStatus(Status.VIEW);
                break;
            case DELETE: //delete mode
                //delete the song from datacontext
                dataContext.deleteEntity(selectedSong);
                
                //set selectedsong to null
                selectedSong = null;
                
                //reload the combobox
                loadComboBox();
                
                //set status back to VIEW
                setStatus(Status.VIEW);
                break;
        }
    }

    /**
        *Method to create a new song from the values 
        *currently stored in the respective controls.
        *
        * @author Baseem Astiphan
        * @return new song
    */
    private SongForDB generateSongFromFields()
    {
        //Create a new song object
        SongForDB song = new SongForDB();
        
        //Below lines set properties on the new song
        //to the corresponding properties in textFieds
        //and the combobox
        song.setTitle(songCombo.getValue());
        song.setItemCode(itemCodeText.getText());
        song.setDescription(descText.getText());
        song.setArtist(artistText.getText());
        song.setAlbum(albumText.getText());
        song.setPrice(Double.parseDouble(priceText.getText()));

        //return newly created song
        return song;
    }

    /**
        *This method sets the status of the form. Based on the status,
        *certain fields have different properties.
        *
        * @author Baseem Astiphan
        * @param status the new form status
    */
    private void setStatus(Status status)
    {
        //set form status to the new status
        formStatus = status;
        
        //update text in status label
        statusLabel.setText("Status: " + formStatus.name());
        
        switch(status)
        {
            case ADD:  //add mode
                //set a placeholder song 
                placeholderSong = selectedSong;
                
                //set selected song to a new one
                selectedSong = new SongForDB();

                //set disabled property of controls
                songCombo.setDisable(false);
                itemCodeText.setDisable(false);
                descText.setDisable(false);
                artistText.setDisable(false);
                albumText.setDisable(false);
                priceText.setDisable(false);

                //set disabled property of buttons
                addButton.setDisable(true);
                editButton.setDisable(true);
                deleteButton.setDisable(true);
                acceptButton.setDisable(false);
                cancelButton.setDisable(false);
                exitButton.setDisable(true);

                //set editable property of combo
                songCombo.setEditable(true);

                break;
            case EDIT: //edit mode
                //set disabled property of controls
                songCombo.setEditable(false);
                songCombo.setDisable(true);
                itemCodeText.setDisable(true);
                descText.setDisable(false);
                artistText.setDisable(false);
                albumText.setDisable(false);
                priceText.setDisable(false);

                //set disabled property of buttons    
                addButton.setDisable(true);
                editButton.setDisable(true);
                deleteButton.setDisable(true);
                acceptButton.setDisable(false);
                cancelButton.setDisable(false);
                exitButton.setDisable(true);

                break;
            case DELETE: //delete mode
                //set disabled property of controls
                songCombo.setEditable(false);
                songCombo.setDisable(true);
                itemCodeText.setDisable(true);
                descText.setDisable(true);
                artistText.setDisable(true);
                albumText.setDisable(true);
                priceText.setDisable(true);
                
                //set disabled property of buttons
                addButton.setDisable(true);
                editButton.setDisable(true);
                deleteButton.setDisable(true);
                acceptButton.setDisable(false);
                cancelButton.setDisable(false);
                exitButton.setDisable(true);

                break;
            case VIEW: // view mode
                //set editable property of combo
                songCombo.setEditable(false);
                
                //set disabled property of controls
                songCombo.setDisable(false);
                itemCodeText.setDisable(true);
                descText.setDisable(true);
                artistText.setDisable(true);
                albumText.setDisable(true);
                priceText.setDisable(true);
                
                //set disabled property of buttons
                addButton.setDisable(false);
                editButton.setDisable(false);
                deleteButton.setDisable(false);
                acceptButton.setDisable(true);
                cancelButton.setDisable(true);
                exitButton.setDisable(false);

                break;

        }

        //refresh song array when stat changes
        refreshSongArray();
        
        //refresh layout on status change
        refreshLayout();
    }

    /**
        *Method to create an array of songs from the TreeMap containing
        *the key, song pairs.
        *
        * @author Baseem Astiphan
    */
    private void refreshSongArray()
    {
        //Create a new SongForDB typed array of the songs
        songArray = dataContext.getEntities().values().toArray(new SongForDB[0]);
    }

    /**
        *Main method that may or may not be called to launch application,
        *depending on version.
        *
        * @author Baseem Astiphan
        * @param args command line arguments
    */
    public static void main(String [] args)
    {
        launch(args);
    }

    /**
        *Enum holding form states
        *
        * @author Baseem Astiphan
    */
    enum Status
    {
        ADD,
        EDIT,
        DELETE,
        VIEW
    }
}