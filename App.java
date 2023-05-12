import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
  static Database database = new Database();
  static ArrayList<User> users = database.getUsers();
  static ArrayList<Tournament> tournaments = database.getTournaments();
  static ArrayList<Team> teams = database.getTeams();

  @Override
  public void start(Stage primaryStage) {

    Parent root;
    try {

      root = FXMLLoader.load(getClass().getResource("Login.fxml"));
      Scene scene = new Scene(root);

      primaryStage.setTitle("Tournament App");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    System.out.println(App.getUsers());

    launch(args);
  }

  public static void write() {
    database.write(tournaments, teams, users);
    tournaments = database.getTournaments();
    users = database.getUsers();
    teams = database.getTeams();
  }

  public static ArrayList<Tournament> getTournaments() {
    return tournaments;
  }

  public static ArrayList<User> getUsers() {
    return users;
  }

  public static ArrayList<Team> getTeams() {
    return teams;
  }

}
