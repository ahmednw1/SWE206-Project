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

            if (username.equals("admin") && password.equals("p")) {
                loginStatus.setText("Login successful!");
                primaryStage.setScene(mainScene);
            } else {
                loginStatus.setText("Incorrect username or password.");
            }
        });

        loginGroup.getChildren().add(borderPane);

        ///

        
        // Main page ( Main scene )
        BorderPane mainLayout = new BorderPane();

        ToolBar toolBar = new ToolBar();
        Button homeButton = new Button("Home");
        Button tournamentButton = new Button("Tournaments");
        Button profileButton = new Button("Profile");
        toolBar.getItems().addAll(homeButton, tournamentButton, profileButton);
        mainLayout.setBottom(toolBar);

        Label titleLabel = new Label("Welcome to the Tournament App");
        titleLabel.setFont(new Font("Arial", 20));
        mainLayout.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);

        HBox searchBox = new HBox();
        Label searchLabel = new Label("Search by date:");
        DatePicker searchDate = new DatePicker();
        Button searchButton = new Button("Search");
        searchBox.getChildren().addAll(searchLabel, searchDate, searchButton);
        mainLayout.setLeft(searchBox);

        Button addTournamentButton = new Button("Add Tournament");
        mainLayout.setRight(addTournamentButton);
        BorderPane.setAlignment(addTournamentButton, Pos.CENTER);

        VBox tournamentList = new VBox();
        tournamentList.setSpacing(10);
        tournamentList.setAlignment(Pos.CENTER);
        mainLayout.setCenter(tournamentList);

        Button tournamentPageButton = new Button("Tournaments");

        tournamentPageButton.setOnAction(e -> primaryStage.setScene(tournamentScene));

        // Tournament page ( Tournament scene )
        BorderPane tournamentLayout = new BorderPane();
        tournamentLayout.setTop(new Label("Tournaments"));
        tournamentLayout.setCenter(tournamentList);
        tournamentLayout.setBottom(toolBar);
        tournamentGroup.getChildren().add(tournamentLayout);

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
            tournaments.add(new Tournament(0, null, null, 0, name));
            tournamentList.getChildren().clear();
            for (Tournament t : tournaments) {
                tournamentList.getChildren().add(new Label("t.getName()"));
            }
            primaryStage.setScene(tournamentScene);
        });
        addTournamentLayout.add(addTournamentSubmitButton, 1, 3);
        addTournamentGroup.getChildren().add(addTournamentLayout);

        // Add the buttons to the main layout
        mainLayout.setCenter(tournamentPageButton);
        mainLayout.setRight(addTournamentPageButton);
        mainLayout.setLeft(searchBox);
        mainLayout.setBottom(toolBar);
        mainLayout.setTop(titleLabel);
        BorderPane.setAlignment(tournamentPageButton, Pos.CENTER);
        BorderPane.setAlignment(addTournamentPageButton, Pos.CENTER);
        mainGroup.getChildren().add(mainLayout);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

}