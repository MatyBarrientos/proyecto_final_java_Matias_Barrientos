package com.example.articulo.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import tools.jackson.databind.exc.UnrecognizedPropertyException;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiError> handleValidationErrors(
                        MethodArgumentNotValidException ex,
                        HttpServletRequest request) {

                List<String> errores = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                                .collect(Collectors.toList());

                ApiError apiError = new ApiError(
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                "Bad Request",
                                "Error de validación",
                                errores,
                                request.getRequestURI());

                return ResponseEntity.badRequest().body(apiError);
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ApiError> handleJsonParseError(
                        HttpMessageNotReadableException ex,
                        HttpServletRequest request) {

                String message = "Error en el formato del JSON";
                List<String> errores = null;

                if (ex.getCause() instanceof UnrecognizedPropertyException upe) {
                        String campo = upe.getPropertyName();
                        message = "Campo JSON no reconocido: " + campo;
                        errores = List.of("El campo \"" + campo + "\" no existe. Verifique el nombre.");
                }

                ApiError apiError = new ApiError(
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                "Bad Request",
                                message,
                                errores,
                                request.getRequestURI());

                return ResponseEntity.badRequest().body(apiError);
        }
}
