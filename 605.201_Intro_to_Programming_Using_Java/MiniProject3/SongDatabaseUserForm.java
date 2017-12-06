import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.collections.*;

public class SongDatabaseUserForm extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Songs Libaray");
        // FlowPane rootNode = new FlowPane(Orientation.VERTICAL, 5, 5);
        // rootNode.setAlignment(Pos.TOP_CENTER);

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
        ComboBox<String> songCombo = new ComboBox<String>();
        // songCombo.setEditable(true);
        // songCombo.setPromptText("Enter something here");

        Label itemCodeLabel = new Label("Item Code");
        TextField itemCodeText = new TextField();
        itemCodeText.setPrefWidth(100);
        GridPane.setFillWidth(itemCodeText, false);

        Label descLabel = new Label("Description");
        TextField descText = new TextField();

        Label artistLabel = new Label("Artist");
        TextField artistText = new TextField();

        Label albumLabel = new Label("Album");
        TextField albumText = new TextField();

        Label priceLabel = new Label("Price");
        TextField priceText = new TextField();
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

        Button addButton = new Button("Add");
        Button editButton = new Button("Edit");
        Button deleteButton = new Button("Delete");
        Button acceptButton = new Button("Accept");
        Button cancelButton = new Button("Cancel");
        Button exitButton = new Button("Exit");

        addButton.setPrefWidth(70);
        editButton.setPrefWidth(70);
        deleteButton.setPrefWidth(70);
        acceptButton.setPrefWidth(70);
        cancelButton.setPrefWidth(70);
        exitButton.setPrefWidth(70);

        buttonPane.getChildren().addAll(addButton, editButton,
                                        deleteButton, acceptButton,
                                        cancelButton, exitButton);

        entryGrid.add(buttonPane, 0, 6, 2, 1);

        Scene songScene = new Scene(entryGrid, 500, 350);

        primaryStage.setScene(songScene);
        primaryStage.show();
    }

    public static void main(String [] args)
    {
        launch(args);
    }
}