package controllers;


import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;

public class ClubControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testSchoolClubs(){
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/api/schoolClubs");
        Result result = route(app,request);
        assertEquals(OK,result.status());

    }

    @Test
    public void testUniClubs(){
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/api/uniClubs");
        Result result = route(app,request);
        assertEquals(OK,result.status());
    }

}