package com.example.Springdesafio.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDTO {
    private String name;
    private String description;

}