package controllers;


import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;
import play.mvc.Http;
import play.mvc.Result;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;


public class MatchControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testGetMatches(){
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/api/matches");
        Result result = route(app,request);
        assertEquals(OK,result.status());
    }


}