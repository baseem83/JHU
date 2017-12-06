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

public class SongDatabaseUserForm extends Application
{
    private SongDataContext dataContext;
    private Button addButton = new Button("Add");
    private Button editButton;
    private Button deleteButton;
    private Button acceptButton;
    private Button cancelButton;
    private Button exitButton;
    private ComboBox<String> songCombo;
    private TextField itemCodeText;
    private TextField descText;
    private TextField artistText;
    private TextField albumText;
    private TextField priceText;
    private Label statusLabel;

    private Status formStatus;

    private SongForDB[] songArray;
    private SongForDB selectedSong;
    private SongForDB placeholderSong;
    private int selectedSongIndex;

    @Override
    public void start(Stage primaryStage)
    {
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

        primaryStage.setTitle("Songs Libaray");

        GridPane entryGrid = new GridPane();
        // entryGrid.setPrefSize(300, 450);
        entryGrid.setPadding(new Insets(20, 20, 20, 20));
        entryGrid.setVgap(10);
        entryGrid.setHgap(10);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        column1.setHalignment(HPos.RIGHT);
        
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(70);

        entryGrid.getColumnConstraints().addAll(column1, column2);

        Label titleLabel = new Label("Title");
        songCombo = new ComboBox<String>();
        // songCombo.setEditable(true);
        // songCombo.setPromptText("Enter something here");

        Label itemCodeLabel = new Label("Item Code");
        itemCodeText = new TextField();
        itemCodeText.setPrefWidth(100);
        GridPane.setFillWidth(itemCodeText, false);

        Label descLabel = new Label("Description");
        descText = new TextField();

        Label artistLabel = new Label("Artist");
        artistText = new TextField();

        Label albumLabel = new Label("Album");
        albumText = new TextField();

        Label priceLabel = new Label("Price");
        priceText = new TextField();
        priceText.setPrefWidth(100);
        GridPane.setFillWidth(priceText, false);

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


        entryGrid.getChildren().addAll(titleLabel, songCombo,
                                       itemCodeLabel, itemCodeText,
                                       descLabel, descText,
                                       artistLabel, artistText,
                                       albumLabel, albumText,
                                       priceLabel, priceText);
        // rootNode.getChildren().add(entryGrid);

        // Scene songScene = new Scene(rootNode, 700, 500);

        FlowPane buttonPane = new FlowPane(20, 10);

        addButton = new Button("Add");
        editButton = new Button("Edit");
        deleteButton = new Button("Delete");
        acceptButton = new Button("Accept");
        cancelButton = new Button("Cancel");
        exitButton = new Button("Exit");

        addButton.setPrefWidth(70);
        editButton.setPrefWidth(70);
        deleteButton.setPrefWidth(70);
        acceptButton.setPrefWidth(70);
        cancelButton.setPrefWidth(70);
        exitButton.setPrefWidth(70);

        addButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent ae)
            {
                setStatus(Status.ADD);
            }
        });

        editButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent ae)
            {
                setStatus(Status.EDIT);
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent ae)
            {
                setStatus(Status.DELETE);
            }
        });

        acceptButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent ae)
            {
                try
                {
                    acceptChanges();
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                }
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent ae)
            {
                if (formStatus == Status.ADD)
                {
                    selectedSong = placeholderSong;
                    placeholderSong = null;
                }
                setStatus(Status.VIEW);
            }
        });


        songCombo.getSelectionModel().selectedIndexProperty().addListener(
            new ChangeListener<Number>()
            {
                public void changed(ObservableValue<? extends Number> change, 
                    Number oldVal, Number newVal)
                {
                    if (newVal.intValue() != -1)
                    {
                        selectedSongIndex = newVal.intValue();
                        selectedSong = songArray[selectedSongIndex];
                        refreshLayout();
                    }
                }
            });

        buttonPane.getChildren().addAll(addButton, editButton,
                                        deleteButton, acceptButton,
                                        cancelButton, exitButton);

        entryGrid.add(buttonPane, 0, 6, 2, 1);

        statusLabel = new Label();

        GridPane.setConstraints(statusLabel, 0, 7);
        entryGrid.getChildren().add(statusLabel);

        Scene songScene = new Scene(entryGrid, 500, 350);


        loadComboBox();
        setStatus(Status.VIEW);

        primaryStage.setScene(songScene);
        primaryStage.show();
    }

    private void loadComboBox()
    {
        refreshSongArray();

        if (songArray.length == 0)
        {
            return;
        }

        if (selectedSong == null)
        {
            selectedSong = songArray[0];
        }

        songCombo.getItems().clear();

        for(SongForDB s : songArray)
        {
            songCombo.getItems().add(s.getTitle());
        }

        // songCombo.setValue(songArray[0].getTitle());
        // selectedSong = songArray[0];
        // refreshLayout();
    }

    private void refreshLayout()
    {
        if (selectedSong == null)
        {
            songCombo.setPromptText("No Selected Item");
            itemCodeText.setText("");
            descText.setText("");
            artistText.setText("");
            albumText.setText("");
            priceText.setText("");
        }
        else
        {
            songCombo.setValue(selectedSong.getTitle());
            itemCodeText.setText(selectedSong.getItemCode());
            descText.setText(selectedSong.getDescription());
            artistText.setText(selectedSong.getArtist());
            albumText.setText(selectedSong.getAlbum());
            priceText.setText(Double.toString(selectedSong.getPrice()));
        }

        // Song song = songArray[index];
        // itemCodeText.setText(song.getItemCode());
        // descText.setText(song.getDescription());
        // artistText.setText(song.getArtist());
        // albumText.setText(song.getAlbum());
        // priceText.setText(Double.toString(song.getPrice()));
    }

    private void acceptChanges()
        throws Exception
    {
        SongForDB song;

        switch(formStatus)
        {
            case ADD:
                song = generateSongFromFields();
                dataContext.addEntity(song);
                selectedSong = song;
                loadComboBox();
                setStatus(Status.VIEW);
                break;
            case EDIT:
                song = generateSongFromFields();
                dataContext.editEntity(song);
                loadComboBox();
                setStatus(Status.VIEW);
                break;
            case DELETE:
                dataContext.deleteEntity(selectedSong);
                selectedSong = null;
                loadComboBox();
                setStatus(Status.VIEW);
                break;
        }
    }


    private SongForDB generateSongFromFields()
    {
        SongForDB song = new SongForDB();
        song.setTitle(songCombo.getValue());
        song.setItemCode(itemCodeText.getText());
        song.setDescription(descText.getText());
        song.setArtist(artistText.getText());
        song.setAlbum(albumText.getText());
        song.setPrice(Double.parseDouble(priceText.getText()));

        return song;
    }

    private void setStatus(Status status)
    {
        formStatus = status;
        statusLabel.setText("Status: " + formStatus.name());



        switch(status)
        {
            case ADD:
                placeholderSong = selectedSong;
                selectedSong = new SongForDB();

                songCombo.setDisable(false);
                itemCodeText.setDisable(false);
                descText.setDisable(false);
                artistText.setDisable(false);
                albumText.setDisable(false);
                priceText.setDisable(false);

                addButton.setDisable(true);
                editButton.setDisable(true);
                deleteButton.setDisable(true);
                acceptButton.setDisable(false);
                cancelButton.setDisable(false);
                exitButton.setDisable(true);

                songCombo.setEditable(true);

                break;
            case EDIT:
                songCombo.setEditable(false);
                songCombo.setDisable(true);
                itemCodeText.setDisable(true);
                descText.setDisable(false);
                artistText.setDisable(false);
                albumText.setDisable(false);
                priceText.setDisable(false);

                addButton.setDisable(true);
                editButton.setDisable(true);
                deleteButton.setDisable(true);
                acceptButton.setDisable(false);
                cancelButton.setDisable(false);
                exitButton.setDisable(true);

                break;
            case DELETE:
                songCombo.setEditable(false);
                songCombo.setDisable(true);
                itemCodeText.setDisable(true);
                descText.setDisable(true);
                artistText.setDisable(true);
                albumText.setDisable(true);
                priceText.setDisable(true);

                addButton.setDisable(true);
                editButton.setDisable(true);
                deleteButton.setDisable(true);
                acceptButton.setDisable(false);
                cancelButton.setDisable(false);
                exitButton.setDisable(true);

                break;
            case VIEW:
                songCombo.setEditable(false);
                songCombo.setDisable(false);
                itemCodeText.setDisable(true);
                descText.setDisable(true);
                artistText.setDisable(true);
                albumText.setDisable(true);
                priceText.setDisable(true);

                addButton.setDisable(false);
                editButton.setDisable(false);
                deleteButton.setDisable(false);
                acceptButton.setDisable(true);
                cancelButton.setDisable(true);
                exitButton.setDisable(false);

                break;

        }

        refreshSongArray();
        refreshLayout();
    }

    private void refreshSongArray()
    {
        songArray = dataContext.getEntities().values().toArray(new SongForDB[0]);
    }

    public static void main(String [] args)
    {
        launch(args);
    }

    enum Status
    {
        ADD,
        EDIT,
        DELETE,
        VIEW
    }
}