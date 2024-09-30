package org.example.questions;

import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.json.JSONObject;

public class GetSchema implements Question<Boolean> {
    private final String pathSchema;
    public GetSchema(String pathSchema){
        this.pathSchema = pathSchema;
    }
    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            SerenityRest
                    .then()
                    .assertThat()
                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(pathSchema));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }
    public static GetSchema schema(String pathSchema){
        return new GetSchema(pathSchema);
    }
}
