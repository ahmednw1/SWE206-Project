import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthenticationExample {
    public static void main(String[] args) {
        String username = "your_username";
        String password = "your_password";
        String endpointUrl = "https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn";
        
        try {
            URL url = new URL(endpointUrl + "?username=" + username + "&password=" + password);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Add authorization header with Base64 encoded username and password
            String auth = username + ":" + password;
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

            // Output the response from the API endpoint
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
