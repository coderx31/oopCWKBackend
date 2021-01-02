package services;

import models.FootballClub;


import java.util.ArrayList;
import java.util.List;

public class ClubService {
    private static ClubService instance;
    private List<FootballClub> footballClubs = new ArrayList<FootballClub>();

    /*load data from file to arrayList*/

    public static ClubService getInstance(){
        if (instance == null ){
            instance = new ClubService();
        }
        return instance;
    }

    public FootballClub addClub(FootballClub footballClub){
        this.footballClubs.add(footballClub);
        return footballClub;
    }

    public void setFootballClubs(List<FootballClub> footballClubs) {
        this.footballClubs = footballClubs;
    }

    public List<FootballClub> getFootballClubs(){
        return footballClubs;
    }
}
