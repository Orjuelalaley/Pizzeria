package com.orjuelalaley.Pizzeria.Exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private String name;
    private String message;
    private String stackTrace;
}
