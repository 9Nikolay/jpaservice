package com.luxoft.dbapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAdvice {
    @Pointcut("@annotation(com.luxoft.dbapp.aop.AdviceRequired)")
    public void findExpiredRents(){
    }

    @Before("findExpiredRents()")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Запущен тамер для поиска истекших аренд");
    }


}
