package test.ngocpt.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import test.ngocpt.auth.dto.response.BaseResponse;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse globalExceptionHandler(Exception ex, WebRequest request) {
        return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.toString());
    }
}
