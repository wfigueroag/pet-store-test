package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.tasks.*;
import org.example.questions.GetSchema;
import org.example.questions.GetStatus;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.example.utilities.resources.GetConstant.ACTOR_BODY_NEW_PET;
import static org.example.utilities.resources.GetConstant.ACTOR_TEST_DATA;

public class stepsPetResource {
    @Given("^I have the next data:$")
    public void dataTable(Map<String, String> data) {
        theActorInTheSpotlight().remember(ACTOR_TEST_DATA.getConstant(), data);

    }


    @When("^I execute de service of create a new pet$")
    public void executeServiceOfCreatePet() {
        theActorInTheSpotlight().attemptsTo(CreateBodyToCreateNewPet.with(
                theActorInTheSpotlight().recall(ACTOR_TEST_DATA.getConstant())
        ));
        theActorInTheSpotlight().attemptsTo(
                ConsumeApiAddNewPet.createNewPet(
                        theActorInTheSpotlight().recall(ACTOR_BODY_NEW_PET.getConstant())
                )
        );

    }

    @When("^I execute de service of update a pet$")
    public void executeServiceOfUpdatePet() {
        theActorInTheSpotlight().attemptsTo(CreateBodyToCreateNewPet.with(
                theActorInTheSpotlight().recall(ACTOR_TEST_DATA.getConstant())
        ));
        theActorInTheSpotlight().attemptsTo(
                ConsumeApiUpdatePet.updatePet(
                        theActorInTheSpotlight().recall(ACTOR_BODY_NEW_PET.getConstant())
                )
        );

    }

    @When("^I execute de service to get pet by status$")
    public void executeServiceToGetPetByStatus() {
        theActorInTheSpotlight().attemptsTo(
                ConsumeApiGetPetByStatus.getPetByStatus(
                        theActorInTheSpotlight().recall(ACTOR_TEST_DATA.getConstant())
                )
        );

    }

    @When("^I execute de service to get pet by tag")
    public void executeServiceToGetPetByTag() {
        theActorInTheSpotlight().attemptsTo(
                ConsumeApiGetPetByTag.getPetByTag(
                        theActorInTheSpotlight().recall(ACTOR_TEST_DATA.getConstant())
                )
        );

    }

    @When("^I execute de service to get pet by id")
    public void executeServiceToGetPetById() {
        theActorInTheSpotlight().attemptsTo(
                ConsumeApiGetPetById.getPetById(
                        theActorInTheSpotlight().recall(ACTOR_TEST_DATA.getConstant())
                )
        );

    }

    @Then("^the response will be (.*)$")
    public void validateStatus(int status) {
        theActorInTheSpotlight().should(
                seeThat("The answer code obtained by the API is correct", GetStatus.code(status))
        );


    }

    @Then("^its schema will be ok with the structure (.*)$")
    public void validateSchema(String pathSchema) {
        theActorInTheSpotlight().should(
                seeThat("The Schema obtained by API is correct ", GetSchema.schema(pathSchema))
        );

    }


}
