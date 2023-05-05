import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.json.JSONObject;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void LoginClicked(ActionEvent event) {
        
    }

    public static String authentiacate(String username, String pass) throws Exception {
        String endpointUrl = "https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn";

        try {
            URL url = new URL(endpointUrl + "?username=" + username + "&password=" + pass);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Add authorization header with Base64 encoded username and password
            String auth = username + ":" + pass;
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);
            connection.setRequestProperty("Authorization", authHeaderValue);

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