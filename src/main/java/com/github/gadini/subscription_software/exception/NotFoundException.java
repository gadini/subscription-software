package com.github.gadini.subscription_software.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String resource, Object identifier) {
        super("%s with identifier %s not found".formatted(resource, identifier));
    }

}
