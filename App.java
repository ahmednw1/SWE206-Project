import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
    Scene mainScene;
    Scene tournamentScene;
    Scene addTournamentScene;
    VBox tournamentList;
    BorderPane mainLayout = new BorderPane();
    Database database = new Database();
    ArrayList<Tournament> tournaments = database.getTournaments();
    ArrayList<Student> students = database.getStudents();
    ArrayList<Team> teams = database.getTeams();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Start page
        primaryStage.setTitle("Tournament App");
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

            // Check if the username and password are correct
            if (username.equals("admin") && password.equals("password")) {
                loginStatus.setText("Login successful!");
                loginButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.setScene(loginScene());
                    }
                });
            } else {
                loginStatus.setText("Incorrect username or password.");
            }
        });
       

        // Create a toolbar for the main page
        ToolBar toolBar = new ToolBar();
        Button homeButton = new Button("Home");
        Button tournamentButton = new Button("Tournaments");
        Button profileButton = new Button("Profile");
        toolBar.getItems().addAll(homeButton, tournamentButton, profileButton);
        mainLayout.setBottom(toolBar);

        // Create a label for the title of the page
        Label titleLabel = new Label("Welcome to the Tournament App");
        titleLabel.setFont(new Font("Arial", 20));
        mainLayout.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        // Create a search bar for matches by date
        HBox searchBox = new HBox();
        Label searchLabel = new Label("Search by date:");
        DatePicker searchDate = new DatePicker();
        Button searchButton = new Button("Search");
        searchBox.getChildren().addAll(searchLabel, searchDate, searchButton);
        mainLayout.setLeft(searchBox);

        // Create a button to add a tournament
        Button addTournamentButton = new Button("Add Tournament");
        mainLayout.setRight(addTournamentButton);
        BorderPane.setAlignment(addTournamentButton, Pos.CENTER);

        // Create a list of tournaments
        tournamentList = new VBox();
        tournamentList.setSpacing(10);
        tournamentList.setAlignment(Pos.CENTER);
        mainLayout.setCenter(tournamentList);

        // Create the scene for the main page
        mainScene = new Scene(mainLayout, 800, 600);

        // Create a button to navigate to the tournament page
        Button tournamentPageButton = new Button("Tournaments");

        tournamentPageButton.setOnAction(e -> primaryStage.setScene(tournamentScene));

        // Create the scene for the tournament page
        BorderPane tournamentLayout = new BorderPane();
        tournamentLayout.setTop(new Label("Tournaments"));
        tournamentLayout.setCenter(tournamentList);
        tournamentLayout.setBottom(toolBar);
        tournamentScene = new Scene(tournamentLayout, 800, 600);

        // Create a button to navigate to the add tournament page
        Button addTournamentPageButton = new Button("Add Tournament");
        addTournamentPageButton.setOnAction(e -> primaryStage.setScene(addTournamentScene));

        // Create the scene for the add tournament page
        GridPane addTournamentLayout = new GridPane();
        addTournamentLayout.setAlignment(Pos.CENTER);
        addTournamentLayout.setHgap(10);
        addTournamentLayout.setVgap(10);
        addTournamentLayout.add(new Label("Tournament Name:"), 0, 0);
        TextField tournamentName = new TextField();
        addTournamentLayout.add(tournamentName, 1, 0);
        addTournamentLayout.add(new Label("Start Date:"), 0, 1);
        DatePicker startDate = new DatePicker();
        addTournamentLayout.add(startDate, 1, 1);
        addTournamentLayout.add(new Label("End Date:"), 0, 2);
        DatePicker endDate = new DatePicker();
        addTournamentLayout.add(endDate, 1, 2);
        Button addTournamentSubmitButton = new Button("Submit");
        addTournamentSubmitButton.setOnAction(e -> {
            String name = tournamentName.getText();
            LocalDate start = startDate.getValue();
            LocalDate end = endDate.getValue();
            tournaments.add(new Tournament());
            tournamentList.getChildren().clear();
            for (Tournament t : tournaments) {
                tournamentList.getChildren().add(new Label("t.getName()"));
            }
            primaryStage.setScene(tournamentScene);
        });
        addTournamentLayout.add(addTournamentSubmitButton, 1, 3);
        addTournamentScene = new Scene(addTournamentLayout, 800, 600);

        // Create a button to navigate to the match score page
        Button matchScoreButton = new Button("Add Match Scores");
        matchScoreButton.setOnAction(e -> {
            // Display a list of matches and enable the user to add scores
            VBox matchList = new VBox();
            matchList.setSpacing(10);
            matchList.setAlignment(Pos.CENTER);
            for (Tournament t : tournaments) {
                
            }
            BorderPane matchScoreLayout = new BorderPane();
            matchScoreLayout.setTop(new Label("Add Match Scores"));
            matchScoreLayout.setCenter(matchList);
            matchScoreLayout.setBottom(toolBar);
            Scene matchScoreScene = new Scene(matchScoreLayout, 800, 600);
            primaryStage.setScene(matchScoreScene);
        });

        // Add the buttons to the main layout
        mainLayout.setCenter(tournamentPageButton);
        mainLayout.setRight(addTournamentPageButton);
        mainLayout.setLeft(searchBox);
        mainLayout.setBottom(toolBar);
        mainLayout.setTop(titleLabel);
        mainLayout.setRight(matchScoreButton);
        BorderPane.setAlignment(tournamentPageButton, Pos.CENTER);
        BorderPane.setAlignment(addTournamentPageButton, Pos.CENTER);
        BorderPane.setAlignment(matchScoreButton, Pos.CENTER);

        // Display the main scene
        primaryStage.setScene(mainScene);
        primaryStage.show();

        Scene scene = new Scene(borderPane, 400, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Login page
    public Scene loginScene() {
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

            // Check if the username and password are correct
            if (username.equals("admin") && password.equals("password")) {
                loginStatus.setText("Login successful!");
            } else {
                loginStatus.setText("Incorrect username or password.");
            }
        });

        Scene scene = new Scene(borderPane, 400, 275);
        return scene;
    }

    // Main page
   
}