package com.dmall.framework.views.cart

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.use
import com.dmall.framework.R
import com.dmall.framework.utils.PriceUtil
import com.dmall.framework.views.SpecialPriceView
import com.dmall.gacommon.util.StringUtils

/**
 * 商品视图的价格组件
 */
class GoodsItemPriceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CanChangeConfigurationFrameLayout(context, attrs) {

    private lateinit var mGoodsPriceLayout: LinearLayout
    private lateinit var mChineTextView: TextView //价格后面的标
    private lateinit var mDisplayPriceTextView: TextView //商品当前显示价格TextView
    private lateinit var mUnderLinePriceTextView: TextView //商品划线价格
    private lateinit var mTagPriceTextView: SpecialPriceView // 商品命中的身份价格

    private lateinit var mNoPriceTextView: TextView //暂无报价


    private var mConfiguration: GoodItemPriceViewConfiguration? = null


    override fun setDataInternal(data: CanChangeConfigurationData) {
        data as GoodsItemPriceViewData
        // 如果有"暂无报价"优先展示"暂无报价"
        if (!TextUtils.isEmpty(data.priceDisplay)) {
            mGoodsPriceLayout.visibility = GONE
            mNoPriceTextView.visibility = VISIBLE
            mNoPriceTextView.text = data.priceDisplay
            mNoPriceTextView.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                mConfiguration!!.noPriceTextSize
            )
        } else {
            mGoodsPriceLayout.visibility = VISIBLE
            mNoPriceTextView.visibility = GONE

            PriceUtil.formatPrice(
                mDisplayPriceTextView,
                data.commonPrice,
                mConfiguration!!.prePriceSize,
                mConfiguration!!.midPriceSize
            )

            // 设置价格后面的单位
            if (!TextUtils.isEmpty(data.chine) && !TextUtils.isEmpty(data.commonPrice)
                && StringUtils.isNumeric(data.commonPrice)
            ) {
                mChineTextView.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    mConfiguration!!.chineTextSize
                )
                mChineTextView.visibility = VISIBLE
                mChineTextView.text = "/" + data.chine
            } else {
                mChineTextView.visibility = GONE
            }

            // 优先展示会员价格
            if (!TextUtils.isEmpty(data.tagPrice)) {
                mTagPriceTextView.visibility = VISIBLE
                mUnderLinePriceTextView.visibility = GONE
                mTagPriceTextView.setPriceShow(
                    mConfiguration!!.specialViewSize,
                    data.tagPriceType,
                    data.tagPrice
                )
            } else if (!TextUtils.isEmpty(data.lineationPrice) && data.lineationPrice != data.commonPrice) {
                mTagPriceTextView.visibility = GONE
                mUnderLinePriceTextView.visibility = VISIBLE
                mUnderLinePriceTextView.setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    mConfiguration!!.lineationPriceTextSize
                )

                PriceUtil.formatOriginalPrice(mUnderLinePriceTextView, data.lineationPrice)
            } else {
                mTagPriceTextView.visibility = GONE
                mUnderLinePriceTextView.visibility = GONE
            }
        }
    }

    override fun initView() {
        inflate(context, R.layout.framework_goods_item_price_view, this)

        mGoodsPriceLayout = findViewById(R.id.goods_ware_price_layout)
        mChineTextView = findViewById(R.id.goods_ware_chine)
        mDisplayPriceTextView = findViewById(R.id.goods_ware_display_price)
        mTagPriceTextView = findViewById(R.id.goods_ware_special_price)
        mUnderLinePriceTextView = findViewById(R.id.goods_ware_underline_price)

        mNoPriceTextView = findViewById(R.id.goods_ware_no_price_text_view)
    }

    override fun getConfigurationFromAttributes(typedArray: TypedArray) {
        typedArray.use {
            val priceViewWidth =
                it.getDimension(R.styleable.GoodsItemPriceView_priceViewWidth, 0F).toInt()
            val priceViewHeight =
                it.getDimension(R.styleable.GoodsItemPriceView_priceViewHeight, 0F).toInt()
            val prePriceSize = it.getInt(R.styleable.GoodsItemPriceView_prePriceSize, 0)
            val midPriceSize = it.getInt(R.styleable.GoodsItemPriceView_midPriceSize, 0)
            val chineTextSize = it.getDimension(R.styleable.GoodsItemPriceView_chineTextSize, 0F)
            val specialViewSize = it.getFloat(R.styleable.GoodsItemPriceView_specialViewSize, 1F)
            val lineationPriceTextSize =
                it.getDimension(R.styleable.GoodsItemPriceView_lineationPriceTextSize, 0F)
            val noPriceTextSize =
                it.getDimension(R.styleable.GoodsItemPriceView_noPriceTextSize, 0F)
            mConfiguration = GoodItemPriceViewConfiguration(
                priceViewWidth,
                priceViewHeight,
                prePriceSize,
                midPriceSize,
                chineTextSize,
                specialViewSize,
                lineationPriceTextSize,
                noPriceTextSize
            )
        }
    }

    override fun getTypedArrayFromXml(attrs: AttributeSet?): TypedArray {
        return context.obtainStyledAttributes(
            attrs,
            R.styleable.GoodsItemPriceView,
            0,
            0
        )
    }

    override fun updateUiWhenConfigurationChanged() {
        if (mConfiguration!!.priceViewWidth != 0) {
            layoutParams.width = mConfiguration!!.priceViewWidth
        }

        if (mConfiguration!!.priceViewHeight != 0) {
            layoutParams.height = mConfiguration!!.priceViewHeight
        }

    }

    override fun getStyleArrays(): IntArray {
        return R.styleable.GoodsItemPriceView

    }

    override fun getConfiguration(): Configuration? {
        return mConfiguration
    }

}

/**
 * 当前价格组件的UI配置。
 * 1N、2N、3N 价格标签、划线价、会员价、单位视图等控件的字体大小各不相同，通过该类控制
 */
data class GoodItemPriceViewConfiguration(
    var priceViewWidth: Int,
    var priceViewHeight: Int,
    var prePriceSize: Int, // 价格前半部分字体大小
    var midPriceSize: Int, // 价格后半部分字体大小
    var chineTextSize: Float, // 价格后半部分字体大小
    var specialViewSize: Float,// 标签价的整体大小，1或0.8
    var lineationPriceTextSize: Float,//划线价字体大小
    var noPriceTextSize: Float //暂无报价字体大小
) : Configuration

interface GoodsItemPriceViewData : CanChangeConfigurationData {
    val priceDisplay: String?//文字价格比如暂无报价
    val commonPrice: String?//售价
    val chine: String?
    val tagPriceType: Int//身份类型
    val tagPrice: String?//身份价格
    val lineationPrice: String?//划线价
}