package com.example.api.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MonedasIgualesException.class)
    public String handleMonedasIgualesException() {
        return "redirect:/?err=2";
    }
}
