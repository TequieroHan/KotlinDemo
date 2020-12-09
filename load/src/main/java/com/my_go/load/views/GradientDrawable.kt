package com.my_go.load.views

import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import com.my_go.load.utils.Constant

/**
 * Create by Package com.my_go.load.views
 * Created by 毛勇 on 2020/11/19
 * Current System Time 14:54
 * Describe:
 */
class GradientDrawable(
    val startGradientColor: Int,
    val endGradientColor: Int,
    val cornerRadius: Int
) :
    Drawable() {
    private lateinit var paint: Paint
    private var with: Int = 0
    private var position: Int = 0

    override fun draw(canvas: Canvas) {
        val viewBounds = bounds
        canvas.save()
        translateCanves(canvas, position)
        val left = viewBounds.left.toFloat() - (position * with.toFloat())
        val right = viewBounds.right.toFloat() - (position * with.toFloat())
        val top = viewBounds.top.toFloat()
        val bottom = viewBounds.bottom.toFloat()
        val rect = RectF(
            left,
            top,
            right,
            bottom
        )
        canvas.drawRoundRect(
            rect,
            cornerRadius.toFloat(),
            cornerRadius.toFloat(),
            paint
        )
        canvas.restore()
    }

    fun translateCanves(canvas: Canvas?, position: Int) {
        canvas?.translate(position * with.toFloat(), 0f)
    }

    override fun setAlpha(alpha: Int) {
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    fun prepareGradientSize(with: Int) {
        this.with = with
        preparePaint(with)
    }

    private fun preparePaint(with: Int) {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val linearGradient = LinearGradient(
            0f,
            0f,
            with.toFloat() * Constant.VIEW_COUNT,
            0f,
            startGradientColor,
            endGradientColor,
            Shader.TileMode.CLAMP
        )
        paint.shader = linearGradient
    }

    fun translateCanvas(canvas: Canvas?, position: Int) {
        canvas?.translate(position * with.toFloat(), 0f)
    }

    fun setPositionOffset(position: Int) {
        this.position = position
    }

}