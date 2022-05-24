package com.dmall.framework.views.cart

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.AbsListView
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.dmall.framework.R
import com.dmall.framework.drawable.CodeColorStateList
import com.dmall.framework.drawable.CodeGradientDrawable
import com.dmall.framework.drawable.Corner
import com.dmall.framework.drawable.Stroke

class AnimateDotTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private var mAddCartCountTextView: TextView

    init {
        View.inflate(context, R.layout.framework_animate_dot_text_view, this)
        mAddCartCountTextView = findViewById(R.id.framework_add_cart_count_text_view)
        val gradientDrawable = CodeGradientDrawable.Builder(context).apply {
            this.debugName("加购数量背景")
            this.corner(Corner.Builder(context).apply {
                this.radius(radius = 14F)
            })
            this.stroke(Stroke.Builder(context).apply {
                this.setStroke(width = 1F, colorStateList = CodeColorStateList.valueOf(Color.WHITE))
            })
            this.solidColor(CodeColorStateList.valueOf(Color.parseColor("#F43000")))
        }.build().newDrawable()
        mAddCartCountTextView.background = gradientDrawable
    }

    private var hasClipChildren = false
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        if (!hasClipChildren) {
            /**
             * 因为动画会放大到115% 所以会出现切边的情况。为了防止出现切边的问题，
             * 递归设置父布局clipChildren = false
             */
            var parentView = parent
            while (parentView != null) {
                /**
                 * 不需要所有的父View都设置clipChildren = false
                 * 碰到RecyclerView或者AbsListView或者ViewPager 或者ViewPager2中断遍历
                 **/
                if (parentView is ViewGroup
                    && parentView !is RecyclerView
                    && parentView !is AbsListView
                    && parentView !is ViewPager
                    && parentView !is ViewPager2
                    && parentView !is IgnoreClipChildren
                ) {
                    parentView.clipChildren = false
                    parentView = parentView.parent
                } else {
                    parentView = null
                }
            }
            hasClipChildren = true
        }
    }

    /**
     * 设置商品的加购数量。
     * 1. 当前时disable状态直接返回
     * 2. count等于0时mAddCartCountTextView控件不可见
     * 3. 加购数量从0到1时需要做动画
     * 4. count>9时mAddCartCountTextView左右留出4dp的padding
     * 5. count>99时，数量显示99+
     * 6. mAddCartCountGhostTextView与mAddCartCountTextView设置文本逻辑同步
     */
    fun setCartCount(count: Int, needAnimator: Boolean) {
        if (count == 0) {
            mAddCartCountTextView.visibility = GONE
        } else {
            mAddCartCountTextView.visibility = VISIBLE
        }
        if (count == 1) {
            //只有加购触发的才需要做动画
            // 这里还需要判断是否已经做过动画了，如果不判断在RV复用的时候可能会再次触发动画，所以需要保存动画记录，从购物车删除商品时需要，清空该商品的动画记录
            if (needAnimator) {
                playAddToCartAnimator()
            }
        }
        if (count > 9) {
            val padding =
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    4F,
                    resources.displayMetrics
                ).toInt()
            mAddCartCountTextView.setPadding(padding, 0, padding, 0)
        } else {
            mAddCartCountTextView.setPadding(0, 0, 0, 0)

        }

        val countNumber = if (count > 99) {
            "99+"
        } else {
            "$count"
        }
        this.mAddCartCountTextView.text = countNumber
    }

    /**
     * 加购数量从0到1时需要做动画。
     * 动画规则：先加速后减速。先从0%缩放到115%，再从115%缩放到100%。
     */
    fun playAddToCartAnimator() {
        val scaleTo115xAnimator = ObjectAnimator.ofFloat(mAddCartCountTextView, "scaleX", 0F, 1.15F)
        val scaleTo115yAnimator = ObjectAnimator.ofFloat(mAddCartCountTextView, "scaleY", 0F, 1.15F)

        val scaleTo100xAnimator = ObjectAnimator.ofFloat(mAddCartCountTextView, "scaleX", 1.15F, 1F)
        val scaleTo100yAnimator = ObjectAnimator.ofFloat(mAddCartCountTextView, "scaleY", 1.15F, 1F)
        val scaleTo115AnimatorSet = AnimatorSet()
        scaleTo115AnimatorSet.playTogether(scaleTo115xAnimator, scaleTo115yAnimator)

        val scaleTo100AnimatorSet = AnimatorSet()
        scaleTo100AnimatorSet.playTogether(scaleTo100xAnimator, scaleTo100yAnimator)

        val addToCartAnimatorSet = AnimatorSet()
        addToCartAnimatorSet.duration = 240L
        addToCartAnimatorSet.interpolator = AccelerateDecelerateInterpolator()
        addToCartAnimatorSet.playSequentially(scaleTo115AnimatorSet, scaleTo100AnimatorSet)
        addToCartAnimatorSet.start()
    }
}

/**
 * 实现该接口的ViewGroup 将会忽略掉 parentView.clipChildren = false
 * 同时具备 扩大加购 和预售按钮点击区域的功能
 */
interface IgnoreClipChildren {
    fun getExpandRoot(): ViewGroup
}