package com.dmall.framework.views.cart

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dmall.framework.R
import com.dmall.framework.drawable.*
import com.dmall.framework.module.bridge.app.MainBridgeManager
import com.dmall.framework.module.bridge.app.MainBridgeManager.Companion.instance
import com.dmall.framework.module.bridge.trade.TradeBridgeManager
import com.dmall.framework.module.bridge.waredetail.WareDetailBridgeManager
import com.dmall.framework.utils.AndroidUtil
import com.dmall.framework.utils.ExpandTouchAreaHelper
import com.dmall.framework.views.goods.CartParams
import com.dmall.gacommon.util.SizeUtils


/**
 * 商品视图加购区域视图，可能会有以下几种情况
 * 1. 单独的加购按钮
 * 2. 选规格按钮
 * 3. 预售按钮
 * 4. 3N多规格图标是"选"图标
 */
class GoodsItemAddCartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CanChangeConfigurationFrameLayout(context, attrs), View.OnClickListener {
    private lateinit var mAddToCartView: AddToCartView
    private lateinit var mMultiSpecTextView: TextView
    private lateinit var mPreSaleImageView: ImageView
    private lateinit var mMultiSpecImageView: ImageView
    private var mGoodItemAddCartViewOnClickListener: GoodsItemAddCartViewClickListener? = null
    private lateinit var mData: GoodsItemAddCartViewData

    fun setGoodItemAddCartViewOnClickListener(listener: GoodsItemAddCartViewClickListener) {
        mGoodItemAddCartViewOnClickListener = listener
    }

    private var mConfiguration: GoodsItemAddCartViewConfiguration? = null
    override fun initView() {
        inflate(context, R.layout.framework_goods_item_add_cart_view, this)
        mAddToCartView = findViewById(R.id.goods_ware_add_cart)
        mMultiSpecTextView = findViewById(R.id.goods_ware_multi_spec)
        mPreSaleImageView = findViewById(R.id.goods_ware_pre_sale)
        mMultiSpecImageView = findViewById(R.id.goods_ware_multi_spec_image)
        mAddToCartView.setOnClickListener(this)
        mMultiSpecTextView.setOnClickListener(this)
        mPreSaleImageView.setOnClickListener(this)
        mMultiSpecImageView.setOnClickListener(this)
    }

    private var expandTouchParent: ViewGroup? = null

    override fun getConfigurationFromAttributes(typedArray: TypedArray) {
        /**
         * 这个类比较特殊，自定义了获取方式
         * @see customGetConfigurationFromAttributes
         */
    }


    override fun setDataInternal(data: CanChangeConfigurationData) {
        data as GoodsItemAddCartViewData
        this.mData = data
        /**
         * Attention 目前多规格和预售是互斥的，等待预售可进购物车这里可能会修改
         */
        when {
            // 3N由于空间问题，选规格变成 加购按钮
            data.isMultiSpec && mConfiguration!!.multiSpecOrigin -> {
                mMultiSpecTextView.text = "选规格"
                mMultiSpecTextView.visibility = VISIBLE
                mPreSaleImageView.visibility = GONE
                mAddToCartView.visibility = GONE
                mMultiSpecImageView.visibility = GONE // 小规格图标隐藏掉
                expandTouch(mMultiSpecTextView)
            }
            data.isPreSale -> {
                mMultiSpecImageView.visibility = GONE
                mMultiSpecTextView.visibility = GONE
                mPreSaleImageView.visibility = VISIBLE
                mAddToCartView.visibility = GONE
                expandTouch(mPreSaleImageView)

            }
            data.isMultiSpec -> {
                mMultiSpecImageView.visibility = VISIBLE
                mMultiSpecTextView.visibility = GONE
                mPreSaleImageView.visibility = GONE
                mAddToCartView.visibility = GONE
                expandTouch(mMultiSpecImageView)
            }
            else -> {
                mMultiSpecImageView.visibility = GONE
                mMultiSpecTextView.visibility = GONE
                mPreSaleImageView.visibility = GONE
                mAddToCartView.visibility = VISIBLE
                expandTouch(mAddToCartView)

            }
        }

        if (data.statusCode == 0) {
            mAddToCartView.alpha = 1.0f
            mMultiSpecTextView.alpha = 1.0f
            mPreSaleImageView.alpha = 1.0f
        } else {
            mAddToCartView.alpha = 0.3f
            mMultiSpecTextView.alpha = 0.3f
            mPreSaleImageView.alpha = 0.3f
        }

        mAddToCartView.setData(object : AddToCartViewData {
            override val cartParams: CartParams?
                get() = data.cartParams
            override val enable: Boolean
                get() = true
            override val goodsItemViewStyle: GoodsItemViewStyle?
                get() = data.goodsItemViewStyle

        })


    }

    override fun getStyleArrays(): IntArray {
        return R.styleable.GoodsItemAddCartView
    }

    override fun getConfiguration(): Configuration? {
        return mConfiguration
    }

    override fun changeGoodsItemViewStyle(style: GoodsItemViewStyle) {

        val thisViewTypedArray = context.obtainStyledAttributes(
            null,
            R.styleable.GoodsItemAddCartView,
            0,
            style.styleRes
        )

        val addCartViewTypedArray = context.obtainStyledAttributes(
            null,
            R.styleable.AddToCartView,

            0,
            style.styleRes
        )
        changeConfiguration(thisViewTypedArray, addCartViewTypedArray)
    }

    private fun changeConfiguration(
        thisViewTypedArray: TypedArray,
        addCartViewTypedArray: TypedArray
    ) {
        customGetConfigurationFromAttributes(thisViewTypedArray, addCartViewTypedArray)
        if (isAttachedToWindow) {
            updateUiWhenConfigurationChanged()
        }
    }

    private fun customGetConfigurationFromAttributes(
        thisViewTypedArray: TypedArray,
        addCartViewTypedArray: TypedArray
    ) {

        var multiSpecPaddingLeft =
            thisViewTypedArray.getDimension(
                R.styleable.GoodsItemAddCartView_multiSpecTextPaddingLeft,
                0F
            )
                .toInt()
        var multiSpecPaddingTop =
            thisViewTypedArray.getDimension(
                R.styleable.GoodsItemAddCartView_multiSpecTextPaddingTop,
                0F
            )
                .toInt()
        var multiSpecPaddingRight =
            thisViewTypedArray.getDimension(
                R.styleable.GoodsItemAddCartView_multiSpecTextPaddingRight,
                0F
            )
                .toInt()
        var multiSpecPaddingBottom =
            thisViewTypedArray.getDimension(
                R.styleable.GoodsItemAddCartView_multiSpecTextPaddingBottom,
                0F
            )
                .toInt()

        var multiSpecTextSize =
            thisViewTypedArray.getDimension(R.styleable.GoodsItemAddCartView_multiSpecTextSize, 0F)
        var multiSpecBorderRadius =
            thisViewTypedArray.getDimension(
                R.styleable.GoodsItemAddCartView_multiSpecBorderRadius,
                0F
            )
        var multiSpecOrigin =
            thisViewTypedArray.getBoolean(R.styleable.GoodsItemAddCartView_multiSpecOrigin, true)
        var preSaleWidth =
            thisViewTypedArray.getDimension(R.styleable.GoodsItemAddCartView_preSaleWidth, 0F)
        var preSaleHeight =
            thisViewTypedArray.getDimension(R.styleable.GoodsItemAddCartView_preSaleWidth, 0F)
        thisViewTypedArray.recycle()

        val wholeAddCartViewWidth =
            addCartViewTypedArray.getDimension(R.styleable.AddToCartView_wholeAddCartViewWidth, -1F)
                .toInt()
        val wholeAddCartViewHeight =
            addCartViewTypedArray.getDimension(
                R.styleable.AddToCartView_wholeAddCartViewHeight,
                -1F
            ).toInt()
        val addCartImageViewWidth =
            addCartViewTypedArray.getDimension(R.styleable.AddToCartView_addCartImageViewWidth, -1F)
                .toInt()
        val addCartImageViewHeight =
            addCartViewTypedArray.getDimension(
                R.styleable.AddToCartView_addCartImageViewHeight,
                -1F
            ).toInt()
        var marginLeft = (wholeAddCartViewWidth - addCartImageViewWidth) / 2
        var marginTop = (wholeAddCartViewHeight - addCartImageViewHeight) / 2
        addCartViewTypedArray.recycle()

        mConfiguration = GoodsItemAddCartViewConfiguration(
            multiSpecPaddingLeft,
            multiSpecPaddingRight,
            multiSpecPaddingTop,
            multiSpecPaddingBottom,
            multiSpecTextSize,
            multiSpecBorderRadius,
            multiSpecOrigin,
            preSaleWidth,
            preSaleHeight,
            marginTop,
            marginLeft
        )
    }

    override fun getTypedArrayFromXml(attrs: AttributeSet?): TypedArray {
        return context.obtainStyledAttributes(
            attrs,
            R.styleable.GoodsItemAddCartView,
            0,
            0
        )
    }

    private fun expandTouch(view: View) {

        this.post {
            if (expandTouchParent == null) {
                var parentView = parent
                while (parentView != null) {
                    if (parentView is IgnoreClipChildren) {
                        expandTouchParent = parentView.getExpandRoot()
                        parentView = null
                    } else {
                        parentView = parentView.parent
                    }
                }
            }
            if (expandTouchParent != null) {
                val expand = SizeUtils.dp2px(context, 20)
                val touchDelegateHelper =
                    ExpandTouchAreaHelper(expandTouchParent, view, expand, expand, expand, expand)
                touchDelegateHelper.expandPureTouchDelegate()
            }
        }
    }

    override fun updateUiWhenConfigurationChanged() {
        mMultiSpecTextView.background = CodeGradientDrawable.Builder(context).apply {
            this.debugName("加购多规格")
            this.solidColor(CodeColorStateList.valueOf(resources.getColor(R.color.common_color_app_brand_d1)))

            this.gradient(Gradient.Builder(context).apply {
                this.orientation(GradientDrawable.Orientation.LEFT_RIGHT)
                this.gradientColors(IntArray(2).apply {
                    this[0] = resources.getColor(R.color.common_color_app_brand_d4s)
                    this[1] = resources.getColor(R.color.common_color_app_brand_d4e)
                })
            })
            this.corner(
                Corner.Builder(context).radius(mConfiguration!!.multiSpecBorderRadius, PX_UNIT)
            )
        }.build().newDrawable()

        mMultiSpecTextView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            mConfiguration!!.multiSpecTextSize
        )

        mMultiSpecTextView.setPadding(
            mConfiguration!!.multiSpecPaddingLeft,
            mConfiguration!!.multiSpecPaddingTop,
            mConfiguration!!.multiSpecPaddingRight,
            mConfiguration!!.multiSpecPaddingBottom
        )

        (mPreSaleImageView.layoutParams as? MarginLayoutParams)?.let {
            it.setMargins(
                mConfiguration!!.buttonMarginLeft,
                mConfiguration!!.buttonMarginTop,
                mConfiguration!!.buttonMarginLeft,
                mConfiguration!!.buttonMarginTop
            )
        }
        (mMultiSpecImageView.layoutParams as? MarginLayoutParams)?.let {
            it.setMargins(
                mConfiguration!!.buttonMarginLeft,
                mConfiguration!!.buttonMarginTop,
                mConfiguration!!.buttonMarginLeft,
                mConfiguration!!.buttonMarginTop
            )
        }

        mPreSaleImageView.layoutParams?.let {
            it.width = mConfiguration!!.preSaleWidth.toInt()
            it.height = mConfiguration!!.preSaleHeight.toInt()
        }

        mMultiSpecImageView.layoutParams?.let {
            it.width = mConfiguration!!.preSaleWidth.toInt()
            it.height = mConfiguration!!.preSaleHeight.toInt()
        }

        (mMultiSpecTextView.layoutParams as? MarginLayoutParams)?.let {
            it.setMargins(
                mConfiguration!!.buttonMarginLeft,
                mConfiguration!!.buttonMarginTop,
                mConfiguration!!.buttonMarginLeft,
                mConfiguration!!.buttonMarginTop
            )
        }


    }

    override fun onClick(v: View) {
        if (mData.needCheckLogin && !MainBridgeManager.instance.mainService.checkLoginState()) return
        if (mData.statusCode != 0) return
        mGoodItemAddCartViewOnClickListener?.let {
            when {
                this.mData.isMultiSpec -> {
                    if (AndroidUtil.isFastClick(1000L)) return@let
                    val cartParams = mData.cartParams
                    if (cartParams != null) {
                        WareDetailBridgeManager.instance.wareDetailService.searchShowSpecificationChooseDialog(
                            cartParams.venderId,
                            cartParams.storeId,
                            cartParams.sku
                        ) { result ->
                            val skuCount = if (result?.second == null) 0 else result.second!!
                            //多规格建立映射关系
                            GoodsItemHeadView.skuRelationMap[cartParams.storeId + cartParams.sku] =
                                cartParams.storeId + result?.first
                            TradeBridgeManager.instance.cartService.sendAddToCartSimpleReqUUID(
                                cartParams.storeId,
                                result?.first,
                                "",
                                skuCount,
                                cartParams.pageType,
                                cartParams.pageName,
                                cartParams.actionType,
                                null, null, cartParams.addCartUUID
                            )
                            it.onMultiSpecClick()
                        }
                    }

                }
                this.mData.isPreSale -> {

                    it.onPreSaleClick()
                }
                else -> {
                    val cartParams = mData.cartParams
                    if (cartParams !== null) {
                        if (!cartParams.isGroupBuy) {
                            var upperBuryParams = cartParams.upperBuryParams
                            val extraBuryParams = cartParams.extraBuryParams
                            TradeBridgeManager.instance.cartService.sendAddToCartSimpleReqUUID(
                                cartParams.storeId,
                                cartParams.sku,
                                cartParams.suitId,
                                1,
                                cartParams.pageType,
                                cartParams.pageName,
                                cartParams.actionType,
                                upperBuryParams,
                                extraBuryParams,
                                cartParams.addCartUUID
                            )
                        } else {
                            val commonService = instance.appCommonService
                            commonService.cartSearchAdd(
                                cartParams.venderId,
                                cartParams.storeId,
                                cartParams.groupBuyType,
                                "1",
                                cartParams.sku
                            )
                        }
                    }
                    it.onAddToCartClick()
                }
            }
        }
    }
}

/**
 * 当前加购组件的UI配置。
 * 1N、2N、3N 价格标签、划线价、会员价、单位视图等控件的字体大小各不相同，通过该类控制
 */
data class GoodsItemAddCartViewConfiguration(
    var multiSpecPaddingLeft: Int,
    var multiSpecPaddingRight: Int,
    var multiSpecPaddingTop: Int,
    var multiSpecPaddingBottom: Int,
    var multiSpecTextSize: Float,
    var multiSpecBorderRadius: Float,
    var multiSpecOrigin: Boolean,
    var preSaleWidth: Float,
    var preSaleHeight: Float,

    var buttonMarginTop: Int,// 由于加购按钮有一层外边距，所以预售和多规格也需要
    var buttonMarginLeft: Int
) : Configuration

interface GoodsItemAddCartViewData : CanChangeConfigurationData {
    val isMultiSpec: Boolean
    val isPreSale: Boolean

    /**
     * 商品当前的状态
     */
    val statusCode: Int
    val needCheckLogin: Boolean
}

interface GoodsItemAddCartViewClickListener {
    fun onAddToCartClick()
    fun onPreSaleClick()
    fun onMultiSpecClick()
}

