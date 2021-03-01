package com.abrari.buffoon.joke.dto;

import com.abrari.buffoon.joke.datalayer.entities.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JokeIDLessDTO {

    @JsonProperty(value = "guid")
    private String guid;

    @NonNull
    @JsonProperty(value = "setup")
    private String setup;

    @NonNull
    @JsonProperty(value = "punchline")
    private String punchline;

    @NonNull
    @JsonProperty(value = "category")
    private Category category;
}
