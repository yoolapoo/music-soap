package com.epsi.music.exception.handler;

import com.epsi.music.data.MediaApiStatus;
import com.epsi.music.dto.ErrorResponse;
import com.epsi.music.exception.AllMusicsAlreadyReturnedException;
import com.epsi.music.exception.MusicNotFoundException;
import com.epsi.music.exception.MusicSoapException;
import com.epsi.music.exception.UnavailableMusicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorResponse> throwableHandler(Throwable exception) {
		log.error(exception.getMessage(), exception);
		ErrorResponse res = new ErrorResponse(MediaApiStatus.MEDIA_API_STATUS_1500.toString(), MediaApiStatus.MEDIA_API_STATUS_1500.getReason());
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MusicSoapException.class)
	public ResponseEntity<ErrorResponse> dafReportPortalApiExceptionHandler(MusicSoapException exception) {
		ErrorResponse res = new ErrorResponse(exception.getCode(), exception.getMessage());
		return new ResponseEntity<>(res, exception.getStatus());
	}
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public ResponseEntity<ErrorResponse> processValidationError(HttpRequestMethodNotSupportedException ex) {
		ErrorResponse res = new ErrorResponse(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), ex.getMessage());
		return new ResponseEntity<>(res, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(MusicNotFoundException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<ErrorResponse> processValidationError(MusicNotFoundException ex) {

		ErrorResponse res = new ErrorResponse(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), ex.getMessage());
		return new ResponseEntity<>(res, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(UnavailableMusicException.class)
	@ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
	public ResponseEntity<ErrorResponse> processValidationError(UnavailableMusicException ex){
		ErrorResponse res = new ErrorResponse(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), ex.getMessage());
		return new ResponseEntity<>(res, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(AllMusicsAlreadyReturnedException.class)
	@ResponseStatus(HttpStatus.MULTIPLE_CHOICES)
	public ResponseEntity<ErrorResponse> processValidationError(AllMusicsAlreadyReturnedException ex){
		ErrorResponse res = new ErrorResponse(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), ex.getMessage());
		return new ResponseEntity<>(res, HttpStatus.METHOD_NOT_ALLOWED);
	}
}
