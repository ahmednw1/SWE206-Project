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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TournamentsController implements Initializable {

    @FXML
    private Label finishedEndDate;

    @FXML
    private Label finishedParticipantsNumber;

    @FXML
    private Label finishedStartDate;

    @FXML
    private Label finishedTournament;

    @FXML
    private Label finishedType;

    @FXML
    private Label onGoingEndDate;

    @FXML
    private Label onGoingParticipantsNumber;

    @FXML
    private ScrollPane ongoingScrollPane;

    @FXML
    private Label onGoingTournament;

    @FXML
    private Label onGoingType;

    @FXML
    private Label upComingEndDate;

    @FXML
    private Label upComingParticipantsNumber;

    @FXML
    private ScrollPane upcomingScrollPane;

    @FXML
    private Label upComingTournament;

    @FXML
    private Label upComingType;

    @FXML
    private TabPane tabPane;

    @FXML
    private ScrollPane finishedScrollPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VBox vboxFinish = new VBox();
        VBox vboxCurrent = new VBox();
        VBox vboxFuture = new VBox();

        ArrayList<Tournament> tournaments = App.database.getTournaments();
        LocalDate todya = LocalDate.now();
        for (int i = 0; i < tournaments.size(); i++) {
            if (tournaments.get(i).getEndDate().isBefore(todya)) {
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

                Label tournamentLabel = new Label(tournaments.get(i).tString());
                tournamentLabel.setPrefSize(399.0, 38.0);
                tournamentLabel.setAlignment(Pos.CENTER);
                tournamentLabel.setFont(new Font(24.0));
                tournamentLabel.setId("finishedTournament");

                Label typeLabel = new Label(tournaments.get(i).getType());
                typeLabel.setPrefSize(401.0, 40.0);
                typeLabel.setAlignment(Pos.CENTER);
                typeLabel.setFont(new Font(24.0));
                typeLabel.setId("finishedType");

                Label startDateLabel = new Label(tournaments.get(i).getStartDate().toString());
                startDateLabel.setPrefSize(159.0, 38.0);
                startDateLabel.setAlignment(Pos.CENTER);
                startDateLabel.setId("finishedStartDate");

                Label untilLabel = new Label("Until");
                untilLabel.setPrefSize(90.0, 34.0);
                untilLabel.setAlignment(Pos.CENTER);

                Label endDateLabel = new Label(tournaments.get(i).getEndDate().toString());
                endDateLabel.setPrefSize(151.0, 49.0);
                endDateLabel.setAlignment(Pos.CENTER);
                endDateLabel.setId("finishedEndDate");

                Label participantsLabel = new Label(tournaments.get(i).numberString());
                participantsLabel.setPrefSize(309.0, 52.0);
                participantsLabel.setAlignment(Pos.CENTER_LEFT);
                participantsLabel.setId("finishedParticipantsNumber");

                ImageView participantsImage = new ImageView(new Image("Imgs/profile_icon.png"));
                participantsImage.setFitWidth(24.0);
                participantsImage.setFitHeight(28.0);

                participantsLabel.setGraphic(participantsImage);
                RadioButton watchButton = new RadioButton("Watch");
                watchButton.setPrefSize(95.0, 46.0);
                watchButton.setOnAction(event -> {
                    try {
                        watchClicked(event);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                });

                Line line = new Line(-24.577177047729492, 34.9288330078125, 380.7157287597656, 34.92866516113281);

                card.getChildren().addAll(tournamentLabel, typeLabel, startDateLabel, untilLabel, endDateLabel,
                        participantsLabel, watchButton, line);

                ColorAdjust effect = new ColorAdjust();
                effect.setBrightness(-0.1);
                effect.setContrast(-1.0);
                effect.setHue(1.0);
                effect.setSaturation(1.0);

                card.setEffect(effect);

                anchorPane.getChildren().add(card);
                vboxFinish.getChildren().add(anchorPane);
            } else if (tournaments.get(i).getStartDate().isAfter(todya)) {
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

                Label tournamentLabel = new Label(tournaments.get(i).tString());
                tournamentLabel.setPrefSize(399.0, 38.0);
                tournamentLabel.setAlignment(Pos.CENTER);
                tournamentLabel.setFont(new Font(24.0));
                tournamentLabel.setId("finishedTournament");

                Label typeLabel = new Label(tournaments.get(i).getType());
                typeLabel.setPrefSize(401.0, 40.0);
                typeLabel.setAlignment(Pos.CENTER);
                typeLabel.setFont(new Font(24.0));
                typeLabel.setId("finishedType");

                Label startDateLabel = new Label(tournaments.get(i).getStartDate().toString());
                startDateLabel.setPrefSize(159.0, 38.0);
                startDateLabel.setAlignment(Pos.CENTER);
                startDateLabel.setId("finishedStartDate");

                Label untilLabel = new Label("Until");
                untilLabel.setPrefSize(90.0, 34.0);
                untilLabel.setAlignment(Pos.CENTER);

                Label endDateLabel = new Label(tournaments.get(i).getEndDate().toString());
                endDateLabel.setPrefSize(151.0, 49.0);
                endDateLabel.setAlignment(Pos.CENTER);
                endDateLabel.setId("finishedEndDate");

                Label participantsLabel = new Label(tournaments.get(i).numberString());
                participantsLabel.setPrefSize(170.0, 52.0);
                participantsLabel.setAlignment(Pos.CENTER_LEFT);
                participantsLabel.setId("finishedParticipantsNumber");

                ImageView participantsImage = new ImageView(new Image("Imgs/profile_icon.png"));
                participantsImage.setFitWidth(24.0);
                participantsImage.setFitHeight(28.0);

                participantsLabel.setGraphic(participantsImage);
                RadioButton enrollButton = new RadioButton("Enroll");
                enrollButton.setPrefSize(95.0, 46.0);
                Tournament t = tournaments.get(i);
                enrollButton.setOnAction(event -> {
                    try {
                        EnrollmentController.select(t);
                        enrollClicked(event);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block

                        e.printStackTrace();
                    }

                    
                });

                RadioButton generateButton = new RadioButton("Generate");
                    generateButton.setPrefSize(140.0, 46.0);
                    generateButton.setOnAction(event2 -> {
                        try {
                            generateClicked(event2);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    });

                Line line = new Line(-24.577177047729492, 34.9288330078125, 380.7157287597656, 34.92866516113281);

                card.getChildren().addAll(tournamentLabel, typeLabel, startDateLabel, untilLabel, endDateLabel,
                        participantsLabel, generateButton,enrollButton, line);

                ColorAdjust effect = new ColorAdjust();
                effect.setBrightness(-0.1);
                effect.setContrast(-1.0);
                effect.setHue(1.0);
                effect.setSaturation(1.0);

                card.setEffect(effect);

                anchorPane.getChildren().add(card);
                vboxFuture.getChildren().add(anchorPane);
            } else {
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

                Label tournamentLabel = new Label(tournaments.get(i).tString());
                tournamentLabel.setPrefSize(399.0, 38.0);
                tournamentLabel.setAlignment(Pos.CENTER);
                tournamentLabel.setFont(new Font(24.0));
                tournamentLabel.setId("finishedTournament");

                Label typeLabel = new Label(tournaments.get(i).getType());
                typeLabel.setPrefSize(401.0, 40.0);
                typeLabel.setAlignment(Pos.CENTER);
                typeLabel.setFont(new Font(24.0));
                typeLabel.setId("finishedType");

                Label startDateLabel = new Label(tournaments.get(i).getStartDate().toString());
                startDateLabel.setPrefSize(159.0, 38.0);
                startDateLabel.setAlignment(Pos.CENTER);
                startDateLabel.setId("finishedStartDate");

                Label untilLabel = new Label("Until");
                untilLabel.setPrefSize(90.0, 34.0);
                untilLabel.setAlignment(Pos.CENTER);

                Label endDateLabel = new Label(tournaments.get(i).getEndDate().toString());
                endDateLabel.setPrefSize(151.0, 49.0);
                endDateLabel.setAlignment(Pos.CENTER);
                endDateLabel.setId("finishedEndDate");

                Label participantsLabel = new Label(tournaments.get(i).numberString());
                participantsLabel.setPrefSize(309.0, 52.0);
                participantsLabel.setAlignment(Pos.CENTER_LEFT);
                participantsLabel.setId("finishedParticipantsNumber");

                ImageView participantsImage = new ImageView(new Image("Imgs/profile_icon.png"));
                participantsImage.setFitWidth(24.0);
                participantsImage.setFitHeight(28.0);

                participantsLabel.setGraphic(participantsImage);
                RadioButton watchButton = new RadioButton("Watch");
                watchButton.setPrefSize(95.0, 46.0);
                watchButton.setOnAction(event -> {
                    try {
                        watchClicked(event);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });

                Line line = new Line(-24.577177047729492, 34.9288330078125, 380.7157287597656, 34.92866516113281);

                card.getChildren().addAll(tournamentLabel, typeLabel, startDateLabel, untilLabel, endDateLabel,
                        participantsLabel, watchButton, line);

                ColorAdjust effect = new ColorAdjust();
                effect.setBrightness(-0.1);
                effect.setContrast(-1.0);
                effect.setHue(1.0);
                effect.setSaturation(1.0);

                card.setEffect(effect);

                anchorPane.getChildren().add(card);
                vboxCurrent.getChildren().add(anchorPane);
            }
        }

        finishedScrollPane.setContent(vboxFinish);
        upcomingScrollPane.setContent(vboxFuture);
        ongoingScrollPane.setContent(vboxCurrent);

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
    void enrollClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Enrollment.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void finishedTournamentsClicked(ActionEvent event) {

    }

    @FXML
    void onGoingTournamentsClicked(ActionEvent event) {

    }

    @FXML
    void upcomingTournamentsClicked(ActionEvent event) {

    }

    @FXML
    void watchClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("WatchTournament.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void generateClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("WatchTournament.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
