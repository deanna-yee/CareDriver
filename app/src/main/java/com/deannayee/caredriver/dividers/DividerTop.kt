package com.deannayee.caredriver.dividers

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

open class DividerTop(private val dividerDrawable: Drawable):
    RecyclerView.ItemDecoration() {
    protected val dividerHeight = dividerDrawable.intrinsicHeight

    override fun getItemOffsets(rect: Rect, v: View, parent: RecyclerView, s: RecyclerView.State) {
        parent.adapter?.let {
            val childAdapterPosition = parent.getChildAdapterPosition(v)
                .let { if (it == RecyclerView.NO_POSITION) return else it }
            rect.top = when (childAdapterPosition) {
                0 -> dividerHeight
                else -> 0
            }
            rect.bottom = dividerHeight
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.adapter?.let { adapter ->
            parent.children
                .forEach { view ->
                    val top = view.top - dividerHeight
                    drawRect(canvas, parent, view, adapter.itemCount, top)
                }
        }

    }

    protected fun drawRect(canvas: Canvas, parent: RecyclerView, view: View,
                       count: Int, top: Int){
        val childAdapterPosition = parent.getChildAdapterPosition(view)
            .let { if (it == RecyclerView.NO_POSITION) return else it }
        if (childAdapterPosition != count - 1) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight
            val bottom = top + dividerHeight
            dividerDrawable.bounds = Rect(left, top, right, bottom)
            dividerDrawable.draw(canvas)
        }
    }
}