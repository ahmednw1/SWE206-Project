import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class watchTournamentController implements Initializable {

    static int tournament;

    @FXML
    private FlowPane card;

    @FXML
    private Label date;

    @FXML
    private Label goals1;

    @FXML
    private Label goals2;

    @FXML
    private TableColumn<?, ?> points;

    @FXML
    private TableColumn<?, ?> ranks;

    @FXML
    private Label team1;

    @FXML
    private Label team2;

    @FXML
    private TableColumn<?, ?> teams;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label tournamentLabel;

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

    @FXML
    void backClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Tournaments.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(App.getTournaments().get(tournament).getParticipants());
        App.getTournaments().get(tournament).generateMatches();
        App.getTournaments().get(tournament).closeRegistration();
        App.write();
        VBox vbox = new VBox();
        ArrayList<Match> matches = App.getTournaments().get(tournament).getMatches();
        System.out.println(matches);
        Label tournamentLabel = new Label(App.getTournaments().get(tournament).tString());
        tournamentLabel.setPrefSize(317.0, 34.0);
        tournamentLabel.setAlignment(Pos.CENTER);
        for (int j = 0; j < matches.size(); j++) {

            Match match = matches.get(j);
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setPrefSize(353.0, 100.0);
            anchorPane.setTopAnchor(anchorPane, 0.0);
            anchorPane.setLeftAnchor(anchorPane, 0.0);
            anchorPane.setBottomAnchor(anchorPane, 0.0);
            anchorPane.setRightAnchor(anchorPane, 0.0);

            FlowPane card = new FlowPane();
            card.setPrefSize(318.0, 152.0);
            card.setLayoutX(18.0);
            card.setLayoutY(27.0);
            card.setEffect(new ColorAdjust(-0.1, -1.0, 1.0, 1.0));

            Line line = new Line();
            line.setStartX(-100.0);
            line.setEndX(217.2928924560547);
            line.setEndY(-0.621320366859436);
            line.setStrokeWidth(1.0);
            line.setStroke(Color.BLACK);

            Label team1 = new Label(match.getTeam1());
            team1.setPrefSize(144.0, 29.0);
            team1.setAlignment(Pos.CENTER);
            team1.setFont(new Font(24.0));

            Label vs = new Label("vs");

            Label team2 = new Label(match.getTeam2());
            team2.setPrefSize(151.0, 29.0);
            team2.setAlignment(Pos.CENTER);
            team2.setFont(new Font(24.0));

            Label goals1 = new Label(match.getScore().get(0).toString());
            goals1.setPrefSize(143.0, 38.0);
            goals1.setAlignment(Pos.CENTER);

            Label vs2 = new Label("vs");

            Label goals2 = new Label(match.getScore().get(1).toString());
            goals2.setPrefSize(141.0, 38.0);
            goals2.setAlignment(Pos.CENTER);


            Label date = new Label(match.getDate().toString());
            date.setPrefSize(317.0, 34.0);
            date.setAlignment(Pos.CENTER);

            card.getChildren().addAll(team1, vs, team2, goals1, vs2, goals2, tournamentLabel, date, line);

            ColorAdjust effect = new ColorAdjust();
            effect.setBrightness(-0.1);
            effect.setContrast(-1.0);
            effect.setHue(1.0);
            effect.setSaturation(1.0);

            card.setEffect(effect);

            anchorPane.getChildren().add(card);
            vbox.getChildren().add(anchorPane); // add each AnchorPane to the VBox
        }
        scrollPane.setContent(vbox);

       
    }

    public static void select(int t) {
        tournament = t;
        
    }

}