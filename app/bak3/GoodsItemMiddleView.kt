package com.dmall.framework.views.cart

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.use
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import com.dmall.framework.R
import com.dmall.framework.views.PromiseTextView
import com.dmall.framework.views.goods.Promotion
import com.dmall.framework.views.goods.PromotionRecyclerView

/**
 * 商品视图中显示商品名称、广告标签、榜单标签、榜单入口、推荐语、促销等元素的组件
 * 1N定高显示 促销和标签同时存在
 * 2N瀑布流 促销和标签同时存在
 * 2N、3N定高只会展示一个，促销的优先级更高
 */
class GoodsItemMiddleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CanChangeConfigurationLinearLayout(context, attrs) {

    private lateinit var mWareNameTextView: PromiseTextView
    private lateinit var mOccupyWareNameTextView: PromiseTextView// 为了高度固定，占位2行

    private lateinit var mOnlyOneLineTextView: TextView


    // 可点击榜单布局
    private lateinit var mRankLayout: LinearLayout

    //可点击榜单TextView
    private lateinit var mRankTextView: TextView

    //广告语、推荐语、榜单
    private lateinit var mTagTextView: TextView

    private lateinit var mPromotionRecyclerView: PromotionRecyclerView
    private lateinit var mPromotionLayout: FrameLayout

    private var mConfiguration: GoodsItemMiddleViewConfiguration? = null

    private lateinit var mData: GoodsItemMiddleViewData
    private var mHandler = Handler(Looper.getMainLooper())

    private var mListener: GoodsItemMiddleViewClickListener? = null


    override fun initView() {
        inflate(context, R.layout.framework_goods_item_tag_promotion_view, this)
        orientation = VERTICAL
        mRankLayout = findViewById(R.id.goods_ware_rank_layout)
        mRankTextView = findViewById(R.id.goods_ware_rank_text_view)

        mTagTextView = findViewById(R.id.goods_ware_tag_text_view)
        mPromotionRecyclerView = findViewById(R.id.goods_ware_promotion_rv)
        mPromotionLayout = findViewById(R.id.goods_ware_promotion_layout)

        mOccupyWareNameTextView = findViewById(R.id.occupy_goods_ware_name)
        mWareNameTextView = findViewById(R.id.goods_ware_name)
        mWareNameTextView.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        mOnlyOneLineTextView = findViewById(R.id.ware_ad_only_one_line)

        mPromotionRecyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return true
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                if (e.action == MotionEvent.ACTION_UP) {
                    mListener?.onPromotionRecyclerViewClick()
                }
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }

        })

        mRankLayout.setOnClickListener {
            mListener?.onRankLayoutClick()
        }
    }

    override fun getConfigurationFromAttributes(typedArray: TypedArray) {
        typedArray.use {

            val margin =
                it.getDimension(R.styleable.GoodsItemMiddleView_middleViewMargin, 0F).toInt()
            val marginLeft =
                it.getDimension(R.styleable.GoodsItemMiddleView_middleViewMarginLeft, 0F)
                    .toInt()
            val marginTop =
                it.getDimension(R.styleable.GoodsItemMiddleView_middleViewMarginTop, 0F)
                    .toInt()
            val marginRight =
                it.getDimension(R.styleable.GoodsItemMiddleView_middleViewMarginRight, 0F)
                    .toInt()
            val marginBottom =
                it.getDimension(R.styleable.GoodsItemMiddleView_middleViewMarginBottom, 0F)
                    .toInt()


            val tagTextSize =
                it.getDimension(R.styleable.GoodsItemMiddleView_tagTextSize, 0F)
            val rankTextColor = it.getColor(
                R.styleable.GoodsItemMiddleView_rankTextColor,
                Color.parseColor("#D9AE56")
            )

            val recommendTextColor = it.getColor(
                R.styleable.GoodsItemMiddleView_recommendTextColor,
                resources.getColor(R.color.common_color_app_brand_d2)
            )

            val adTextColor = it.getColor(
                R.styleable.GoodsItemMiddleView_adTextColor,
                resources.getColor(R.color.common_color_text_t3)
            )

            val promotionHeight =
                it.getDimension(
                    R.styleable.GoodsItemMiddleView_promotionHeight,
                    0F
                )
                    .toInt()
            val promotionMarginTop =
                it.getDimension(
                    R.styleable.GoodsItemMiddleView_promotionMarginTop,
                    0F
                )
                    .toInt()
            val tagTextViewHeight: Int =
                it.getDimension(
                    R.styleable.GoodsItemMiddleView_tagTextViewHeight,
                    0F
                )
                    .toInt()
            val tagTextViewMarginTop: Int =
                it.getDimension(
                    R.styleable.GoodsItemMiddleView_tagTextViewMarginTop,
                    0F
                ).toInt()

            val isFixHeight = it.getBoolean(
                R.styleable.GoodsItemMiddleView_isFixHeight,
                false
            )

            val warNameTextSize =
                it.getDimension(
                    R.styleable.GoodsItemMiddleView_warNameTextSize,
                    0F
                )

            val hiddenTagView = it.getBoolean(R.styleable.GoodsItemMiddleView_hiddenTagView, false)

            val nameTextViewHeight =
                it.getLayoutDimension(R.styleable.GoodsItemMiddleView_nameTextViewHeight, ViewGroup.LayoutParams.WRAP_CONTENT)

            mConfiguration = GoodsItemMiddleViewConfiguration(
                margin,
                marginLeft,
                marginTop,
                marginRight,
                marginBottom,
                warNameTextSize,
                tagTextSize,
                rankTextColor,
                recommendTextColor,
                adTextColor,
                promotionHeight,
                promotionMarginTop,
                tagTextViewHeight,
                tagTextViewMarginTop,
                isFixHeight,
                hiddenTagView,
                nameTextViewHeight
            )
        }
    }

    override fun getTypedArrayFromXml(attrs: AttributeSet?): TypedArray {
        return context.obtainStyledAttributes(
            attrs,
            R.styleable.GoodsItemMiddleView,
            0,
            0
        )
    }

    override fun updateUiWhenConfigurationChanged() {

        if (mConfiguration!!.wareNameTextSize == 0F) return
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

        mWareNameTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mConfiguration!!.wareNameTextSize)
        mOccupyWareNameTextView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            mConfiguration!!.wareNameTextSize
        )
        mWareNameTextView.layoutParams.height = mConfiguration!!.nameTextViewHeight
        mOccupyWareNameTextView.layoutParams.height = mConfiguration!!.nameTextViewHeight

        mOnlyOneLineTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mConfiguration!!.tagTextSize)

        mTagTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mConfiguration!!.tagTextSize)
        mPromotionLayout.layoutParams.height = mConfiguration!!.promotionHeight
        (mPromotionLayout.layoutParams as MarginLayoutParams).topMargin =
            mConfiguration!!.promotionMarginTop

        mTagTextView.layoutParams.height = mConfiguration!!.tagTextViewHeight
        (mTagTextView.layoutParams as MarginLayoutParams).topMargin =
            mConfiguration!!.tagTextViewMarginTop
    }

    override fun getStyleArrays(): IntArray {
        return R.styleable.GoodsItemMiddleView
    }

    override fun getConfiguration(): Configuration? {
        return mConfiguration
    }

    override fun setDataInternal(data: CanChangeConfigurationData) {
        data as GoodsItemMiddleViewData
        this.mData = data
        mHandler.removeCallbacks(adjustRunnable)

        if (data.isPreSale) {
            //预售处理
            if (!TextUtils.isEmpty(data.preSaleWareSuffix)) {
                mWareNameTextView.setText(
                    data.preSaleWareSuffix,
                    data.wareName,
                    Color.parseColor("#FBA900")
                )
            } else {
                mWareNameTextView.setText(data.shipmentTime, data.wareName)
            }
        } else {
            mWareNameTextView.setText(data.shipmentTime, data.wareName)
        }
        if (TextUtils.isEmpty(data.tag)) {
            mRankLayout.visibility = GONE
            mTagTextView.visibility = GONE
            mTagTextView.text = ""
        } else {
            if (TextUtils.isEmpty(data.rankLinkUrl)) {
                mRankLayout.visibility = GONE
                mTagTextView.visibility = VISIBLE
                mTagTextView.text = data.tag
            } else {
                mRankLayout.visibility = VISIBLE
                mTagTextView.visibility = GONE
                mRankTextView.text = data.tag
            }
        }
        if (data.promotions == null || data.promotions!!.isEmpty()) {
            mPromotionLayout.visibility = GONE
        } else {
            mPromotionLayout.visibility = VISIBLE
        }
        mPromotionRecyclerView.setData(data.promotions)


        when (data.tagType) {
            "1" -> {
                mTagTextView.setTextColor(mConfiguration!!.rankTextColor)
            }
            "2" -> {
                mTagTextView.setTextColor(mConfiguration!!.recommendTextColor)
            }
            "3" -> {
                mTagTextView.setTextColor(mConfiguration!!.adTextColor)
            }
        }

        //判断是瀑布流还是 非瀑布流。瀑布流会同时显示优惠券和tag，而固定高度的商品视图只会显示一个

        if (mConfiguration!!.isFixHeight) {
            setFixHeightData(data)
        } else {
            setStaggerData(data)
        }


    }

    /**
     * 设置瀑布流
     */
    private fun setStaggerData(data: GoodsItemMiddleViewData) {
        mOccupyWareNameTextView.visibility = GONE
        mOnlyOneLineTextView.visibility = GONE
    }

    /**
     * 设置固定高度下的数据
     */
    private fun setFixHeightData(data: GoodsItemMiddleViewData) {
        mOccupyWareNameTextView.visibility = VISIBLE

        if (mConfiguration!!.hiddenTagView) {
            mTagTextView.visibility = GONE
            mPromotionLayout.visibility = GONE
        } else {
            //定高的组件 标签和优惠券 优先显示优惠券
            if (mPromotionLayout.visibility == VISIBLE && mTagTextView.visibility == VISIBLE) {
                mTagTextView.visibility = GONE
                mTagTextView.text = ""
            } else if (mPromotionLayout.visibility == GONE && mTagTextView.visibility == GONE) {
                //2N 3N 定高组件，如果两个都不可见，为了固定高度，设置一个可见
                mTagTextView.visibility = VISIBLE
                mTagTextView.text = ""
            }
            mHandler.post(adjustRunnable)
        }
        //如果只有广告
    }

    private val adjustRunnable = Runnable {
        if (!TextUtils.isEmpty(mTagTextView.text) && "3" == mData.tagType) {
            if (mWareNameTextView.lineCount == 1) {
                mOnlyOneLineTextView.text = mData.tag
                mTagTextView.text = ""
            }
        } else {
            mOnlyOneLineTextView.text = ""
        }
    }

    fun setGoodsItemMiddleViewClickListener(
        listener: GoodsItemMiddleViewClickListener
    ) {
        this.mListener = listener
    }
}


data class GoodsItemMiddleViewConfiguration(
    val margin: Int,
    val marginLeft: Int,
    val marginTop: Int,
    val marginRight: Int,
    val marginBottom: Int,

    val wareNameTextSize: Float,
    val tagTextSize: Float,
    val rankTextColor: Int,
    val recommendTextColor: Int,
    val adTextColor: Int,
    val promotionHeight: Int,
    val promotionMarginTop: Int,
    val tagTextViewHeight: Int,
    val tagTextViewMarginTop: Int,
    val isFixHeight: Boolean,
    val hiddenTagView: Boolean,
    val nameTextViewHeight: Int
) : Configuration

interface GoodsItemMiddleViewData : CanChangeConfigurationData {
    val wareName: String?
    val isPreSale: Boolean
    val preSaleWareSuffix: String?
    val shipmentTime: String?
    val tag: String?
    val rankLinkUrl: String?
    val tagType: String?
    val promotions: List<Promotion>?
}

interface GoodsItemMiddleViewClickListener {
    /**
     * 优惠券RecyclerView点击事件，它会阻挡掉事件，导致点它跳转不了商品详情页面，
     * 需要通过该回调跳转商详
     */
    fun onPromotionRecyclerViewClick()

    /**
     * 点击榜单后跳转到榜单详情页面
     */
    fun onRankLayoutClick()
}

