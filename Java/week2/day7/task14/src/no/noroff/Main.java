package no.noroff;


import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {
    static String root_url;

    public static void main(String[] args) {
        root_url = "https://anapioficeandfire.com/api";
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(true) {
            input = "";

            System.out.println("\n~~~~~~~~~~~~~~~~~~ Type in a number to choose ~~~~~~~~~~~~~~~~~~");
            System.out.println("1. Find a character by id");
            System.out.println("2. Show POV characters in books published by \"Bantam Books\"");
            System.out.println("3. Terminate program.");
            System.out.print("> ");

            input = scanner.nextLine();
            System.out.println();

            if(input.equals("1")) {
                input = "";
                System.out.println("~~~~~~~~~~~~~~~~~~ Find a character by id ~~~~~~~~~~~~~~~~~~");
                System.out.println("Type in the number id of a character: ");
                System.out.print("> ");
                input = scanner.nextLine();

                try {
                    int id = Integer.parseInt(input);
                    boolean display;
                    input = "";

                    System.out.println("\nDo you want to display sworn members in this characters house? (y/n)");
                    System.out.print("> ");

                    input = scanner.nextLine();
                    System.out.println();

                    if(input.equalsIgnoreCase("y")) {
                        display = true;
                    } else if(input.equalsIgnoreCase("n")) {
                        display = false;
                    } else {
                        System.out.println("Not a yes or no answer.");
                        continue;
                    }

                    findCharacter(id, display);

                } catch (NumberFormatException e) {
                    System.out.println("Not a valid number id.");
                }
            } else if(input.equals("2")) {
                System.out.println("~~~~~~~~~~~~~~~~~~ Show POV characters ~~~~~~~~~~~~~~~~~~");
                findPovCharacters();
            } else if(input.equalsIgnoreCase("3")) {
                System.out.println("Goodbye, I hope to see you again soon.");
                System.exit(0);
            } else {
                System.out.println("Not a valid command.");
            }
        }
    }

    /**
     * Allows the user to lookup a character by an id. (Display basic info for that character.)
     * @param id
     */
    public static void findCharacter(int id, boolean displayHouse) {
        System.out.println("\nSearching for character with id: " + id + "...");
        String character_url = root_url + "/characters/" + id;
        JSONObject character_data = new JSONObject(requestURL(character_url));

        String name = character_data.get("name").toString();
        String gender = character_data.get("gender").toString();
        String culture = character_data.get("culture").toString();
        String born = character_data.get("born").toString();
        String died = character_data.get("died").toString();
        String mother = character_data.get("mother").toString();
        String father = character_data.get("father").toString();
        String spouse = character_data.get("spouse").toString();
        JSONArray titles = new JSONArray(character_data.get("titles").toString());
        JSONArray aliases = new JSONArray(character_data.get("aliases").toString());
        JSONArray allegiances = new JSONArray(character_data.get("allegiances").toString());

        System.out.println("\n~~~~~~~~~~~~ Result ~~~~~~~~~~~~");
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Culture: " + culture);
        System.out.println("Born: " + born);
        System.out.println("Died: " + died);
        System.out.println("Mother: " + mother);
        System.out.println("Father: " + father);
        System.out.println("Spouse: " + spouse);
        System.out.println("Titles:");

        for(Object title : titles) {
            System.out.println("\t" + title);
        }

        System.out.println("Aliases:");

        for(Object alias : aliases) {
            System.out.println("\t" + alias);
        }

        if(displayHouse) {
            findCharactersHouse(allegiances);
        }
    }

    /**
     * Gets a characters name from url
     * @param member_url
     * @return
     */
    public static String getCharacterName(String member_url) {
        JSONObject member_data = new JSONObject(requestURL(member_url));
        String member_name = member_data.get("name").toString();

        return member_name;
    }

    /**
     * Optionally allows the user to display the names of all sworn members of the previous characters house.
     * @param allegiances
     */
    public static void findCharactersHouse(JSONArray allegiances) {
        String house_name;
        String house_region;
        JSONArray house_members;
        String house_url;
        JSONObject house_data;

        System.out.println("Allegiances:");

        for(Object house : allegiances) {
            house_url = house.toString();
            house_data = new JSONObject(requestURL(house_url));
            house_name = house_data.get("name").toString();
            house_region = house_data.get("region").toString();
            house_members = new JSONArray(house_data.get("swornMembers").toString());

            System.out.println("\tHouse name: " + house_name);
            System.out.println("\tRegion: " + house_region);
            System.out.println("\tSworn members:");

            for(Object member : house_members) {
                System.out.println("\t\t" + getCharacterName(member.toString()));
            }
        }
    }

    /**
     * Looks up all povCharacters in the books published by "Bantam Books", and then display this information in a grid.
     * (Which characters are point-of-view in which books?)
     */
    public static void findPovCharacters() {
        String books_url = root_url + "/books";
        JSONArray books_data = new JSONArray(requestURL(books_url));
        JSONArray pov_characters;
        String book_name;
        String[][] data = new String[books_data.length()][];

        System.out.println("\nSearching for all POV characters in books published by \"Bantam Books\"...\n");

        for (int i = 0; i < books_data.length(); i++) {
            JSONObject book_data = new JSONObject(books_data.get(i).toString());

            if(book_data.get("publisher").equals("Bantam Books")) {
                pov_characters = new JSONArray(book_data.get("povCharacters").toString());
                book_name = book_data.get("name").toString();

                data[i] = new String[pov_characters.length()+1];
                data[i][0] = book_name;
                //System.out.println(book_name);

                for (int j = 0; j < pov_characters.length(); j++) {
                    data[i][j+1] = getCharacterName(pov_characters.getString(j));
                    //System.out.println("\t"+getCharacterName(pov_characters.getString(j)));
                }
            }
        }

        final String BOOK_COL = "#               Book                ";
        final String CHAR_COL = "#                POV Character                #";
        String edge = repeat("", "#", BOOK_COL.length()+CHAR_COL.length());
        System.out.println(edge);
        System.out.println(BOOK_COL+CHAR_COL);
        System.out.println(edge);

        String book_col;
        String char_col;

        for (int i = 0; i < data.length; i++) {
            if(data[i] != null) {
                book_col = "#   " + data[i][0];
                book_col = repeat(book_col, " ", BOOK_COL.length() - book_col.length());
                book_col += "#";
                book_col = repeat(book_col, " ", CHAR_COL.length()-2);
                book_col += "#";

                System.out.println(book_col);

                for (int j = 1; j < data[i].length; j++) {
                    char_col = repeat("#", " ", BOOK_COL.length()-1);
                    char_col += "#   " + data[i][j];
                    char_col = repeat(char_col, " ", BOOK_COL.length() + CHAR_COL.length() - char_col.length() - 1);
                    char_col += "#";

                    System.out.println(char_col);
                }

                System.out.println(edge);
            }
        }


    }

    /**
     * Requesting JSON data from an url
     * @param url
     * @return data in string
     */
    public static String requestURL(String url) {
        try {
            // Set URL
            URLConnection connection = new URL(url).openConnection();
            // Set property - avoid 403 (CORS)
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            // Creat connection
            connection.connect();
            // Create a buffer of the input
            BufferedReader buffer  = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // Convert the response into a single string
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = buffer.readLine()) != null) {
                stringBuilder.append(line);
            }
            // return the response
            return stringBuilder.toString();
        } catch (MalformedURLException e) {
            return "Error occured when getting data from API.";
        } catch (IOException e) {
            return "Error occured when reading data from API.";
        }
    }

    /**
     * Repeat a string n times
     * @param string
     * @param repeatedString
     * @param n
     * @return
     */
    public static String repeat(String string, String repeatedString, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(string);

        for (int i = 0; i < n; i++) {
            stringBuilder.append(repeatedString);
        }

        return stringBuilder.toString();
    }
}
