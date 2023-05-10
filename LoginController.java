import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import org.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    @FXML
    private Label invalidMessage;

    ArrayList<User> users = App.users;

    @FXML
    void LoginClicked(ActionEvent event) {
        String name = username.getText();
        String pass = password.getText();
        try {
            String info = authentiacate(name, pass);
            if (info != null) {
                User user = App.database.getUser(name);
                if (user == null) {
                    JSONObject jsonObject = new JSONObject(info);
                    if (jsonObject.getString("type").equals("admin")) {
                        Admin admin = new Admin(name, pass);
                        users.add(admin);
                        App.write();
                        App.database.setCurrentUser(admin);

                    } else {
                        Student student = new Student(jsonObject.getString("email"), name,
                                jsonObject.getString("name"), pass);
                        users.add(student);
                        App.write();
                        App.database.setCurrentUser(student);

                    }

                } else {
                    int pos = 0;
                    for (int i = 0; i < App.getUsers().size(); i++) {
                        if ((App.getUsers().get(i).getID()).equals(name)) {
                            pos = i;
                        }
                    }
                    App.database.setCurrentUser(App.getUsers().get(pos));
                    System.out.println(App.database.getCurrentUser());
                }

                Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                invalidMessage.setText("Invalid Username or Password");
            }

        } catch (Exception e) {
            invalidMessage.setText(e.getMessage());
        }

    }

    public static String authentiacate(String username, String pass) throws Exception {
        String endpointUrl = "https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn";

        try {
            URL url = new URL(endpointUrl + "?username=" + username + "&password=" + pass);
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