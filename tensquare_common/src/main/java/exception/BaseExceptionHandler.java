package exception;

import entity.Result;
import entity.StatusCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        log.error("[发生异常] ", e);
        return Result.error(StatusCodeEnum.ERROR.getStatusCode(), StatusCodeEnum.ERROR.getDefaultMessage());
    }

}
