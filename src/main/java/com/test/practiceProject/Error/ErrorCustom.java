package com.test.practiceProject.Error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
public class ErrorCustom {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiResponse> exception(Exception ex)
    {
        String message = getLocalizedMessage("exception.internalServerError");
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity
                .status(status)
                .body(new ApiResponse(status.toString(), message));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse> handleBadRequest(BadRequestException ex) {
        String messageKey = ex.getMessage();
        Map<String, String> additionalInfo = ex.getAdditionalInformation();
        String message = additionalInfo == null
                ? getLocalizedMessage(messageKey)
                : getLocalizedMessage(messageKey, new Object[]{additionalInfo});

        String newMessage = checkExistedTranslationKey(messageKey, new Object[]{additionalInfo})  ? message : messageKey;
        HttpStatus status = ex.getHttpStatus();
        ApiResponse apiResponse = new ApiResponse(status.toString(), newMessage, additionalInfo);
        return ResponseEntity.status(status).body(apiResponse);
    }

    private String getLocalizedMessage(String translationKey)
    {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(translationKey, null, locale);
    }

    private String getLocalizedMessage(String translationKey, Object[] args)
    {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(translationKey, args, locale);
    }

    private boolean checkExistedTranslationKey(String translationKey, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            if (args != null) messageSource.getMessage(translationKey, args, locale);
            else messageSource.getMessage(translationKey, null, locale);
            return true;
        } catch (NoSuchMessageException e) {
            return false;
        }
    }
}
