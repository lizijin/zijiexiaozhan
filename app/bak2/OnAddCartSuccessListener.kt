package com.dmall.framework.views.cart

/**
 * Created by guanqinghua on 2022/5/6
 * Describe:单独接入加购按钮视图，需注册加购完成监听
 */
interface OnAddCartSuccessListener {
    fun onAddCartSuccess(addCartUUID: String?)
}