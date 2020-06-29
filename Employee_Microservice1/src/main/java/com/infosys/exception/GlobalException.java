package com.infosys.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(value=EmployeeAlreadyExistsException.class)
	public ResponseEntity<String> employeeAlreadyExists(EmployeeAlreadyExistsException employeeAlreadyExistsException){
		return new ResponseEntity<String>(employeeAlreadyExistsException.getMessage() , HttpStatus.OK);
	}
	
	@ExceptionHandler(value=EmployeeNotFoundException.class)
	public ResponseEntity<String> employeeAlreadyExists(EmployeeNotFoundException employeeNotFoundException){
		return new ResponseEntity<String>(employeeNotFoundException.getMessage() , HttpStatus.OK);
	} 
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {

		Map<String, String> map= new HashMap<String, String>();
		
		exception.getBindingResult().getAllErrors().forEach((errors)->{
			String fieldName = ((FieldError)errors).getField();
			String errorMessage = errors.getDefaultMessage();
			map.put(fieldName, errorMessage);
		});
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
	}}
