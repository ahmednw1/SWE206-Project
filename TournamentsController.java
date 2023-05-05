import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TournamentsController implements Initializable {

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
    private ScrollPane finishedScrollPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

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
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
