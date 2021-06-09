package com.peter.aspect;

import android.net.Uri;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {
    @After("execution(* com.peter.viewgrouptutorial.activity.DashboardActivity.**(..))")
    public void onResumeMethod(JoinPoint joinPoint) throws Throwable {
        Log.i("helloAOP", "after" + joinPoint.getSignature());
    }

    @Before("execution(* com.peter.viewgrouptutorial.activity.DashboardActivity.**(..))")
    public void onAfter(JoinPoint joinPoint) throws Throwable {
        Log.i("helloAOP", "before:::" + joinPoint.getSignature());
    }

    @Around("execution(* androidx.core.content.FileProvider.getUriForFile(..))")
    public Uri getUriForFile(ProceedingJoinPoint joinPoint) throws Throwable {
        Uri uri = (Uri) joinPoint.proceed();
        Log.i("helloAOP", "uri is " + uri);
        String oldAuthority = uri.getAuthority();
        String encodePath = uri.getEncodedPath();
        Log.i("helloAOP", "oldAuthority " + oldAuthority + "encodePath " + encodePath);

        uri = new Uri.Builder().scheme("content")
                .authority("com.peter.dispatch").encodedPath(oldAuthority + "" + encodePath).build();
        Log.i("helloAOP", "newuri is " + uri);

        return uri;
    }
}
