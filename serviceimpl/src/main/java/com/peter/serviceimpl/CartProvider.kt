package com.peter.serviceimpl

import com.google.auto.service.AutoService
import com.peter.serviceloader.Provider
@AutoService(Provider::class)
class CartProvider :Provider  {
    override fun getService(): String {
        return "Cart Service"
    }
}