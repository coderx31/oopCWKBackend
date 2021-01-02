package controllers;

import junit.framework.TestCase;
import models.Address;
import models.FootballClub;
import org.junit.Test;

public class FootballClubTest extends TestCase {
    String clubName = "Annites Football Club";
    String number = "12";
    String road = "Main rd";
    String town = "Kurunegala";
    int postalCode = 61110;
    String country = "Sri Lanka";


    Address address = new Address(number,road,town,postalCode,country);
    FootballClub footballClub = new FootballClub(clubName,address);


    @Test
    public void testClubName(){
        assertEquals(clubName,footballClub.getClubName());
    }

    @Test
    public void testLocation(){
        assertEquals(address,footballClub.getClubLocation());
    }


}