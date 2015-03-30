import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Bence on 2015.03.30..
 */
public class Main {
    public static void main(String[] args){

        try {
            httpGet("https://onlab.firebaseio.com/.json?print=pretty&auth=iPrzNiHduxciQW6yz6wBBNr1GGrzTgYWAsxjbp8a");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //REST API GET and write data to JSON
    public static void httpGet(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }

        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null){
            sb.append(line);
        }

        System.out.println(sb.toString().length());
        FileWriter file = new FileWriter("output.json");

        try {
            JSONObject jsonObject = new JSONObject(sb.toString());
            file.write(jsonObject.toString());
            System.out.println("Successfully Copied JSON Object to File...");
        } catch (Exception e ) {
            e.printStackTrace();

        } finally {
            file.flush();
            file.close();
        }
        rd.close();
        conn.disconnect();
    }

}
