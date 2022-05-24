package com.dmall.framework.views.cart

import com.dmall.framework.R

/**
 * 商品视图的类型
 * 设置整体控件的大小，在不同的场景大小不一样
 * 1. 1N、2N、2N导航 整体大小32X36dp，加购按钮24dpX24dp
 * 2. 3N、横滑3N、banner横滑、新人楼层整体大小32X36dp，加购按钮22dpX22dp
 * 3. 2N瀑布流中的榜单小卡片整体大小28X34dp，加购按钮18dpX18dp
 */
sealed class GoodsItemViewStyle constructor(val styleRes: Int)
object SpeedKillStyle : GoodsItemViewStyle(R.style.SpeedKill)
object OneColumnFixHeight : GoodsItemViewStyle(R.style.OneColumnFixHeight)
object TwoColumnStaggered : GoodsItemViewStyle(R.style.TwoColumnStaggered)
object TwoColumnFixHeight : GoodsItemViewStyle(R.style.TwoColumnFixHeight)
object ThreeColumnFixHeight : GoodsItemViewStyle(R.style.ThreeColumnFixHeight)
object LittleCard : GoodsItemViewStyle(R.style.LittleCard)
object FreshManStyle : GoodsItemViewStyle(R.style.FreshManStyle)