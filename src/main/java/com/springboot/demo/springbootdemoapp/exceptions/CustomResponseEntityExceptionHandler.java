package com.springboot.demo.springbootdemoapp.exceptions;

import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import javax.security.sasl.AuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//  @ExceptionHandler(NotFoundException.class)
//  public final ResponseEntity<Object> handleNotFoundException(Exception exception,
//      WebRequest request) {
//    String message = "";
//    if exception.getE
//    ExceptionResponse exceptionResponse = new ExceptionResponse(
//        HttpStatus.NOT_FOUND.value(),
//        exception.getMessage(),
//        LocalDateTime.now());
//
//    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
//  }
//
//  @ExceptionHandler(ValidationError.class)
//  public final ResponseEntity<Object> handleValidationErrorException(ValidationError exception,
//      WebRequest request) {
//    ExceptionResponse exceptionResponse = new ExceptionResponse(
//        HttpStatus.BAD_REQUEST.value(),
//        "Validation error",
//        exception.getMessage(),
//        LocalDateTime.now());
//
//    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
//  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex,
      HttpServletResponse response) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        HttpStatus.UNAUTHORIZED.value(),
        ex.getMessage(),
        LocalDateTime.now());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(CustomException.class)
  public final ResponseEntity<Object> handleCustomException(CustomException exception,
      WebRequest request) {
    String message = exception.getMessage();
    if (message == null) {
      message = exception.getErrorType().getMessage();
    }
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        exception.getErrorType().getHttpStatus().value(),
        message,
//        exception.getMessage().split(":")[0],
        LocalDateTime.now());

    return new ResponseEntity<>(exceptionResponse, exception.getErrorType().getHttpStatus());
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        HttpStatus.BAD_REQUEST.value(),
        "Failed to parse JSON",
//        ex.getMessage(),
        LocalDateTime.now());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(
        HttpStatus.BAD_REQUEST.value(),
        "Validation error",
//        ex.getBindingResult().toString(),
        LocalDateTime.now());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}
