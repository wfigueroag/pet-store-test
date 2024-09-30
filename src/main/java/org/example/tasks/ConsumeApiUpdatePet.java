package org.example.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;
import org.apache.http.HttpStatus;
import org.example.exceptions.ServiceExceptions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.example.utilities.resources.GetEndpoint.*;

public class ConsumeApiUpdatePet implements Task {
    private final String body;

    public ConsumeApiUpdatePet(String body) {
        this.body = body;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String baseUrl= API_HOST.getEndpoint() + API_SERVER.getEndpoint();
        theActorInTheSpotlight().whoCan(CallAnApi.at(baseUrl));
        SerenityRest.rest();
        actor.attemptsTo(
                Put.to(API_PET_RESOURCE_ADD_NEW_PET.getEndpoint()).with(request -> request.contentType(ContentType.JSON)
                        .body(body)
                        .relaxedHTTPSValidation()
                        .log().all())
        );

        if (SerenityRest.lastResponse().statusCode() != HttpStatus.SC_OK &&
        SerenityRest.lastResponse().statusCode()!=HttpStatus.SC_NO_CONTENT &&
        SerenityRest.lastResponse().statusCode() != HttpStatus.SC_BAD_REQUEST){
            throw new ServiceExceptions("There was an error while the method was consumed");
        }

    }


    public static ConsumeApiUpdatePet updatePet(String body) {
        return Instrumented.instanceOf(ConsumeApiUpdatePet.class).withProperties(body);
    }

}
