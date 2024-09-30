package org.example.utilities.resources;

import lombok.Getter;

import static org.example.utilities.constants.Endpoints.*;

@Getter
public enum GetEndpoint {
    API_HOST(HOST),
    API_SERVER(SERVER),
    API_PET_RESOURCE_ADD_NEW_PET(PET_RESOURCE_ADD_NEW_PET),
    API_FINDS_PET_BY_TAGS(PET_RESOURCE_FIND_PET_BY_TAGS),
    API_PET_RESOURCE_FIND_PET_BY_STATUS(PET_RESOURCE_FIND_PET_BY_STATUS),
    API_PET_RESOURCE_FIND_PET_BY_ID(PET_RESOURCE_FIND_PET_BY_ID);

    private  final String endpoint;

    GetEndpoint(String endpoint){
        this.endpoint = endpoint;
    }
}
