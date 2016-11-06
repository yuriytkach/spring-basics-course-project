package com.yet.spring.core.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
    
    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}
    
    @Pointcut("allLogEventMethods() && within(*.*File*Logger)")
    private void logEventInsideFileLoggers() {}
    
    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("BEFORE: " + 
                joinPoint.getTarget().getClass().getSimpleName() + " " +
                joinPoint.getSignature().getName());
    }
    
    @AfterReturning(pointcut="allLogEventMethods()",
            returning="retVal")
    public void logAfter(Object retVal) {
        System.out.println("AFTER_RET: " + retVal);
    }
    
    @AfterThrowing(pointcut="allLogEventMethods()",
            throwing="ex")
    public void logAfterThrow(Throwable ex) {
        System.out.println("AFTER_THR: " + ex);
    }

}
