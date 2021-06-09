//package com.peter.aspect;
//
//import android.util.Log;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//
//@Aspect
//public class ClickAspect {
//    @After("within(@ccom.peter.aspect.TraceDelay *)")
//    public void onUi(JoinPoint joinPoint) throws Throwable {
//        Log.i("ClickAspect", "" + joinPoint.getSignature());
//    }
//}
