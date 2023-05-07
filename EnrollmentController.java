import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EnrollmentController implements Initializable {
    static Tournament selectedTournament;
    ArrayList<String> names = new ArrayList<>();
    User user = App.database.getCurrentUser();
    ArrayList<Team> teams = App.database.getTeams();

    ArrayList<User> users = App.users;

    ArrayList<Student> team = new ArrayList<>();
    @FXML
    private FlowPane card;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private ScrollPane scrollPane;

    ArrayList<TextField> ids = new ArrayList<TextField>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VBox vBox = new VBox();
        for (int i = 1; i < selectedTournament.getTeamNumber(); i++) {
            AnchorPane anchorPane = new AnchorPane();

            Pane card = new Pane();
            card.setLayoutX(-8.0);
            card.setLayoutY(6.0);
            card.setPrefHeight(55.0);
            card.setPrefWidth(399.0);

            Label nameLabel = new Label("Name:");
            nameLabel.setLayoutX(15.0);
            nameLabel.setLayoutY(14.0);
            nameLabel.setPrefHeight(29.0);
            nameLabel.setPrefWidth(79.0);
            nameLabel.setFont(new Font(24.0));

            TextField nameField = new TextField();
            nameField.setLayoutX(94.0);
            nameField.setLayoutY(16.0);
            nameField.setPrefHeight(26.0);
            nameField.setPrefWidth(300.0);
            nameField.setPromptText("name");

            card.getChildren().addAll(nameLabel, nameField);

            ids.add(nameField);
            anchorPane.getChildren().add(card);
            vBox.getChildren().add(anchorPane);
            nameField.setId("name" + i);
            scrollPane.setContent(vBox);

        }
    }

    @FXML
    void backClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Tournaments.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void enrollClicked(ActionEvent event) throws IOException {
        boolean failed = false;
        for (int i = 0; i < selectedTournament.getTeamNumber() - 1; i++) {
            try {
                String info = authentiacate(ids.get(i).getText());
                if (info != null) {
                    User user = App.database.getUser(Integer.parseInt(ids.get(i).getText()));
                    if (user == null) {
                        JSONObject jsonObject = new JSONObject(info);
                        if (jsonObject.getString("type").equals("admin")) {
                            throw new Exception();

                        } else {
                            Student student = new Student(jsonObject.getString("email"),
                                    Integer.parseInt(ids.get(i).getText()),
                                    jsonObject.getString("name"));
                            users.add(student);
                            App.write();
                            team.add((Student) student);
                        }

                    } else {
                        if (user instanceof Admin) {
                            throw new Exception();
                        }
                        team.add((Student) user);
                    }
                }
            } catch (Exception e) {
                failed = true;
                System.out.println(e);
            }
        }

        if (team.size() + 1 == selectedTournament.getTeamNumber() && !failed) {
            Team newTeam = new Team(selectedTournament.getName() + " "
                    + ((Integer) (selectedTournament.getParticipants().size() + 1)).toString());
            newTeam.addMember((Student) user);
            ((Student) user).addTeam(newTeam);
            for (int j = 0; j < selectedTournament.getTeamNumber() - 1; j++) {
                newTeam.addMember(team.get(j));
                team.get(j).addTeam(newTeam);

            }
            selectedTournament.addParticipant(newTeam);
            newTeam.tournamentEnroll(selectedTournament);

            teams.add(newTeam);
            App.write();

            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    public static void select(Tournament t) {
        selectedTournament = t;
    }

    public static String authentiacate(String username) throws Exception {
        String endpointUrl = "https://us-central1-swe206-221.cloudfunctions.net/app/User";

        try {
            URL url = new URL(endpointUrl + "?username=" + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Add authorization header with Base64 encoded username and password
            // String auth = username + ":" + pass;
            // byte[] encodedAuth =
            // Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            // String authHeaderValue = "Basic " + new String(encodedAuth);
            // connection.setRequestProperty("Authorization", authHeaderValue);

            // Read the response from the API endpoint
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            return response.toString();
        } catch (Exception e) {
            return null;
        }
    }

}
