package no.experis;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Application {
    static JSONArray operations;
    static List<String> names = Arrays.asList("Ralf", "Philippe", "Bartek", "Kenneth", "Sanay", "Jan", "Quoc", "Michael", "Thinh", "Billy", "Pakee", "Craig" );


    public static String requestURL(String u) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(u).openConnection();
        // Set property - avoid 403 (CORS)
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        // Creat connection
        connection.connect();
        // Create a buffer of the input
        BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        // Convert the response into a single string
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = buffer.readLine()) != null) {
            stringBuilder.append(line);
        }
        // return the response
        return stringBuilder.toString();

    }

    public static String findPrefix(String s){
        for(int i = 0; i < s.length(); i++){
            if(i != 0){
                if(s.charAt(i) == '/'){
                    return s.substring(0,i+1);
                }
            }
        }

        return "";
    }

    public static void main(String[] args) {
        String url = "https://www.foaas.com/operations";
        Random r = new Random();

        Scanner scanner = new Scanner(System.in);

        try {
            operations = new JSONArray(requestURL(url));

            System.out.println("Categories to choose from:");
            for (int i = 0; i < operations.length(); i++) {
                System.out.println("\t" + i + "." +operations.getJSONObject(i).get("name"));
            }

            System.out.println("\nWhich category do you want?");
            System.out.print("> ");
            String input = scanner.next();
            int categoryID = Integer.parseInt(input);

            input = "";

            JSONArray field = operations.getJSONObject(categoryID).getJSONArray("fields");

            String url2 = operations.getJSONObject(categoryID).get("url").toString();
            String prefix = "https://www.foaas.com" + findPrefix(url2);

            String name;

            for (int i = 0; i < field.length(); i++) {
                name = "";
                System.out.println("Type in name " + (i+1) + ": ");
                System.out.print("> ");
                name = scanner.next();
                prefix += name + "/";
            }

            JSONObject message = new JSONObject(requestURL(prefix));
            System.out.println();
            System.out.println("\"" + message.get("message") + "\"");
            System.out.println(message.get("subtitle"));
            System.out.println("\nSource: " + prefix);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
