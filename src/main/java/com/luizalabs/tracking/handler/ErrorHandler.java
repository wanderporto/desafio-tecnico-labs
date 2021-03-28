package com.luizalabs.tracking.handler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.luizalabs.tracking.execption.ScheduleNotFound;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ErrorHandler{

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerValidationExceptions(MethodArgumentNotValidException ex) {
		
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());


        return error(errors,HttpStatus.BAD_REQUEST);
	}

    @ExceptionHandler(ScheduleNotFound.class)
	public ResponseEntity<?> handlerScheduleNotFoundExceptions(ScheduleNotFound ex) {
		return error(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

    public ResponseEntity internalServerError(String data) {
        return error(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity error(String data, HttpStatus httpStatus) {
        Map<Object, Object> model = new LinkedHashMap<>();
        model.put("msg", httpStatus.getReasonPhrase());
        model.put("status", httpStatus.value());
        model.put("data", data);

        return new ResponseEntity(model, httpStatus);
    }

    public ResponseEntity error(List<String> data, HttpStatus httpStatus) {
        Map<Object, Object> model = new LinkedHashMap<>();
        model.put("msg", httpStatus.getReasonPhrase());
        model.put("status", httpStatus.value());
        model.put("data", data);

        return new ResponseEntity(model, httpStatus);
    }
}
