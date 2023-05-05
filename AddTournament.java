import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.util.ResourceBundle;

import javax.swing.plaf.synth.SynthSpinnerUI;

import java.net.URL;
import java.time.LocalDate;

public class AddTournament implements Initializable{

    @FXML
    private ComboBox<Sport> sport;
    

    @FXML
    private TextField daysBetweenMatches;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField name;

    @FXML
    private DatePicker startDate;

    @FXML
    private TextField teamMembers;

    @FXML
    private TextField teamsNumber;

    @FXML
    private ComboBox<String> type;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Sport football = new Sport("Football");
        Sport swimming = new Sport("Swimming");
        Sport vollyball = new Sport("Vollyball");
         ObservableList<Sport> sportList = FXCollections.observableArrayList(football,vollyball,swimming);
         ObservableList<String> typeList = FXCollections.observableArrayList("Elimination","Round Robin");
         sport.setItems(sportList);
         type.setItems(typeList);
    }
    

    @FXML
    void backClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Tournaments.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    void addClicked(ActionEvent event) throws IOException {
        String nameValue = name.getText();
        Sport sportValue = sport.getValue();
        String typeValue = type.getValue();
        int teamsNumberValue = Integer.parseInt(teamsNumber.getText());
        int teamMembersValue = Integer.parseInt(teamMembers.getText());
        LocalDate startDateValue = startDate.getValue();
        LocalDate endDateValue = endDate.getValue();
        int eachStageDays = Integer.parseInt(daysBetweenMatches.getText());
        Tournament newTournament = new Tournament(eachStageDays, startDateValue, endDateValue,sportValue, teamMembersValue, typeValue, teamsNumberValue);
        //App.database.write(newTournament);  
    
        

        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
