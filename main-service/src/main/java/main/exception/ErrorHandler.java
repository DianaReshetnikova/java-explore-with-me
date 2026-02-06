package main.exception;


import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final NotFoundException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Object not found")
                .status(HttpStatus.NOT_FOUND.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(final ValidationException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Validation exception")
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleDuplicateName(final DuplicateException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Conditions are wrong.")
                .status(HttpStatus.CONFLICT.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(ConditionsNotMetException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConditionsNotMet(final ConditionsNotMetException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("For the requested operation the conditions are not met.")
                .status(HttpStatus.CONFLICT.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Method argument not valid")
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMissingServletRequestParameterException(final MissingServletRequestParameterException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Request parameter is missing")
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleHandlerMethodValidationException(final HandlerMethodValidationException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Method argument not valid")
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleConstraintViolationException(final ConstraintViolationException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Constraint violation occurred")
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleHttpMediaTypeNotSupportedException(final HttpMediaTypeNotSupportedException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Http media type not supported")
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Method argument type mismatch occurred")
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Http message not readable")
                .status(HttpStatus.BAD_REQUEST.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleThrowableException(final Exception e) {
        return ApiError.builder()
                .message(e.getMessage())
                .reason("Internal server error occurred")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
