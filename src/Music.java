import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by BennettIronYard on 10/14/15.
 */
public class Music {
    public static void main(String[] args) throws IOException {
        Song song = loadMusic();

        if (song == null) {
            System.out.println("Welcome to Music Saver \nEnter Your Name");

            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();

            System.out.println(name + ", You will be given 5 choices,\nPlease Write or choose Response.");

            System.out.println("[1] Favorite Artist");
            String favoriteArtist = scanner.nextLine();

            System.out.println("[2] Song Title");
            String songTitle = scanner.nextLine();

            System.out.println("[3] Genre");
            String genre = scanner.nextLine();

            System.out.println("Release Date?\n[1] 80's\n[2] 90's\n[3] 00's\n[4] 2015+");
            String releaseDateNum = scanner.nextLine();
            String releaseDate;

            if (releaseDateNum.equals("1")) {
                releaseDate = "80's";
            } else if (releaseDateNum.equals("2")) {
                releaseDate = "90's";
            } else if (releaseDateNum.equals("3")) {
                releaseDate = "00's";
            } else if (releaseDateNum.equals("4")) {
                releaseDate = "2015+";
            } else {
                System.out.println("Invalid Number");
                return;
            }
            System.out.println("Amount of Grammys won.");
            System.out.println("[1] 0\n[2] 1-3\n[3] 4-6\n[4] 7+");
            String grammysNum = scanner.nextLine();
            String grammysWon;

            if (grammysNum.equals("1")) {
                grammysWon = "0 Grammy's Won";
            } else if (grammysNum.equals("2")) {
                grammysWon = "1 to 3 Grammy's Won";
            } else if (grammysNum.equals("3")) {
                grammysWon = "4 to 6 Grammy's Won";
            } else if (grammysNum.equals("4")) {
                grammysWon = "7 or More Grammy's Won";
            } else {
                System.out.println("Invalid Number");
                return;
            }
            System.out.println("Thank You for using Music Save. " + name + ".");

            song = new Song();
            song.artist = favoriteArtist;
            song.title = songTitle;
            song.genre = genre;
            song.grammys = grammysWon;
            song.date = releaseDate;

            saveMusic(song);
        }
        else {
            System.out.println("Loaded Music");
            System.out.println(song.artist);
            System.out.println(song.title);
            System.out.println(song.date);
            System.out.println(song.genre);
            System.out.println(song.grammys);
        }
    } //static void main

    static void saveMusic(Song song) {
        File f = new File("Save.json");
        JsonSerializer serializer = new JsonSerializer();
        String contentsToSave = serializer.serialize(song);

        try {
            FileWriter fw = new FileWriter(f);
            fw.write(contentsToSave);
            fw.close();
        } catch (Exception e) {

        }
    }

    static Song loadMusic() {
        try {
            File f = new File("Save.json");
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] contents = new char[fileSize];
            fr.read(contents);
            String fileContents = new String(contents);
            JsonParser parser = new JsonParser();
            return parser.parse(fileContents, Song.class);
        } catch (Exception e) {
            return null;
        }
    }
} //Music class
