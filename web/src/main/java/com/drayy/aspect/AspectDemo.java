package com.drayy.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectDemo {


    @Before("execution(* com.drayy.controller.IndexController.index())")
    public void beforeDemo(){
        System.out.println("切面运行");
    }
}
