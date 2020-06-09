package com.letter.notifylight.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.letter.notifylight.model.NotifyShape

class NotifyView @JvmOverloads
constructor(context: Context, attrs: AttributeSet?=null, defStyleAttr: Int=0, defStyleRes: Int=0)
    : View(context, attrs, defStyleAttr, defStyleRes) {

    var notifyShape: NotifyShape? = null
    set(value) {
        field = value
        invalidate()
    }

    private val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        notifyShape?.components?.forEach {
            when (it.type) {
                "point" -> drawPoint(canvas, it)
                "line" -> drawLine(canvas, it)
                "circle" -> drawCircle(canvas, it)
                "arc" -> drawArc(canvas, it)
                "rect" -> drawRect(canvas, it)
                "text" -> drawText(canvas, it)
            }
        }
    }

    private fun drawPoint(canvas: Canvas?, component: NotifyShape.NotifyComponent) {
        paint.apply {
            color = component.color
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        canvas?.drawPoint(
            component.x.toFloat(),
            component.y.toFloat(),
            paint)
    }

    private fun drawLine(canvas: Canvas?, component: NotifyShape.NotifyComponent) {
        paint.apply {
            color = component.color
            style = Paint.Style.FILL
            isAntiAlias = true
            strokeWidth = component.strokeWidth.toFloat()
        }
        canvas?.drawLine(
            component.startX.toFloat(),
            component.startY.toFloat(),
            component.endX.toFloat(),
            component.endY.toFloat(),
            paint)
    }

    private fun drawCircle(canvas: Canvas?, component: NotifyShape.NotifyComponent) {
        paint.apply {
            color = component.color
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        canvas?.drawCircle(
            component.x.toFloat(),
            component.y.toFloat(),
            component.radius.toFloat(),
            paint)
    }

    private fun drawArc(canvas: Canvas?, component: NotifyShape.NotifyComponent) {
        paint.apply {
            color = component.color
            style = Paint.Style.STROKE
            isAntiAlias = true
            strokeWidth = component.strokeWidth.toFloat()
        }
        canvas?.drawArc(
            (component.x - component.radius).toFloat(),
            (component.y - component.radius).toFloat(),
            (component.x + component.radius).toFloat(),
            (component.y + component.radius).toFloat(),
            (component.startAngle - 90).toFloat(),
            (component.endAngle - component.startAngle).toFloat(),
            false,
            paint
        )
    }

    private fun drawRect(canvas: Canvas?, component: NotifyShape.NotifyComponent) {
        paint.apply {
            color = component.color
            style = Paint.Style.STROKE
            isAntiAlias = true
            strokeWidth = component.strokeWidth.toFloat()
        }
        canvas?.drawRoundRect(
            component.startX.toFloat(),
            component.startY.toFloat(),
            component.endX.toFloat(),
            component.endY.toFloat(),
            component.radius.toFloat(),
            component.radius.toFloat(),
            paint
        )
    }

    private fun drawText(canvas: Canvas?, component: NotifyShape.NotifyComponent) {

    }
}