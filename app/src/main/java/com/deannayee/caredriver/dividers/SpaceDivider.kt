package com.deannayee.caredriver.dividers

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceDivider(private val space: Int): RecyclerView.ItemDecoration(){
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.adapter?.let{
            outRect.top = when (parent.getChildAdapterPosition(view)){
                RecyclerView.NO_POSITION,
                    0 -> space
                    else -> 0
            }
            outRect.bottom = space
        }
    }
}