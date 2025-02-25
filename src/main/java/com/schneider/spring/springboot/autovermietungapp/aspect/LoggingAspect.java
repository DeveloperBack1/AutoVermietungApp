package com.schneider.spring.springboot.autovermietungapp.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Objects;

/**
 * LoggingAspect handles logging across controller and service layers.
 * <p>
 * This aspect logs incoming HTTP requests, executed service methods,
 * successful return values, and exceptions thrown from controllers.
 * </p>
 */
@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Pointcut for logging all public methods in controller package.
     */
    @Pointcut("execution(public * com.schneider.spring.springboot.autovermietungapp.controller.*.*(..))")
    public void controllerLog() {
    }

    /**
     * Pointcut for logging all public methods in service layer.
     */
    @Pointcut("execution(public * com.schneider.spring.springboot.autovermietungapp.*.*(..))")
    public void serviceLog() {
    }

    /**
     * Logs information before a controller method is executed.
     *
     * @param jp the join point representing the method being executed
     */
    @Before("controllerLog()")
    public void doBeforeController(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        log.info("NEW REQUEST:\n" +
                        "IP : {}\n" +
                        "URL : {}\n" +
                        "HTTP_METHOD : {}\n" +
                        "CONTROLLER_METHOD : {}.{}",
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
    }

    /**
     * Logs information before a service method is executed.
     *
     * @param jp the join point representing the method being executed
     */
    @Before("serviceLog()")
    public void doBeforeService(JoinPoint jp) {
        log.info("RUN SERVICE:\n" +
                        "SERVICE_METHOD : {}.{}",
                jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    /**
     * Logs the return value after a controller method has successfully executed.
     *
     * @param returnObject the object returned by the method
     */
    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnObject) {
        log.info("\nReturn value: {}\n" +
                        "END OF REQUEST",
                returnObject);
    }

    /**
     * Logs exceptions thrown by controller methods.
     *
     * @param jp the join point where the exception was thrown
     * @param ex the exception thrown
     */
    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void throwsException(JoinPoint jp, Exception ex) {
        log.error("Request threw an exception. Cause - {}. {}",
                Arrays.toString(jp.getArgs()), ex.getMessage());
    }
}
