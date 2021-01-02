package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.FootballClub;
import play.libs.Json;
import models.Match;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.MatchService;
import utils.ApplicationUtil;


import java.util.ArrayList;


public class MatchController extends Controller {

    public Result getAllMatches(){
        ArrayList<Match> matches = new ArrayList<>();
        MatchService.getInstance().setMatches(ApplicationUtil.loadMatch(matches));
        ArrayList<Match> allMatches = (ArrayList<Match>) MatchService.getInstance().getMatches();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(allMatches,JsonNode.class);
        return ok(jsonData);
    }

    public Result  addMatch(Http.Request request){
        /**
         * POST request
         * # resource link
         * https://www.playframework.com/documentation/2.8.x/JavaJsonActions
         */
        JsonNode jsonNode = request.body().asJson();
        if (jsonNode == null){
            return badRequest("Error");
        }
        Match match = Json.fromJson(jsonNode, Match.class);
       System.out.println(match);

       ArrayList<FootballClub> footballClubs = new ArrayList<>();
       MatchService.getInstance().setFootballClubs(ApplicationUtil.loadClub(footballClubs));
       ArrayList<Match> matches = new ArrayList<>();
       MatchService.getInstance().setMatches(ApplicationUtil.loadMatch(matches));
       MatchService.getInstance().addMatch(match);
       ApplicationUtil.saveData(footballClubs,matches);

        JsonNode jsonObject = Json.toJson(match);
        return created(ApplicationUtil.createResponse(jsonObject,true));
    }






    



}
