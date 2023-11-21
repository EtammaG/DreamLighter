package com.etammag.dreamlighter.exception;

import com.etammag.icommon.entity.Result;
import com.etammag.icommon.exception.SimpleGlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler extends SimpleGlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public Result<String> handleAccessDeniedException(AccessDeniedException e) {
        return Result.fail("拒绝访问");
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<String> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        return Result.fail("上传文件过大");
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public Result<String> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        return Result.fail("账号或密码错误");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Result<String> handleInternalAuthenticationServiceException(BadCredentialsException e) {
        return Result.fail("账号或密码错误");
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public void handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        log.warn("连接超时");
    }

}
