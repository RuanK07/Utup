package com.ruankennedy.socialnetwork.config.exceptionConfig.handler;


import com.ruankennedy.socialnetwork.config.exceptionConfig.standardError.commonStandardError.StandardError;
import com.ruankennedy.socialnetwork.config.exceptionConfig.standardError.validationArgsStandardError.StandardErrorArgsNotValid;
import com.ruankennedy.socialnetwork.config.exceptionConfig.standardError.validationArgsStandardError.ValidationErrorCollection;
import com.ruankennedy.socialnetwork.service.customException.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;


@ControllerAdvice
@RequiredArgsConstructor
public class ResourceExceptionHandler {

    private final MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(ResourceExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorCollection> handleValidations(MethodArgumentNotValidException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String path = request.getRequestURI();
        String error = "There is one or more parameters invalids";

        ValidationErrorCollection validationErrs = new ValidationErrorCollection(status.value(), path, error);

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            String field = e.getField();
            validationErrs.addStandardErrorArgsNotValid(new StandardErrorArgsNotValid(field, message));
        });
        log(exception);
        return ResponseEntity.status(status).body(validationErrs);

    }

    @ExceptionHandler({EntityNotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<StandardError> mappingNotFound(HttpServletRequest request) {
        return handlingException(new ResourceNotFoundException("The resource isn't mapped"),request,"Not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> methodNotSupportedException(HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
        return handlingException(exception,request,"Method not allowed", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = PropertyReferenceException.class)
    public ResponseEntity<StandardError> propertyReference(PropertyReferenceException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Property Reference error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> emailAlreadyRegistered(EmailAlreadyRegisteredException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Email error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NicknameAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> nicknameAlreadyRegistered(NicknameAlreadyRegisteredException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Username error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = DatabaseException.class)
    public ResponseEntity<StandardError> dataBase(DatabaseException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Database error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PasswordDoesntMatchRegisterUserException.class)
    public ResponseEntity<StandardError> passwordsDontMatchException(PasswordDoesntMatchRegisterUserException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Passwords error", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Resource not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = SamePasswordException.class)
    public ResponseEntity<StandardError> samePassword(SamePasswordException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Same password", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidParamException.class)
    public ResponseEntity<StandardError> invalidParam(InvalidParamException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Invalid Param", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class) 
    public ResponseEntity<StandardError> genericException(RuntimeException exception, HttpServletRequest request) {
        return handlingException(exception, request, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<StandardError> handlingException(Exception exception, HttpServletRequest request, String error, HttpStatus status) {
        StandardError err = new StandardError(status.value(), error, exception.getMessage(), request.getRequestURI());
        log(exception);
        return ResponseEntity.status(status).body(err);
    }

    private void log(Throwable exception) {
        logger.error("error message {}. Details:", exception.getMessage(), exception);
    }

}
