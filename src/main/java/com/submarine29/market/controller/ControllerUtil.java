package com.submarine29.market.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ControllerUtil {

    // Полезный метод получения ошибок
    public static Map<String, String> getErrors(BindingResult bindingResult) {
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

    /**
     * Блен, забейте. ЭТО ПРОСТО Я **** В РОТ и В ****.
     * С фронта, по непонятной мне причине, id абьюзера приходит в виде 123 456 789 101
     * И вот из-за этих еб***х пробелов не хочет автоматом стать лонгом и подгрузить абьюзера из базы
     * В общем, пришлось вот так выпендриваться
     * TODO: Если кто-то знает вариант лучше, пожалуйста, перепишите это Г***О!
     * Делал на скорую руку штобы работало
     */
    static long createNormalniyIdFromUserBleat(String idS) {
        byte[] bytes = idS.getBytes(StandardCharsets.UTF_8);
        List<Byte> byteList = new ArrayList<>();
        for (byte aByte : bytes) {
            if (aByte > 0) {
                byteList.add(aByte);
            }
        }
        bytes = new byte[byteList.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = byteList.get(i);
        }
        String rez = new String(bytes);
        return Long.parseLong(rez);
    }
}
