import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TeamsController implements Initializable {
    
    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-border-color: #007574; -fx-border-radius:10;");
        anchorPane.setPrefSize(452.0, 193.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);

        FlowPane card = new FlowPane();
        card.setPrefSize(428.0, 181.0);
        card.setLayoutX(3.0);
        card.setLayoutY(12.0);
        card.setEffect(new ColorAdjust(-0.1, -1.0, 1.0, 1.0));

        Label teamName = new Label("Team");
        teamName.setFont(new Font(24));
        teamName.setPrefSize(214, 38);
        teamName.setAlignment(Pos.CENTER);

        RadioButton deleteButton = new RadioButton("delete");
        deleteButton.setPrefSize(66.0, 23.0);
        deleteButton.setOnAction(event -> {
            try {
                deleteButton(event);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        });

        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(430, 99);
        gridPane.getColumnConstraints().addAll(
        new ColumnConstraints(),
        new ColumnConstraints(),
        new ColumnConstraints());
        gridPane.getRowConstraints().addAll(
        new RowConstraints(33),
        new RowConstraints(33),
        new RowConstraints(33));

        // GridPane gridPane = new GridPane();
        // gridPane.setPrefSize(430, 99);
        // gridPane.setAlignment(Pos.CENTER); // Set center alignment
        gridPane.setPadding(new Insets(30, 8, 0, 8));
        gridPane.setHgap(60);
        gridPane.setVgap(20);
        // gridPane.setPrefsize (500, 500);
        // Place nodes in the gridPane

        int buttonCount=9;
        for (int i = 0; i < buttonCount; i++) {
            RadioButton radioButton = new RadioButton("Name"+i);
            radioButton.prefHeight(20);
            radioButton.prefWidth(70);
            radioButton.setFont(new Font(17));
            int row = i / 3;
            int col = i % 3;
            GridPane.setConstraints(radioButton, col, row);
            gridPane.getChildren().add(radioButton);
        }
        

        card.getChildren().addAll(deleteButton, teamName,gridPane);// , line);

        ColorAdjust effect = new ColorAdjust();
        effect.setBrightness(-0.1);
        effect.setContrast(-1.0);
        effect.setHue(1.0);
        effect.setSaturation(1.0);

        card.setEffect(effect);

        anchorPane.getChildren().add(card);
        vBox.getChildren().add(anchorPane);
    }

    private void deleteButton(ActionEvent event) throws IOException {

    }

    @FXML
    void HomeClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void MatchesClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Matches.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ProfileClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    void teamsClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Teams.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}