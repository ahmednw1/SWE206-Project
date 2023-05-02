import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    // Data reading  
    Database database = new Database();
    ArrayList<Tournament> tournaments = database.getTournaments();
    ArrayList<Student> students = database.getStudents();
    ArrayList<Team> teams = database.getTeams();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tournament App");

        // Scences
        Group loginGroup = new Group();
        Scene loginScene = new Scene(loginGroup, 400, 275);

        Group mainGroup = new Group();
        Scene mainScene = new Scene(mainGroup, 400, 275);

        Group tournamentGroup = new Group();
        Scene tournamentScene = new Scene(tournamentGroup, 400, 275);

        Group addTournamentGroup = new Group();
        Scene addTournamentScene = new Scene(addTournamentGroup, 400, 275);

        Group profileGroup = new Group();
        Scene profileScene = new Scene(profileGroup, 400, 275);

        Group matchesGroup = new Group();
        Scene matchesScene = new Scene(matchesGroup, 400, 275);

        Group addScoreGroup = new Group();
        Scene addScoreScene = new Scene(addScoreGroup, 400, 275);

        Group selectedTournamnetGroup = new Group();
        Scene selectedTournamnetScene = new Scene(selectedTournamnetGroup, 400, 275);

        // Login page ( Login scene )
        BorderPane borderPane = new BorderPane();

        Text title = new Text("Login");
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        borderPane.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        borderPane.setCenter(gridPane);

        Label usernameLabel = new Label("Username:");
        gridPane.add(usernameLabel, 0, 1);

        TextField usernameTextField = new TextField();
        gridPane.add(usernameTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        gridPane.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        gridPane.add(passwordField, 1, 2);

        Button loginButton = new Button("Login");
        gridPane.add(loginButton, 1, 3);

        final Text loginStatus = new Text();
        gridPane.add(loginStatus, 1, 4);

        // Add a handler for the login button (API)
        loginButton.setOnAction(event -> {
            String username = usernameTextField.getText();
            String password = passwordField.getText();
            primaryStage.setScene(mainScene);
           
        });

        loginGroup.getChildren().add(borderPane);

        ///

        
        
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

}