package com.peter.serviceloader

import java.util.*

object MyClient {
    fun getContent(): String? {
        return ServiceLoader.load(Provider::class.java).iterator().asSequence()
            .toList()[0]?.getService()
    }
}