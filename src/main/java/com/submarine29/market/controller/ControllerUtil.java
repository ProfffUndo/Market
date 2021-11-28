package com.submarine29.market.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ControllerUtil {

    // Полезный метод получения ошибок
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector =
                Collectors.toMap(
                        fieldError -> fieldError.getField() + "Error",
                        FieldError::getDefaultMessage
                );
        return bindingResult.getFieldErrors().stream()
                .collect(collector);
    }

    // Полезный метод проверки карты на наличие ошибок
    static boolean checkForErrors(Map<String, String> errorsMap) {
        return errorsMap.keySet().stream().anyMatch(key -> key.contains("Error"));
    }
}
