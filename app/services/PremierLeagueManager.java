package services;

import models.FootballClub;
import models.Match;
import models.SchoolFootballClub;
import models.UniversityFootballClub;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PremierLeagueManager implements LeagueManager {
    private static PremierLeagueManager instance;
    private List<FootballClub> footballClubs = new ArrayList<>();
    private List<Match> allMatches = new ArrayList<>();




    public static PremierLeagueManager getInstance(){
        if (instance == null ){
            instance = new PremierLeagueManager();
        }
        return instance;
    }



    public  List<FootballClub> getFootballClubs() {
        return this.footballClubs;
    }

    public List<Match> getAllMatches() {
        return this.allMatches;
    }

    @Override
    public void addFootballClub(FootballClub footballClub) {

        // checking progress of if the team name is already taken
        for(FootballClub footballClub1 : getFootballClubs()){
            if (footballClub1.getClubName().toLowerCase().equals(footballClub.getClubName().toLowerCase())){
                System.out.println("Club Name is Already Taken !");
                return;
            }
        }
        // if name is not same then
        getFootballClubs().add(footballClub);
        System.out.println("Successfully added the club ");
        saveToFile();



    }

    @Override
    public void deleteFootballClub(String clubName) {
        /**
         * #resource link
         * https://www.geeksforgeeks.org/arraylist-removeif-method-in-java/
         */

        // checking if the football clubs list empty or not if empty then return from the method
        if (getFootballClubs().isEmpty()){
            System.out.println("No Clubs yet !");
            return;
        }
        // here used removeIf and it checks whether is it there, then delete and give msg or else give no msg
        if (!footballClubs.removeIf(footballClub -> footballClub.getClubName().toLowerCase().equals(clubName.toLowerCase()))){
            System.out.println("No such Club as "+clubName);
        }else{
            System.out.println("Successfully Deleted");
            saveToFile();
        }

    }

    @Override
    public void displayStatistics(String clubName) {

        // checks if the football clubs list empty or not if empty then return from the method
        if (getFootballClubs().isEmpty()){
            System.out.println("No Clubs yet !");
            return;
        }
        boolean checker = false; // this for the availability of club
        // loop through all the clubs checks for available
        for (FootballClub footballClub : getFootballClubs()){
            if (footballClub.getClubName().toLowerCase().equals(clubName.toLowerCase())){
                System.out.println(footballClub.toString());
                checker = true; // set true because it available
            }
        }
        // if the given name not found this  msg will display in console
        if (!checker){
            System.out.println("No such club as "+clubName);
        }
    }

    @Override
    public void displayLeagueTable(String choice) {
        /**
         * # comparator and comparable interface
         * #resource link
         * https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/
         */
        // checks if the football clubs list empty or not if empty then return from the method
        if (getFootballClubs().isEmpty()){
            System.out.println("No Clubs yet !");
            return;
        }

        Collections.sort(getFootballClubs(), Collections.reverseOrder()); // sort the list
        // loop all football clubs from football clubs list according to the choice
        System.out.printf("%25s%4s%4s%4s%4s%6s%6s%n","Club Name","PL","W","D","L","G","P");
        switch (choice){
            case "S":
            case "s":
                for (FootballClub footballClub : getFootballClubs()){
                    if (footballClub instanceof SchoolFootballClub){
                        System.out.printf("%25s%4d%4d%4d%4d%6d%6d%n",footballClub.getClubName(),
                                footballClub.getNumOfMatchPlayed(),footballClub.getNumOfWins(),footballClub.getNumOfDraws(),
                                footballClub.getNumOfDefeats(),footballClub.getGoalScored(),footballClub.getPoints());
                    }
                }
                break;
            case "U":
            case "u":
                for (FootballClub footballClub : getFootballClubs()){
                    if (footballClub instanceof UniversityFootballClub){
                        System.out.printf("%25s%4d%4d%4d%4d%6d%6d%n",footballClub.getClubName(),
                                footballClub.getNumOfMatchPlayed(),footballClub.getNumOfWins(),footballClub.getNumOfDraws(),
                                footballClub.getNumOfDefeats(),footballClub.getGoalScored(),footballClub.getPoints());
                    }
                }
                break;
        }

    }

    @Override
    public void addPlayedMatch(Match match) {

        boolean opponentWin = false; // this is check if opponent team win or not
        boolean opponentDefeat = false; // this is check if opponent team defeat or not
        boolean homeChecker = false; // this is check if home team exist in league
        boolean opponentChecker = false; // this is check if opponent team exist in league
        String homeClubType = null; // this is set the home team club type whether school or uni
        String opponentClubType = null; // this is set the opponent team club type
        boolean clubChecker = false;  // this check whether both teams are same type or not

        // loop through football clubs list for check both teams are exist set the club types
        for (FootballClub footballClub : footballClubs){
            if (footballClub.getClubName().toLowerCase().equals(match.getHomeTeam().toLowerCase())){
                if (footballClub instanceof SchoolFootballClub){
                    homeClubType = "scl"; // updating club types
                }else if (footballClub instanceof UniversityFootballClub){
                    homeClubType = "uni";  // updating club types
                }
                homeChecker = true; // if home club exist then set to true

                // checks about the opponent team exist and its type
                for (FootballClub footballClub1 : footballClubs){
                    if (footballClub1.getClubName().toLowerCase().equals(match.getOpponentTeam().toLowerCase())){
                        if (footballClub1 instanceof SchoolFootballClub){
                            opponentClubType = "scl";
                        }else if (footballClub1 instanceof UniversityFootballClub){
                            opponentClubType = "uni";
                        }

                        opponentChecker = true;
                    }
                }

                if (homeClubType.equals(opponentClubType)){
                    clubChecker = true; // checks if both clubs are in same type and if true set it to true
                }

                // if home or opponent or club types are not match then return from the method
                if (!opponentChecker || !homeChecker || !clubChecker){
                    System.out.println("Something wrong check both clubs are registered in league");
                    return;
                }
                // checks the win team and update their statistics
                footballClub.setGoalScored(match.getHomeScore());
                footballClub.setNumOfMatchPlayed();
                if (match.getHomeScore() > match.getOpponentScore()){
                    footballClub.setNumOfWins();
                    footballClub.setPoints(3);
                    opponentDefeat = true;
                }else if (match.getHomeScore() < match.getOpponentScore()){
                    footballClub.setNumOfDefeats();
                    opponentWin = true;
                }else{
                    footballClub.setNumOfDraws();
                    footballClub.setPoints(1);
                }

            }

        }


        // again same thing for opponent team
        for (FootballClub footballClub : footballClubs){
            if (footballClub.getClubName().toLowerCase().equals(match.getOpponentTeam().toLowerCase())){
                opponentChecker = true;
                for(FootballClub footballClub1 : footballClubs){
                    if (footballClub1.getClubName().toLowerCase().equals(match.getHomeTeam().toLowerCase())){
                        homeChecker = true;
                    }
                }
                if (!opponentChecker || !homeChecker || !clubChecker){
                    System.out.println("Something wrong check both clubs are registered in league");
                    return;
                }

                footballClub.setGoalScored(match.getOpponentScore());
                footballClub.setNumOfMatchPlayed();

                if (opponentWin){
                    footballClub.setNumOfWins();
                    footballClub.setPoints(3);
                }else if (opponentDefeat){
                    footballClub.setNumOfDefeats();
                }else {
                    footballClub.setNumOfDraws();
                    footballClub.setPoints(1);
                }
            }
        }

        // finally added to the all matches list
        if (homeChecker && opponentChecker){
            allMatches.add(match);
            saveToFile();
            System.out.println("successfully added match");
        }

    }

    @Override
    public void saveToFile() {

        // all football clubs to the file

        //create the file or get existing file for write
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

    @Override
    public void loadFromFile() {
        /**
         * #infinite for loop
         * # InputStream#readObject
         * #resourse link
         * https://www.baeldung.com/infinite-loops-java
         * https://docs.oracle.com/javase/7/docs/api/java/io/ObjectInputStream.html#readObject()
         */
        // checks if the file is empty, if true then return
        File club_data = new File("football_clubs.txt");
        if (club_data.length() == 0){
            System.out.println("File is Empty");
            return;
        }

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

        File match_data = new File("matches.txt");
        if (match_data.length() == 0){
            System.out.println("File is Empty");
            return;
        }
        /*match objects to arrayList*/

        try{
            FileInputStream matchesFile = new FileInputStream("matches.txt");
            ObjectInputStream matches = new ObjectInputStream(matchesFile);

            for (;;){
                try{
                    allMatches.add((Match)matches.readObject());
                }catch (Exception e){
                    break;
                }
            }
        }catch (Exception e){
            System.err.println("Error Occurred!");
        }

        System.out.println("Match data loaded");

    }

    // open gui from console
    @Override
    public void gui(){

        try{
            ProcessBuilder builder = new ProcessBuilder(); // creating the process builder
            builder.command("cmd.exe","/c", "sbt run"); // give the command to run play
            Process process = builder.start(); // start the process
            System.out.println("Please wait....");   // give msg to console
            System.out.println("GUI will automatically open");


        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
