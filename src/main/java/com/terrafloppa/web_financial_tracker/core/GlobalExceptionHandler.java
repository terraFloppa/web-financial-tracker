package com.terrafloppa.web_financial_tracker.core;

import com.terrafloppa.web_financial_tracker.category.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.databind.exc.InvalidFormatException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // TODO Добавить обработку для всех сущностей
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFound(CategoryNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();

        // Проверяем, является ли причиной ошибки InvalidFormatException от Jackson
        if (ex.getCause() instanceof InvalidFormatException invalidFormatException) {

            // 1. Автоматически определяем имя поля, в котором пришла ошибка
            String fieldName = invalidFormatException.getPath().stream()
                    .map(InvalidFormatException.Reference::getPropertyName)
                    .findFirst()
                    .orElse("field");

            // 2. Проверяем, является ли целевой тип перечислением (Enum)
            Class<?> targetType = invalidFormatException.getTargetType();
            if (targetType != null && targetType.isEnum()) {

                // 3. Динамически собираем список всех доступных значений этого Enum
                String allowedValues = Arrays.toString(targetType.getEnumConstants());

                error.put(fieldName, "Недопустимое значение. Допустимые варианты: " + allowedValues);
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
        }

        // Сюда попадут все остальные синтаксические ошибки JSON (забытые запятые, скобки и т.д.)
        error.put("error", "Неверный формат JSON-запроса");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
