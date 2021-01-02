package controllers;

import junit.framework.TestCase;
import models.Address;
import models.SchoolFootballClub;
import org.junit.Test;

public class SchoolFootballClubTest extends TestCase {
    String schoolName = "St. Anne's College";
    String clubName = "Annites Football Club";
    String number = "12";
    String road = "Main rd";
    String town = "Kurunegala";
    int postalCode = 61110;
    String country = "Sri lanka";

    Address address = new Address(number,road,town,postalCode,country);
    SchoolFootballClub schoolFootballClub = new SchoolFootballClub(schoolName,clubName,address);

    @Test
    public void testSchoolName(){
        assertEquals(schoolName,schoolFootballClub.getSchoolName());
    }

    @Test
    public void testClubName(){
        assertEquals(clubName, schoolFootballClub.getClubName());
    }

    @Test
    public void testLocation(){
        assertEquals(address, schoolFootballClub.getClubLocation());
    }

}