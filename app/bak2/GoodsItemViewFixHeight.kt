//package com.dmall.framework.views.cart
//
//import android.content.Context
//import android.util.AttributeSet
//import android.widget.LinearLayout
//import com.dmall.framework.R
//
///**
// * 固定高度的商品视图。
// * 目前适用于2N固定高度、3N固定高度
// */
//class GoodsItemViewFixHeight @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null
//) : LinearLayout(context, attrs) {
//    init {
//        inflate(context, R.layout.framework_goods_item_view_fix_height, this)
//        orientation = VERTICAL
//
//    }
//}
//
//sealed class GoodsItemViewFixHeightStyle
//object TwoColumnFixHeightStyle : GoodsItemViewFixHeightStyle()
//object ThreeColumnFixHeightStyle : GoodsItemViewFixHeightStyle()