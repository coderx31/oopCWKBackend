package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.FootballClub;
import models.SchoolFootballClub;
import models.UniversityFootballClub;
import play.mvc.Controller;
import play.mvc.Result;
import services.ClubService;


import utils.ApplicationUtil;

import java.util.ArrayList;


public class ClubController extends Controller {

    public Result getSchoolFootballClubs(){
        // create new arraylist and load clubs data from file to it
        ArrayList<FootballClub> footballClubs = new ArrayList<>();
        ClubService.getInstance().setFootballClubs(ApplicationUtil.loadClub(footballClubs));
        ArrayList<SchoolFootballClub> schoolFootballClubs = new ArrayList<>();
        for (FootballClub footballClub : ClubService.getInstance().getFootballClubs()){
            if (footballClub instanceof SchoolFootballClub){
                schoolFootballClubs.add((SchoolFootballClub) footballClub);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        // return data as json
        JsonNode jsonData = mapper.convertValue(schoolFootballClubs,JsonNode.class);
        return ok(jsonData);

    }



    public Result getUniFootballClubs(){
        ArrayList<FootballClub> footballClubs = new ArrayList<>();
        ClubService.getInstance().setFootballClubs(ApplicationUtil.loadClub(footballClubs));
        ArrayList<UniversityFootballClub> universityFootballClubs = new ArrayList<>();
        for (FootballClub footballClub : ClubService.getInstance().getFootballClubs()){
            if (footballClub instanceof UniversityFootballClub){
                universityFootballClubs.add((UniversityFootballClub) footballClub);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(universityFootballClubs,JsonNode.class);
        return ok(jsonData);
    }


}
