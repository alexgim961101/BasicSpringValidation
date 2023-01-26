package com.alexgim.validation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @NotBlank
    private String name;

    @NotBlank
    @JsonProperty("car_number")
    private String carNumber;

    @NotBlank
    @JsonProperty("TYPE")
    private String type;

}
