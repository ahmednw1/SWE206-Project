import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TournamentsController {

    @FXML
    private FlowPane card;

    @FXML
    private FlowPane card1;

    @FXML
    private FlowPane card2;

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
    private Label onGoingStartDate;

    @FXML
    private Label onGoingTournament;

    @FXML
    private Label onGoingType;

    @FXML
    private Label upComingEndDate;

    @FXML
    private Label upComingParticipantsNumber;

    @FXML
    private Label upComingStartDate;

    @FXML
    private Label upComingTournament;

    @FXML
    private Label upComingType;

    @FXML
    private TabPane tabPane;

    @FXML
    void HomeClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    void MatchesClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Matches.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ProfileClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void enrollClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Enrollment.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void finishedTournamentsClicked(ActionEvent event) {
        tabPane.getSelectionModel().select(0);
    }

    @FXML
    void onGoingTournamentsClicked(ActionEvent event) {
        tabPane.getSelectionModel().clearAndSelect(1);

    }

    @FXML
    void upcomingTournamentsClicked(ActionEvent event) {
        tabPane.getSelectionModel().select(2);;
    }

    @FXML
    void watchClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("WatchTournament.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
