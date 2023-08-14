package com.system.management.university.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundExceptions extends RuntimeException{
    private String message;
}
