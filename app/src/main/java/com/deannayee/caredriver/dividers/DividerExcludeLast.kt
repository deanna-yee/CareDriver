package com.deannayee.caredriver.dividers

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class DividerExcludeLast(private val dividerDrawable: Drawable):
    DividerTop(dividerDrawable){
    override fun getItemOffsets(rect: Rect, v: View, parent: RecyclerView, s: RecyclerView.State) {
        parent.adapter?.let { adapter ->
            val childAdapterPosition = parent.getChildAdapterPosition(v)
                .let { if (it == RecyclerView.NO_POSITION) return else it }
            rect.bottom = when (childAdapterPosition) {
                adapter.itemCount - 1 -> 0
                else -> dividerHeight
            }
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.adapter?.let { adapter ->
            parent.children
                .forEach { view ->
                    val top = view.bottom
                    drawRect(canvas, parent, view, adapter.itemCount, top)
                }
        }

    }
}