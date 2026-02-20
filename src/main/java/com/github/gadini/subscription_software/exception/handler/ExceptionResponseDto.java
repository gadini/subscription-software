package com.github.gadini.subscription_software.exception.handler;

import java.util.Date;

public record ExceptionResponseDto(
        Date timestamp,
        String message,
        String error,
        String path
) { }
