package com.dmall.framework.views.cart

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import androidx.core.content.res.use
import androidx.core.view.setMargins
import com.dmall.framework.R
import com.dmall.framework.drawable.CodeColorStateList
import com.dmall.framework.drawable.CodeGradientDrawable
import com.dmall.framework.drawable.Corner

class SpeedKillBottomView(context: Context, attrs: AttributeSet? = null) :
    CanChangeConfigurationRelativeLayout(context, attrs) {
    private lateinit var mGoodsItemPriceView: GoodsItemPriceView
    private lateinit var mSpeedKillAddCartView: SpeedKillActionView
    private var mConfiguration: GoodItemBottomViewConfiguration? = null

    private var mListener: SpeedKillActionViewClickListener? = null


    override fun setDataInternal(data: CanChangeConfigurationData) {
        data as SpeedKillBottomViewData
        mGoodsItemPriceView.setData(data.priceViewData)
        mSpeedKillAddCartView.setData(data.speedKillAddCartViewData)
    }

    override fun initView() {
        inflate(context, R.layout.framework_speed_kill_bottom_view, this)
        mGoodsItemPriceView = findViewById(R.id.goods_item_price_view)
        mSpeedKillAddCartView = findViewById(R.id.speed_kill_add_cart_view)

        mSpeedKillAddCartView.setSpeedKillActionViewClickListener(object :
            SpeedKillActionViewClickListener {
            override fun onPanicClick() {
                mListener?.onPanicClick()
            }

            override fun onRemindClick() {
                mListener?.onRemindClick()
            }

            override fun onRevokeRemindClick() {
                mListener?.onRevokeRemindClick()
            }

            override fun onSpeedKillOverClick() {
                mListener?.onSpeedKillOverClick()
            }

        })
    }

    override fun getConfigurationFromAttributes(typedArray: TypedArray) {
        typedArray.use {
            val margin =
                it.getDimension(R.styleable.GoodsItemBottomView_bottomViewMargin, 0F).toInt()
            val marginLeft =
                it.getDimension(R.styleable.GoodsItemBottomView_bottomViewMarginLeft, 0F).toInt()
            val marginTop =
                it.getDimension(R.styleable.GoodsItemBottomView_bottomViewMarginTop, 0F).toInt()
            val marginRight =
                it.getDimension(R.styleable.GoodsItemBottomView_bottomViewMarginRight, 0F).toInt()
            val marginBottom =
                it.getDimension(R.styleable.GoodsItemBottomView_bottomViewMarginBottom, 0F).toInt()
            mConfiguration = GoodItemBottomViewConfiguration(
                margin,
                marginLeft,
                marginTop,
                marginRight,
                marginBottom
            )
        }
    }

    fun setSpeedKillActionViewClickListener(listener: SpeedKillActionViewClickListener) {
        this.mListener = listener
    }

    override fun getTypedArrayFromXml(attrs: AttributeSet?): TypedArray {
        return context.obtainStyledAttributes(
            attrs,
            R.styleable.GoodsItemBottomView,
            0,
            0
        )
    }

    override fun updateUiWhenConfigurationChanged() {

        //todo delete common_shape_speed_kill_one.xml
        this.background = CodeGradientDrawable.Builder(context).apply {
            this.solidColor(CodeColorStateList.valueOf(Color.parseColor("#fffff3ec")))
            this.corner(Corner.Builder(context).apply {
                this.radius(radius = 8F)
            })
        }.build().newDrawable()
        val layoutParams = layoutParams as? MarginLayoutParams
        if (layoutParams != null) {
            if (mConfiguration!!.margin != 0) {
                layoutParams.setMargins(mConfiguration!!.margin)
            } else {
                layoutParams.setMargins(
                    mConfiguration!!.marginLeft,
                    mConfiguration!!.marginTop,
                    mConfiguration!!.marginRight,
                    mConfiguration!!.marginBottom
                )
            }
        }
    }

    override fun getStyleArrays(): IntArray {
        return R.styleable.GoodsItemBottomView
    }

    override fun getConfiguration(): Configuration? = mConfiguration
}

interface SpeedKillBottomViewData : CanChangeConfigurationData {
    val priceViewData: GoodsItemPriceViewData
    val speedKillAddCartViewData: SpeedKillActionViewData
}
