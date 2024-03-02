package com.example.productsservice.controlleradvices;

import com.example.productsservice.dtos.ExceptionsDto;
import com.example.productsservice.exceptions.InvalidInputException;
import com.example.productsservice.exceptions.ProductNotExistsException;
import com.example.productsservice.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ProductNotExistsException.class)
        public ResponseEntity<ExceptionsDto> handleProductNotExists(ProductNotExistsException productNotExistsException){
            ExceptionsDto exceptionsDto = new ExceptionsDto();
            exceptionsDto.setMessage(productNotExistsException.getMessage());
            exceptionsDto.setDetails("Please search for other similar products");
            return new ResponseEntity<>(exceptionsDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputException.class)
        public ResponseEntity<ExceptionsDto> handleInvalidInput(InvalidInputException invalidInputException){
            ExceptionsDto exceptionsDto = new ExceptionsDto();
            exceptionsDto.setMessage(invalidInputException.getMessage());
            exceptionsDto.setDetails("Please provide a valid product id.");
            return new ResponseEntity<>(exceptionsDto, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(NoResourceFoundException.class)
        public ResponseEntity<ExceptionsDto> handleNoResourceFound(NoResourceFoundException noResourceFoundException){
            ExceptionsDto exceptionsDto = new ExceptionsDto();
            exceptionsDto.setMessage(noResourceFoundException.getMessage());
            exceptionsDto.setDetails("Please try again later.");
            return new ResponseEntity<>(exceptionsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
