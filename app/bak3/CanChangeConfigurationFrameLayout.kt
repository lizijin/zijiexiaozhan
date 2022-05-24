package com.dmall.framework.views.cart

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.FrameLayout

abstract class CanChangeConfigurationFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs), CanChangeConfigurationView {
    private var mItemViewStyle: GoodsItemViewStyle? = null
    private var mNeedChangeUI = false

    init {
        initView()
        changeConfiguration(getTypedArrayFromXml(attrs))
        for (index in 0..childCount) {
            (getChildAt(index) as? CanChangeConfigurationView)?.let {
                it.changeConfiguration(it.getTypedArrayFromXml(attrs))
            }
        }
    }

    override fun changeConfiguration(typedArray: TypedArray) {
        getConfigurationFromAttributes(typedArray)
        mNeedChangeUI = true
        if (isAttachedToWindow) {
            if (getConfiguration() != null) {
                updateUiWhenConfigurationChangedThenReset()
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (getConfiguration() != null && mNeedChangeUI) {
            println("${this.javaClass.simpleName}  change UI")
            updateUiWhenConfigurationChangedThenReset()
        } else {
            println("${this.javaClass.simpleName}  not change UI")
        }
    }

    private fun updateUiWhenConfigurationChangedThenReset() {
        updateUiWhenConfigurationChanged()
        mNeedChangeUI = false

    }


    override fun changeGoodsItemViewStyle(style: GoodsItemViewStyle) {

        val typedArray = context.obtainStyledAttributes(
            null,
            getStyleArrays(),
            0,
            style.styleRes
        )
        changeConfiguration(typedArray)
    }

    final override fun setData(data: CanChangeConfigurationData) {
        data.goodsItemViewStyle?.let {
            if (mItemViewStyle != it) {
                println("${this.javaClass.simpleName}  change style")
                mItemViewStyle = it
                changeGoodsItemViewStyle(it)
            } else {
                println("${this.javaClass.simpleName} not change style")
            }
        }

        if (getConfiguration() != null) {
            setDataInternal(data)
        }
    }

    protected abstract fun setDataInternal(data: CanChangeConfigurationData)

    abstract fun initView()


}