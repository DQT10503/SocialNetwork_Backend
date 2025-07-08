package com.source_user_auth.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final HttpServletRequest request;

    public LoggingAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointcut() {}

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void servicePointcut() {}

    @Around("controllerPointcut()")
    public Object incomingRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        logger.info("[Request] {} {} - starting", method, uri);

        Object response;
        try {
            response = joinPoint.proceed();
            logger.info("[Response] {} {} - finished with result {}", method, uri, response);
        } catch (Throwable e) {
            logger.error("[Error] {} {} - Exception!", method, uri, e);
            throw e;
        }
        return response;
    }

    @Around("servicePointcut()")
    public Object serviceExcution(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.getSignature().getName();
        logger.info("[Service] {} - starting", method);

        Object response;
        try {
            response = joinPoint.proceed();
        } catch(Throwable e) {
            logger.error("[Error] {} - Exception", method, e);
            throw e;
        }
        return response;
    }
}
