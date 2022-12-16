package com.nt.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TouristErrorHandler {
	
	@ExceptionHandler(TouristNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleTouristNotFound(TouristNotFoundException tnfe)
	{
		ErrorDetails obj=new ErrorDetails(LocalDateTime.now(),tnfe.getMessage(),"404-Tourist Not Found");
		return new ResponseEntity<ErrorDetails>(obj,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllProblem(Exception e)
	{
		ErrorDetails obj=new ErrorDetails(LocalDateTime.now(),e.getMessage(),"Problem In Execution");
		return new ResponseEntity<ErrorDetails>(obj,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
