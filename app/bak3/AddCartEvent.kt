package com.dmall.framework.views.cart

import com.dmall.framework.module.event.BaseEvent
import java.util.*

/**
 * 加购成功的事件
 */
data class AddCartEvent(
    val storeId: String,
    val sku: String,
    val wareCount: Int,
    val addCartUUID: String?
) : BaseEvent()