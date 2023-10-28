package com.devsuperior.bds02.controller.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ExcpetionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardErrorr> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		StandardErrorr err = new StandardErrorr();
		HttpStatus notFound = HttpStatus.NOT_FOUND;
		err.setTimestamp(Instant.now());
		err.setStatus(notFound.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(notFound).body(err);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardErrorr> integrityViolation(DatabaseException e, HttpServletRequest request) {
		StandardErrorr err = new StandardErrorr();
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		err.setTimestamp(Instant.now());
		err.setStatus(badRequest.value());
		err.setError("Database Exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(badRequest).body(err);
	}

}
