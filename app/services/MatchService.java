package services;

import models.FootballClub;
import models.Match;
import models.SchoolFootballClub;
import models.UniversityFootballClub;
import utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

public class MatchService {
    private static MatchService instance;
    private List<Match> matches = new ArrayList<>();
    private List<FootballClub> footballClubs = new ArrayList<>();


    public static MatchService getInstance(){
        if (instance == null){
            instance = new MatchService();
        }
        return instance;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void setFootballClubs(List<FootballClub> footballClubs) {
        this.footballClubs = footballClubs;
    }

    public List<Match> getMatches(){
        return this.matches;
    }

    public void addMatch(Match match){
        boolean opponentWin = false; // this is check if opponent team win or not
        boolean opponentDefeat = false; // this is check if opponent team defeat or not
        boolean homeChecker = false; // this is check if home team exist in league
        boolean opponentChecker = false; // this is check if opponent team exist in league
        String homeClubType = null; // this is set the home team club type whether school or uni
        String opponentClubType = null; // this is set the opponent team club type
        boolean clubChecker = false;  // this check whether both teams are same type or not

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
            this.matches.add(match);
            ApplicationUtil.saveData(footballClubs,matches);
            System.out.println("successfully added match");
        }

    }
}
