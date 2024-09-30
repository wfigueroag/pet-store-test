package org.example.models.createNewPet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    public int id;
    public String name;
    public category category;
    public List<String> photoUrls;
    public List<Tags> tags;
    public String status;
}
