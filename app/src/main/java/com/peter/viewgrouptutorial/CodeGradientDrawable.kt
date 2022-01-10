@file:JvmName("CodeGradientDrawableKt")

package com.peter.viewgrouptutorial

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue

class CodeGradientDrawable private constructor(
    private val theme: Resources.Theme,
    private val shapeType: Int,
    private val gradient: Gradient?,
    private val corner: Corner?,
    private val solidColor: ColorStateList?,
    private val stroke: Stroke?,
    private val padding: Padding?,
    private val width: Int,
    private val height: Int
) : GradientDrawable() {

    init {
        applyTheme(theme)

        shape = shapeType

        solidColor?.let {
            color = it
        }
        gradient?.let {
            with(it) {
                setGradientCenter(centerX, centerY)
                setUseLevel(useLevel)
                setGradientType(gradientType)
                setGradientRadius(gradientRadius)
                setOrientation(orientation)
                colors = gradientColors
            }
        }

        corner?.let {
            with(it) {
                if (radii == null) {
                    cornerRadius = radius
                } else {
                    for (index in radii.indices) {
                        if (radii[index] === 0.0f) {
                            radii[index] = radius
                        }
                    }
                    cornerRadii = radii
                }
            }
        }

        stroke?.let {
            with(it) {
                if (dashWidth !== 0.0f) {
                    setStroke(width, colorStateList, dashWidth, dashGap)
                } else {
                    setStroke(width, colorStateList)
                }
            }
        }

        padding?.let {
            with(it) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    setPadding(left, top, right, bottom)
                }
            }
        }

        if (width !== -1 && height !== -1) {
            setSize(width, height)
        }
    }

    class Builder constructor(context: Context) {
        private var solidColor: ColorStateList? = null
        private var shape: Int = RECTANGLE

        private var width: Int = -1
        private var height: Int = -1

        private var gradient: Gradient? = null
        private var corner: Corner? = null
        private var stroke: Stroke? = null
        private var padding: Padding? = null
        private var theme: Resources.Theme = context.theme
        private val metrics = context.resources.displayMetrics


        fun shape(shape: Int) = apply {
            this.shape = shape
        }

        fun solidColor(solidColor: ColorStateList) = apply {
            this.solidColor = solidColor
            this.gradient = null
        }

        fun gradient(gradient: Gradient) = apply {
            this.gradient = gradient
            this.corner = null
        }

        fun corner(corner: Corner) = apply {
            this.corner = corner
        }

        fun stroke(stroke: Stroke) = apply {
            this.stroke = stroke
        }

        fun padding(padding: Padding) = apply {
            this.padding = padding
        }

        @JvmOverloads
        fun size(width: Int, widthUnit: Int = DP_UNIT, height: Int, heightUnit: Int = DP_UNIT) =
            apply {
                this.width = getDimensionPixelSize(widthUnit, width.toFloat(), metrics)
                this.height = getDimensionPixelSize(heightUnit, height.toFloat(), metrics)
            }

        fun build(): CodeGradientDrawable {
            return CodeGradientDrawable(
                theme,
                shape,
                gradient,
                corner,
                solidColor,
                stroke,
                padding,
                width,
                height
            )
        }
    }
}


class Gradient private constructor(
    internal val centerX: Float,
    internal val centerY: Float,
    internal val useLevel: Boolean,
    internal val gradientType: Int,
    internal val gradientRadius: Float,
    internal val orientation: GradientDrawable.Orientation,
    internal val gradientColors: IntArray
) {
    class Builder constructor(context: Context) {
        private var centerX: Float = 0.5f
        private var centerY: Float = 0.5f
        private var useLevel: Boolean = false
        private var gradientType: Int = GradientDrawable.LINEAR_GRADIENT
        private var gradientRadius: Float = 0.5f
        private var orientation: GradientDrawable.Orientation =
            GradientDrawable.Orientation.LEFT_RIGHT
        private lateinit var gradientColors: IntArray
        private val metrics = context.resources.displayMetrics

        fun gradientCenter(x: Float, y: Float): Builder = apply {
            this.centerX = x
            this.centerY = y
        }

        fun useLevel(useLevel: Boolean): Builder = apply {
            this.useLevel = useLevel
        }

        fun gradientType(gradientType: Int): Builder = apply {
            this.gradientType = gradientType
        }

        fun orientation(orientation: GradientDrawable.Orientation): Builder = apply {
            this.orientation = orientation
        }

        fun gradientRadius(gradientRadius: Float, gradientRadiusUnit: Int = DP_UNIT): Builder =
            apply {
                this.gradientRadius = getDimension(gradientRadiusUnit, gradientRadius, metrics)
            }

        fun gradientColors(colors: IntArray): Builder = apply {
            this.gradientColors = colors
        }

        fun build(): Gradient {
            return Gradient(
                centerX,
                centerY,
                useLevel,
                gradientType,
                gradientRadius,
                orientation,
                gradientColors
            )
        }
    }

}

class Corner private constructor(
    internal val radius: Float,
    internal val radii: FloatArray? = null
) {

    class Builder constructor(context: Context) {
        private var radius: Float = 0.0f
        private var radii: FloatArray? = null
        private val metrics = context.resources.displayMetrics

        @JvmOverloads
        fun radius(radius: Float, radiusUnit: Int = DP_UNIT) = apply {
            this.radius = getDimensionPixelSize(radiusUnit, radius, metrics).toFloat()
        }

        @JvmOverloads
        fun radii(
            topLeftRadius: Float = 0f,
            topLeftRadiusUnit: Int = DP_UNIT,
            topRightRadius: Float = 0f,
            topRightRadiusUnit: Int = DP_UNIT,
            bottomRightRadius: Float = 0f,
            bottomRightRadiusUnit: Int = DP_UNIT,
            bottomLeftRadius: Float = 0f,
            bottomLeftRadiusUnit: Int = DP_UNIT,

            ) = apply {
            radii = FloatArray(8)

            val newTopLeftRadius =
                getDimensionPixelSize(topLeftRadiusUnit, topLeftRadius, metrics).toFloat()
            radii!![0] = newTopLeftRadius
            radii!![1] = newTopLeftRadius

            val newTopRightRadius =
                getDimensionPixelSize(topRightRadiusUnit, topRightRadius, metrics).toFloat()
            radii!![2] = newTopRightRadius
            radii!![3] = newTopRightRadius

            val newBottomRightRadius =
                getDimensionPixelSize(bottomRightRadiusUnit, bottomRightRadius, metrics).toFloat()

            radii!![4] = newBottomRightRadius
            radii!![5] = newBottomRightRadius

            val newBottomLeftRadius =
                getDimensionPixelSize(bottomLeftRadiusUnit, bottomLeftRadius, metrics).toFloat()

            radii!![6] = newBottomLeftRadius
            radii!![7] = newBottomLeftRadius
        }

        fun build(): Corner {
            return Corner(radius = radius, radii)
        }
    }

}

class Stroke private constructor(
    internal val width: Int,
    internal val colorStateList: ColorStateList,
    internal val dashWidth: Float,
    internal val dashGap: Float
) {
    class Builder constructor(context: Context) {
        private var width: Int = 0
        private lateinit var colorStateList: ColorStateList
        private var dashWidth: Float = 0.0f
        private var dashGap: Float = 0.0f
        private val metrics = context.resources.displayMetrics

        @JvmOverloads
        fun setStroke(width: Float, widthUnit: Int = DP_UNIT, colorStateList: ColorStateList) =
            apply {
                this.width = getDimensionPixelSize(widthUnit, width, metrics)
                this.colorStateList = colorStateList
            }

        @JvmOverloads
        fun setStroke(
            width: Float,
            widthUnit: Int = DP_UNIT,
            colorStateList: ColorStateList,
            dashWidth: Float,
            dashWidthUnit: Int = DP_UNIT,
            dashGap: Float,
            dashGapUnit: Int
        ) =
            apply {
                this.width = getDimensionPixelSize(widthUnit, width, metrics)
                this.colorStateList = colorStateList
                this.dashWidth = getDimension(dashWidthUnit, dashWidth, metrics)
                this.dashGap = getDimension(dashGapUnit, dashGap, metrics)
            }

        fun build(): Stroke {
            return Stroke(width, colorStateList, dashWidth, dashGap)
        }
    }
}

class Padding private constructor(
    internal val top: Int,
    internal val bottom: Int,
    internal val left: Int,
    internal val right: Int
) {
    class Builder constructor(context: Context) {
        private var top: Int = 0
        private var bottom: Int = 0
        private var left: Int = 0
        private var right: Int = 0
        private val metrics = context.resources.displayMetrics

        @JvmOverloads
        fun setPadding(
            top: Int = 0,
            topUnit: Int = DP_UNIT,
            bottom: Int = 0,
            bottomUnit: Int = DP_UNIT,
            left: Int = 0,
            leftUnit: Int = DP_UNIT,
            right: Int = 0,
            rightUnit: Int = DP_UNIT,
        ) = apply {
            this.top = getDimensionPixelSize(topUnit, top.toFloat(), metrics)
            this.bottom = getDimensionPixelSize(bottomUnit, bottom.toFloat(), metrics)
            this.left = getDimensionPixelSize(leftUnit, left.toFloat(), metrics)
            this.right = getDimensionPixelSize(rightUnit, right.toFloat(), metrics)
        }

        fun build(): Padding {
            return Padding(top, bottom, left, right)
        }
    }
}

const val PX_UNIT = 0
const val DP_UNIT = 1

// copy from  #TypedValue.complexToDimensionPixelSize
fun getDimensionPixelSize(unit: Int, value: Float, metrics: DisplayMetrics): Int {
    val f = TypedValue.applyDimension(
        unit,
        value,
        metrics
    )
    val res = (if (f >= 0) f + 0.5f else f - 0.5f).toInt()
    if (res != 0) return res
    if (value === 0F) return 0
    return if (value > 0) 1 else -1
}

fun getDimension(unit: Int, value: Float, metrics: DisplayMetrics): Float {
    return TypedValue.applyDimension(
        unit,
        value,
        metrics
    )
}