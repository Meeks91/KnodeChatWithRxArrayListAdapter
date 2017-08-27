package com.example.micah.knodechat.rxRecyclerViewArrayListAdaper

import android.support.v7.widget.RecyclerView

/**
 *
 * A container for the data that's published in updates from the RxArrayListRecyclerAdapter.
 * Each update contains the viewHolder (of generic type VH) at the specified position inside the recyclerView.
 *
 */
class LayoutUpdateHolder<VH: RecyclerView.ViewHolder> (val default: VH, val position: Int)