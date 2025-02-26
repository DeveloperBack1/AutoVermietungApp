package com.schneider.spring.springboot.autovermietungapp.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Arrays;
import java.util.Objects;

/**
 * Aspect for logging controller and service methods.
 * <p>
 * This class logs requests, services executed, return values, and any exceptions thrown.
 * It is applied to all public methods within the controllers and services of the AutoVermietungApp.
 */
@Aspect
@Component
@Slf4j
@Tag(name = "Logging", description = "Logs information for controllers and services")
public class LoggingAspect {

    /**
     * Pointcut for controller methods.
     */
    @Pointcut("execution(public * com.schneider.spring.springboot.autovermietungapp.controller.*.*(..))")
    public void controllerLog() {}

    /**
     * Pointcut for service methods.
     */
    @Pointcut("execution(public * com.schneider.spring.springboot.autovermietungapp.*.*(..))")
    public void serviceLog() {}

    /**
     * Logs details before executing a controller method.
     *
     * @param jp the join point representing the controller method
     */
    @Before("controllerLog()")
    @Operation(summary = "Log controller method request", description = "Logs the details of a request coming to a controller method")
    public void doBeforeController(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        log.info("""
                        NEW REQUEST:
                        IP : {}
                        URL : {}
                        HTTP_METHOD : {}
                        CONTROLLER_METHOD : {}.{}""",
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName());
    }

    /**
     * Logs details before executing a service method.
     *
     * @param jp the join point representing the service method
     */
    @Before("serviceLog()")
    @Operation(summary = "Log service method execution", description = "Logs the details of a service method execution")
    public void doBeforeService(JoinPoint jp) {
        log.info("RUN SERVICE:\n" +
                        "SERVICE_METHOD : {}.{}",
                jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    /**
     * Logs return values after the controller method execution.
     *
     * @param returnObject the return value of the controller method
     */
    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    @Operation(summary = "Log controller method response", description = "Logs the return value of a controller method after execution")
    public void doAfterReturning(@Parameter(description = "Return value of the controller method") Object returnObject) {
        log.info("""
                        Return value: {}
                        END OF REQUEST""",
                returnObject);
    }

    /**
     * Logs exception details if a controller method throws an exception.
     *
     * @param jp the join point representing the controller method
     * @param ex the exception thrown by the controller method
     */
    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    @Operation(summary = "Log controller method exception", description = "Logs exception details if a controller method throws an exception")
    public void throwsException(JoinPoint jp, Exception ex) {
        log.error("Request throw an exception. Cause - {}. {}",
                Arrays.toString(jp.getArgs()), ex.getMessage());
    }
}