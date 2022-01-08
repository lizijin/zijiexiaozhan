package com.peter.viewgrouptutorial

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.os.Build

class CodeGradientDrawable private constructor(
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

    class Builder {
        private var solidColor: ColorStateList? = null
        private var shape: Int = RECTANGLE

        private var width: Int = -1
        private var height: Int = -1

        private var gradient: Gradient? = null
        private var corner: Corner? = null
        private var stroke: Stroke? = null
        private var padding: Padding? = null


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

        fun size(width: Int, height: Int) = apply {
            this.width = width
            this.height = height
        }

        fun build(): CodeGradientDrawable {
            return CodeGradientDrawable(
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
    val centerX: Float,
    val centerY: Float,
    val useLevel: Boolean,
    val gradientType: Int,
    val gradientRadius: Float,
    val orientation: GradientDrawable.Orientation,
    val gradientColors: IntArray
) {
    class Builder {
        private var centerX: Float = 0.5f
        private var centerY: Float = 0.5f
        private var useLevel: Boolean = false
        private var gradientType: Int = GradientDrawable.LINEAR_GRADIENT
        private var gradientRadius: Float = 0.5f
        private var orientation: GradientDrawable.Orientation =
            GradientDrawable.Orientation.LEFT_RIGHT
        private lateinit var gradientColors: IntArray

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

        fun gradientRadius(gradientRadius: Float): Builder = apply {
            this.gradientRadius = gradientRadius
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

class Corner(
    val radius: Float,
    val radii: FloatArray?
) {

    class Builder {
        private var radius: Float = 0.0f
        private var radii: FloatArray? = null

        fun radius(radius: Float) = apply {
            this.radius = radius
        }

        fun radii(
            topLeftRadius: Float = 0f,
            topRightRadius: Float = 0f,
            bottomRightRadius: Float = 0f,
            bottomLeftRadius: Float = 0f
        ) {
            radii = FloatArray(8)

            radii!![0] = topLeftRadius
            radii!![1] = topLeftRadius

            radii!![2] = topRightRadius
            radii!![3] = topRightRadius

            radii!![4] = bottomRightRadius
            radii!![5] = bottomRightRadius

            radii!![6] = bottomLeftRadius
            radii!![7] = bottomLeftRadius
        }

        fun build(): Corner {
            return Corner(radius = radius, radii)
        }
    }

}

class Stroke(
    val width: Int,
    val colorStateList: ColorStateList,
    val dashWidth: Float,
    val dashGap: Float
) {
    class Builder {
        private var width: Int = 0
        private lateinit var strokeColors: ColorStateList
        private var dashWidth: Float = 0.0f
        private var dashGap: Float = 0.0f

        fun setStroke(width: Int, colors: ColorStateList) = apply {
            this.width = width
            this.strokeColors = colors
        }

        fun setStroke(width: Int, colors: ColorStateList, dashWidth: Float, dashGap: Float) =
            apply {
                this.width = width
                this.strokeColors = colors
                this.dashWidth = dashWidth
                this.dashGap = dashGap
            }

        fun build(): Stroke {
            return Stroke(width, strokeColors, dashWidth, dashGap)
        }
    }
}

class Padding(val top: Int, val bottom: Int, val left: Int, val right: Int) {
    class Builder {
        private var top: Int = 0
        private var bottom: Int = 0
        private var left: Int = 0
        private var right: Int = 0

        fun setPadding(top: Int = 0, bottom: Int = 0, left: Int = 0, right: Int = 0) = apply {
            this.top = top
            this.bottom = bottom
            this.left = left
            this.right = right
        }

        fun build(): Padding {
            return Padding(top, bottom, left, right)
        }
    }
}