package controllers;

import junit.framework.TestCase;
import models.Date;
import org.junit.Test;

public class DateTest extends TestCase {
    int day = 12;
    int month = 10;
    int year = 2020;

    Date date = new Date(day,month,year);

    @Test
    public void testDay(){
        assertEquals(day, date.getDay());
    }

    @Test
    public void testMonth(){
        assertEquals(month, date.getMonth());
    }

    @Test
    public void testYear(){
        assertEquals(year, date.getYear());
    }

}