package controllers;

import junit.framework.TestCase;
import models.Address;
import models.UniversityFootballClub;
import org.junit.Test;

public class UniversityFootballClubTest extends TestCase {
    String universityName = "Informatics Institute of Technology";
    String clubName = "IIT Football Club";
    String number = "57";
    String road = "Ramakrishna road";
    String town = "Colombo 6";
    int postalCode = 00600;
    String country = "Sri Lanka";

    Address address = new Address(number,road,town,postalCode,country);
    UniversityFootballClub universityFootballClub = new UniversityFootballClub(universityName,
            clubName,address);

    @Test
    public void testUniversityName(){
        assertEquals(universityName,universityFootballClub.getUniversityName());
    }

    @Test
    public void testClubName(){
        assertEquals(clubName,universityFootballClub.getClubName());
    }

    @Test
    public void testClubLocation(){
        assertEquals(address,universityFootballClub.getClubLocation());
    }

}