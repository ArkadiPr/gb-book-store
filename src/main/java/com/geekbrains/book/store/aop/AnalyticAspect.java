package com.geekbrains.book.store.aop;

import com.geekbrains.book.store.entities.MethodStat;
import com.geekbrains.book.store.services.analytic.MethodStatService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
//@AllArgsConstructor
public class AnalyticAspect {
    @Autowired
    private MethodStatService methodStatService;

//    @Before("execution(public * com.geekbrains.book.store.services.*(..))") // pointcut expression
    @Before("execution(public * com.geekbrains.book.store.services.BookService.*(..))") // pointcut expression
    public void beforeAnyMethodInBookService(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        MethodStat methodStat = methodStatService.getByMethodName(methodSignature.toString());
        methodStat.incrementCallAmount();
        methodStatService.saveOrUpdate(methodStat);

//        System.out.println("В BookService был вызван метод: " + methodSignature);
//        Object[] args = joinPoint.getArgs();
//        if (args.length > 0) {
//            System.out.println("Аргументы:");
//            for (Object o : args) {
//                System.out.println(o);
//            }
//        }
    }

    @Before("execution(public * com.geekbrains.book.store.services.UserService.*(..))") // pointcut expression
    public void beforeAnyMethodInUserService(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        MethodStat methodStat = methodStatService.getByMethodName(methodSignature.toString());
        methodStat.incrementCallAmount();
        methodStatService.saveOrUpdate(methodStat);
//        System.out.println("В UserService был вызван метод: " + methodSignature);
    }

    @Before("execution(public * com.geekbrains.book.store.services.OrderService.*(..))") // pointcut expression
    public void beforeAnyMethodInOrderService(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        MethodStat methodStat = methodStatService.getByMethodName(methodSignature.toString());
        methodStat.incrementCallAmount();
        methodStatService.saveOrUpdate(methodStat);
    }

    @Before("execution(public * com.geekbrains.book.store.services.OrderItemService.*(..))") // pointcut expression
    public void beforeAnyMethodInOrderItemService(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        MethodStat methodStat = methodStatService.getByMethodName(methodSignature.toString());
        methodStat.incrementCallAmount();
        methodStatService.saveOrUpdate(methodStat);
    }
}
