package com.abrari.buffoon.joke.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class JokeGUIDLessDTO {

    @NonNull
    @JsonProperty(value = "setup")
    private String setup;

    @NonNull
    @JsonProperty(value = "punchline")
    private String punchline;

    @NonNull
    @JsonProperty(value = "category")
    private CategoryNamelessDTO category;
}
