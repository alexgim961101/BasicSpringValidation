package com.alexgim.validation.exception.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Error {
    private String field;
    private String message;
    private String invalidValue;
}
