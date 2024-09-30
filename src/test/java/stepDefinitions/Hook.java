package stepDefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.example.utilities.resources.GetConstant.ACTOR_NAME;

public class Hook {

    @Before
    public void setUpConfiguration(){
        setTheStage(new OnlineCast());
        theActorCalled(ACTOR_NAME.getConstant());
    }
}
