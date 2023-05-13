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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TournamentsController implements Initializable {
    @FXML
    private Label invalidMessage;

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
        vboxFinish.setSpacing(5);
        vboxFinish.setPadding(new Insets(0, 0, 0, 5));
        VBox vboxCurrent = new VBox();
        vboxCurrent.setSpacing(5);
        vboxCurrent.setPadding(new Insets(0, 0, 0, 5));
        VBox vboxFuture = new VBox();
        vboxFuture.setSpacing(5);
        vboxFuture.setPadding(new Insets(0, 0, 0, 5));

        ArrayList<Tournament> tournaments = App.getTournaments();
        LocalDate todya = LocalDate.now();
        for (int i = 0; i < tournaments.size(); i++) {
            if (tournaments.get(i).getEndDate().isBefore(todya)) {
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setStyle("-fx-border-color: #007574; -fx-border-radius:10;");
                anchorPane.setPrefSize(350.0, 100.0);
                AnchorPane.setTopAnchor(anchorPane, 0.0);
                AnchorPane.setLeftAnchor(anchorPane, 0.0);
                AnchorPane.setBottomAnchor(anchorPane, 0.0);
                AnchorPane.setRightAnchor(anchorPane, 0.0);
                
                FlowPane card = new FlowPane();
                card.setPrefSize(318.0, 152.0);
                card.setLayoutX(10.0);
                card.setLayoutY(20.0);
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

                // Label participantsLabel = new Label(tournaments.get(i).numberString());
                // participantsLabel.setPrefSize(309.0, 52.0);
                // participantsLabel.setAlignment(Pos.CENTER_LEFT);
                // participantsLabel.setId("finishedParticipantsNumber");

                ImageView participantsImage = new ImageView(new Image("Imgs/profile_icon.png"));
                participantsImage.setFitWidth(24.0);
                participantsImage.setFitHeight(28.0);

                HBox cardHBox = new HBox();
                cardHBox.prefHeight(50.0);
                cardHBox.prefWidth(422);

                RadioButton teamsButton = new RadioButton("Teams");
                teamsButton.setPrefSize(133.0, 46.0);
                int t = i;
                teamsButton.setOnAction(event -> {
                    try {
                        TeamsController.select(t);
                        teamsClicked(event);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                });
                teamsButton.setPadding(new Insets(5));


                // participantsLabel.setGraphic(participantsImage);
                RadioButton watchButton = new RadioButton("Watch");
                watchButton.setPrefSize(95.0, 46.0);
                watchButton.setOnAction(event -> {
                    try {
                        watchClicked(event, tournaments.get(t));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                });
                watchButton.setPadding(new Insets(5));

                cardHBox.getChildren().add(teamsButton);
                cardHBox.getChildren().add(watchButton);
                cardHBox.setMargin(watchButton,new Insets(0, 0, 0, 196));

                //Line line = new Line(-24.577177047729492, 34.9288330078125, 380.7157287597656, 34.92866516113281);

                card.getChildren().addAll(tournamentLabel, typeLabel, startDateLabel, untilLabel, endDateLabel,
                cardHBox);//, line);

                ColorAdjust effect = new ColorAdjust();
                effect.setBrightness(-0.1);
                effect.setContrast(-1.0);
                effect.setHue(1.0);
                effect.setSaturation(1.0);

                card.setEffect(effect);

                anchorPane.getChildren().add(card);
                vboxFinish.getChildren().add(anchorPane);
            } else if (tournaments.get(i).getStartDate().isAfter(todya) && tournaments.get(i).getRegistrationStatus() && tournaments.get(i).getMatches().size() ==0) {
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setStyle("-fx-border-color: #007574; -fx-border-radius:10;");
                //anchorPane.setStyle("-fx-border-radius:10");
                anchorPane.setPrefSize(350.0, 100.0);
                anchorPane.setTopAnchor(anchorPane, 0.0);
                anchorPane.setLeftAnchor(anchorPane, 0.0);
                anchorPane.setBottomAnchor(anchorPane, 0.0);
                anchorPane.setRightAnchor(anchorPane, 0.0);

                FlowPane card = new FlowPane();
                card.setPrefSize(318.0, 152.0);
                card.setLayoutX(10.0);
                card.setLayoutY(20.0);
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

                // Label participantsLabel = new Label(tournaments.get(i).numberString());
                // participantsLabel.setPrefSize(170.0, 52.0);
                // participantsLabel.setAlignment(Pos.CENTER_LEFT);
                // participantsLabel.setId("finishedParticipantsNumber");

                ImageView participantsImage = new ImageView(new Image("Imgs/profile_icon.png"));
                participantsImage.setFitWidth(24.0);
                participantsImage.setFitHeight(28.0);

                HBox cardHBox = new HBox();
                cardHBox.prefHeight(50.0);
                cardHBox.prefWidth(422);

                RadioButton teamsButton = new RadioButton("Teams");
                teamsButton.setPrefSize(133.0, 46.0);
                int t = i;
                teamsButton.setOnAction(event -> {
                    try {
                        TeamsController.select(t);
                        teamsClicked(event);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                });
                teamsButton.setPadding(new Insets(5));

                // participantsLabel.setGraphic(participantsImage);
                RadioButton enrollButton = new RadioButton("Enroll");
                enrollButton.setPrefSize(95.0, 46.0);
               
                enrollButton.setOnAction(event -> {
                    try {
                        if (App.users.get(App.getCurrentUser()) instanceof Student) {
                            EnrollmentController.select(t);
                            enrollClicked(event);
                        } else {
                            invalidMessage.setText("          Sorry, You Must Be a Student to Enroll !");

                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block

                        e.printStackTrace();
                    }

                });
                enrollButton.setPadding(new Insets(5));


                RadioButton generateButton = new RadioButton("Generate");
                generateButton.setPrefSize(140.0, 46.0);
                generateButton.setOnAction(event -> {
                    try {
                        if (!(App.users.get(App.getCurrentUser()) instanceof Admin)) {
                            invalidMessage.setText("Sorry, You Must Be an Admin to Generate Matches !");
                        } else if (tournaments.get(t).getParticipants().size() == 0) {
                            invalidMessage.setText("               Sorry, There Are No Participants !");
                        } else {
                            
                            generateClicked(event,tournaments.get(t));
                        }

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                });
                generateButton.setPadding(new Insets(5));

                cardHBox.getChildren().add(teamsButton);
                cardHBox.getChildren().add(generateButton);
                cardHBox.getChildren().add(enrollButton);
                cardHBox.setMargin(enrollButton,new Insets(0, 0, 0, 16));
                cardHBox.setMargin(generateButton,new Insets(0, 0, 0, 40));

                //Line line = new Line(-24.577177047729492, 34.9288330078125, 380.7157287597656, 34.92866516113281);

                card.getChildren().addAll(tournamentLabel, typeLabel, startDateLabel, untilLabel, endDateLabel,
                        cardHBox);//, line);

                ColorAdjust effect = new ColorAdjust();
                effect.setBrightness(-0.1);
                effect.setContrast(-1.0);
                effect.setHue(1.0);
                effect.setSaturation(1.0);

                card.setEffect(effect);

                anchorPane.getChildren().add(card);
                vboxFuture.getChildren().add(anchorPane);
                //vboxFuture.setMargin(anchorPane,new Insets(2, 2, 2, 2));

            } else {
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setStyle("-fx-border-color: #007574; -fx-border-radius:10;");
                anchorPane.setPrefSize(350.0, 100.0);
                anchorPane.setTopAnchor(anchorPane, 0.0);
                anchorPane.setLeftAnchor(anchorPane, 0.0);
                anchorPane.setBottomAnchor(anchorPane, 0.0);
                anchorPane.setRightAnchor(anchorPane, 0.0);

                FlowPane card = new FlowPane();
                card.setPrefSize(318.0, 152.0);
                card.setLayoutX(10.0);
                card.setLayoutY(20.0);
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

                // Label participantsLabel = new Label(tournaments.get(i).numberString());
                // participantsLabel.setPrefSize(309.0, 52.0);
                // participantsLabel.setAlignment(Pos.CENTER_LEFT);
                // participantsLabel.setId("finishedParticipantsNumber");

                ImageView participantsImage = new ImageView(new Image("Imgs/profile_icon.png"));
                participantsImage.setFitWidth(24.0);
                participantsImage.setFitHeight(28.0);

                // participantsLabel.setGraphic(participantsImage);

                HBox cardHBox = new HBox();
                cardHBox.prefHeight(50.0);
                cardHBox.prefWidth(422);

                RadioButton teamsButton = new RadioButton("Teams");
                teamsButton.setPrefSize(133.0, 46.0);
                int t= i;
                teamsButton.setOnAction(event -> {
                    try {
                        TeamsController.select(t);
                        teamsClicked(event);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                });
                teamsButton.setPadding(new Insets(5));


                RadioButton watchButton = new RadioButton("Watch");
                watchButton.setPrefSize(95.0, 46.0);
                watchButton.setOnAction(event -> {
                    try {
                        
                        watchClicked(event, tournaments.get(t));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                });
                watchButton.setPadding(new Insets(5));

                cardHBox.getChildren().add(teamsButton);
                cardHBox.getChildren().add(watchButton);
                cardHBox.setMargin(watchButton,new Insets(0, 0, 0, 196));

                //Line line = new Line(-24.577177047729492, 34.9288330078125, 380.7157287597656, 34.92866516113281);

                card.getChildren().addAll(tournamentLabel, typeLabel, startDateLabel, untilLabel, endDateLabel,
                        cardHBox);//, line);

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

    void teamsClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Teams.fxml"));
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
    void watchClicked(ActionEvent event, Tournament t) throws IOException {
        if(t.getType().equals("Elimination")){
        eleminationController.select(t);
        Parent root = FXMLLoader.load(getClass().getResource("Elemination.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }else{
        watchTournamentController.select(t);
        Parent root = FXMLLoader.load(getClass().getResource("WatchTournament.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    }

    @FXML
    void generateClicked(ActionEvent event, Tournament t) throws IOException {
        System.out.println(t.getParticipants());
        if(t.getType().equals("Elimination")){
            eleminationController.select(t);
            Parent root = FXMLLoader.load(getClass().getResource("Elemination.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            }else{
            watchTournamentController.select(t);
            Parent root = FXMLLoader.load(getClass().getResource("WatchTournament.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            }
    }

}
