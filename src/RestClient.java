import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SG0222006 on 26.4.2016.
 */
public class RestClient {

    public void callRestService(String userSSN){

        callRestServicePost(userSSN);

    }

    private void callRestServicePost(String userSSN) {



            URL url;
            HttpURLConnection connection = null;
            try {

                Date d = new Date();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");




                String urlParameters ="loginTime=" + URLEncoder.encode(dateFormat.format(d), "UTF-8") + "&" +
                                "userssn=" + URLEncoder.encode(userSSN, "UTF-8");

                //Create connection
                url = new URL("http://nokkvi.net/crossfit/login");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded");


                connection.setRequestProperty("X-API-KEY","612e648bf9594adb50844cad6895f2cf");
                connection.setRequestProperty("Content-Length", "" +
                        Integer.toString(urlParameters.getBytes().length));
                connection.setRequestProperty("Content-Language", "en-US");

                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                //Send request
                DataOutputStream wr = new DataOutputStream(
                        connection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                //Get Response
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                //return response.toString();

            }
            catch (Exception e) {

                e.printStackTrace();
                //return null;

            }
            finally {

                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
    }


