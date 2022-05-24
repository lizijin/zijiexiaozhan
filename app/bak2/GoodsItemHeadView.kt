package com.dmall.framework.views.cart

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.*
import androidx.core.content.res.use
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.dmall.framework.R
import com.dmall.framework.module.bridge.app.MainBridgeManager
import com.dmall.framework.preference.MemoryStorageHelper
import com.dmall.framework.utils.AddCartAnimation
import com.dmall.framework.utils.ToastUtil
import com.dmall.framework.views.goods.SquareTagsImageView
import com.dmall.garouter.animation.DropBoxAnimation
import com.dmall.image.main.GAImageView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 商品视图的图片区域，一般作为商品视图的头部区域
 */
class GoodsItemHeadView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : CanChangeConfigurationFrameLayout(context, attrs) {

    companion object {
        // 多规格的sku映射
        val skuRelationMap = hashMapOf<String, String>()
    }

    private lateinit var mSquareTagsImageView: SquareTagsImageView
    private lateinit var mVideoImageView: ImageView
    private lateinit var mCoverLayout: FrameLayout
    private lateinit var mCoverTextView: TextView
    private lateinit var mAdIconView: GAImageView
    private var mData: GoodsItemHeadViewData? = null

    private var mConfiguration: GoodItemHeadViewConfiguration? = null


    override fun setDataInternal(data: CanChangeConfigurationData) {
        data as GoodsItemHeadViewData
        this.mData = data
        // 初始化图片
        data.goodsImageUrl?.let {
            mSquareTagsImageView.setImageUrl(
                it,
                0,
                0,
                R.drawable.framework_icon_default
            )
        }

        data.goodsImageCorners?.let {
            mSquareTagsImageView.setImageTags(it)
        }

        //视频图标
        if (data.goodsIsVideo) {
            mVideoImageView.visibility = VISIBLE
        } else {
            mVideoImageView.visibility = GONE
        }

        //商品状态
        when (data.goodsStatusCode) {
            0 -> mCoverLayout.visibility = GONE //正常商品
            1 -> {
                mCoverLayout.visibility = VISIBLE
                mCoverTextView.text = "补货中"
                mVideoImageView.visibility = GONE

            }
            2 -> {
                mCoverLayout.visibility = VISIBLE
                mCoverTextView.text = "已下架"
                mVideoImageView.visibility = GONE
            }
        }

        // 广告标识
        if (TextUtils.isEmpty(data.goodsAdIconUrl)) {
            mAdIconView.visibility = GONE
        } else {
            mAdIconView.visibility = VISIBLE
            mAdIconView.setNormalImageUrl(data.goodsAdIconUrl)
        }
    }

    override fun initView() {
        inflate(context, R.layout.framework_goods_item_head_view, this)
        mSquareTagsImageView = findViewById(R.id.goods_ware_square_image_view)
        mVideoImageView = findViewById(R.id.goods_ware_video_image_view)
        mVideoImageView.alpha = 0.6F
        mCoverLayout = findViewById(R.id.goods_cover_layout)
        mCoverTextView = findViewById(R.id.goods_cover_text_view)
        mAdIconView = findViewById(R.id.goods_ware_ad_icon)
    }

    override fun getConfigurationFromAttributes(typedArray: TypedArray) {
        typedArray.use {
            val margin = it.getDimension(R.styleable.GoodsItemHeadView_headViewMargin, 0F).toInt()
            val marginLeft =
                it.getDimension(R.styleable.GoodsItemHeadView_headViewMarginLeft, 0F).toInt()
            val marginTop =
                it.getDimension(R.styleable.GoodsItemHeadView_headViewMarginTop, 0F).toInt()
            val marginRight =
                it.getDimension(R.styleable.GoodsItemHeadView_headViewMarginRight, 0F).toInt()
            val marginBottom =
                it.getDimension(R.styleable.GoodsItemHeadView_headViewMarginBottom, 0F).toInt()

            val imageViewWidth =
                it.getDimension(R.styleable.GoodsItemHeadView_headViewImageViewWidth, 0F).toInt()

            val imageViewHeight =
                it.getDimension(R.styleable.GoodsItemHeadView_headViewImageViewHeight, 0F).toInt()
            mConfiguration = GoodItemHeadViewConfiguration(
                imageViewWidth,
                imageViewHeight,
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
            R.styleable.GoodsItemHeadView,
            0,
            0
        )
    }

    override fun updateUiWhenConfigurationChanged() {
        if (mConfiguration!!.imageViewWidth != 0 && mConfiguration!!.imageViewHeight != 0) {
            mSquareTagsImageView.layoutParams.width = mConfiguration!!.imageViewWidth
            mSquareTagsImageView.layoutParams.height = mConfiguration!!.imageViewHeight
            mSquareTagsImageView.requestLayout()
        } else {
            mSquareTagsImageView.layoutParams.width = LayoutParams.MATCH_PARENT
            mSquareTagsImageView.layoutParams.height = LayoutParams.WRAP_CONTENT
        }
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
        return R.styleable.GoodsItemHeadView
    }

    override fun getConfiguration(): Configuration? {
        return mConfiguration
    }

    private fun animateWareImageView() {
        mData?.let {
            if (it.needAnimation) {
                if (MemoryStorageHelper.getInstance().targetAnimView != null) {
                    DropBoxAnimation.animate(
                        mSquareTagsImageView,
                        MemoryStorageHelper.getInstance().targetAnimView
                    )
                } else {
                    AddCartAnimation.animate(
                        mSquareTagsImageView,
                        MainBridgeManager.instance.mainService.shopCartIcon
                    )
                }
            } else {
                ToastUtil.showSuccessToast(
                    context,
                    "加入购物车成功",
                    Toast.LENGTH_SHORT
                )
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAddCartComplete(event: AddCartEvent) {
        mData?.cartParams?.let {
            if (!TextUtils.isEmpty(it.addCartUUID) && it.addCartUUID != event.addCartUUID)
                return@let
            val theSameSku = it.storeId == event.storeId && it.sku == event.sku
            val theSameSpu = (event.storeId + event.sku) == skuRelationMap[it.storeId + it.sku]

            if (theSameSku || theSameSpu) {

                animateWareImageView()


                if (theSameSpu) {
                    skuRelationMap.remove(it.storeId + it.sku)
                }
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        EventBus.getDefault().register(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        EventBus.getDefault().unregister(this)

    }
}

data class GoodItemHeadViewConfiguration(
    val imageViewWidth: Int,
    val imageViewHeight: Int,
    val margin: Int,
    val marginLeft: Int,
    val marginTop: Int,
    val marginRight: Int,
    val marginBottom: Int
) : Configuration

interface GoodsItemHeadViewData : CanChangeConfigurationData {
    val goodsIsVideo: Boolean
    val goodsImageUrl: String?
    val goodsImageCorners: List<String>?
    val goodsStatusCode: Int
    val goodsAdIconUrl: String?
    val needAnimation: Boolean // 是否需要做动画，购物车二级页面不需要做动画
}


