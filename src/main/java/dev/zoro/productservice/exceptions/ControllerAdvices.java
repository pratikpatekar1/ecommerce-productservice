package dev.zoro.productservice.exceptions;

import dev.zoro.productservice.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity(
                new ExceptionDto(HttpStatus.NOT_FOUND,notFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(UnauthorizedException.class)
    private ResponseEntity<ExceptionDto> handleUnauthorizedException(UnauthorizedException unauthorizedException){
        return new ResponseEntity(
                new ExceptionDto(HttpStatus.UNAUTHORIZED,unauthorizedException.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }
}
