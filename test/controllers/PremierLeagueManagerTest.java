package controllers;

import junit.framework.TestCase;
import models.Address;
import models.FootballClub;
import org.junit.Test;
import services.LeagueManager;
import services.PremierLeagueManager;

import java.util.ArrayList;
import java.util.List;

public class PremierLeagueManagerTest extends TestCase {
    LeagueManager leagueManager = new PremierLeagueManager();

    List<FootballClub> footballClubs = new ArrayList<FootballClub>();

    @Test
    public void testAddClub(){
        // need to implement
        Address address = new Address("12","Main rd","Kurunegala",
                61110,"Sri Lanka");
        FootballClub footballClub = new FootballClub("Annites Football Club",address);
        assertArrayEquals(footballClubs.add(footballClub));
    }

    private void assertArrayEquals(boolean add) {
    }

    @Test
    public void testDelete(){
        Address address = new Address("12","Main rd","Kurunegala",
                61110,"Sri Lanka");
        FootballClub footballClub = new FootballClub("Annites Football Club",address);
        assertArrayEquals(footballClubs.remove(footballClub));
    }


}