package com.example.demo.exception_handling;



import com.example.demo.exceptions.IsNotAvailableException;
import com.example.demo.exceptions.NoSuchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.annotation.PostConstruct;

@Component
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @PostConstruct
    public void init() {
        log.info("⚡️ GlobalExceptionHandler загружен!");
    }

    @ExceptionHandler(NoSuchException.class)
    public ResponseEntity<Object> handleNoSuchException(
            NoSuchException exception){
        log.error("Handled NoSuchException: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(IsNotAvailableException.class)
    public ResponseEntity<Object> handleIsNotAvailableException(
            IsNotAvailableException exception){

        return ResponseEntity.status(HttpStatus.IM_USED).body(exception.getMessage());
    }


}
