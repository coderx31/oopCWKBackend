package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.FootballClub;
import models.Match;
import play.libs.Json;

import java.io.*;
import java.util.List;

public class ApplicationUtil {

    public static ObjectNode createResponse(Object response, boolean ok) {
        ObjectNode result = Json.newObject();
        result.put("status", ok);
        if (response instanceof String)
            result.put("response", (String) response);
        else result.set("response", (JsonNode) response);

        return result;
    }

    public static List<FootballClub> loadClub(List<FootballClub> footballClubs){


        /*football club objects to arrayList*/

        // setting the path for read objects to file
        try {
            FileInputStream footballClubFile = new FileInputStream("football_clubs.txt");
            ObjectInputStream football_clubs = new ObjectInputStream(footballClubFile);

            // using infinite for loop read all objects to football clubs list
            for (;;){
                try {
                    footballClubs.add((FootballClub)football_clubs.readObject());
                }catch (Exception e){
                    break;
                }
            }
        }catch (Exception e){
            System.err.println("Error Occurred!");
        }
        System.out.println("Club data loaded");
    return footballClubs;
    }

    public static List<Match> loadMatch(List<Match> matches){
        try{
            FileInputStream matchesFile = new FileInputStream("matches.txt");
            ObjectInputStream matchObjects = new ObjectInputStream(matchesFile);

            for (;;){
                try{
                    matches.add((Match)matchObjects.readObject());
                }catch (Exception e){
                    break;
                }
            }
        }catch (Exception e){
            System.err.println("Error Occurred!");
        }

        System.out.println("Match data loaded");
        return matches;
    }

    public static void saveData(List<FootballClub> footballClubs, List<Match> allMatches){
        try {
            FileOutputStream footballClubsFile = new FileOutputStream("football_clubs.txt");
            ObjectOutputStream footballClubObjects = new ObjectOutputStream(footballClubsFile);

            //using for loop write all football objects to the file
            for (FootballClub footballClub: footballClubs){
                footballClubObjects.writeObject(footballClub); // writing progress
            }
            System.out.println("Clubs data has been added to file");
        }catch (Exception e){
            System.err.println("Error Occurred!");
        }

        /*all Matches to the file*/
        try {
            FileOutputStream matchesFile = new FileOutputStream("matches.txt");
            ObjectOutputStream matchObjects = new ObjectOutputStream(matchesFile);

            for (Match match : allMatches){
                matchObjects.writeObject(match);
            }
            System.out.println("Matches data has been added to File");

        }catch (Exception e){
            System.err.println("Error Occurred!");
        }
    }
}
