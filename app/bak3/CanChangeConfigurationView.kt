package com.dmall.framework.views.cart

import android.content.res.TypedArray
import android.util.AttributeSet

/**
 * UI 的属性可以根据使用场景配置
 * 可以根据配置改变View的大小，背景的View
 */
interface CanChangeConfigurationView {
    /**
     * 根据Style中定义的配置，修改View的UI属性
     */
    fun getConfigurationFromAttributes(typedArray: TypedArray)

    /**
     * 从layout的xml中获取定义的Style
     */
    fun getTypedArrayFromXml(attrs: AttributeSet?): TypedArray

    /**
     * 当配置发生改变时，更新UI
     */
    fun updateUiWhenConfigurationChanged()

    /**
     * 更改配置
     */
    fun changeConfiguration(typedArray: TypedArray)

    /**
     * 设置数据
     */
    fun setData(data: CanChangeConfigurationData)


    fun changeGoodsItemViewStyle(style: GoodsItemViewStyle)

    fun getStyleArrays(): IntArray

    fun getConfiguration(): Configuration?
}

interface Configuration