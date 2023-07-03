package com.cg.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@RequestMapping("/KanbanBoard")
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })

	public ErrorInfo handleValidationError(MethodArgumentNotValidException ex, HttpServletRequest req) {

		String msg = "validation faild";

		
		FieldError error = ex.getFieldError();

		if (error != null)

			msg = error.getDefaultMessage();

		return new ErrorInfo(req.getRequestURI(), msg, LocalDateTime.now().toString());

	}

	@ResponseBody
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = { Exception.class })
	public ErrorInfo handleGeneric(Exception ex, HttpServletRequest req) {

		String uri = req.getRequestURL().toString();
		return new ErrorInfo(uri, ex.getMessage(), LocalDateTime.now().toString());

	}

}