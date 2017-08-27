package com.example.micah.knodechat.rxRecyclerViewArrayListAdaper

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

/**
 * Created by Micah on 27/08/2017.
 */

/**
 * Creates the type of layoutManager that should be assigned to the
 * recyclerView bound to the RxRecyclerViewArrayList.
 * The [orientation], [isStaggered] and [spanCount] are all used to calculate
 * the type of LayoutManager the LayoutConfig can make
 */
class LayoutConfig(val orientation: Orientation, val isStaggered: Boolean, val spanCount: Int?) {

    /**
     * Generates and returns a layoutManager for the bounded recyclerView. The type of layoutManager is
     * deciphered through the [layoutConfig]
     */
     fun generateLayoutManagerUsing(context: Context): RecyclerView.LayoutManager {

        //use the orentation type to figure out the orientation type of the layoutManager
        if (orientation == Orientation.horiontal){

                //check if we should create linearLayoutManager or a StaggeredGridLayoutManager
                if (isStaggered == false)

                    return LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                else

                    return  StaggeredGridLayoutManager(spanCount!!, StaggeredGridLayoutManager.HORIZONTAL)
            }

            else {

                if (isStaggered == false)

                    return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                else

                    return StaggeredGridLayoutManager(spanCount!!, StaggeredGridLayoutManager.VERTICAL)
            }
        }
    }
