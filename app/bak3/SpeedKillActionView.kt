package com.dmall.framework.views.cart

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.widget.TextView
import com.dmall.framework.R
import com.dmall.framework.module.bridge.trade.TradeBridgeManager
import com.dmall.framework.views.goods.SpeedKillType
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SpeedKillActionView(context: Context, attrs: AttributeSet? = null) :
    CanChangeConfigurationFrameLayout(context, attrs) {
    private var mConfiguration: SpeedKillActionViewConfiguration? = null
    private lateinit var mActionTextView: TextView
    private lateinit var mAnimateDotTextView: AnimateDotTextView
    private lateinit var mData: SpeedKillActionViewData

    private var mListener: SpeedKillActionViewClickListener? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        EventBus.getDefault().register(this)
        // 非立马抢 不需要显示加购数量
        if (this.mData?.speedKillType === SpeedKillType.SpeedKillPanic) {
            this.mData?.cartParams?.let {
                val count = TradeBridgeManager.instance.cartService.getWareCount(it.storeId, it.sku)
                mAnimateDotTextView.setCartCount(count, false)
            }
        } else {
            mAnimateDotTextView.setCartCount(0, false)
        }

    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        EventBus.getDefault().unregister(this)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onAddCartComplete(event: AddCartEvent) {
        mData?.cartParams?.let {
            if (it.storeId == event.storeId && it.sku == event.sku) {
                mAnimateDotTextView.setCartCount(event.wareCount, true)
            }
        }

    }

    override fun setDataInternal(data: CanChangeConfigurationData) {
        data as SpeedKillActionViewData
        mData = data
        when (data.speedKillType) {
            SpeedKillType.SpeedKillPanic -> {
                mActionTextView.text = "马上抢"
                mActionTextView.setTextColor(Color.WHITE)

                mActionTextView.setBackgroundResource(R.drawable.framework_speed_kill_1)
            }

            SpeedKillType.SpeedKillSetRemind -> {
                mActionTextView.text = "设置提醒"
                mActionTextView.setTextColor(Color.WHITE)
                mActionTextView.setBackgroundResource(R.drawable.framework_speed_kill_4)

            }

            SpeedKillType.SpeedKillRevokeRemind -> {
                mActionTextView.text = "取消提醒"
                mActionTextView.setTextColor(Color.parseColor("#0BCA60"))

                mActionTextView.setBackgroundResource(R.drawable.framework_speed_kill_3)

            }
            SpeedKillType.SpeedKillOver -> {
                mActionTextView.text = "补货中"
                mActionTextView.setTextColor(Color.WHITE)
                mActionTextView.setBackgroundResource(R.drawable.framework_speed_kill_2)
            }
        }

    }

    override fun initView() {
        inflate(context, R.layout.framework_speed_kill_action_view, this)
        mActionTextView = findViewById(R.id.framework_speed_kill_action_text_view)
        mAnimateDotTextView = findViewById(R.id.framework_add_cart_dot_view)


        mActionTextView.setOnClickListener {
            when (mData.speedKillType) {
                SpeedKillType.SpeedKillPanic -> {
                    mListener?.onPanicClick()
                }

                SpeedKillType.SpeedKillSetRemind -> {
                    mListener?.onRemindClick()
                }

                SpeedKillType.SpeedKillRevokeRemind -> {
                    mListener?.onRevokeRemindClick()
                }
                SpeedKillType.SpeedKillOver -> {
                    mListener?.onSpeedKillOverClick()
                }
            }
        }
    }

    override fun getConfigurationFromAttributes(typedArray: TypedArray) {
        //本视图无须配置
        mConfiguration = SpeedKillActionViewConfiguration(true)
    }

    override fun getTypedArrayFromXml(attrs: AttributeSet?): TypedArray {
        return context.obtainStyledAttributes(
            attrs,
            getStyleArrays(),
            0,
            0
        )
    }

    override fun updateUiWhenConfigurationChanged() {
//        不需要实现
    }

    override fun getStyleArrays(): IntArray {
        return R.styleable.SpeedKillActionView

    }

    override fun getConfiguration(): Configuration? = mConfiguration

    fun setSpeedKillActionViewClickListener(listener: SpeedKillActionViewClickListener) {
        this.mListener = listener
    }
}

interface SpeedKillActionViewData : CanChangeConfigurationData {
    val speedKillType: SpeedKillType
}

data class SpeedKillActionViewConfiguration(
    val meaningless: Boolean//无意义的
) : Configuration

interface SpeedKillActionViewClickListener {
    /**
     * 立即抢购
     */
    fun onPanicClick()

    fun onRemindClick()

    fun onRevokeRemindClick()

    fun onSpeedKillOverClick()

}


