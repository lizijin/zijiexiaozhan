package com.peter.viewgrouptutorial.coroutines

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class MyElement :AbstractCoroutineContextElement(MyElement) {
    public companion object Key : CoroutineContext.Key<MyElement>
}