package org.example.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.example.models.createNewPet.category;
import org.example.models.createNewPet.Pet;
import org.example.models.createNewPet.Tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.example.utilities.resources.GetConstant.ACTOR_BODY_NEW_PET;

public class CreateBodyToCreateNewPet implements Task {
    public Map<String, String> dataToBody;
    public Pet pet;
    public List<String> photoUrls;
    public category categorias = new category();
    public List<Tags> tags = new ArrayList<>();


    public CreateBodyToCreateNewPet(Map<String, String> dataToBody) {
        this.dataToBody = dataToBody;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (dataToBody.get("photoUrl").contains(",")) {
            String[] arrayUrls = dataToBody.get("photoUrl").split(",");
            photoUrls = Arrays.asList(arrayUrls);
        } else {
            photoUrls.add(dataToBody.get("photoUrl"));
        }

        categorias.setId(Integer.parseInt(dataToBody.get("idCategory")));
        categorias.setName(dataToBody.get("nameCategory"));


        String[] arrayTagsId;
        String[] arrayTagsName;
        if (dataToBody.get("idTags").contains(",")) {
            arrayTagsId = dataToBody.get("idTags").split(",");
            arrayTagsName = dataToBody.get("nameTags").split(",");

            for (int i = 0; i < arrayTagsName.length; i++) {
                Tags tags1 = new Tags();
                tags1.setId(Integer.parseInt(arrayTagsId[i]));
                tags1.setName(arrayTagsName[i]);
                tags.add(tags1);
            }

        } else {
            tags.add(new Tags(Integer.parseInt(dataToBody.get("idTags")), dataToBody.get("nameTags")));
        }


        pet = Pet.builder()
                .id(Integer.parseInt(dataToBody.get("id")))
                .name(dataToBody.get("name"))
                .category(categorias)
                .photoUrls(photoUrls)
                .tags(tags)
                .status(dataToBody.get("status")).build();

        ObjectMapper mapper = new ObjectMapper();
        try {
            actor.remember(ACTOR_BODY_NEW_PET.getConstant(), mapper.writeValueAsString(pet));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static CreateBodyToCreateNewPet with(Map<String, String> dataToBody) {
        return Instrumented.instanceOf(CreateBodyToCreateNewPet.class).withProperties(dataToBody);
    }
}
