package com.abrari.buffoon.joke.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class CategoryNamelessDTO {

    @NonNull
    @JsonProperty(value = "id")
    private Integer id;
}
