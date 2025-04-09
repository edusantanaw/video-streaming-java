package com.edusantanaw.message.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NewUserDTO (UUID id, String name, String email){
}
