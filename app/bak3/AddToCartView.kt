package com.dmall.framework.views.cart

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.core.content.res.use
import com.dmall.framework.R
import com.dmall.framework.drawable.NegativeStateEnabled
import com.dmall.framework.drawable.State
import com.dmall.framework.drawable.StateEnabled
import com.dmall.framework.drawable.generateStateListDrawable
import com.dmall.framework.module.bridge.app.MainBridgeManager
import com.dmall.framework.module.bridge.trade.TradeBridgeManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 加购的通用组件，显示加购按钮和加购数量。
 * 处理在不同业务场景下，加购按钮大小变化，加购数字边距变化等逻辑。
 * 处理加购后动效问题
 */
class AddToCartView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CanChangeConfigurationFrameLayout(context, attrs), CanChangeConfigurationView {
    private lateinit var mAddCartImageView: ImageView
    private lateinit var mAnimateDotTextView: AnimateDotTextView

    private var mConfiguration: AddToCartViewConfiguration? = null
    private var mData: AddToCartViewData? = null
    private var onAddCartSuccessListener: OnAddCartSuccessListener? = null

    override fun initView() {
        View.inflate(context, R.layout.framework_add_to_cart_view, this)
        mAddCartImageView = findViewById(R.id.framework_add_cart_image_view)
        mAnimateDotTextView = findViewById(R.id.framework_add_cart_dot_view)


        /**
         * 因为动画会放大到115% 所以会出现切边的情况。为了防止出现切边的问题，
         * 递归设置父布局clipChildren = false
         *
         */
        clipChildren = false
    }


    override fun getConfigurationFromAttributes(typedArray: TypedArray) {
        typedArray.use {
            val addCartImageViewWidth =
                it.getDimension(R.styleable.AddToCartView_addCartImageViewWidth, -1F).toInt()
            val addCartImageViewHeight =
                it.getDimension(R.styleable.AddToCartView_addCartImageViewHeight, -1F).toInt()


            val wholeAddCartViewWidth =
                it.getDimension(R.styleable.AddToCartView_wholeAddCartViewWidth, -1F).toInt()
            val wholeAddCartViewHeight =
                it.getDimension(R.styleable.AddToCartView_wholeAddCartViewHeight, -1F).toInt()

            mConfiguration = AddToCartViewConfiguration(
                addCartImageViewWidth,
                addCartImageViewHeight,
                wholeAddCartViewHeight,
                wholeAddCartViewWidth
            )
        }
    }

    override fun getTypedArrayFromXml(attrs: AttributeSet?): TypedArray {
        return context.obtainStyledAttributes(
            attrs,
            R.styleable.AddToCartView,
            0,
            0
        )
    }

    override fun updateUiWhenConfigurationChanged() {
        // 设置selectorDrawable
        if (mConfiguration == null) return

        val drawables = intArrayOf(
            R.drawable.app_brand_btn_add_nor, R.drawable.app_brand_btn_add_dis
        )
        val states = arrayOf(arrayOf<State>(StateEnabled), arrayOf<State>(NegativeStateEnabled))
        val buttonDrawable: Drawable = generateStateListDrawable(context, drawables, states)
        mAddCartImageView.setImageDrawable(buttonDrawable)

        with(mConfiguration!!) {
            if (this.addCartImageViewWidth != -1) {
                setAddCartImageViewSize(
                    this.addCartImageViewWidth,
                    this.addCartImageViewHeight,
                    needConvert = false
                )
            }
            if (this.wholeAddCartViewWidth != -1) {
                setWholeAddCartViewSize(
                    this.wholeAddCartViewWidth,
                    this.wholeAddCartViewHeight,
                    needConvert = false
                )
            }
        }

        requestLayout()
    }

    override fun setDataInternal(data: CanChangeConfigurationData) {
        data as AddToCartViewData
        this.mData = data
        refreshCartCount()
    }

    override fun getStyleArrays(): IntArray {
        return R.styleable.AddToCartView
    }

    override fun getConfiguration(): Configuration? {
        return mConfiguration
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        EventBus.getDefault().register(this)

        refreshCartCount()

    }

    private fun refreshCartCount() {
        this.mData?.cartParams?.let {
            if (it.isGroupBuy) {
                val count = MainBridgeManager.instance.appCommonService.getCountByWareCode(it.sku);
                setCartCount(count, false)
            } else {
                val count = TradeBridgeManager.instance.cartService.getWareCount(it.storeId, it.sku)
                setCartCount(count, false)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        EventBus.getDefault().unregister(this)
    }

    fun setOnAddCartCompleteListener(onAddCartSuccessListener: OnAddCartSuccessListener) {
        this.onAddCartSuccessListener = onAddCartSuccessListener
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAddCartComplete(event: AddCartEvent) {
        mData?.cartParams?.let {
            if (it.storeId == event.storeId && it.sku == event.sku) {
                setCartCount(event.wareCount, true)
                onAddCartSuccessListener?.onAddCartSuccess(event.addCartUUID)
            }
        }

    }

    /**
     * 商品在无货、售光、补货等状态下，需要置灰且不可点击
     */
    fun setAddCartEnable(enable: Boolean) {
        this.isEnabled = enable
        this.mAddCartImageView.isEnabled = enable
        if (!enable) {
            mAnimateDotTextView.visibility = View.GONE
        }
    }

    /**
     * 设置商品的加购数量。
     * 1. 当前时disable状态直接返回
     * 2. count等于0时mAddCartCountTextView控件不可见
     * 3. 加购数量从0到1时需要做动画
     * 4. count>9时mAddCartCountTextView左右留出4dp的padding
     * 5. count>99时，数量显示99+
     */
    fun setCartCount(count: Int, needAnimation: Boolean = false) {
        if (!isEnabled) return
        mAnimateDotTextView.setCartCount(count, needAnimation)
    }

    /**
     * 加购数量从0到1时需要做动画。
     * 动画规则：先加速后减速。先从0%缩放到115%，再从115%缩放到100%。
     */
    fun playAddToCartAnimator() {
        mAnimateDotTextView.playAddToCartAnimator()
    }


    /**
     * 设置加购按钮的大小，在不同的场景大小不一样
     * 1. 1N、2N、2N导航 24dp
     * 2. 3N、横滑3N、banner横滑、新人楼层 22dp
     * 3. 2N瀑布流中的榜单小卡片18dp
     *
     */
    private fun setAddCartImageViewSize(width: Int, height: Int, needConvert: Boolean = true) {
        val layoutParams = mAddCartImageView.layoutParams
        if (needConvert) {
            layoutParams.width = width.toDp()
            layoutParams.height = height.toDp()
        } else {
            layoutParams.width = width
            layoutParams.height = height
        }
    }

    /**
     * 设置整体控件的大小，在不同的场景大小不一样
     * 1. 1N、2N、2N导航 32X36dp
     * 2. 3N、横滑3N、banner横滑、新人楼层 32X36dp
     * 3. 2N瀑布流中的榜单小卡片   28X34dp
     */
    private fun setWholeAddCartViewSize(width: Int, height: Int, needConvert: Boolean = true) {
        val layoutParams = this.layoutParams
        if (needConvert) {
            layoutParams.width = width.toDp()
            layoutParams.height = height.toDp()
        } else {
            layoutParams.width = width
            layoutParams.height = height
        }
    }

    private fun Int.toDp(): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            resources.displayMetrics
        ).toInt()
    }


}

data class AddToCartViewConfiguration(
    var addCartImageViewWidth: Int,
    var addCartImageViewHeight: Int,
    var wholeAddCartViewHeight: Int,
    var wholeAddCartViewWidth: Int,
) : Configuration

interface AddToCartViewData : CanChangeConfigurationData {
    val enable: Boolean
}

