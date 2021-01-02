package controllers;

import junit.framework.TestCase;
import models.Date;
import models.Match;
import org.junit.Test;

public class MatchTest extends TestCase {
    String homeTeam = "Annites Football Club";
    String opponentTeam = "Devans Football Club";
    int homeScore = 3;
    int opponentScore = 2;
    int day = 12;
    int month = 12;
    int year = 2020;

    Date date = new Date(day,month,year);
    Match match = new Match(homeTeam,opponentTeam,homeScore,opponentScore,date);

    @Test
    public void testHomeTeam(){
        assertEquals(homeTeam, match.getHomeTeam());
    }

    @Test
    public void testOpponentTeam(){
        assertEquals(opponentTeam,match.getOpponentTeam());
    }

    @Test
    public void testHomeScore(){
        assertEquals(homeScore, match.getHomeScore());
    }

    @Test
    public void testOpponentScore(){
        assertEquals(opponentScore, match.getOpponentScore());
    }

    @Test
    public void testDate(){
        assertEquals(date, match.getDate());
    }

}