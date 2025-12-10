package com.example.articulo.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private List<String> errors;
    private String path;

    public ApiError(LocalDateTime timestamp, int status, String error, String message, List<String> errors,
            String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.errors = errors;
        this.path = path;
    }

    public ApiError() {
    }

    public LocalDateTime getLocalDateTime() {
        return this.timestamp;
    }

    public void setLocalDateTime(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return this.status;
    }

    public void setLocalDateTime(int status) {
        this.status = status;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return this.errors;
    }

    public void setError(List<String> errors) {
        this.errors = errors;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
