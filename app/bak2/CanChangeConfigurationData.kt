package com.dmall.framework.views.cart

import com.dmall.framework.views.goods.CartParams

interface CanChangeConfigurationData {
    val goodsItemViewStyle: GoodsItemViewStyle?
    val cartParams: CartParams?
}