package com.example.last.entity;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class APIResponse<T> extends RepresentationModel<APIResponse<T>> {
    T response;
}
