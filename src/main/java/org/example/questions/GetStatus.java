package org.example.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetStatus implements Question<Boolean> {
    private final int code;

    public GetStatus(int code) {
        this.code = code;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(
                seeThatResponse("The code obtained is " + code, response -> response.statusCode(code))
        );
        return true;
    }

    public static GetStatus code(int code) {
        return new GetStatus(code);
    }
}
