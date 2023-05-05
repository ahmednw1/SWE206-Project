import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ProfileController implements Initializable {



    @FXML
    private TableColumn<TabelProfile, String> team;

    @FXML
    private TableColumn<TabelProfile, String> tournament;

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label type;

    @FXML
    private TableView tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = App.database.getCurrentUser();
        name.setText(user.getName());
        if(user instanceof Admin){
            type.setText("Admin");
        }
        else{
            type.setText("Student");
            Student student = (Student) user;
            tournament.setCellValueFactory(
            new PropertyValueFactory<>("tournamnet"));
            team.setCellValueFactory(
            new PropertyValueFactory<>("team"));
        

            ObservableList<TabelProfile> list = FXCollections.observableArrayList();
            for(int i=0; i<student.getTeams().size();i++ ){
                list.add(new TabelProfile(student.getTeams().get(i).toString(), student.getTeams().get(i).getTournament().tString()));
            }
            tableView.setItems(list);
                                            
        }
        email.setText(user.getEmail());  

        

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

  
    }


