package com.dmall.framework.views.cart

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.dmall.framework.R
import com.dmall.framework.views.goods.CartArriveRemindParams
import com.dmall.framework.views.goods.IExtCartParams

/**
 *到货提醒视图
 * */
class ArriveRemindView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    val tvSubscribeAct: TextView

    init {
        inflate(context, R.layout.framework_arrive_remind_view, this)
        tvSubscribeAct = findViewById(R.id.tv_subscribe_act)
    }

    fun setData(addCartView: GoodsItemAddCartView, data: CanChangeConfigurationData) {
        if (data.cartParams == null || data.cartParams!!.extParams !is CartArriveRemindParams) {
            visibility = GONE
            addCartView.visibility = VISIBLE
            return
        }
        val extParams: CartArriveRemindParams = data.cartParams!!.extParams as CartArriveRemindParams
        if (!extParams.isDisplayArrive) {
            visibility = GONE
            addCartView.visibility = VISIBLE
            return
        }
        //不可订阅的时候不展示提醒按钮
        if (!extParams.isSupportSubscribe) {
            visibility = GONE
            addCartView.visibility = VISIBLE
        }
        visibility = VISIBLE
        addCartView.visibility = GONE
        if (extParams.hasSubscribe) {
            tvSubscribeAct.text = "已设提醒"
            tvSubscribeAct.background = ResourcesCompat.getDrawable(context.resources, R.drawable.framework_shape_border_ccc_corner_11, null)
            tvSubscribeAct.setTextColor(ResourcesCompat.getColor(context.resources, R.color.common_color_text_t2, null))
        } else {
            tvSubscribeAct.text = "到货提醒"
            tvSubscribeAct.background = ResourcesCompat.getDrawable(context.resources, R.drawable.framework_shape_border_680a_corner_11, null)
            tvSubscribeAct.setTextColor(ResourcesCompat.getColor(context.resources, R.color.common_color_app_brand_d1, null))
        }
    }
}