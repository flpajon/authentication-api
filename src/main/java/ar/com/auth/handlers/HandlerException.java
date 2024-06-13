package ar.com.auth.handlers;

import ar.com.auth.controllers.dtos.ErrorDTO;
import ar.com.auth.exceptions.PermissionNotFoundException;
import ar.com.auth.exceptions.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request
  ) {
    log.info(ex.getMessage());
    return new ResponseEntity<>(ErrorDTO.from(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request
  ) {
    String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    log.info(errorMessage);
    return new ResponseEntity<>(ErrorDTO.from(errorMessage), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {UserNotFoundException.class, PermissionNotFoundException.class,
      EntityNotFoundException.class})
  public ResponseEntity<ErrorDTO> handleUserNotFoundException(Exception e) {
    log.info(e.getMessage());
    return new ResponseEntity<>(ErrorDTO.from(e.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
