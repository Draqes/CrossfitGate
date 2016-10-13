import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebClient {

    RestClient restClient = new RestClient();

    public String wodifyRequest(String pass) {
        try {

            URL url = new URL("http://app.wodify.com/API/GetHasMembershipEntry.aspx?apikey=192m48m0rhbq3t0dltyee7bf2&type=json&encoding=utf-8&username=" + pass);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            String basicAuth = "Basic user:TXGsL2HF76ATxGGRTXdLfpkM";
            conn.setRequestProperty ("Authorization", basicAuth);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output = br.readLine();
            //System.out.println("Output from Server .... \n");
            //while ((output = br.readLine()) != null) {
            //    System.out.println(output);
            //}

            conn.disconnect();

            restClient.callRestService(pass);

            return output;
        }
        catch (MalformedURLException e) {

            e.printStackTrace();

        }
        catch (IOException e) {

            e.printStackTrace();

        }
        return "Error";
    }

    public String openGate()
    {
        try {

            URL url = new URL("http://localhost/api/values/5");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            String basicAuth = "Basic user:TXGsL2HF76ATxGGRTXdLfpkM";
            conn.setRequestProperty ("Authorization", basicAuth);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output = br.readLine();
            //System.out.println("Output from Server .... \n");
            //while ((output = br.readLine()) != null) {
            //    System.out.println(output);
            //}

            conn.disconnect();

            return output;
        }
        catch (MalformedURLException e) {

            e.printStackTrace();

        }
        catch (IOException e) {

            e.printStackTrace();

        }
        return "Error";
    }
}
