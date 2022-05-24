package com.dmall.framework.views.cart


import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.core.content.res.use
import androidx.core.view.setMargins
import com.dmall.framework.R

/**
 * 商品视图，底部组件。包含价格组件和加购组件
 */
class GoodItemBottomView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null
) : CanChangeConfigurationRelativeLayout(context, attrs) {

    private lateinit var mGoodsItemPriceView: GoodsItemPriceView
    private lateinit var mGoodsItemAddCartView: GoodsItemAddCartView
    private var mArriveRemindView: ArriveRemindView? = null

    private var mConfiguration: GoodItemBottomViewConfiguration? = null

    private var mGoodsItemAddCartViewClickListener: GoodsItemAddCartViewClickListener? = null
    private var mGoodsItemBottomClickListener: GoodsItemBottomViewClickListener? = null
    private var mData: GoodItemBottomViewData? = null

    fun setGoodsItemAddCartViewClickListener(listener: GoodsItemAddCartViewClickListener) {
        this.mGoodsItemAddCartViewClickListener = listener
    }

    fun setGoodsItemBottomViewClickListener(listener: GoodsItemBottomViewClickListener) {
        this.mGoodsItemBottomClickListener = listener
    }

    override fun setDataInternal(data: CanChangeConfigurationData) {
        data as GoodItemBottomViewData
        mData = data
        mGoodsItemPriceView.setData(data.priceViewData)
        mGoodsItemAddCartView.setData(data.addCartViewData)
        mArriveRemindView?.setData(mGoodsItemAddCartView, data)
    }

    override fun initView() {
        inflate(context, R.layout.framework_goods_item_bottom_view, this)
        mGoodsItemPriceView = findViewById(R.id.goods_item_price_view)
        mGoodsItemAddCartView = findViewById(R.id.goods_item_add_cart_view)
        mArriveRemindView = findViewById(R.id.arriveRemindView)

        mGoodsItemAddCartView.setGoodItemAddCartViewOnClickListener(object :
                GoodsItemAddCartViewClickListener {
            override fun onAddToCartClick() {
                mGoodsItemAddCartViewClickListener?.let {
                    it.onAddToCartClick()
                }
            }

            override fun onPreSaleClick() {
                mGoodsItemAddCartViewClickListener?.let {
                    it.onPreSaleClick()
                }
            }

            override fun onMultiSpecClick() {
                mGoodsItemAddCartViewClickListener?.let {
                    it.onMultiSpecClick()
                }
            }

        })
        mArriveRemindView?.tvSubscribeAct?.setOnClickListener {
            mGoodsItemBottomClickListener?.onArriveRemindClick()
        }

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

    override fun getTypedArrayFromXml(attrs: AttributeSet?): TypedArray {
        return context.obtainStyledAttributes(
                attrs,
                R.styleable.GoodsItemBottomView,
                0,
                0
        )
    }

    override fun updateUiWhenConfigurationChanged() {
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

    override fun getConfiguration(): Configuration? {
        return mConfiguration
    }
}

data class GoodItemBottomViewConfiguration(
        val margin: Int,
        val marginLeft: Int,
        val marginTop: Int,
        val marginRight: Int,
        val marginBottom: Int
) : Configuration

interface GoodItemBottomViewData : CanChangeConfigurationData {
    val priceViewData: GoodsItemPriceViewData
    val addCartViewData: GoodsItemAddCartViewData
}

interface GoodsItemBottomViewClickListener {
    fun onArriveRemindClick()
}