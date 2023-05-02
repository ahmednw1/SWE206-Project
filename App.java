import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 600;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tournament App");

        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);
        // Scences
        Group loginGroup = new Group();
        Scene loginScene = new Scene(loginGroup,600,600);

        Group mainGroup = new Group();
        Scene mainScene = new Scene(mainGroup,600,600);

        Group tournamentGroup = new Group();
        Scene tournamentScene = new Scene(tournamentGroup, 600, 600);

        Group addTournamentGroup = new Group();
        Scene addTournamentScene = new Scene(addTournamentGroup, 600, 600);

        Group profileGroup = new Group();
        Scene profileScene = new Scene(profileGroup, 600, 600);

        Group matchesGroup = new Group();
        Scene matchesScene = new Scene(matchesGroup, 600, 600);

        Group addScoreGroup = new Group();
        Scene addScoreScene = new Scene(addScoreGroup, 600, 600);

        Group selectedTournamnetGroup = new Group();
        Scene selectedTournamnetScene = new Scene(selectedTournamnetGroup, 600, 600);

        ///
        // Login page ( Login scene )
        ///

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
        gridPane.setAlignment(Pos.CENTER);
        // Add a handler for the login button (API)
        loginButton.setOnAction(event -> {
            String username = usernameTextField.getText();
            String password = passwordField.getText();
            primaryStage.setScene(mainScene);
           
        });

        loginGroup.getChildren().add(borderPane);

        ///
        /// Main page (main scene)
        ///

        Label topLabel = new Label("Welcome to the Tournament App");
        topLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        Button exploreTournamentsButton = new Button("Explore Tournaments");
        exploreTournamentsButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 16;");
        exploreTournamentsButton.setPrefSize(200, 60);

        Button addNewTournamentButton = new Button("Add New Tournament");
        addNewTournamentButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 16;");
        addNewTournamentButton.setPrefSize(200, 60);

        Button addScoreButton = new Button("Add Score");
        addScoreButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white; -fx-font-size: 16;");
        addScoreButton.setPrefSize(200, 60);

        Image homeIcon = new Image("home_icon.png", 50, 50, true, true);
        ImageView homeImageView = new ImageView(homeIcon);

        Image tournamentIcon = new Image("tournament_icon.png", 50, 50, true, true);
        ImageView tournamentImageView = new ImageView(tournamentIcon);

        Image personalIcon = new Image("profile_icon.png", 50, 50, true, true);
        ImageView personalImageView = new ImageView(personalIcon);

        HBox bottomBar = new HBox(30);
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        bottomBar.setPadding(new Insets(10));
        bottomBar.getChildren().addAll(homeImageView, tournamentImageView, personalImageView);

        // root pane and add all elements
        VBox root = new VBox(50);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(topLabel, exploreTournamentsButton, addNewTournamentButton, addScoreButton, bottomBar);

        mainGroup.getChildren().add(root);
        
        ///
        /// Add tournament page (add tournament scene)
        ///

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

}