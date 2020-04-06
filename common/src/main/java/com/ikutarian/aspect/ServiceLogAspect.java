package com.ikutarian.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 监控Service层代码的执行时长
 */
@Aspect
@Component
@Slf4j
public class ServiceLogAspect {

    /**
     * 切面表达式
     *
     * execution 代表所要执行的表达式主体
     * 第一处 * 代表方法返回值类型 *代表所有类型
     * 第二处 包名代表aop监控的类所在的包
     * 第三处 .. 代表该包以及子包下的所有类
     * 第四处 * 代表类名，*表示所有类
     * 第五处 *(..) *代表类中的方法名，(..)表示方法中的任何参数
     */
    @Around("execution(* com.ikutarian.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("====== 开始执行 {}.{} =======", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - start;

        if (duration > 3 * 1000) {
            log.error("执行结束，耗时: {}ms", duration);
        } else if (duration > 2 * 1000) {
            log.warn("执行结束，耗时: {}ms", duration);
        } else {
            log.info("执行结束，耗时: {}ms", duration);
        }

        return result;
    }
}
