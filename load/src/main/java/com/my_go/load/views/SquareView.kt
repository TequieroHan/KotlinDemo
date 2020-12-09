package com.my_go.load.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * Create by Package com.my_go.load.views
 * Created by 毛勇 on 2020/11/19
 * Current System Time 15:21
 * Describe:
 */
class SquareView : View {

    private lateinit var drawable: GradientDrawable
    private var viewSize: Int = 0

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context?, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        drawable.setBounds(0, 0, right - left, bottom - top)
    }

    fun prepareGradientDrawable(
        startGradientColor: Int,
        endGradientColor: Int,
        cornerRadius: Int,
        viewSize: Int
    ) {
        this.viewSize = viewSize
        drawable = GradientDrawable(startGradientColor, endGradientColor, cornerRadius)
        drawable.prepareGradientSize(viewSize)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(viewSize, viewSize)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawable.draw(canvas)
    }

    fun updateGradientPosition(position: Int) {
        drawable.setPositionOffset(position)
        invalidate()
    }

}