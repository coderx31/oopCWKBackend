package controllers;

import junit.framework.TestCase;
import models.Address;
import org.junit.Test;

public class AddressTest extends TestCase {
    String number = "12";
    String road = "Main rd";
    String town = "Kurunegala";
    int postalCode = 61110;
    String country = "Sri lanka";

    Address address = new Address(number,road,town,postalCode,country);
    @Test
    public void testNumber(){
        assertEquals(number, address.getNumber());
    }

    @Test
    public void testRoad(){
        assertEquals(road,address.getRoad());
    }

    @Test
    public void testTown(){
        assertEquals(town, address.getTown());
    }

    @Test
    public void testPostalCode(){
        assertEquals(postalCode, address.getPostalCode());
    }

    @Test
    public void testCountry(){
        assertEquals(country, address.getCountry());
    }

}