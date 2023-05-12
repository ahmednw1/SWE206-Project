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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class eleminationController implements Initializable {

    @FXML
    private ScrollPane stgesScrollPane;

    
    static int tournament;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label tournamentLabel;

    @FXML
    private TreeView<String> treeView;

    private TreeItem<String> root = new TreeItem<>("Matches");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tournament t = App.getTournaments().get(tournament);
        App.getTournaments().get(tournament).generateMatches();
        App.getTournaments().get(tournament).closeRegistration();
        App.write();
        AnchorPane stagesAnchorPane = new AnchorPane();
            stagesAnchorPane.setPrefSize(253.0, 100.0);
            stagesAnchorPane.setTopAnchor(stagesAnchorPane, 0.0);
            stagesAnchorPane.setLeftAnchor(stagesAnchorPane, 0.0);
            stagesAnchorPane.setBottomAnchor(stagesAnchorPane, 0.0);
            stagesAnchorPane.setRightAnchor(stagesAnchorPane, 0.0);
        ArrayList<Match> matches = App.getTournaments().get(tournament).getMatches();
        treeView.setRoot(root);
        treeView.setShowRoot(false);
        treeView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> System.out.println(newValue.getValue()));
        int numOfRounds = (int) Math.ceil(t.getParticipants().size()/2);
        
        System.out.println(t.getParticipants());
        System.out.println(numOfRounds);
        TreeItem<String>[] stageNodes = new TreeItem[numOfRounds];
        for (int i = 0; i < numOfRounds; i++) {
            stageNodes[i] = new TreeItem<>("Stage " + (i + 1));
            root.getChildren().add(stageNodes[i]);
        }

        TreeItem<String>[] matchNodes = new TreeItem[t.getParticipants().size()-1];
        int matchIndex = 0;
        for (int i = 0; i < numOfRounds; i++) {
            for (int j = 0; j < Math.pow(numOfRounds, numOfRounds - i - 1); j++) {
                matchNodes[matchIndex] = new TreeItem<>("Match " + (matchIndex + 1));
                stageNodes[i].getChildren().add(matchNodes[matchIndex]);
                matchIndex++;
            }
        }

        treeView.getRoot().setExpanded(true);
                for (TreeItem<String> item : treeView.getRoot().getChildren()) {
                    item.setExpanded(true);
                    for (TreeItem<String> subItem : item.getChildren()) {
                        subItem.setExpanded(true);
                    }
                }

        // Create scene
        VBox stagesVbox = new VBox(treeView);
        //anchorPane.getChildren().add(treeView);
        stagesVbox.getChildren().add(stagesAnchorPane);
        stgesScrollPane.setContent(stagesVbox);


        VBox vbox = new VBox();
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

    public static void select(int t) {
        tournament = t;

    }

}
