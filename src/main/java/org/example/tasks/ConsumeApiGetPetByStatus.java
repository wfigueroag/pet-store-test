package org.example.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.apache.http.HttpStatus;
import org.example.exceptions.ServiceExceptions;

import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.example.utilities.resources.GetEndpoint.*;

public class ConsumeApiGetPetByStatus implements Task {
    public Map<String, String> status;

    public ConsumeApiGetPetByStatus(Map<String, String> status) {
        this.status = status;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String baseUrl= API_HOST.getEndpoint() + API_SERVER.getEndpoint();
        theActorInTheSpotlight().whoCan(CallAnApi.at(baseUrl));
        SerenityRest.rest();
        actor.attemptsTo(
                Get.resource(API_PET_RESOURCE_FIND_PET_BY_STATUS.getEndpoint()).with(request -> request
                        .queryParam("status",status.get("status"))
                        .relaxedHTTPSValidation()
                        .log().all())
        );

        if (SerenityRest.lastResponse().statusCode() != HttpStatus.SC_OK &&
        SerenityRest.lastResponse().statusCode()!=HttpStatus.SC_NO_CONTENT &&
        SerenityRest.lastResponse().statusCode() != HttpStatus.SC_BAD_REQUEST){
            throw new ServiceExceptions("There was an error while the method was consumed");
        }

    }


    public static ConsumeApiGetPetByStatus getPetByStatus(Map<String, String> status) {
        return Instrumented.instanceOf(ConsumeApiGetPetByStatus.class).withProperties(status);
    }

}
