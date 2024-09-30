package org.example.utilities.resources;

import lombok.Getter;

import static org.example.utilities.constants.Constants.*;

@Getter
public enum GetConstant {
    ACTOR_NAME(ACTOR),
    ACTOR_TEST_DATA(TEST_DATA),
    ACTOR_BODY_NEW_PET(BODY_NEW_PET);
    private String constant;
    private String[] constants;
    GetConstant(String constant){
        this.constant = constant;
    }
    GetConstant(String[] constants){
        this.constants = constants;
    }
}
